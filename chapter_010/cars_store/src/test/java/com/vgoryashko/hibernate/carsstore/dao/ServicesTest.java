package com.vgoryashko.hibernate.carsstore.dao;

import com.vgoryashko.hibernate.carsstore.models.items.Item;
import com.vgoryashko.hibernate.carsstore.models.users.User;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Class that
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 2/26/18
 */
public class ServicesTest {

    @Test
    public void test() throws SQLException {

        SessionFactory sessionFactory = DAOManager.getInstanse().getSessionFactory();
//        User user = new User();
//        user.setName("Pit");
//        user.setLogin("pit");
//        user.setPassword("pit");
//        ((UserDAO) DAOManager.getInstanse().daoFactory(DAOManager.TABLES.USERS)).create(user);
        List<User> users = ((UserDAO) DAOManager.getInstanse().daoFactory(DAOManager.TABLES.USERS)).readAll();
//        List<Item> items = ((ItemDAO) DAOManager.getInstanse().daoFactory(DAOManager.TABLES.ITEMS)).readAll();
        DAOManager.getInstanse().closeSessionFactory();
        assertTrue(sessionFactory.isClosed());

    }

}
