package com.vgoryashko.spring.carsstore.dao;

import com.vgoryashko.spring.carsstore.models.users.User;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Class that implements integration testing of UserDAO.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/27/18
 */
public class UserDAOIntegrationTest {

    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setName("User");
        user.setLogin("user");
        user.setPassword("user");
        ((UserDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS)).create(user);
    }

    @Test
    public void whenCreateInvokedThenNewUserCreatedInDataBase() throws SQLException {
        User persistentUser = (User) DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS).read(1L);
        assertThat(user, is(persistentUser));
    }

    @Test
    public void whenReadAllInvokedThenListOfUsersReturned() throws SQLException {
        User user1 = new User();
        user1.setName("User1");
        user1.setLogin("user1");
        user1.setPassword("user1");

        ((UserDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS)).create(user1);

        List users = DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS).readAll();

        assertTrue(users.size() == 2);
        assertThat(user1, is(DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS).read(2L)));

    }

    @Test
    public void whenDeleteInvokedThenUserRemovedFromDB() throws SQLException {
        ((UserDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS)).delete(user);
        List users = DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS).readAll();

        assertTrue(users.size() == 0);
    }

    @Test
    public void whenUpdateInvokedThenUserUpdatedInTheDB() throws SQLException {
        user.setName("User1");
        ((UserDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS)).update(user);
        User user1 = (User) DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS).read(1);
        assertTrue(user1.getName().equals("User1"));
    }
}
