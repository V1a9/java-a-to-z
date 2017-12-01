package com.vgoryashko.servlet.crudservlet;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Class that tests implementation of the crudservlet application.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 12/01/17
 */
public class UserServletTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() throws SQLException {

        UserStore userStore = UserStore.getInstance();
        Connection connection = userStore.getConnection();
        assertTrue(!connection.isClosed());
        connection.close();
        assertTrue(connection.isClosed());
        
    }

    @Test
    public void testCreateDb() {
        UserStore userStore = UserStore.getInstance();
        userStore.init();
    }
}