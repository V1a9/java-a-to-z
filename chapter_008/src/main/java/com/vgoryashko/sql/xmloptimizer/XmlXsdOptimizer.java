package com.vgoryashko.sql.xmloptimizer;

import com.google.common.base.Joiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class that implements console application that connects to the SQLite database,
 * deletes/inserts N entries in it, writes field values into 1.xml file, transforms this file with 1.xsl
 * and writes a result into 2.xml.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 10/27/17
 */
public class XmlXsdOptimizer {

    /**
     * Final field that stores an instance of the Logger.
     */
    private final Logger logger = LoggerFactory.getLogger(XmlXsdOptimizer.class);

    /**
     * Constant that defines file separator string value.
     */
    private static final String FS = System.getProperty("file.separator");

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
    private final File file = new File(String.format(".%s1.xml", FS));

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

        String ins = "INSERT INTO TEST VALUES (?)";
        String del = "DELETE FROM TEST WHERE field NOTNULL ";

        PreparedStatement preparedStatement;

        try {
            if (delete) {
                preparedStatement = connection.prepareStatement(del);
                preparedStatement.executeUpdate();
                logger.trace(String.format("%d fields were deleted", rowsNumber));
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
            logger.trace(String.format("%d fields were inserted", this.n));

        } catch (SQLException e) {
            logger.trace(e.getMessage(), e);
        }
    }

    /**
     * Method that retrieves data from the table and writes it into 1.xml file.
     */
    public void readDataBase(Connection connection) {

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        if (this.file.exists()) {
            logger.trace(String.format("File %s exists.", this.file.getAbsolutePath()));
            this.file.delete();
            logger.trace(String.format("File %s has been deleted.", this.file.getAbsolutePath()));
        }

        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM TEST");
            resultSet = preparedStatement.executeQuery();

            try (Writer writer = new FileWriter(this.file)) {
                logger.trace(String.format("File %s has been created.", this.file.getAbsolutePath()));

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
            logger.trace(String.format("File %s has been written.", this.file.getAbsolutePath()));

        } catch (SQLException | IOException ex) {
            logger.trace(ex.getMessage(), ex);
        }
    }

    /**
     * Method that implements main logic of the application.
     */
    public void startApp(XsltTransformer transformer) {

        Connection connection = null;
        PreparedStatement preparedStatement;

        try {

            connection = DriverManager.getConnection(url);

            logger.debug("connection to DB " + url + " has been established.");

            preparedStatement = connection.prepareStatement("SELECT count(*) FROM TEST");
            ResultSet rst = preparedStatement.executeQuery();

            int rowsNumber = rst.getInt("count(*)");
            logger.trace("number of rows is: " + rowsNumber);

            if (rowsNumber > 0) {
                this.updateTable(connection, rowsNumber, true);
            } else {
                this.updateTable(connection, this.n, false);
            }

            this.readDataBase(connection);
            transformer.transform(this.file);


        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    logger.debug("Connection to database %s has been closed.", this.url);
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
        xmlXsdOptimizer.setN(Integer.getInteger(args[0]));
        xmlXsdOptimizer.startApp(new XsltTransformer());

    }

}
