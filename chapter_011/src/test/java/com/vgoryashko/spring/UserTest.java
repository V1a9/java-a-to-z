package com.vgoryashko.spring;

import com.vgoryashko.spring.dao.JdbcStorage;
import com.vgoryashko.spring.dao.MemoryStorage;
import com.vgoryashko.spring.dao.UserStorage;
import com.vgoryashko.spring.models.User;
import com.vgoryashko.spring.start.ImportUser;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Class that tests UserStorage.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 4/09/18
 */
public class UserTest {

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

    @Test
    public void testConnectionToTheDataBase() throws SQLException {
        JdbcStorage storage = JdbcStorage.getInstance();
        Connection connection = storage.setConnection();
        assertTrue(!connection.isClosed());
        storage.closeConnection(connection);
        assertTrue(connection.isClosed());
    }

    @Test
    public void whenNewUserAddedIntoMemoryItExists() {
        User user = new User();
        user.setName("User");
        context = new ClassPathXmlApplicationContext("spring-context-test.xml");
        userStorage = context.getBean(UserStorage.class);
        new ImportUser(userStorage).addUser(user);
        List users = ((MemoryStorage) userStorage.getStorage()).getUsers();
        assertTrue(((User) users.get(0)).getName().equals("User"));
    }
}