package com.vgoryashko.testexercise.dao;

import com.vgoryashko.testexercise.models.Address;
import com.vgoryashko.testexercise.models.Entity;
import com.vgoryashko.testexercise.models.Music;
import com.vgoryashko.testexercise.models.Role;
import com.vgoryashko.testexercise.models.User;
import com.vgoryashko.testexercise.repositories.UserRepository;
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
 * Class that implements SQL DAO for User.
 *
 * @author Vlad Goryashko
 * @version 0.6
 * @since 2/16/18
 */
public class SQLUserDAO implements DAO<User>, UserRepository<Entity> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private DataSource dataSource;

    private PreparedStatement preparedStatement;

    private ResultSet resultSet;

    public SQLUserDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public long exists(User user) {

        long result = 0;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            this.preparedStatement = connection.prepareStatement(
                    "SELECT id FROM users WHERE public.users.login=?");
            this.preparedStatement.setString(1, user.getLogin());
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

    public long validateUser(User user) {

        long result = 0;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            this.preparedStatement = connection.prepareStatement(
                    "SELECT id FROM users WHERE public.users.login=? AND public.users.password=?");
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

    /**
     * Method that creates an User and returns id of new created user or id of existing.
     * @param user to be created
     * @return id
     */
    @Override
    public long create(User user) {

        long result = 0;
        long existingUserId;

        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            existingUserId = this.exists(user);
            if (existingUserId == 0) {
                this.preparedStatement = connection.prepareStatement("INSERT INTO entities.public.users(name, login, password, role, address) values(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                this.preparedStatement.setString(1, user.getName());
                this.preparedStatement.setString(2, user.getLogin());
                this.preparedStatement.setString(3, user.getPassword());
                this.preparedStatement.setLong(4, user.getRole());
                this.preparedStatement.setLong(5, user.getAddress());
                this.preparedStatement.executeUpdate();
                this.resultSet = this.preparedStatement.getGeneratedKeys();
                if (this.resultSet.next()) {
                    result = this.resultSet.getLong(1);
                }
            } else {
                result = existingUserId;
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

    private User constructUser(ResultSet resultSet) {
        User user = new User();
        try {
            user.setId(resultSet.getLong(1));
            user.setName(resultSet.getString(2));
            user.setLogin(resultSet.getString(3));
            user.setPassword(resultSet.getString(4));
            user.setRole(resultSet.getLong(5));
            user.setAddress(resultSet.getLong(6));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User read(long id) {

        User user = null;

        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            this.preparedStatement = connection.prepareStatement("SELECT * FROM entities.public.users WHERE id=?");
            this.preparedStatement.setLong(1, id);
            this.resultSet = this.preparedStatement.executeQuery();

            if (this.resultSet.next()) {
                user = this.constructUser(this.resultSet);
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

        return user;
    }

    @Override
    public List<User> readAll() {

        List<User> result = new ArrayList<>();

        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            this.preparedStatement = connection.prepareStatement("SELECT * FROM entities.public.users");
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {

                User user = this.constructUser(this.resultSet);
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
    public boolean update(User user, long id) {

        boolean result = false;

        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            this.preparedStatement = connection.prepareStatement("UPDATE entities.public.users SET name = ?, login = ?, password = ?, role = ? WHERE id=?");
            this.preparedStatement.setString(1, user.getName());
            this.preparedStatement.setString(2, user.getLogin());
            this.preparedStatement.setString(3, user.getPassword());
            this.preparedStatement.setLong(4, user.getRole());
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

        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            this.preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id=?");
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

    @Override
    public boolean add(List<Entity> entities) {

        boolean result = false;

        Connection connection = null;

        try {

            Address address = (Address) entities.get(1);
            long addressId = ((SQLAddressDAO) DAOManager.getInstance().DAOFactory(DAOManager.TABLES.ADDRESSES)).create(address);

            Role role = (Role) entities.get(2);
            long roleId = ((SQLRoleDAO) DAOManager.getInstance().DAOFactory(DAOManager.TABLES.ROLES)).exists(role);

            User user = (User) entities.get(0);
            if (this.exists(user) == 0) {
                user.setAddress(addressId);
                user.setRole(roleId);
                long newUserId = this.create(user);

                List<Long> musicIds = new ArrayList<>();

                connection = this.dataSource.getConnection();
                this.preparedStatement = connection.prepareStatement("BEGIN ");
                this.preparedStatement.execute();

                for (int i = 3; i < entities.size(); i++) {
                    this.preparedStatement = connection.prepareStatement("SELECT id FROM musics WHERE music=?");
                    this.preparedStatement.setString(1, ((Music) entities.get(i)).getMusicGenre());
                    this.resultSet = this.preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        musicIds.add(this.resultSet.getLong(1));
                    }
                }

                for (Long id : musicIds) {

                    this.preparedStatement = connection.prepareStatement("INSERT INTO users_music(\"user\", genre) VALUES (?, ?)");
                    this.preparedStatement.setLong(1, newUserId);
                    this.preparedStatement.setLong(2, id);
                    this.preparedStatement.executeUpdate();
                }

                this.preparedStatement = connection.prepareStatement("COMMIT ");
                this.preparedStatement.execute();

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
    public List<User> find(Entity entity) {
        List<User> users = new ArrayList<>();
        User user;
        long addressId;
        Connection connection = null;

        try {
            connection = this.dataSource.getConnection();
            if (entity instanceof Address) {
                this.preparedStatement = connection.prepareStatement("SELECT id FROM addresses WHERE address=?");
                this.preparedStatement.setString(1, ((Address) entity).getAddress());
                this.resultSet = this.preparedStatement.executeQuery();
                if (this.resultSet.next()) {
                    addressId = this.resultSet.getLong(1);

                    this.preparedStatement = connection.prepareStatement("SELECT * FROM entities.public.users WHERE address=?");
                    this.preparedStatement.setLong(1, addressId);
                    this.resultSet = this.preparedStatement.executeQuery();

                    while (this.resultSet.next()) {
                        user = this.constructUser(this.resultSet);
                        users.add(user);
                    }
                }
            } else if (entity instanceof Role) {
                long roleId = 0;
                this.preparedStatement = connection.prepareStatement("SELECT id FROM roles WHERE role=?");
                this.preparedStatement.setString(1, ((Role) entity).getRoleName().toUpperCase());
                this.resultSet = this.preparedStatement.executeQuery();
                if (this.resultSet.next()) {
                    roleId = this.resultSet.getLong(1);
                }
                this.preparedStatement = connection.prepareStatement("SELECT * FROM entities.public.users WHERE role=?");
                this.preparedStatement.setLong(1, roleId);
                this.resultSet = this.preparedStatement.executeQuery();

                while (this.resultSet.next()) {
                    user = this.constructUser(this.resultSet);
                    users.add(user);
                }
            } else if (entity instanceof Music) {
                long musicId = 0;
                this.preparedStatement = connection.prepareStatement("SELECT id FROM musics WHERE music=?");
                this.preparedStatement.setString(1, ((Music) entity).getMusicGenre());
                this.resultSet = this.preparedStatement.executeQuery();
                if (this.resultSet.next()) {
                    musicId = this.resultSet.getLong(1);
                }
                this.preparedStatement = connection.prepareStatement("SELECT \"user\" FROM users_music WHERE genre=?");
                this.preparedStatement.setLong(1, musicId);
                this.resultSet = this.preparedStatement.executeQuery();

                ArrayList<Long> usersIds = new ArrayList<>();
                while (this.resultSet.next()) {
                    usersIds.add(this.resultSet.getLong(1));
                }

                this.preparedStatement = connection.prepareStatement("BEGIN ");
                this.preparedStatement.execute();

                for (Long id : usersIds) {
                    this.preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id=?");
                    this.preparedStatement.setLong(1, id);
                    this.resultSet = this.preparedStatement.executeQuery();

                    if (this.resultSet.next()) {
                        user = this.constructUser(this.resultSet);
                        users.add(user);
                    }
                }

                this.preparedStatement = connection.prepareStatement("COMMIT ");
                this.preparedStatement.execute();
            }
        } catch (Exception e) {
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

    /**
     * Method that retrieves all linked entities for a given Entity.
     *
     * @param id of an User
     * @return List of Entities
     */
    @Override
    public List<Entity> get(long id) {
        List<Entity> entities = new ArrayList<>();

        try {

            User user = this.read(id);
            if (user != null) {
                entities.add(user);
                Address address = ((SQLAddressDAO) DAOManager.getInstance().DAOFactory(DAOManager.TABLES.ADDRESSES)).read(user.getAddress());
                entities.add(address);
                Role role = ((SQLRoleDAO) DAOManager.getInstance().DAOFactory(DAOManager.TABLES.ROLES)).read(user.getRole());
                entities.add(role);
                entities.addAll(((SQLMusicDAO) DAOManager.getInstance().DAOFactory(DAOManager.TABLES.MUSICS)).getUsersMusic(user.getId()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return entities;
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
