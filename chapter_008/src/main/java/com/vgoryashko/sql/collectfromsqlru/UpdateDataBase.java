package com.vgoryashko.sql.collectfromsqlru;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class that updates Data Base with a new job advertisement.
 *
 * @author Vlad Goryashko
 * @version 0.6
 * @since 11/29/17
 */
public class UpdateDataBase {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    private Connection connection;

    private Connect connect;
    
    private PreparedStatement preparedStatement;

    public UpdateDataBase() {
    }

    public UpdateDataBase(Connect connect) {
        this.connect = connect;
    }

    public boolean exists(String desc) {

        logger.traceEntry("exists()");

        boolean exists = true;
        
        ResultSet resultSet;

        try {

            this.preparedStatement = this.connection.prepareStatement("SELECT href FROM adverts WHERE description=?");
            this.preparedStatement.setString(1, desc);
            resultSet = this.preparedStatement.executeQuery();

            if (!resultSet.next()) {
                exists = false;
                logger.trace("Advert doesn't exist.");
            } else {
                logger.debug(String.format("Advert exists, href: %s", resultSet.getString(1)));
            }

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

        return logger.traceExit("insert()", exists);

    }

    public boolean insert(Advertisement advertisement) {

        logger.traceEntry("insert()");

        boolean inserted = false;

        try {

            this.connection = this.connect.getConnection();
            this.preparedStatement = this.connection.prepareStatement("BEGIN ");
            this.preparedStatement.execute();

            if (!this.exists(advertisement.getDescription())) {
                logger.trace("Advert is going to be inserted.");
                this.preparedStatement = this.connection.prepareStatement("INSERT INTO adverts(header, href, description, create_date) VALUES (?, ?, ?, ?);");
                this.preparedStatement.setString(1, advertisement.getHeader());
                this.preparedStatement.setString(2, advertisement.getHref());
                this.preparedStatement.setString(3, advertisement.getDescription());
                this.preparedStatement.setString(4, advertisement.getDate());
                this.preparedStatement.execute();

                this.preparedStatement = this.connection.prepareStatement("COMMIT ");
                this.preparedStatement.execute();
                inserted = true;
                logger.trace("Advert was inserted.");
            } else {

                this.preparedStatement = this.connection.prepareStatement("ROLLBACK ");
                this.preparedStatement.execute();
                logger.trace("Rollback performed.");

            }

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
            if (this.connection != null) {
                this.connect.closeConnection();
            }
        }

        return logger.traceExit("insert()", inserted);
    }

    public void setConnect(Connect connect) {
        this.connect = connect;
    }
}
