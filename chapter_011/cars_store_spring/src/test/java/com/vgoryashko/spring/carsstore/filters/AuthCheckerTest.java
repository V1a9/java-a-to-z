package com.vgoryashko.spring.carsstore.filters;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Class that tests AuthChecker controller.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/26/18
 */
public class AuthCheckerTest {

    @Mock
    private FilterChain filterChain;

    @Mock
    private FilterConfig filterConfig;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    private AuthChecker authChecker;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        authChecker = new AuthChecker();
    }

    @Test
    public void whenDoFilterInvokedWithURILoginThenChainDoFilterInvoked() throws Exception {
        when(request.getRequestURI()).thenReturn("login");
        authChecker.doFilter(request, response, filterChain);
        verify(filterChain, atMost(1)).doFilter(request, response);
    }

    @Test
    public void whenDoFilterInvokedWithURIRegisterThenChainDoFilterInvoked() throws Exception {
        when(request.getRequestURI()).thenReturn("register");
        authChecker.doFilter(request, response, filterChain);
        verify(filterChain, atMost(1)).doFilter(request, response);
    }

    @Test
    public void whenDoFilterInvokedWithURIRootThenChainDoFilterInvoked() throws Exception {
        when(request.getRequestURI()).thenReturn("/");
        authChecker.doFilter(request, response, filterChain);
        verify(filterChain, atMost(1)).doFilter(request, response);
    }

    @Test
    public void whenRequestSentToAnyOtherURIAndSessionLoginAttributeNullThenRequestRedirectedToLoginPage() throws IOException, ServletException {
        when(request.getRequestURI()).thenReturn("advert");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("login")).thenReturn(null);
        authChecker.doFilter(request, response, filterChain);
        verify(response, atMost(1)).sendRedirect(anyString());
    }

    @Test
    public void whenRequestSentToAnyOtherURIAndSessionLoginAttributeNotNullThenChainDoFilterInvoked() throws IOException, ServletException {
        when(request.getRequestURI()).thenReturn("advert");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("login")).thenReturn("user");
        authChecker.doFilter(request, response, filterChain);
        verify(filterChain, atMost(1)).doFilter(request, response);
    }

    @Test
    public void initTest() throws ServletException {
        authChecker.init(filterConfig);
    }

    @Test
    public void destroyTest() {
        authChecker.destroy();
    }
}