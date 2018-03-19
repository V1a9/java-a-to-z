package com.vgoryashko.hibernate.carsstore.dao;

import com.vgoryashko.hibernate.carsstore.models.users.User;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest(LoggerFactory.class)

/**
 * Class that tests DAOHelper class.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/3/18
 */
public class DAOHelperTest {

    @Mock
    private LoggerFactory loggerFactoryMock;

    @Mock
    private Session sessionMock;

    private DAOHelper<Object> daoHelper;

    private User user;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(LoggerFactory.class);
        daoHelper = new DAOHelper<>(sessionMock);
        user = new User();
    }

    @Test
    public void whenCreateInvokedThenSaveMethodInvokedOnObject() throws Exception {
        daoHelper.create(user);
        verify(sessionMock, times(1)).save(user);
        verify(sessionMock, times(1)).close();
    }

    @Test
    public void whenUpdateInvokedThenUpdateMethodInvokedOnObject() throws Exception {
        daoHelper.update(user);
        verify(sessionMock, times(1)).update(user);
        verify(sessionMock, times(1)).close();
    }

    @Test
    public void whenDeleteInvokedThenDeleteMethodInvokedOnObject() throws Exception {
        daoHelper.delete(user);
        verify(sessionMock, times(1)).delete(user);
        verify(sessionMock, times(1)).close();
    }

}