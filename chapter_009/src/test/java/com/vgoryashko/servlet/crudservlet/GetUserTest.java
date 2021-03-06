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

import static org.mockito.Mockito.*;
import static org.powermock.api.support.membermodification.MemberMatcher.constructor;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserStore.class)

/**
 * Class that tests GetUser servlet.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 12/14/17
 */
public class GetUserTest {

    @Mock
    private HttpServletRequest requestMock;

    @Mock
    private HttpServletResponse responseMock;

    @Mock
    private RequestDispatcher requestDispatcherMock;

    private GetUser getUserMock;

    private ArrayList<User> users;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        getUserMock = new GetUser();

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
        when(requestMock.getRequestDispatcher("/ustorage/WEB-INF/views/Get.jsp")).thenReturn(requestDispatcherMock);
        getUserMock.doGet(requestMock, responseMock);
        verify(requestDispatcherMock).forward(requestMock, responseMock);
    }

    @Test
    public void whenCreateUserDoGetInvokedThenRequestForwardedToNewJsp() throws Exception {
        when(requestMock.getParameter("email")).thenReturn("email");
        when(requestMock.getRequestDispatcher("/ustorage/WEB-INF/views/UsersView.jsp")).thenReturn(requestDispatcherMock);
        getUserMock.doPost(requestMock, responseMock);
        verify(requestDispatcherMock, atMost(1)).forward(requestMock, responseMock);
    }

}