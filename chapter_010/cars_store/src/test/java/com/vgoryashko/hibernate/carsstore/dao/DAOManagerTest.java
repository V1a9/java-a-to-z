package com.vgoryashko.hibernate.carsstore.dao;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class that tests DAOManager class.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/2/18
 */
public class DAOManagerTest {

    private DAOManager daoManager;

    @Before
    public void setUp() throws Exception {
        daoManager = DAOManager.getInstanse();
    }

    @Test
    public void whenGetInstanseInvokedThanDAOManagerInstanceReturned() throws Exception {
        assertNotNull(daoManager);
        daoManager.closeSessionFactory();
    }

    @Test
    public void whenGetSessionFactoryInvokedThanFactoryReturned() throws Exception {
        assertNotNull(daoManager.getSessionFactory());
        daoManager.closeSessionFactory();
    }

    @Test
    public void closeSessionFactory() throws Exception {
        assertTrue(daoManager.daoFactory(DAOManager.TABLES.USERS) instanceof UserDAO);
    }

}