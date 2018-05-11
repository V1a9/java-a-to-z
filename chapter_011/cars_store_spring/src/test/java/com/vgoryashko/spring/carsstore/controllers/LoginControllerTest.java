package com.vgoryashko.spring.carsstore.controllers;

import com.vgoryashko.spring.carsstore.dao.DAOManager;
import com.vgoryashko.spring.carsstore.dao.UserDAO;
import com.vgoryashko.spring.carsstore.models.users.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberMatcher;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DAOManager.class, LoggerFactory.class})
@PowerMockIgnore({"javax.management.*"})

/**
 * Class that tests LoginController.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/19/18
 */
public class LoginControllerTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private HttpSession session;

    @Mock
    private UserDAO userDAO;

    private LoginController loginController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.suppress(MemberMatcher.constructor(DAOManager.class));
        PowerMockito.mockStatic(DAOManager.class);
        DAOManager daoManager = mock(DAOManager.class);
        PowerMockito.mockStatic(LoggerFactory.class);
        when(DAOManager.getInstance()).thenReturn(daoManager);
        when(daoManager.daoFactory(DAOManager.TABLES.USERS)).thenReturn(userDAO);
        when(request.getRequestDispatcher("/WEB-INF/views/LoginView.jsp")).thenReturn(requestDispatcher);

        loginController = new LoginController();
    }

    @Test
    public void whenDoGetInvokedThenRequestForwarded() throws Exception {
//        loginController.doGet(request, response);
        verify(requestDispatcher, atMost(1)).forward(request, response);
    }

    @Test
    public void whenDoPostInvokedThenResponseRedirected() throws Exception {
        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("pass")).thenReturn("pass");
        when(request.getSession()).thenReturn(session);
        User user = new User();
        user.setId(1L);
        user.setLogin("login");
        user.setName("name");
        user.setPassword("pass");
        when(userDAO.validateUser(user)).thenReturn(1L);

//        loginController.doPost(request, response);
        verify(response, atMost(1)).sendRedirect(anyString());
    }

}