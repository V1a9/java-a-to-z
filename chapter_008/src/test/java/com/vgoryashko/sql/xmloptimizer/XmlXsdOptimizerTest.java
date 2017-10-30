package com.vgoryashko.sql.xmloptimizer;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
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
 * @version 0.3
 * @since 10/30/17
 */
public class XmlXsdOptimizerTest {

    private static final String FS = System.getProperty("file.separator");

    private final String url = String.format("jdbc:sqlite:.%ssrc%smain%sresources%sjava_a_to_z.db", FS, FS, FS, FS);

    private XmlXsdOptimizer optimizer;

    private Connection connection;

    private PreparedStatement preparedStatement;

    @Before
    public void setUp() throws Exception {
        optimizer = new XmlXsdOptimizer();
        optimizer.setUrl(url);
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

        assertTrue(new File(String.format(".%s1.xml", FS)).exists());

        connection.close();
    }

    /**
     * Method that tests startApp().
     */
    @Test
    public void whenStartAppInvokedThen1XmlTransformedAnd2XmlCreated() {

        optimizer.startApp(new XsltTransformer());
        assertTrue(new File(String.format(".%s1.xml", FS)).exists());

    }
}