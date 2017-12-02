package com.vgoryashko.servlet.crudservlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class that implements interactions with the DataBase which stores Users.
 *
 * @author Vlad Goryashko
 * @version 0.5
 * @since 12/02/17
 */
public class UserStore {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private String propertiesFile = "app.properties";

    private final static UserStore INSTANCE = new UserStore();

    private Connection connection;

    private PreparedStatement preparedStatement;

    private ResultSet resultSet;

    private UserStore() { }

    public static synchronized UserStore getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
        }

        Properties properties = new Properties();
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(this.propertiesFile);

        try {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        return DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("user"),
                properties.getProperty("password")
        );
    }

    public void init(Connection connection) {

        try {
            this.preparedStatement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS users("
                            + "id SERIAL PRIMARY KEY,"
                            + "USER_NAME VARCHAR (255),"
                            + "USER_LOGIN VARCHAR (255),"
                            + "eMAIL VARCHAR (255),"
                            + "CREATE_DATE VARCHAR (255));"
            );

            this.preparedStatement.execute();

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (this.preparedStatement != null) {
                try {
                    this.preparedStatement.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    public boolean create(Connection connection, User user) {

        boolean created = false;
        
        try {
            
            if (!this.exists(connection, user.getEmail())) {
                
                this.preparedStatement = connection.prepareStatement("INSERT INTO users(user_name, user_login, email, create_date) VALUES (?, ?, ?, ?);");
                this.preparedStatement.setString(1, user.getName());
                this.preparedStatement.setString(2, user.getLogin());
                this.preparedStatement.setString(3, user.getEmail());
                this.preparedStatement.setString(4, user.getCreateDate());
                this.preparedStatement.execute();
                created = true;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (this.preparedStatement != null) {
                try {
                    this.preparedStatement.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        return created;
    }

    public boolean exists(Connection connection, String userEmail) {
        
        boolean exists = false;
        
        try {

            this.preparedStatement = connection.prepareStatement("SELECT id FROM users WHERE email=?");
            this.preparedStatement.setString(1, userEmail);
            this.resultSet = this.preparedStatement.executeQuery();

            if (this.resultSet.next()) {
                exists = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (this.preparedStatement != null) {
                try {
                    this.preparedStatement.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        return exists;

    }

    public User read(Connection connection, String userEmail) {

        User user = null;

        try {

            this.preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email=?");
            this.preparedStatement.setString(1, userEmail);
            this.resultSet = this.preparedStatement.executeQuery();

            if (this.resultSet.next()) {

                user = new User();
                user.setName(this.resultSet.getString(2));
                user.setLogin(this.resultSet.getString(3));
                user.setEmail(this.resultSet.getString(4));
                user.setCreateDate(this.resultSet.getString(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (this.preparedStatement != null) {
                try {
                    this.preparedStatement.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        return user;
    }

    public boolean delete(Connection connection, String userEmail) {

        boolean deleted = false;

        try {

            if (this.exists(connection, userEmail)) {
                this.preparedStatement = connection.prepareStatement("DELETE FROM users WHERE email=?");
                this.preparedStatement.setString(1, userEmail);
                this.preparedStatement.execute();
                deleted = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (this.preparedStatement != null) {
                try {
                    this.preparedStatement.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        return deleted;
    }

    public boolean update(Connection connection, User user) {

        boolean updated = false;

        try {

            if (exists(connection, user.getEmail())) {

                this.preparedStatement = connection.prepareStatement("BEGIN");
                this.preparedStatement.execute();

                this.preparedStatement = connection.prepareStatement("UPDATE users SET user_name=? WHERE email=? ");
                this.preparedStatement.setString(1, user.getName());
                this.preparedStatement.setString(2, user.getEmail());
                this.preparedStatement.executeUpdate();

                this.preparedStatement = connection.prepareStatement("UPDATE users SET user_login=? WHERE email=? ");
                this.preparedStatement.setString(1, user.getLogin());
                this.preparedStatement.setString(2, user.getEmail());
                this.preparedStatement.executeUpdate();

                this.preparedStatement = connection.prepareStatement("UPDATE users SET email=? WHERE email=? ");
                this.preparedStatement.setString(1, user.getEmail());
                this.preparedStatement.setString(2, user.getEmail());
                this.preparedStatement.executeUpdate();

                this.preparedStatement = connection.prepareStatement("UPDATE users SET create_date=? WHERE email=? ");
                this.preparedStatement.setString(1, user.getCreateDate());
                this.preparedStatement.setString(2, user.getEmail());
                this.preparedStatement.executeUpdate();

                this.preparedStatement = connection.prepareStatement("COMMIT");
                this.preparedStatement.execute();

                updated = true;

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (this.preparedStatement != null) {
                try {
                    this.preparedStatement.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        return updated;
    }
}