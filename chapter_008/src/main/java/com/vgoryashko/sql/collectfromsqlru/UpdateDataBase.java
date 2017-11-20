package com.vgoryashko.sql.collectfromsqlru;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class that updates Data Base with new job advertisements.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 11/20/17
 */
public class UpdateDataBase {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    private Connection connection;

    private Connect connect;

    public UpdateDataBase() {
        this.connect = new Connect("app.properties");
    }

    public boolean exists(String desc) {

        logger.traceEntry("exists()");

        boolean exists = true;

        PreparedStatement statement = null;
        ResultSet resultSet;

        try {

            statement = this.connection.prepareStatement("SELECT href FROM adverts WHERE description=?");
            statement.setString(1, desc);
            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                exists = false;
                logger.trace("Advert doesn't exist.");
            } else {
                logger.debug(String.format("Advert exists, href: %s", resultSet.getString(1)));
            }

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }


        return logger.traceExit("insert()", exists);

    }

    public boolean insert(Advertisement advertisement) {

        logger.traceEntry("insert()");

        boolean inserted = false;

        PreparedStatement statement = null;

        try {

            this.connection = this.connect.getConnection();
            statement = this.connection.prepareStatement("BEGIN ");
            statement.execute();

            if (!this.exists(advertisement.getDescription())) {
                logger.trace("Advert is going to be inserted.");
                statement = this.connection.prepareStatement("INSERT INTO adverts(header, href, description, create_date) VALUES (?, ?, ?, ?);");
                statement.setString(1, advertisement.getHeader());
                statement.setString(2, advertisement.getHref());
                statement.setString(3, advertisement.getDescription());
                statement.setString(4, advertisement.getDate());
                statement.execute();

                statement = this.connection.prepareStatement("COMMIT ");
                statement.execute();
                inserted = true;
                logger.trace("Advert was inserted.");
            } else {

                statement = this.connection.prepareStatement("ROLLBACK ");
                statement.execute();
                logger.trace("Rollback performed.");

            }


        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if (this.connection != null) {
                this.connect.closeConnection();
            }
        }

        return logger.traceExit("insert()", inserted);
    }

}
