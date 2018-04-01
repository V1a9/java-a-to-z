package com.vgoryashko.spring;

import com.vgoryashko.spring.dao.JdbcStorage;
import com.vgoryashko.spring.dao.MemoryStorage;
import com.vgoryashko.spring.dao.Storage;
import com.vgoryashko.spring.dao.UserStorage;
import com.vgoryashko.spring.models.User;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Class that tests UserStorage.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/30/18
 */
public class UserTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserTest.class);

    private ApplicationContext context;

    private UserStorage userStorage;

    @Test
    public void whenTestContextUsedThenJDBCStorageInjected() throws Exception {
        context = new ClassPathXmlApplicationContext("spring-context-test.xml");
        userStorage = context.getBean(UserStorage.class);
        userStorage.add(new User());
        assertTrue(userStorage.getStorage().getClass().getName().equals(MemoryStorage.class.getName()));
    }

    @Test
    public void whenProductionContextUsedThenJDBCStorageInjected() {
        context = new ClassPathXmlApplicationContext("spring-context.xml");
        userStorage = context.getBean(UserStorage.class);
        userStorage.add(new User());
        assertTrue(userStorage.getStorage().getClass().getName().equals(JdbcStorage.class.getName()));
    }
}