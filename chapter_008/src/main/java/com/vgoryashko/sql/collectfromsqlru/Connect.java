package com.vgoryashko.sql.collectfromsqlru;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class that creates connection to the database.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 11/20/17
 */
public class Connect {

    private final String propertiesFile;

    private Connection connection;

    public Connect(String properties) {
        this.propertiesFile = properties;
    }

    private final Logger logger = LogManager.getLogger(this.getClass());

    public Connection getConnection() throws SQLException {

        logger.traceEntry("getConnection()");

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

        this.connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("user"),
                properties.getProperty("password")
        );

        return logger.traceExit("Connected to sqlru database", this.connection);
    }

    public void closeConnection() {
        logger.traceEntry("closeConnection()");
        if (this.connection != null) {
            try {
                this.connection.close();
                logger.traceExit("connection to sqlru database is closed");
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

}
