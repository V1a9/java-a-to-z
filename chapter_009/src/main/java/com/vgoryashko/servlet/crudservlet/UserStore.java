package com.vgoryashko.servlet.crudservlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Class that implements interactions with the DataBase which stores Users.
 *
 * @author Vlad Goryashko
 * @version 0.11
 * @since 12/18/17
 */
public class UserStore {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private String propertiesFile = "app.properties";

    private final static UserStore INSTANCE = new UserStore();

    private DataSource dataSource;

    private PreparedStatement preparedStatement;

    private ResultSet resultSet;

    private UserStore() { }

    public static synchronized UserStore getInstance() {
        return INSTANCE;
    }

    public DataSource setupPool() throws SQLException {

        Properties properties = new Properties();
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(this.propertiesFile);

        try {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        PoolProperties p = new PoolProperties();
        p.setUrl(properties.getProperty("url"));
        p.setDriverClassName("org.postgresql.Driver");
        p.setUsername(properties.getProperty("user"));
        p.setPassword(properties.getProperty("password"));
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(100);
        p.setInitialSize(10);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
                        + "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        this.dataSource = new DataSource();
        this.dataSource.setPoolProperties(p);

        return this.dataSource;

    }

    public void init() {

        Connection connection = null;

        try {
            connection = this.dataSource.getConnection();
            this.preparedStatement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS users("
                            + "id SERIAL PRIMARY KEY,"
                            + "USER_NAME VARCHAR (255),"
                            + "USER_ROLE VARCHAR (255),"
                            + "USER_LOGIN VARCHAR (255),"
                            + "PASSWORD VARCHAR (255),"
                            + "eMAIL VARCHAR (255),"
                            + "COUNTRY VARCHAR (255),"
                            + "CITY VARCHAR (255),"
                            + "CREATE_DATE VARCHAR (255));"
            );

            this.preparedStatement.execute();

            this.create(new User("Admin", "Admin", "root", "root", "admin@gmail.com", "EMPTY", "EMPTY", "EMPTY"));

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
    }

    public boolean isValid(String login, String password) {

        boolean isValid = false;
        Connection connection = null;

        try {
            connection = this.dataSource.getConnection();
            this.preparedStatement = connection.prepareStatement("SELECT id FROM users WHERE (user_login=? AND password=?)");
            this.preparedStatement.setString(1, login);
            this.preparedStatement.setString(2, password);
            this.resultSet = this.preparedStatement.executeQuery();

            if (this.resultSet.next()) {
                isValid = true;
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        return isValid;

    }

    public boolean create(User user) {

        Connection connection = null;
        boolean created = false;
        
        try {
            connection = this.dataSource.getConnection();
            if (!this.exists(user.getEmail())) {
                
                this.preparedStatement = connection.prepareStatement("INSERT INTO users(user_name, user_role, user_login, password, email, country, city, create_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
                this.preparedStatement.setString(1, user.getName());
                this.preparedStatement.setString(2, user.getRole());
                this.preparedStatement.setString(3, user.getLogin());
                this.preparedStatement.setString(4, user.getPassword());
                this.preparedStatement.setString(5, user.getEmail());
                this.preparedStatement.setString(6, user.getCountry());
                this.preparedStatement.setString(7, user.getCity());
                this.preparedStatement.setString(8, user.getCreateDate());
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        return created;
    }

    public boolean exists(String userEmail) {

        boolean exists = false;
        Connection connection = null;
        try {
            connection = this.dataSource.getConnection();
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        return exists;

    }

    public User read(String identifier) {

        User user = null;
        Connection connection = null;
        try {
            connection = this.dataSource.getConnection();
            this.preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email=? OR user_login=?");
            this.preparedStatement.setString(1, identifier);
            this.preparedStatement.setString(2, identifier);
            this.resultSet = this.preparedStatement.executeQuery();

            if (this.resultSet.next()) {

                user = new User();
                user.setName(resultSet.getString(2));
                user.setRole(resultSet.getString(3));
                user.setLogin(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setEmail(resultSet.getString(6));
                user.setCountry(resultSet.getString(7));
                user.setCity(resultSet.getString(8));
                user.setCreateDate(resultSet.getString(9));
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        return user;
    }

    public boolean delete(String userEmail) {

        boolean deleted = false;
        Connection connection = null;
        try {
            connection = this.dataSource.getConnection();
            if (this.exists(userEmail)) {
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        return deleted;
    }

    public ArrayList<User> getAll() {

        ArrayList<User> users = new ArrayList<>();
        User user;
        Connection connection = null;

        try {
            connection = this.dataSource.getConnection();
            this.preparedStatement = connection.prepareStatement("SELECT * FROM users");
            this.resultSet = this.preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = new User();
                user.setName(resultSet.getString(2));
                user.setRole(resultSet.getString(3));
                user.setLogin(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setEmail(resultSet.getString(6));
                user.setCountry(resultSet.getString(7));
                user.setCity(resultSet.getString(8));
                user.setCreateDate(resultSet.getString(9));
                users.add(user);
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        return users;

    }

    public boolean update(User user) {

        boolean updated = false;

        Connection connection = null;

        try {
            connection = this.dataSource.getConnection();
            if (exists(user.getEmail())) {

                this.preparedStatement = connection.prepareStatement("BEGIN");
                this.preparedStatement.execute();

                this.preparedStatement = connection.prepareStatement("UPDATE users SET user_name=? WHERE email=? ");
                this.preparedStatement.setString(1, user.getName());
                this.preparedStatement.setString(2, user.getEmail());
                this.preparedStatement.executeUpdate();

                this.preparedStatement = connection.prepareStatement("UPDATE users SET user_role=? WHERE email=? ");
                this.preparedStatement.setString(1, user.getRole());
                this.preparedStatement.setString(2, user.getEmail());
                this.preparedStatement.executeUpdate();

                this.preparedStatement = connection.prepareStatement("UPDATE users SET user_login=? WHERE email=? ");
                this.preparedStatement.setString(1, user.getLogin());
                this.preparedStatement.setString(2, user.getEmail());
                this.preparedStatement.executeUpdate();

                this.preparedStatement = connection.prepareStatement("UPDATE users SET password=? WHERE email=? ");
                this.preparedStatement.setString(1, user.getPassword());
                this.preparedStatement.setString(2, user.getEmail());
                this.preparedStatement.executeUpdate();

                this.preparedStatement = connection.prepareStatement("UPDATE users SET country=? WHERE email=? ");
                this.preparedStatement.setString(1, user.getCountry());
                this.preparedStatement.setString(2, user.getEmail());
                this.preparedStatement.executeUpdate();

                this.preparedStatement = connection.prepareStatement("UPDATE users SET city=? WHERE email=? ");
                this.preparedStatement.setString(1, user.getCity());
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        return updated;

    }
}