package com.vgoryashko.servlet.crudservlet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserStore.class)

/**
 * Class that tests Logout servlet.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 12/14/17
 */
public class LogoutTest  {

    @Mock
    private HttpServletRequest requestMock;

    @Mock
    private HttpServletResponse responseMock;

    @Mock
    private HttpSession sessionMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenDoGetInvokedThenRequestRedirectedToRootContext() throws Exception {
        Logout logout = new Logout();
        when(requestMock.getSession()).thenReturn(sessionMock);
        logout.doGet(requestMock, responseMock);

        verify(sessionMock, atMost(1)).invalidate();
        verify(responseMock, atMost(1)).sendRedirect(anyString());
    }

}