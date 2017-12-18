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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.powermock.api.support.membermodification.MemberMatcher.constructor;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserStore.class)

/**
 * Class that tests Signin servlet.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 12/14/17
 */
public class SignInTest {

    @Mock
    private HttpServletRequest requestMock;

    @Mock
    private HttpServletResponse responseMock;

    @Mock
    private RequestDispatcher requestDispatcherMock;

    @Mock
    private HttpSession sessionMock;

    private SignIn signIn;

    private UserStore userStoreMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        signIn = new SignIn();
        suppress(constructor(UserStore.class));
        PowerMockito.mockStatic(UserStore.class);
        userStoreMock = mock(UserStore.class);
        when(UserStore.getInstance()).thenReturn(userStoreMock);
        when(requestMock.getRequestDispatcher("/WEB-INF/views/Login.jsp")).thenReturn(requestDispatcherMock);
    }

    @Test
    public void whenDoGetInvokedThenRequestForwarderToLoginJsp() throws Exception {

        signIn.doGet(requestMock, responseMock);
        verify(requestDispatcherMock, atMost(1)).forward(requestMock, responseMock);

    }

    @Test
    public void whenDoPostInvokedAndCredentialsValidThenRequsetRedirectedToRootContext() throws Exception {

        when(requestMock.getParameter("login")).thenReturn("login");
        when(requestMock.getParameter("password")).thenReturn("password");

        when(UserStore.getInstance().read("login")).thenReturn(new User(
                "name",
                "role",
                "login",
                "pass",
                "email",
                "country",
                "city",
                "date")
        );

        when(UserStore.getInstance().isValid("login", "password")).thenReturn(true);
        when(requestMock.getSession()).thenReturn(sessionMock);

        signIn.doPost(requestMock, responseMock);

        verify(responseMock, atMost(1)).sendRedirect(anyString());
    }

    @Test
    public void whenDoPostInvokedWithInvalidCredentialsThenDoGetInvoked() throws Exception {

        when(requestMock.getParameter("login")).thenReturn("login");
        when(requestMock.getParameter("password")).thenReturn("password");

        when(UserStore.getInstance().read("login")).thenReturn(new User(
                "name",
                "role",
                "login",
                "pass",
                "email",
                "country",
                "city",
                "date")
        );

        when(UserStore.getInstance().isValid("login", "password")).thenReturn(false);
        signIn.doPost(requestMock, responseMock);
        verify(requestDispatcherMock, atMost(1)).forward(requestMock, responseMock);
    }

}