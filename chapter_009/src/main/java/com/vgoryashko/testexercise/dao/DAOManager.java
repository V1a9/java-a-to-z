package com.vgoryashko.testexercise.dao;

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
import java.util.Properties;

/**
 * Class that implements connection pool for JDBC and DAO Fabric method for instantiation of DAOs.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 1/30/18
 */
public class DAOManager {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private final static DAOManager INSTANCE = new DAOManager();

    private String propertiesFile = "entities.properties";

    private DataSource dataSource;

    private Connection connection;

    public enum TABLES {USERS, ADDRESSES, ROLES, MUSICS}

    private DAOManager() {


    }

    public static synchronized DAOManager getInstance () {
        return INSTANCE;
    }

    {

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

        try {
            if (this.connection == null || this.connection.isClosed())
                this.connection = dataSource.getConnection();
        } catch(SQLException se) {
            logger.error(se.getMessage(), se);
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        
        try {
            
            preparedStatement = this.connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS addresses("
                            + " id SERIAL PRIMARY KEY ,"
                            + "  address VARCHAR(255));"
            );

            preparedStatement.execute();

            preparedStatement = this.connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS roles("
                            + " id SERIAL PRIMARY KEY ,"
                            + "  role VARCHAR(255));"
            );

            preparedStatement.execute();

            preparedStatement = this.connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS musics("
                            + " id SERIAL PRIMARY KEY ,"
                            + "  music VARCHAR(255));"
            );

            preparedStatement.execute();

            preparedStatement = this.connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS users("
                            + "  id SERIAL PRIMARY KEY ,"
                            + "  name VARCHAR(255),"
                            + "  login VARCHAR(255),"
                            + "  password VARCHAR(255),"
                            + "  role INTEGER,"
                            + "  address INTEGER,"
                            + "  FOREIGN KEY (role) REFERENCES roles(id),"
                            + "  FOREIGN KEY (address) REFERENCES addresses(id));"
            );

            preparedStatement.execute();

            preparedStatement = this.connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS users_music("
                            + "  user_id INTEGER,"
                            + "  music_id INTEGER,"
                            + "  FOREIGN KEY (user_id) REFERENCES users(id),"
                            + "  FOREIGN KEY (music_id) REFERENCES musics(id));"
            );

            preparedStatement.execute();

            preparedStatement = this.connection.prepareStatement("SELECT * FROM roles");
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                preparedStatement = this.connection.prepareStatement("BEGIN ");
                preparedStatement.execute();
                preparedStatement = this.connection.prepareStatement(
                        "INSERT INTO roles(role) VALUES ('ADMIN');"
                                + "INSERT INTO roles(role) VALUES ('MODERATOR');"
                                + "INSERT INTO roles(role) VALUES ('USER');"
                );

                preparedStatement.executeUpdate();
            }

            preparedStatement = this.connection.prepareStatement("SELECT * FROM musics");
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                preparedStatement = this.connection.prepareStatement("BEGIN ");
                preparedStatement.execute();
                preparedStatement = this.connection.prepareStatement(
                        "INSERT INTO musics(music) VALUES ('ROCK');"
                                + "INSERT INTO musics(music) VALUES ('GRUNGE');"
                                + "INSERT INTO musics(music) VALUES ('METAL');"
                );

                preparedStatement.executeUpdate();
            }

            preparedStatement = this.connection.prepareStatement("COMMIT ");
            preparedStatement.execute();

            preparedStatement = this.connection.prepareStatement("SELECT * FROM entities.public.users WHERE login=?");
            preparedStatement.setString(1, "admin");
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                preparedStatement = this.connection.prepareStatement("SELECT id FROM roles WHERE role=?");
                preparedStatement.setString(1, "ADMIN");
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    preparedStatement = this.connection.prepareStatement("INSERT INTO entities.public.users(name, login, password, role) VALUES ('admin', 'admin', 'admin', ?)");
                    preparedStatement.setLong(1, resultSet.getLong(1));
                    preparedStatement.executeUpdate();
                }
            }

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
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

    }

    public DAO DAOFactory(TABLES table) throws SQLException {

        switch (table) {
            case USERS: return new SQLUserDAO(this.dataSource);
            case ADDRESSES: return new SQLAddressDAO(this.dataSource);
            case ROLES: return new SQLRoleDAO(this.dataSource);
            case MUSICS: return new SQLMusicDAO(this.dataSource);
            default: throw new SQLException("Trying to link to an unexisting table.");
        }

    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
