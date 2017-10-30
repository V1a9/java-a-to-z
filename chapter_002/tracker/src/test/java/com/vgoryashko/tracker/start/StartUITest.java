package com.vgoryashko.tracker.start;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Class for testing StartUI class.
 * @author Vlad Goryashko
 * @version 4.0
 * @since 30/10/2017
 */
public class StartUITest {

    private Connection connection;

    /**
     * Field that stores the value of the path separator.
     */
    private static final String FS = System.getProperty("file.separator");

    /**
     * Constant that stores the path to the app.properties file.
     */
    private static final File PROP = new File(String.format(".%ssrc%smain%sresources%sapp.properties", FS, FS, FS, FS));

    /**
     * Method for automatic testing of adding Item to tracking system with name "Item_01" and description "Item_decs_01".
     */
    @Test
    public void whenItemWithNameItem01IsAddedThenItsNotNull() {

        Properties properties;

        String[] modelingInputs = new String[]{
                "1",
                "1",
                "Item_01",
                "Item_decs_01",
                "y"};

        Input input = new StubInput(modelingInputs);

        try (FileInputStream inputStream = new FileInputStream(PROP)) {

            properties = new Properties();
            properties.load(inputStream);

            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tracker", properties);

            Tracker tracker = new Tracker(connection);
            StartUI startUI = new StartUI(input, tracker, connection);
            startUI.init();
            assertNotNull(tracker.findByName("Item_01"));

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Method for automatic testing of adding Task to tracking system with name "Task_01" and description "Task_desc_01".
     */
    @Test
    public void whenTaskWithNameTask01IsAddedThenItsNotNull() {

        Properties properties;

        String[] modelingInputs = new String[]{
                "1",
                "2",
                "Task_01",
                "Task_desc_01",
                "y"};

        Input input = new StubInput(modelingInputs);

        try (FileInputStream inputStream = new FileInputStream(PROP)) {

            Class.forName("org.postgresql.Driver");

            properties = new Properties();
            properties.load(inputStream);

            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tracker", properties);

            Tracker tracker = new Tracker(connection);
            StartUI startUI = new StartUI(input, tracker, connection);
            startUI.init();
            assertNotNull(tracker.findByName("Task_01"));

        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Method for automatic testing of adding a Comment to "Task_01".
     */
    @Test
    public void whenCommentToTask01IsAddedThenItsFieldContentsComment() {

        Properties properties;

        String[] modelingInputs;
        modelingInputs = new String[]{
                "1",
                "2",
                "Task_01",
                "Task_desc_01",
                "n",
                "2",
                "Task_01",
                "Task_01_comment1",
                "y"};
        Input input = new StubInput(modelingInputs);

        try (FileInputStream inputStream = new FileInputStream(PROP)) {

            Class.forName("org.postgresql.Driver");

            properties = new Properties();
            properties.load(inputStream);

            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tracker", properties);

            Tracker tracker = new Tracker(connection);
            StartUI startUI = new StartUI(input, tracker, connection);
            startUI.init();
            assertThat(tracker.findByName("Task_01").getComment().getCommentField(), is("Task_01_comment1"));

        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Method for automatic testing of adding Bug_01.
     */
    @Test
    public void whenBugIsAddedThenItsNotNull() {

        Properties properties;

        String[] modelingInputs;
        modelingInputs = new String[]{
                "1",
                "3",
                "Bug_01",
                "Bug_01_desc",
                "y"};
        Input input = new StubInput(modelingInputs);

        try (FileInputStream inputStream = new FileInputStream(PROP)) {

            Class.forName("org.postgresql.Driver");

            properties = new Properties();
            properties.load(inputStream);

            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tracker", properties);

            Tracker tracker = new Tracker(connection);
            StartUI startUI = new StartUI(input, tracker, connection);
            startUI.init();
            assertNotNull(tracker.findByName("Bug_01"));

        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Method that tests removing of Task_01.
     */
    @Test
    public void whenTask01IsRemovedThenItsNull() {

        Properties properties;

        String[] modelingInputs = new String[]{
                "4",
                null,
                "y"};
        Input input = new StubInput(modelingInputs);

        try (FileInputStream inputStream = new FileInputStream(PROP)) {

            Class.forName("org.postgresql.Driver");

            properties = new Properties();
            properties.load(inputStream);

            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tracker", properties);

            Statement statement = connection.createStatement();

            Tracker tracker = new Tracker(connection);
            PreparedStatement pst = connection.prepareCall("INSERT INTO ITEMS(item_type, name, description) VALUES ('Task', 'Task_01', 'Task_01_desc');");
            pst.execute();

            StartUI startUI = new StartUI(input, tracker, connection);
            ResultSet resultSet = statement.executeQuery("SELECT item_id FROM ITEMS WHERE item_id=lastval()");

            int id = -1;

            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            modelingInputs[1] = String.valueOf(id);
            startUI.init();
            assertNull(tracker.findById(modelingInputs[1]));

        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}