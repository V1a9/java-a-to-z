package com.vgoryashko.testexercise.dao;

import com.vgoryashko.testexercise.models.Entity;
import com.vgoryashko.testexercise.models.User;
import com.vgoryashko.testexercise.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements SQL DAO for User.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 1/30/18
 */
public class SQLUserDAO implements DAO<User>, UserRepository<User, Entity> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private Connection connection;

    private PreparedStatement preparedStatement;

    private ResultSet resultSet;

    public SQLUserDAO(Connection connection){
        this.connection = connection;
    }

    public long exists(User user) {

        long result = 0;

        try {

            this.preparedStatement = this.connection.prepareStatement(
                    "SELECT id FROM users WHERE public.users.login=? OR public.users.password=?");
            this.preparedStatement.setString(1, user.getLogin());
            this.preparedStatement.setString(2, user.getPassword());
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
            if (this.connection != null) {
                try {
                    this.connection.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        return result;
    }

    @Override
    public long create(User user) {

        long result = 0;

        try {
            if (this.exists(user) > 0) {
                this.preparedStatement = this.connection.prepareStatement("INSERT INTO users(name, login, password) values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                this.preparedStatement.setString(1, user.getName());
                this.preparedStatement.setString(2, user.getLogin());
                this.preparedStatement.setString(3, user.getPassword());
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
            if (this.connection != null) {
                try {
                    this.connection.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        return result;
    }

    @Override
    public User read(long id) {

        User user = null;

        try {

            this.preparedStatement = this.connection.prepareStatement("SELECT * FROM users WHERE id=?");
            this.preparedStatement.setLong(1, id);
            this.resultSet = this.preparedStatement.executeQuery();

            if (this.resultSet.next()) {
                user = new User();
                user.setId(this.resultSet.getLong(1));
                user.setName(this.resultSet.getString(2));
                user.setLogin(this.resultSet.getString(3));
                user.setPassword(this.resultSet.getString(4));
                user.setRole(this.resultSet.getLong(5));
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

        return user;
    }

    @Override
    public List<User> readAll() {

        List<User> result = new ArrayList<>();

        try {

            this.preparedStatement = this.connection.prepareStatement("SELECT * FROM users");
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {

                User user = new User();
                user.setId(this.resultSet.getLong(1));
                user.setName(this.resultSet.getString(2));
                user.setLogin(this.resultSet.getString(3));
                user.setPassword(this.resultSet.getString(4));
                user.setRole(this.resultSet.getLong(5));
                result.add(user);
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

        return result;

    }

    @Override
    public boolean update(User user, long id) {

        boolean result = false;

        try {

            this.preparedStatement = this.connection.prepareStatement("UPDATE users SET name = ?, login = ?, dao.public.users.password = ? WHERE id=?");
            this.preparedStatement.setString(1, user.getName());
            this.preparedStatement.setString(2, user.getLogin());
            this.preparedStatement.setString(3, user.getPassword());
            this.preparedStatement.setLong(4, id);
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
            if (this.connection != null) {
                try {
                    this.connection.close();
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

            this.preparedStatement = this.connection.prepareStatement("DELETE FROM users WHERE id=?");
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
            if (this.connection != null) {
                try {
                    this.connection.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return result;
    }

    @Override
    public boolean add(User user) {

        boolean result = false;

        long userId = 0;
        long addressId = 0;
        long roleId = 0;

        try {

            this.preparedStatement = this.connection.prepareStatement("BEGIN ");
            this.preparedStatement.execute();

            this.preparedStatement = this.connection.prepareStatement("INSERT INTO addresses(address) values(?)", Statement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setLong(1, user.getAddress());
            this.preparedStatement.executeUpdate();

            this.resultSet = this.preparedStatement.getGeneratedKeys();

            if (this.resultSet.next()) {
                addressId = this.resultSet.getLong(1);
            }

            this.preparedStatement = this.connection.prepareStatement("SELECT id FROM roles WHERE role=?;");
            this.preparedStatement.setLong(1, user.getRole());
            this.resultSet = this.preparedStatement.executeQuery();

            if (this.resultSet.next()) {
                roleId = this.resultSet.getLong(1);
            }

            this.preparedStatement = this.connection.prepareStatement("INSERT INTO users(name, login, password, role, address) values(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, user.getName());
            this.preparedStatement.setString(2, user.getLogin());
            this.preparedStatement.setString(3, user.getPassword());
            this.preparedStatement.setLong(4, roleId);
            this.preparedStatement.setLong(5, addressId);
            this.preparedStatement.executeUpdate();
            this.resultSet = this.preparedStatement.getGeneratedKeys();

            if (this.resultSet.next()) {
                userId = this.resultSet.getLong(1);
            }

            for (Long music : user.getMusics()) {

                this.preparedStatement = this.connection.prepareStatement("INSERT INTO users_music(user_id, genre_id) values(?, ?)");
                this.preparedStatement.setLong(1, userId);
                this.preparedStatement.setLong(2, music);
                this.preparedStatement.executeUpdate();
            }

            this.preparedStatement = this.connection.prepareStatement("COMMIT ");
            this.preparedStatement.execute();

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
            if (this.connection != null) {
                try {
                    this.connection.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        return result;
    }

    @Override
    public User find(String criteria) {
        return null;
    }

    @Override
    public List<Entity> get(User user) {
        return null;
    }

    public PreparedStatement getPreparedStatement() {
        return this.preparedStatement;
    }

    public void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    public ResultSet getResultSet() {
        return this.resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

}
