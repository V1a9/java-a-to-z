package com.vgoryashko.spring.carsstore.dao;

import com.vgoryashko.spring.carsstore.models.users.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LoggerFactory.class, UserDAO.class})
@PowerMockIgnore({"javax.management.*"})

/**
 * Class that tests User DAO.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/3/18
 */
public class UserDAOTest {

    private UserDAO userDAO;

    @Mock
    private DAOHelper<User> daoHelperMock;

    @Mock
    private Session sessionMock;

    @Mock
    private Transaction transactionMock;

    @Mock
    private Query queryMock;

    private User user;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(LoggerFactory.class);
        whenNew(DAOHelper.class).withAnyArguments().thenReturn(daoHelperMock);
        when(sessionMock.beginTransaction()).thenReturn(transactionMock);
        when(sessionMock.createQuery("FROM User")).thenReturn(queryMock);
        userDAO = new UserDAO(sessionMock);
        user = new User();
        user.setName("User");
        user.setLogin("user");
        user.setPassword("user");
    }

    @Test
    public void whenCreateInvokedThenNewUserCreateInvoked() throws Exception {
        when(sessionMock.createQuery("SELECT id FROM User where login=:login")).thenReturn(queryMock);
        when(queryMock.list()).thenReturn(new ArrayList<User>());
        userDAO.create(user);
        verify(daoHelperMock, times(1)).create(user);
    }

    @Test
    public void whenReadInvokedThenUserGetInvoked() throws Exception {
        userDAO.read(1L);
        verify(sessionMock, times(1)).get(User.class, 1L);
    }

    @Test
    public void whenReadAllInvokedThenQueryListInvoked() throws Exception {
        userDAO.readAll();
        verify(queryMock, times(1)).list();
    }

    @Test
    public void whenUpdateInvokedThenUserUpdateInvoked() throws Exception {
        userDAO.update(user);
        verify(daoHelperMock, times(1)).update(user);
    }

    @Test
    public void whenDeleteInvokedThenUserDeleteInvoked() throws Exception {
        userDAO.delete(user);
        verify(daoHelperMock, times(1)).delete(user);
    }
}