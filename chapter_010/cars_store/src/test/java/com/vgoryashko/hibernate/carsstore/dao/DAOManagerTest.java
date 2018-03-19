package com.vgoryashko.hibernate.carsstore.dao;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Class that tests DAOManager class.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/2/18
 */
public class DAOManagerTest {

    private DAOManager daoManager;

    private SessionFactory sessionFactory;

    @Before
    public void setUp() throws Exception {
        daoManager = DAOManager.getInstance();
        sessionFactory = daoManager.getSessionFactory();
    }

    @Test
    public void whenGetInstanseInvokedThanDAOManagerInstanceReturned() throws Exception {
        assertNotNull(daoManager);
    }

    @Test
    public void whenGetSessionFactoryInvokedThanFactoryReturned() throws Exception {
        assertNotNull(sessionFactory);
    }

    @Test
    public void whenGetUserDAOInvokedThenInstanceOfUserDAOReturned() throws Exception {
        assertTrue(daoManager.daoFactory(DAOManager.TABLES.USERS) instanceof UserDAO);
        assertTrue(daoManager.closeSessionFactory());
    }
}