package com.vgoryashko.testexercise.dao;

import com.vgoryashko.testexercise.models.Music;
import com.vgoryashko.testexercise.models.User;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Class that test DAO class.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 1/15/18
 */
public class SQLUserDAOTest {

    private DAOManager daoManager;

    @Before
    public void setUp() throws Exception {

        daoManager = DAOManager.getInstance();

    }

    @Test
    public void testConnection() throws SQLException {

        User user = (User) daoManager.DAOFactory(DAOManager.TABLES.USERS).read(1);
//        userDAO.create(new User("Tom", "tom", "tompass"));
        assertEquals("Tom", user.getName());
        List<User> users = daoManager.DAOFactory(DAOManager.TABLES.USERS).readAll();
        for (User u : users) {
            assertEquals(u.getLogin(), "tom");
        }

    }

//    @Test
//    public void createTest() {
//
//        User user = new User("Ken", "ken", "ken");
//        assertFalse(SQLUserDAO.create(user));
//
//        Music music = new Music("RAP");
//        assertTrue(SQLUserDAO.create(music));
//
//    }
//
//    @Test
//    public void existsTest() {
//        User user = new User("Tom", "tom", "pass");
//        assertTrue(SQLUserDAO.exists(user));
//
//        Music music = new Music("ROCK");
//        assertTrue(SQLUserDAO.exists(music));
//    }
//
//    @Test
//    public void readTest() {
//        User user = new User("Tom", "tom", "pass");
//        assertTrue(user.equals(SQLUserDAO.read(new User(), 1)));
//    }
//
//    @Test
//    public void readAllTest() {
//
//        List users = SQLUserDAO.readAll("users");
//        System.out.println("Pause");
//
//    }
//
//    @Test
//    public void updateTest() {
//        SQLUserDAO.update(new Music("HEAVY METAL"), 4);
//    }
//
//    @Test
//    public void deleteTest() {
//        Music music = new Music("RAP");
//        assertTrue(SQLUserDAO.create(music));
////        assertTrue(SQLUserDAO.delete("musics", 5));
//    }
}