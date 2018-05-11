package com.vgoryashko.spring.dao;

import com.vgoryashko.spring.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class that implements Users' storage in the DB.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 4/09/18
 */
public class JdbcStorage implements Storage {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcStorage.class);

    private static final JdbcStorage INSTANCE = new JdbcStorage();

    private Properties connProperties = new Properties();

    private JdbcStorage() {

    }

    {
        ClassLoader classLoader = this.getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("app.properties")) {
            connProperties.load(inputStream);
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(
                        connProperties.getProperty("url"),
                        connProperties.getProperty("userName"),
                        connProperties.getProperty("pwd")
                );
                PreparedStatement statement = connection.prepareStatement(
                        "CREATE TABLE IF NOT EXISTS users.public.users (name VARCHAR(255 ));");
                statement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            } finally {
                this.closeConnection(connection);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public static synchronized JdbcStorage getInstance() {
        return INSTANCE;
    }

    public Connection setConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    connProperties.getProperty("url"),
                    connProperties.getProperty("userName"),
                    connProperties.getProperty("pwd")
            );
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public void add(User user) {
        Connection connection = this.setConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name) VALUES (?)");
            statement.setString(1, user.getName());
            statement.executeUpdate();
            this.closeConnection(connection);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
