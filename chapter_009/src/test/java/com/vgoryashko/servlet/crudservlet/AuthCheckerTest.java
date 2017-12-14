package com.vgoryashko.servlet.crudservlet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

/**
 * Class that tests AuthChecker filter.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 12/14/17
 */
public class AuthCheckerTest {

    @Mock
    private HttpServletRequest requestMock;

    @Mock
    private HttpServletResponse responseMock;

    @Mock
    private FilterChain filterChainMock;

    @Mock
    private HttpSession sessionMock;

    private AuthChecker authChecker;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        authChecker = new AuthChecker();
    }

    @Test
    public void whenRequestUriContainsSignThenDoFilterChain() throws Exception {
        when(requestMock.getRequestURI()).thenReturn("/sign");
        authChecker.doFilter(requestMock, responseMock, filterChainMock);
        verify(filterChainMock).doFilter(requestMock, responseMock);
    }

    @Test
    public void whenRequestUriDoesnContainSignAndSessionNotNullThenFilterDoesNotRun() throws Exception {
        when(requestMock.getRequestURI()).thenReturn("/get");
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("login")).thenReturn(null);
        authChecker.doFilter(requestMock, responseMock, filterChainMock);
        verify(filterChainMock, never()).doFilter(requestMock, responseMock);
    }

    @Test
    public void whenRequestUriDoesnContainSignAndSessionNullThenDoFilterChain() throws Exception {
        when(requestMock.getRequestURI()).thenReturn("/get");
        when(requestMock.getSession()).thenReturn(sessionMock);
        when(sessionMock.getAttribute("login")).thenReturn(new Object());
        authChecker.doFilter(requestMock, responseMock, filterChainMock);
        verify(filterChainMock, atMost(1)).doFilter(requestMock, responseMock);
    }
}