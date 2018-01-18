package com.vgoryashko.testexercise.dao;

import com.vgoryashko.testexercise.models.User;
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
 * Class that defines Data Access Object for the application.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 1/15/18
 */
public class Dao {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private String properties = "dao.properties";

    private final static Dao INSTANCE = new Dao();

    private DataSource dataSource;

    private PreparedStatement preparedStatement;

    private ResultSet resultSet;

    private Dao(){

    }

    public static synchronized Dao getInstance () {
        return INSTANCE;
    }

    public DataSource setupPool() throws SQLException {

        Properties properties = new Properties();
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(this.properties);

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
                    "CREATE TABLE IF NOT EXISTS addresses("
                            + " id SERIAL PRIMARY KEY ,"
                            + "  address VARCHAR(255)[5]);"
            );

            this.preparedStatement.execute();

            this.preparedStatement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS roles("
                            + " id SERIAL PRIMARY KEY ,"
                            + "  role VARCHAR(255));"
            );

            this.preparedStatement.execute();

            this.preparedStatement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS musics("
                            + " id SERIAL PRIMARY KEY ,"
                            + "  ganre VARCHAR(255));"
            );

            this.preparedStatement.execute();

            this.preparedStatement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS users("
                            + "  id SERIAL PRIMARY KEY ,"
                            + "  name VARCHAR(255),"
                            + "  role INTEGER,"
                            + "  address INTEGER,"
                            + "  login VARCHAR(255),"
                            + "  password VARCHAR(255),"
                            + "  FOREIGN KEY (role) REFERENCES roles(id),"
                            + "  FOREIGN KEY (address) REFERENCES addresses(id));"
            );

            this.preparedStatement.execute();

            this.preparedStatement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS users_music("
                            + "  \"user\" INTEGER,"
                            + "  ganre INTEGER,"
                            + "  FOREIGN KEY (\"user\") REFERENCES users(id),"
                            + "  FOREIGN KEY (ganre) REFERENCES musics(id));"
            );

            this.preparedStatement.execute();

            this.preparedStatement = connection.prepareStatement("SELECT * FROM roles");
            this.resultSet = this.preparedStatement.executeQuery();

            if (!this.resultSet.next()) {
                this.preparedStatement = connection.prepareStatement("BEGIN ");
                this.preparedStatement.execute();
                this.preparedStatement = connection.prepareStatement(
                        "INSERT INTO roles(role) VALUES ('USER');"
                                + "INSERT INTO roles(role) VALUES ('MODERATOR');"
                                + "INSERT INTO roles(role) VALUES ('ADMIN');"
                );

                this.preparedStatement.executeUpdate();
            }

            this.preparedStatement = connection.prepareStatement("SELECT * FROM musics");
            this.resultSet = this.preparedStatement.executeQuery();

            if (!this.resultSet.next()) {
                this.preparedStatement = connection.prepareStatement("BEGIN ");
                this.preparedStatement.execute();
                this.preparedStatement = connection.prepareStatement(
                        "INSERT INTO musics(ganre) VALUES ('ROCK');"
                                + "INSERT INTO musics(ganre) VALUES ('GRUNGE');"
                                + "INSERT INTO musics(ganre) VALUES ('METAL');"
                );

                this.preparedStatement.executeUpdate();
            }

            this.preparedStatement = connection.prepareStatement("COMMIT ");
            this.preparedStatement.execute();

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

    public <T> boolean create(T t) {
        //todo logic
        boolean result = true;

        if (t instanceof User) {

        }


        return false;
    }

    public String getProperties() {
        return this.properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public DataSource getDataSource() {
        return this.dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
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
