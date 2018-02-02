package com.vgoryashko.testexercise.dao;

import com.vgoryashko.testexercise.models.Music;
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
 * Class that implements DAO for Music.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 1/30/18
 */
public class SQLMusicDAO implements DAO<Music> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private PreparedStatement preparedStatement;

    private DataSource dataSource;

    private ResultSet resultSet;

    public SQLMusicDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public long exists(Music music) {
        long result = 0;
        Connection connection = null;
        try {
            connection = this.dataSource.getConnection();
            this.preparedStatement = connection.prepareStatement(
                    "SELECT id FROM musics WHERE genre=?");
            this.preparedStatement.setString(1, music.getMusicGenre());
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
    public long create(Music music) {
        long result = 0;
        Connection connection = null;
        try {
            connection = this.dataSource.getConnection();
            if (this.exists(music) == 0) {
                this.preparedStatement = connection.prepareStatement("INSERT INTO musics(genre) values(?)", Statement.RETURN_GENERATED_KEYS);
                this.preparedStatement.setString(1, music.getMusicGenre());
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
    public Music read(long id) {
        Music music = null;
        Connection connection = null;
        try {
            connection = this.dataSource.getConnection();
            this.preparedStatement = connection.prepareStatement("SELECT * FROM musics WHERE id=?");
            this.preparedStatement.setLong(1, id);
            this.resultSet = this.preparedStatement.executeQuery();

            if (this.resultSet.next()) {
                music = new Music();
                music.setId(this.resultSet.getLong(1));
                music.setMusicGenre(this.resultSet.getString(2));
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

        return music;
    }

    @Override
    public List<Music> readAll() {

        List<Music> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = this.dataSource.getConnection();
            result = new ArrayList<>();
            this.preparedStatement = connection.prepareStatement("SELECT * FROM musics");
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {

                Music music = new Music();
                music.setId(this.resultSet.getLong(1));
                music.setMusicGenre(this.resultSet.getString(2));
                result.add(music);
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
    public boolean update(Music music, long id) {
        boolean result = false;
        Connection connection = null;
        try {
            connection = this.dataSource.getConnection();
            this.preparedStatement = connection.prepareStatement("UPDATE musics SET genre=? WHERE id=?");
            this.preparedStatement.setString(1, music.getMusicGenre());
            this.preparedStatement.setLong(2, id);
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
            this.preparedStatement = connection.prepareStatement("DELETE FROM musics WHERE id=?");
            this.preparedStatement.setLong(1, id);
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
