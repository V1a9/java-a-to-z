package com.vgoryashko.sql.xmloptimizer;

import com.google.common.base.Joiner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class that implements console application that connects to the SQLite database,
 * deletes/inserts N entries in it, writes field values into 1.xml file, transforms this file with 1.xsl
 * and writes a result into 2.xml.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 11/05/17
 */
public class XmlXsdOptimizer {

    /**
     * Final field that stores an instance of the Logger.
     */
    private final Logger logger = LogManager.getLogger(XmlXsdOptimizer.class);

    /**
     * Constant that stores string value for a new line symbols.
     */
    private static final String NL = System.getProperty("line.separator");

    /**
     * Field that refers to an url of a database.
     */
    private String url;

    /**
     * Field that stores qty N of elements to be added into the database.
     */
    private int n;

    /**
     * Field that defines path of the 1.xml file.
     */
    private File file;

    /**
     * Constructor for the class that takes no parameters.
     */
    public XmlXsdOptimizer() { }

    /**
     * Method that inserts or deletes entries in a database.
     *
     * @param connection to the database
     * @param rowsNumber number of rows to be deleted or added.
     * @param delete flag that defines an operation to be performed (true - DELETE/ false - !DELETE)
     */
    public void updateTable(Connection connection, int rowsNumber, boolean delete) {

        String ins = "INSERT INTO main.TEST VALUES (?)";
        String del = "DELETE FROM main.TEST WHERE field NOTNULL ";

        PreparedStatement preparedStatement;

        try {
            if (delete) {
                preparedStatement = connection.prepareStatement(del);
                preparedStatement.executeUpdate();
                logger.debug(String.format("%d fields were deleted", rowsNumber));
            }
            preparedStatement = connection.prepareStatement("BEGIN TRANSACTION ; ");
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement(ins);
            for (int i = 1; i <= this.n; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            preparedStatement = connection.prepareStatement("COMMIT ; ");
            preparedStatement.execute();
            logger.debug(String.format("%d fields were inserted", this.n));

        } catch (SQLException e) {
            logger.debug(e.getMessage(), e);
        }
   }

    /**
     * Method that retrieves data from the table and writes it into 1.xml file.
     */
    public void readDataBase(Connection connection) {

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        if (this.file.exists()) {
            logger.debug(String.format("File %s exists.", this.file.getAbsolutePath()));
            this.file.delete();
            logger.debug(String.format("File %s has been deleted.", this.file.getAbsolutePath()));
        }

        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM main.TEST");
            resultSet = preparedStatement.executeQuery();

            try (Writer writer = new FileWriter(this.file)) {
                logger.debug(String.format("File %s has been created.", this.file.getAbsolutePath()));

                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                writer.write("<entries>");
                while (resultSet.next()) {
                    int fieldValue = resultSet.getInt("field");
                    writer.write(Joiner.on(NL).join(
                            "",
                            "\t<entry>",
                            String.format("\t\t<field>%d</field>", fieldValue),
                                    "\t</entry>"
                            )

                    );
                }
                writer.write(NL);
                writer.write("</entries>");
            }
            logger.debug(String.format("File %s has been written.", this.file.getAbsolutePath()));

        } catch (SQLException | IOException ex) {
            logger.debug(ex.getMessage(), ex);
        }
    }

    /**
     * Method that reads config.
     */
    public void readConfig() {
        ReadConfig readConfig = new ReadConfig();
        ClassLoader loader = ReadConfig.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream("app.properties")) {
            readConfig.load(in);
            this.setUrl(readConfig.getProperty("server"));
            this.setFile(new File(readConfig.getProperty("source")));
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /**
     * Method that implements main logic of the application.
     */
    public void startApp(XsltTransformer transformer) {

        Connection connection = null;
        PreparedStatement preparedStatement;

        this.readConfig();

        try {

            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s", new File(this.url).getAbsolutePath()));

            try {
                logger.debug("connection to DB " + new File(this.url).getCanonicalPath() + " has been established.");
            } catch (IOException e) {
                e.printStackTrace();
            }

            preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS TEST(field INTEGER)");
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement("SELECT count(*) FROM TEST");
            ResultSet rst = preparedStatement.executeQuery();

            int rowsNumber = rst.getInt("count(*)");
            logger.debug("number of rows is: " + rowsNumber);

            if (rowsNumber > 0) {
                this.updateTable(connection, rowsNumber, true);
            } else {
                this.updateTable(connection, this.n, false);
            }

            this.readDataBase(connection);
            transformer.transform(this.file);


        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    logger.debug(String.format("Connection to database %s has been closed.", this.url));
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * Setter for the url member.
     * @param url of a database
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * Setter for the file member.
     * @param file
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Setter for the n member.
     * @param n number of fields to be inserted
     */
    public void setN(final int n) {
        this.n = n;
    }

    /**
     * Getter for the field n.
     * @return int n
     */
    public int getN() {
        return this.n;
    }

    /**
     * Main method that start application.
     *
     * @param args standard array of String
     */
    public static void main(String[] args) {

        XmlXsdOptimizer xmlXsdOptimizer = new XmlXsdOptimizer();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter integer N: ");
            xmlXsdOptimizer.setN(scanner.nextInt());
        } catch (InputMismatchException ime) {
            ime.printStackTrace();
        }

        xmlXsdOptimizer.startApp(new XsltTransformer());

    }

}
