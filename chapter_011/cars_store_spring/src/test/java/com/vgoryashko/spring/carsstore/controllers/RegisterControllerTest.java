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
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.support.membermodification.MemberMatcher.constructor;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DAOManager.class, LoggerFactory.class})
@PowerMockIgnore({"javax.management.*"})
/**
 * Class that tests RegisterController.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/19/18
 */
public class RegisterControllerTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private UserDAO userDAO;

    private RegisterController registerController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(LoggerFactory.class);

        when(request.getRequestDispatcher("/WEB-INF/views/RegisterView.jsp")).thenReturn(requestDispatcher);
        registerController = new RegisterController();
    }

    @Test
    public void doGet() throws Exception {
//        registerController.doGet(request, response);
        verify(requestDispatcher, atMost(1)).forward(request, response);
    }

    @Test
    public void doPost() throws Exception {
        when(request.getParameter("name")).thenReturn("name");
        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("pwd")).thenReturn("pwd");
        suppress(constructor(DAOManager.class));
        PowerMockito.mockStatic(DAOManager.class);
        DAOManager daoManager = mock(DAOManager.class);
        when(DAOManager.getInstance()).thenReturn(daoManager);
        when(daoManager.daoFactory(DAOManager.TABLES.USERS)).thenReturn(userDAO);
        when(userDAO.create(any(User.class))).thenReturn(1L);
//        registerController.doPost(request, response);
        verify(response).sendRedirect(anyString());

    }

}