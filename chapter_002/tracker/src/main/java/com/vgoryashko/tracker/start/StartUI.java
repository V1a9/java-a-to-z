package com.vgoryashko.tracker.start;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Class that implements system that implements UI for tracking system.
 * @author Vlad Goryashko
 * @version 4.0
 * @since 30/10/2017
 */

public class StartUI {

	/**
	 * Variable that is used for implementation of a number different types of input methods.
	 */
	private Input input;

	/**
	 * Variable that is used for operating with the class Tracker.
	 */
	private Tracker tracker;

	/**
	 *
	 */
	private Connection connection;

	/**
	 * Field that stores the value of the path separator.
	 */
	private static final String FS = System.getProperty("file.separator");

    /**
     * Constant that stores the path to the app.properties file.
     */
    private static final File PROP = new File(String.format(".%schapter_002%stracker%ssrc%smain%sresources%sapp.properties", FS, FS, FS, FS, FS, FS));
    
	/**
	 * Constructor for the class.
	 * @param aInput							an object that implements Input interface for realization
	 *                                          of interaction with an end user.
	 * @param aTracker							an object that implements Tracking system.
     */
	public StartUI(Input aInput, Tracker aTracker, Connection connection) {
		this.input = aInput;
		this.tracker = aTracker;
		this.connection = connection;
	}

	/**
	 * Variable that holds value of action 1.
	 */
	private final int action1 = 1;
	/**
	 * Variable that holds value of action 2.
	 */
	private final int action2 = 2;
	/**
	 * Variable that holds value of action 3.
	 */
	private final int action3 = 3;
	/**
	 * Variable that holds value of action 4.
	 */
	private final int action4 = 4;
	/**
	 * Variable that holds value of action 5.
	 */
	private final int action5 = 5;
	/**
	 * Variable that holds value of action 6.
	 */
	private final int action6 = 6;

	/**
	 * Range of valid input keys available for an user in the main menu.
	 */
	private int[] rangeMain = new int[]{action1, action2, action3, action4, action5, action6};

	/**
	 * Range of valid input keys available for an user in the create item menu.
	 */
	private int[] rangeCreate = new int[]{action1, action2, action3};

	/**
	 * Range of valid input keys available for an user in the find menu.
	 */
	private int[] rangeFind = new int[]{action1, action2};

	/**
	 * Range of valid input keys available for an user in the replace menu.
	 */
	private int[] rangeReplace = new int[]{action1, action2, action3};

	/**
	 * Variable that represents key number for adding a new request in the main menu.
	 */
	private final int addKey = 1;

	/**
	 * Variable that represents key number for searching a request in the main menu.
	 */
	private final int findKey = 5;

	/**
	 * Variable that represents key number for replacing a request in the main menu.
	 */
	private final int replaceKey = 6;

	/**
	 * Method that initialize application.
	 */
	public void init() {

		this.createTables(this.connection);

        TrackerMenu menu = new TrackerMenu(tracker, this.input);

        menu.fillActionsMain();
        menu.fillActionsCreateItem();
        menu.fillActionsFindItem();
        menu.fillActionsReplaceItem();
        do {
            menu.showActionsMain();
            int key = input.ask("\nSelect: ", rangeMain);
            if (key == addKey) {
                menu.showActionsCreateItem();
                menu.selectActionsCreateItem(input.ask("\nSelect: ", rangeCreate));
            } else if (key == findKey) {
                menu.showActionsFindItem();
                menu.selectActionsFindItem(input.ask("\nSelect: ", rangeFind));
            } else if (key == replaceKey) {
                menu.showActionsReplaceItem();
                menu.selectActionsReplaceItem(input.ask("\nSelect: ", rangeReplace));
            } else {
                menu.selectActionsMain(key);
            }
        } while (!"y".equals(this.input.ask("\n\nExit: y/n")));
    }

    public void createTables(Connection connection) {

		Statement statement;

		try {
			statement = connection.createStatement();

			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS COMMENTS(comment_id SERIAL PRIMARY KEY, description text);"
			);

			statement.executeUpdate(
					"CREATE TABLE IF NOT EXISTS ITEMS(item_id SERIAL PRIMARY KEY, item_type varchar(256), name VARCHAR(256), description text, comment_id INTEGER REFERENCES COMMENTS(comment_id), create_date TIMESTAMP WITHOUT TIME ZONE);"
			);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Main method - the enter point for the application.
	 * @param args							array of String default parameter
     */
	public static void main(String[] args) {
	    
	    Connection connection = null;
	    Properties properties;

	    try (FileInputStream inputStream = new FileInputStream(PROP)) {
            
	        Class.forName("org.postgresql.Driver");
	        
	        properties = new Properties();
	        properties.load(inputStream);
	        
	        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tracker", properties);

            Tracker tracker = new Tracker(connection);
            Input input = new ValidateInput();
            new StartUI(input, tracker, connection).init();
	        
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