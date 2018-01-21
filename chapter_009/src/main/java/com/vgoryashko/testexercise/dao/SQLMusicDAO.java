package com.vgoryashko.testexercise.dao;

import com.vgoryashko.testexercise.models.Music;
import com.vgoryashko.testexercise.models.Music;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements DAO for Music.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 1/21/18
 */
public class SQLMusicDAO implements DAO<Music> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private Connection connection;

    private PreparedStatement preparedStatement;

    private ResultSet resultSet;

    public SQLMusicDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Music music) {
        boolean result = false;

        try {

            this.preparedStatement = connection.prepareStatement("INSERT INTO musics(genre) values(?)");
            this.preparedStatement.setString(1, music.getMusicGenre());
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
    public Music read(long id) {
        Music music = null;

        try {

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
            if (this.connection != null) {
                try {
                    this.connection.close();
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

        try {

            result = new ArrayList<>();
            this.preparedStatement = this.connection.prepareStatement("SELECT * FROM musics");
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

        try {

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

        try {

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
