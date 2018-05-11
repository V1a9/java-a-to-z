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
 * Class that tests UserViewServlet.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 12/14/17
 */
public class UsersViewTest {

    @Mock
    private HttpServletRequest requestMock;

    @Mock
    private HttpServletResponse responseMock;

    @Mock
    private RequestDispatcher requestDispatcherMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenUsersViewDoGetInvokedThenRequestForwardedToJSP() throws Exception {

        ArrayList<User> users = new ArrayList<>(Arrays.asList(new User(
                "name",
                "role",
                "login",
                "pass",
                "email",
                "country",
                "city",
                "date"))
        );

        UsersView usersView = new UsersView();

        suppress(constructor(UserStore.class));
        PowerMockito.mockStatic(UserStore.class);
        UserStore userStoreMock = mock(UserStore.class);
        when(UserStore.getInstance()).thenReturn(userStoreMock);
        when(UserStore.getInstance().getAll()).thenReturn(users);
        when(requestMock.getRequestDispatcher("/ustorage/WEB-INF/views/UsersView.jsp")).thenReturn(requestDispatcherMock);

        usersView.doGet(requestMock, responseMock);

        verify(requestDispatcherMock, atMost(1)).forward(requestMock, responseMock);

    }
}