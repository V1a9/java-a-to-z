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
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.powermock.api.support.membermodification.MemberMatcher.constructor;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserStore.class)

/**
 * Class that tests DeleteUser servlet.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 12/14/17
 */
public class DeleteUserTest {

    @Mock
    private HttpServletRequest requestMock;

    @Mock
    private HttpServletResponse responseMock;

    @Mock
    private RequestDispatcher requestDispatcherMock;

    @Mock
    private HttpSession sessionMock;

    @Mock
    private User userMock;

    private DeleteUser deleteUserMock;

    private ArrayList<User> users;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        deleteUserMock = new DeleteUser();

        users = new ArrayList<>(Arrays.asList(new User(
                "name",
                "role",
                "login",
                "pass",
                "email",
                "country",
                "city",
                "date"))
        );

        suppress(constructor(UserStore.class));
        PowerMockito.mockStatic(UserStore.class);
        UserStore userStoreMock = mock(UserStore.class);
        when(UserStore.getInstance()).thenReturn(userStoreMock);
        when(UserStore.getInstance().getAll()).thenReturn(users);
    }

    @Test
    public void whenDeleteUserDoGetInvokedThenRequestForwardedToUsersViewJsp() throws Exception {
        when(requestMock.getRequestDispatcher("/ustorage/WEB-INF/views/Delete.jsp")).thenReturn(requestDispatcherMock);
        deleteUserMock.doGet(requestMock, responseMock);
        verify(requestDispatcherMock).forward(requestMock, responseMock);
    }

    @Test
    public void whenDeleteUserDoPostInvokedThenRequestForwardedToUsersViewJsp() throws Exception {
        when(requestMock.getParameter("email")).thenReturn("email");
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("loggedInUser")).thenReturn(userMock);
        when(userMock.getRole()).thenReturn("User");
        when(requestMock.getRequestDispatcher("/ustorage/WEB-INF/views/UsersView.jsp")).thenReturn(requestDispatcherMock);
        deleteUserMock.doPost(requestMock, responseMock);
        verify(requestDispatcherMock, atMost(1)).forward(requestMock, responseMock);
    }
}