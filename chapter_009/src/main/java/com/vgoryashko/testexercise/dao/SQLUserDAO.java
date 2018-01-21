package com.vgoryashko.testexercise.dao;

import com.vgoryashko.testexercise.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements SQL DAO for User.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 1/21/18
 */
public class SQLUserDAO implements DAO<User> {


    private final Logger logger = LogManager.getLogger(this.getClass());

    private Connection connection;

    private PreparedStatement preparedStatement;

    private ResultSet resultSet;

    public SQLUserDAO(Connection connection){
        this.connection = connection;
    }

//    public <T> boolean exists(T t) {
//
//        boolean result = false;
//        Connection this.connection = null;
//
//        try {
//
//            this.connection = this.dataSource.getConnection();
//
//            if (t instanceof User) {
//                this.preparedStatement = this.connection.prepareStatement("SELECT id FROM users WHERE login=?");
//                this.preparedStatement.setString(1, ((User) t).getLogin());
//            } else if (t instanceof Address) {
//                this.preparedStatement = this.connection.prepareStatement("SELECT id FROM addresses WHERE country=? AND city=? AND street=? AND apartment=?");
//                this.preparedStatement.setString(1, ((Address) t).getCountry());
//                this.preparedStatement.setString(2, ((Address) t).getCity());
//                this.preparedStatement.setString(3, ((Address) t).getStreet());
//                this.preparedStatement.setString(4, ((Address) t).getApartment());
//            } else if (t instanceof Music) {
//                this.preparedStatement = this.connection.prepareStatement("SELECT id FROM musics WHERE genre=?");
//                this.preparedStatement.setString(1, ((Music) t).getMusicGenre());
//            } else {
//                this.preparedStatement = this.connection.prepareStatement("SELECT id FROM roles WHERE role=?");
//                this.preparedStatement.setString(1, ((Role) t).getRoleName());
//            }
//
//            this.resultSet = this.preparedStatement.executeQuery();
//            if (this.resultSet.next()) {
//                result = true;
//            }
//
//        } catch (SQLException e) {
//            logger.error(e.getMessage(), e);
//        } finally {
//            if (this.preparedStatement != null) {
//                try {
//                    this.preparedStatement.close();
//                } catch (SQLException e) {
//                    logger.error(e.getMessage(), e);
//                }
//            }
//            if (this.connection != null) {
//                try {
//                    this.connection.close();
//                } catch (SQLException e) {
//                    logger.error(e.getMessage(), e);
//                }
//            }
//        }
//
//        return result;
//    }

    @Override
    public boolean create(User user) {

        boolean result = false;

        try {

            this.preparedStatement = this.connection.prepareStatement("INSERT INTO users(name, login, password) values(?, ?, ?)");
            this.preparedStatement.setString(1, user.getName());
            this.preparedStatement.setString(2, user.getLogin());
            this.preparedStatement.setString(3, user.getPassword());
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
