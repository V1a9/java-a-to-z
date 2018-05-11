package com.vgoryashko.testexercise.controllers;

import com.vgoryashko.servlet.crudservlet.UserStore;
import com.vgoryashko.testexercise.dao.DAOManager;
import com.vgoryashko.testexercise.dao.SQLUserDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.support.membermodification.MemberMatcher.constructor;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOManager.class)

/**
 * Class that tests AddUserController.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 2/16/18
 */
public class AddUserControllerTest {

    @Mock
    private HttpServletRequest requestMock;

    @Mock
    private HttpServletResponse responseMock;

    @Mock
    private RequestDispatcher requestDispatcherMock;

    private AddUserController addUserController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        addUserController = new AddUserController();

        suppress(constructor(DAOManager.class));
        PowerMockito.mockStatic(DAOManager.class);
        DAOManager daoManagerMock = mock(DAOManager.class);
        when(DAOManager.getInstance()).thenReturn(daoManagerMock);

    }

    @Test
    public void doGet() throws Exception {
        when(requestMock.getRequestDispatcher("/WEB-INF/views/AddUserView.jsp")).thenReturn(requestDispatcherMock);
        addUserController.doGet(requestMock, responseMock);
        verify(requestDispatcherMock).forward(requestMock, responseMock);
    }

    @Test
    public void doPost() throws Exception {

    }

}