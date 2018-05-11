package com.vgoryashko.servlet.crudservlet;

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
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.powermock.api.support.membermodification.MemberMatcher.constructor;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserStore.class)

/**
 * Class that tests CreateUser servlet.
 *
 * @author Vlad Goryashko
 * @version 0.12
 * @since 12/01/18
 */
public class CreateUserTest {

    @Mock
    private HttpServletRequest requestMock;

    @Mock
    private HttpServletResponse responseMock;

    @Mock
    private RequestDispatcher requestDispatcherMock;

    private CreateUser createUser;

    private ArrayList<User> users;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        createUser = new CreateUser();

        users = new ArrayList<>(Arrays.asList(new User(
                "firstName secondName",
                "role",
                "login",
                "pass",
                "email@email.com",
                "country",
                "city",
                "01/01/18"))
        );

        suppress(constructor(UserStore.class));
        PowerMockito.mockStatic(UserStore.class);
        UserStore userStoreMock = mock(UserStore.class);
        when(UserStore.getInstance()).thenReturn(userStoreMock);
        when(UserStore.getInstance().getAll()).thenReturn(users);
    }

    @Test
    public void whenCreateUserDoGetInvokedThenRequestForwardedToUsersViewJsp() throws Exception {
        when(requestMock.getRequestDispatcher("/ustorage/WEB-INF/views/New.jsp")).thenReturn(requestDispatcherMock);
        createUser.doGet(requestMock, responseMock);
        verify(requestDispatcherMock).forward(requestMock, responseMock);
    }

    @Test
    public void whenCreateUserDoGetInvokedThenRequestForwardedToNewJsp() throws Exception {

        when(requestMock.getParameter("name")).thenReturn("firstName secondName");
        when(requestMock.getParameter("role")).thenReturn("role");
        when(requestMock.getParameter("login")).thenReturn("login");
        when(requestMock.getParameter("password")).thenReturn("password");
        when(requestMock.getParameter("email")).thenReturn("email@email.com");
        when(requestMock.getParameter("country")).thenReturn("country");
        when(requestMock.getParameter("city")).thenReturn("city");
        when(requestMock.getParameter("date")).thenReturn("01/01/18");

        when(requestMock.getRequestDispatcher("/ustorage/WEB-INF/views/UsersView.jsp")).thenReturn(requestDispatcherMock);
        when(requestMock.getRequestDispatcher("/ustorage/WEB-INF/views/New.jsp")).thenReturn(requestDispatcherMock);

        createUser.doPost(requestMock, responseMock);

        verify(requestDispatcherMock, atMost(1)).forward(requestMock, responseMock);

    }

}