package com.vgoryashko.testexercise.dao;

import com.vgoryashko.testexercise.models.Address;
import com.vgoryashko.testexercise.models.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implement DAO for Address class.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 1/21/18
 */
public class SQLAddressDAO implements DAO<Address> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private Connection connection;

    private PreparedStatement preparedStatement;

    private ResultSet resultSet;

    public SQLAddressDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Address address) {
        boolean result = false;

        try {

            this.preparedStatement = connection.prepareStatement("INSERT INTO addresses(country, city, street, appartment) values(?, ?, ?, ?)");
            this.preparedStatement.setString(1, address.getCountry());
            this.preparedStatement.setString(2, address.getCity());
            this.preparedStatement.setString(3, address.getStreet());
            this.preparedStatement.setString(4, address.getApartment());
            this.preparedStatement.executeUpdate();
            result = true;

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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        return result;
    }

    @Override
    public Address read(long id) {

        Address address = null;

        try {

            this.preparedStatement = this.connection.prepareStatement("SELECT * FROM addresses WHERE id=?");
            this.preparedStatement.setLong(1, id);
            this.resultSet = this.preparedStatement.executeQuery();

            if (this.resultSet.next()) {
                address = new Address();
                address.setId(this.resultSet.getLong(1));
                address.setCountry(this.resultSet.getString(2));
                address.setCity(this.resultSet.getString(3));
                address.setStreet(this.resultSet.getString(4));
                address.setApartment(this.resultSet.getString(5));
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
                try {
                    this.connection.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        return address;
    }

    @Override
    public List<Address> readAll() {

        List<Address> result = new ArrayList<>();

        try {

            this.preparedStatement = connection.prepareStatement("SELECT * FROM addresses");
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {

                Address address = new Address();
                address.setId(this.resultSet.getLong(1));
                address.setCountry(this.resultSet.getString(2));
                address.setCity(this.resultSet.getString(3));
                address.setStreet(this.resultSet.getString(4));
                address.setApartment(this.resultSet.getString(5));
                result.add(address);
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        return result;

    }

    @Override
    public boolean update(Address address, long id) {

        boolean result = false;

        try {

            this.preparedStatement = connection.prepareStatement("UPDATE addresses SET country=?, city=?, street=?, appartment=? WHERE id=?");
            this.preparedStatement.setString(1, address.getCountry());
            this.preparedStatement.setString(2, address.getCity());
            this.preparedStatement.setString(3, address.getStreet());
            this.preparedStatement.setString(4, address.getApartment());
            this.preparedStatement.setLong(5, id);
            this.preparedStatement.executeUpdate();
            if (this.preparedStatement.executeUpdate() > 0) {
                result = true;
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        return result;
    }

    @Override
    public boolean delete(long id) {

        boolean result = false;

        try {

            this.preparedStatement = connection.prepareStatement("DELETE FROM addresses WHERE id=?");
            this.preparedStatement.setLong(1, id);
            this.preparedStatement.executeUpdate();
            if (this.preparedStatement.executeUpdate() > 0) {
                result = true;
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return result;
    }
}
