package com.vgoryashko.sql.xmloptimizer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

/**
 * Class that tests XmlXsdOptimizer.class.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 11/05/17
 */
public class XmlXsdOptimizerTest {

    private String url;

    private String source;

    private String style;

    private String dest;

    private XmlXsdOptimizer optimizer;

    private Connection connection;

    private PreparedStatement preparedStatement;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {

        Class.forName("org.sqlite.JDBC");
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

            ResultSet rst;

            connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s", new File(this.url).getAbsolutePath()));

            preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS TEST(field INTEGER)");
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement("SELECT count(*) FROM TEST");
            rst = preparedStatement.executeQuery();

            int rowsNumber = rst.getInt("count(*)");

            if (rowsNumber > 0) {
                optimizer.updateTable(connection, rowsNumber, true);
            } else {
                optimizer.updateTable(connection, optimizer.getN(), false);
            }

            preparedStatement = connection.prepareStatement("SELECT count(*) FROM TEST");
            rst = preparedStatement.executeQuery();

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

        connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s", new File(this.url).getAbsolutePath()));

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

    /**
     * Method that tests ReadConfig().
     * @throws Exception Exception
     */
    @Test
    public void whenReadConfigInvokedThenSetupResourcesReadCorrectly() throws Exception {

        ReadConfig readConfig = new ReadConfig();
        ClassLoader loader = ReadConfig.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream("app.properties")) {
            readConfig.load(in);
            this.url = readConfig.getProperty("server");
            this.style = readConfig.getProperty("style");
            this.source = readConfig.getProperty("source");
            this.dest = readConfig.getProperty("dest");
        }

        assertThat(this.url, is("TEST.db"));
        assertThat(this.style, is("./src/main/resources/2.xsl"));
        assertThat(this.dest, is("2.xml"));
        assertThat(this.source, is("1.xml"));
    }

    /**
     * Method that tests Exception in XsltTransformer().transform().
     */
    @Test
    public void whenStyleFileWrongThenNullPointException() {

        expectedException.expect(NullPointerException.class);

        new XsltTransformer().transform(new File("abcdef"));

        throw new NullPointerException();

    }

}