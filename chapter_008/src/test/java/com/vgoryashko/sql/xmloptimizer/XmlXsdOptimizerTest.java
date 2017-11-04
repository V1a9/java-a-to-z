package com.vgoryashko.sql.xmloptimizer;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.sql.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

/**
 * Class that tests XmlXsdOptimizer.class.
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 10/30/17
 */
public class XmlXsdOptimizerTest {

    private String url;

    private String source;

    private String style;

    private String dest;

    private XmlXsdOptimizer optimizer;

    private Connection connection;

    private PreparedStatement preparedStatement;

    @Before
    public void setUp() throws Exception {

        ReadConfig readConfig = new ReadConfig();
        ClassLoader loader = ReadConfig.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream("app.properties")) {
            readConfig.load(in);
            this.url = readConfig.getProperty("server");
            this.source = readConfig.getProperty("source");
            this.style = readConfig.getProperty("style");
            this.dest = readConfig.getProperty("dest");
        }

        optimizer = new XmlXsdOptimizer();

        optimizer.setUrl(url);
        optimizer.setFile(new File(this.source));

        optimizer.setN(10000);
    }

    /**
     * Method that tests updateTable().
     * @throws Exception Exception
     */
    @Test
    public void whenUpdateTableInvokedThenNEntriesInsertedIntoDatabase() throws Exception {

        try {

            connection = DriverManager.getConnection(url);

            preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS TEST(field INTEGER)");
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement("SELECT count(*) FROM TEST");
            ResultSet rst = preparedStatement.executeQuery();

            int rowsNumber = rst.getInt("count(*)");

            if (rowsNumber > 0) {
                optimizer.updateTable(connection, rowsNumber, true);
            } else {
                optimizer.updateTable(connection, optimizer.getN(), false);
            }

            assertThat(rst.getInt("count(*)"), is(optimizer.getN()));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }


    }

    /**
     * Method that tests readDataBase().
     */
    @Test
    public void whenReadDataBaseInvokedThenAllEntriesReadAndWrittenInto1XmlFile() throws Exception {

        connection = DriverManager.getConnection(url);

        optimizer.readDataBase(connection);

        assertTrue(new File(this.source).exists());

        connection.close();
    }

    /**
     * Method that tests startApp().
     */
    @Test
    public void whenStartAppInvokedThen1XmlTransformedAnd2XmlCreated() {

        optimizer.startApp(new XsltTransformer());
        assertTrue(new File(this.dest).exists());

    }

    @Test
    public void testReadConfig() throws Exception {

        ReadConfig readConfig = new ReadConfig();
        ClassLoader loader = ReadConfig.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream("app.properties")) {
            readConfig.load(in);
            this.url = readConfig.getProperty("server");
            this.style = readConfig.getProperty("style");
            this.source = readConfig.getProperty("source");
            this.dest = readConfig.getProperty("dest");
        }

        assertThat(this.url, is("jdbc:sqlite:TEST"));
        assertThat(this.style, is("src/main/resources/2.xsl"));
        assertThat(this.dest, is("2.xml"));
        assertThat(this.source, is("1.xml"));
    }
}