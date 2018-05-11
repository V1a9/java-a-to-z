package com.vgoryashko.tracker.start;

import com.vgoryashko.tracker.models.Bug;
import com.vgoryashko.tracker.models.Comment;
import com.vgoryashko.tracker.models.Item;
import com.vgoryashko.tracker.models.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class that implements system that performs tracking of user's requests and allows to perform different actions via UI.
 * @author Vlad Goryashko
 * @version 5.0
 * @since 08/11/2017
 */

public class Tracker {

	/**
	 * Field that refers to a connection to the database.
	 */
	private final Connection connection;

    /**
     * Field that refers to PreparedStatement.
     */
    private PreparedStatement preparedStatement;

	/**
	 * Object that is used for generation of random numbers.
	 */
	protected static final Random RN = new Random();

    /**
     * Constructor for the class.
     */
    public Tracker(Connection connection) {
        this.connection = connection;
    }

	/**
	 * Method that adds a new Item to the tracking system.
	 * @param item							an Item to be added into array of requests.
	 * @return								<code>item</code>
	 */
	public Item addItem(Item item) {

        try {

            preparedStatement = connection.prepareStatement(
                    "INSERT INTO ITEMS(item_type, name, description, create_date) VALUES (?, ?, ?, now());");

            if (item instanceof Task) {
                preparedStatement.setString(1, "Task");
            } else if (item instanceof Bug) {
                preparedStatement.setString(1, "Bug");
            } else {
                preparedStatement.setString(1, "Item");
            }

            preparedStatement.setString(2, item.getName());
            preparedStatement.setString(3, item.getDescription());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return item;
	}

	/**
	 * Method that search an Item based on a given id.
	 * @param id							id to be used for searching of an Item
	 * @return 								<code>result</code>
	 * @throws 								InvalidRequestException if there is no a request with entered id.
	 */
	protected Item findById(String id) throws InvalidRequestException {

        Item result = null;
        ResultSet resultSet;

        try {

            preparedStatement = connection.prepareStatement(
            		"SELECT * FROM ITEMS WHERE item_id=?;");

            preparedStatement.setInt(1, Integer.parseInt(id));

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getString(2).equals("Item")) {
                    result = new Item(resultSet.getString(3), resultSet.getString(4));
                } else if (resultSet.getString(2).equals("Task")) {
                    result = new Task(resultSet.getString(3), resultSet.getString(4));
                } else {
                    result = new Bug(resultSet.getString(3), resultSet.getString(4));
                }

                preparedStatement = connection.prepareStatement(
                        "SELECT * FROM COMMENTS WHERE comment_id=?;");
                preparedStatement.setInt(1, resultSet.getInt(5));
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    result.setComment(new Comment(resultSet.getString(2)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
	}

	/**
	 * Method that search an Item based on a given name.
	 * @param name							name to be used for searching of an Item
	 * @return								<code>result</code>
	 * @throws 								InvalidRequestException if there is no a request with entered name.
	 */
	protected Item findByName(String name) throws InvalidRequestException {

	    Item result = null;
	    ResultSet resultSet;

        try {

            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM ITEMS WHERE name=?;");

            preparedStatement.setString(1, name);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                result = this.pickType(resultSet);

                result.setId(String.valueOf(resultSet.getInt(1)));

                preparedStatement = connection.prepareStatement(
                        "SELECT description FROM COMMENTS WHERE comment_id=(SELECT comment_id FROM ITEMS WHERE item_id=?)");
                preparedStatement.setInt(1, resultSet.getInt(1));
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    result.setComment(new Comment(resultSet.getString(1)));
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
	}

	/**
	 * Method that removes an Item based on an given id.
	 * @param itemId						an id of an Item to be removed
	 * @throws 								InvalidRequestException if there is no a request with entered id.
	 */
	public void removeItem(String itemId) throws InvalidRequestException {

        ResultSet resultSet;

        try {

            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM ITEMS WHERE item_id=?;");
            preparedStatement.setInt(1, Integer.parseInt(itemId));
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                preparedStatement = connection.prepareStatement(
                        "DELETE FROM ITEMS WHERE item_id=?;");
                preparedStatement.setInt(1, Integer.parseInt(itemId));
                preparedStatement.execute();

                preparedStatement = connection.prepareStatement("DELETE FROM comments WHERE comment_id=?");
                preparedStatement.setInt(1, resultSet.getInt(5));
                preparedStatement.execute();
            } else {
                throw new InvalidRequestException("\nThere is no a request with such id.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
	}

	/**
	 * Method that gets all Items.
	 * @return result							<code>result</code>
	 */
	public ArrayList<Item> getAll() {

	    ArrayList<Item> result = new ArrayList<>();
	    ResultSet resultSet;

        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM ITEMS;");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Item item;

                String id = String.valueOf(resultSet.getInt("item_id"));
                String type = resultSet.getString("item_type");
                String name = resultSet.getString("name");
                String desc = resultSet.getString("description");
                String comm = null;

                PreparedStatement prst = connection.prepareStatement(
                        "SELECT description FROM comments WHERE comment_id=(SELECT comment_id FROM ITEMS where item_id=?);");
                prst.setInt(1, Integer.parseInt(id));
                ResultSet rst = prst.executeQuery();

                if (rst.next()) {
                    comm = rst.getString(1);
                }

                if (type.equals("Item")) {
                    item = new Item(name, desc);
                    item.setId(id);
                    if (comm != null) {
                        item.setComment(new Comment(comm));
                    }
                } else if (type.equals("Task")) {
                    item = new Task(name, desc);
                    item.setId(id);
                    if (comm != null) {
                        item.setComment(new Comment(comm));
                    }
                } else {
                    item = new Bug(name, desc);
                    item.setId(id);
                    if (comm != null) {
                        item.setComment(new Comment(comm));
                    }
                }

                result.add(item);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;

	}

	/**
	 * Method that adds a Comment to an Item.
	 * @param name								name of an Item which a comment to be added to
	 * @param comm								comment to be added to an Item
	 * @return									<code>item</code>
	 */
	public Item addComment(String name, String comm) {

		Item item = null;
        ResultSet resultSet;

        try {

            preparedStatement = connection.prepareStatement("SELECT name FROM ITEMS;");
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                preparedStatement = connection.prepareStatement("INSERT INTO COMMENTS(description) VALUES (?) RETURNING comment_id;");
                preparedStatement.setString(1, comm);
                preparedStatement.execute();

                preparedStatement = connection.prepareStatement("SELECT lastval() FROM COMMENTS;");
                resultSet = preparedStatement.executeQuery();

                int commentId = -1;

                if (resultSet.next()) {
                    commentId = resultSet.getInt(1);
                }

                preparedStatement = connection.prepareStatement(
                        "UPDATE ITEMS SET comment_id=? WHERE name=? RETURNING *;");
                preparedStatement.setInt(1, commentId);
                preparedStatement.setString(2, name);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {

                    item = this.pickType(resultSet);

                    item.setComment(new Comment(comm));
                    item.setId(String.valueOf(resultSet.getInt(1)));
                }

            } else {

                throw new InvalidRequestException("\nThere is no an Item with such name.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

		return item;
	}

	/**
	 * Method that edits Item content.
	 * @param item								an Item to be edited
	 * @return									<code>boolean</code>
	 */
	public boolean replace(Item item) {

		boolean result = false;
		ResultSet resultSet;

        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM ITEMS WHERE item_id=?;");
            preparedStatement.setInt(1, Integer.parseInt(item.getId()));
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                String type;
                if (item instanceof Task) {
                    type = "Task";
                } else if (item instanceof Bug) {
                    type = "Bug";
                } else {
                    type = "Item";
                }

                preparedStatement = connection.prepareStatement("UPDATE ITEMS SET item_type=? WHERE item_id=?;");
                preparedStatement.setString(1, type);
                preparedStatement.setInt(2, Integer.parseInt(item.getId()));
                preparedStatement.execute();

                preparedStatement = connection.prepareStatement("UPDATE ITEMS SET name=? WHERE item_id=?;");
                preparedStatement.setString(1, item.getName());
                preparedStatement.setInt(2, Integer.parseInt(item.getId()));
                preparedStatement.execute();

                preparedStatement = connection.prepareStatement("UPDATE ITEMS SET description=? WHERE item_id=?;");
                preparedStatement.setString(1, item.getDescription());
                preparedStatement.setInt(2, Integer.parseInt(item.getId()));
                preparedStatement.execute();

                preparedStatement = connection.prepareStatement(
                        "SELECT description FROM comments WHERE comment_id=(SELECT comment_id FROM ITEMS WHERE item_id=?);");
                preparedStatement.setInt(1, Integer.parseInt(item.getId()));
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    resultSet.updateInt(5, resultSet.getInt(1));
                }

                result = true;

            } else {

                throw new InvalidRequestException("\nThere is no an Item with such name.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

		return result;
	}

    /**
     * Method that pick correct type of an Item and returns a new Item.
     */
    public Item pickType(ResultSet resultSet) {

        Item item = null;

        try {
            if ("Item".equals(resultSet.getString(2))) {
                item = new Item(resultSet.getString(3), resultSet.getString(4));
            } else if ("Task".equals(resultSet.getString(2))) {
                item = new Task(resultSet.getString(3), resultSet.getString(4));
            } else {
                item = new Bug(resultSet.getString(3), resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return item;
    }

}
