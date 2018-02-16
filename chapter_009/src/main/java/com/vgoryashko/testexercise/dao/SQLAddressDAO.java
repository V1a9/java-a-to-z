package com.vgoryashko.testexercise.dao;

import com.vgoryashko.testexercise.models.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implement DAO for Address class.
 *
 * @author Vlad Goryashko
 * @version 0.5
 * @since 2/18/18
 */
public class SQLAddressDAO implements DAO<Address> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private PreparedStatement preparedStatement;

    private DataSource dataSource;

    private ResultSet resultSet;

    public SQLAddressDAO(DataSource dataSource) {
        this.dataSource= dataSource;
    }

    @Override
    public long exists(Address address) {
        long result = 0;

        Connection connection = null;

        try {
            connection = this.dataSource.getConnection();
            this.preparedStatement = connection.prepareStatement("SELECT id FROM addresses WHERE address=?");
            this.preparedStatement.setString(1, address.getAddress());

            this.resultSet = this.preparedStatement.executeQuery();

            if (this.resultSet.next()) {
                result = this.resultSet.getLong(1);
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
    public long create(Address address) {
        long result = 0;
        long existingAddressId;
        Connection connection = null;
        try {
            connection = this.dataSource.getConnection();
            existingAddressId = this.exists(address);
            if (existingAddressId == 0) {
                this.preparedStatement = connection.prepareStatement("INSERT INTO addresses(address) values(?)", Statement.RETURN_GENERATED_KEYS);
                this.preparedStatement.setString(1, address.getAddress());
                this.preparedStatement.executeUpdate();
                this.resultSet = this.preparedStatement.getGeneratedKeys();
                if (this.resultSet.next()) {
                    result = this.resultSet.getLong(1);
                }
            }else {
                result = existingAddressId;
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
    public Address read(long id) {

        Address address = null;
        Connection connection = null;
        try {
            connection = this.dataSource.getConnection();
            this.preparedStatement = connection.prepareStatement("SELECT * FROM addresses WHERE id=?");
            this.preparedStatement.setLong(1, id);
            this.resultSet = this.preparedStatement.executeQuery();

            if (this.resultSet.next()) {
                address = new Address();
                address.setId(this.resultSet.getLong(1));
                address.setAddress(this.resultSet.getString(2));
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

        return address;
    }

    @Override
    public List<Address> readAll() {

        List<Address> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = this.dataSource.getConnection();
            this.preparedStatement = connection.prepareStatement("SELECT * FROM addresses");
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {

                Address address = new Address();
                address.setId(this.resultSet.getLong(1));
                address.setAddress(this.resultSet.getString(2));
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
        Connection connection = null;
        try {
            connection = this.dataSource.getConnection();
            this.preparedStatement = connection.prepareStatement("UPDATE addresses SET address=? WHERE id=?");
            this.preparedStatement.setString(1, address.getAddress());
            this.preparedStatement.setLong(2, id);
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
        Connection connection = null;
        try {
            connection = this.dataSource.getConnection();
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
