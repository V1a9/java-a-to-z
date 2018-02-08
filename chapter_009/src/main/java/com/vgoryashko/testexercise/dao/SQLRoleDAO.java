package com.vgoryashko.testexercise.dao;

import com.vgoryashko.testexercise.models.Role;
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
 * Class that implement DAO for Role.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 2/08/18
 */
public class SQLRoleDAO implements DAO<Role> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private DataSource dataSource;

    private PreparedStatement preparedStatement;

    private ResultSet resultSet;

    public SQLRoleDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long exists(Role role) {
        long result = 0;
        Connection connection = null;
        try {
            connection = this.dataSource.getConnection();
            this.preparedStatement = connection.prepareStatement(
                    "SELECT id FROM roles WHERE role=?");
            this.preparedStatement.setString(1, role.getRoleName());
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
    public long create(Role role) {

        long result = 0;
        Connection connection = null;
        try {
            connection = this.dataSource.getConnection();
            if (this.exists(role) == 0) {
                this.preparedStatement = connection.prepareStatement("INSERT INTO roles(role) values(?)", Statement.RETURN_GENERATED_KEYS);
                this.preparedStatement.setString(1, role.getRoleName());
                this.preparedStatement.executeUpdate();
                this.resultSet = this.preparedStatement.getGeneratedKeys();

                if (this.resultSet.next()) {
                    result = this.resultSet.getLong(1);
                }
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
    public Role read(long id) {

        Role role = null;
        Connection connection = null;
        try {
            connection = this.dataSource.getConnection();
            this.preparedStatement = connection.prepareStatement("SELECT * FROM roles WHERE id=?");
            this.preparedStatement.setLong(1, id);
            this.resultSet = this.preparedStatement.executeQuery();

            if (this.resultSet.next()) {
                role = new Role();
                role.setId(this.resultSet.getLong(1));
                role.setRoleName(this.resultSet.getString(2));
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

        return role;

    }

    @Override
    public List<Role> readAll() {

        List<Role> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = this.dataSource.getConnection();
            this.preparedStatement = connection.prepareStatement("SELECT * FROM roles");
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {

                Role role = new Role();
                role.setId(this.resultSet.getLong(1));
                role.setRoleName(this.resultSet.getString(2));
                result.add(role);
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
    public boolean update(Role role, long id) {

        boolean result = false;
        Connection connection = null;
        try {
            connection = this.dataSource.getConnection();
            this.preparedStatement = connection.prepareStatement("UPDATE roles SET role=? WHERE id=?");
            this.preparedStatement.setString(1, role.getRoleName());
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
            this.preparedStatement = connection.prepareStatement("DELETE FROM roles WHERE id=?");
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
