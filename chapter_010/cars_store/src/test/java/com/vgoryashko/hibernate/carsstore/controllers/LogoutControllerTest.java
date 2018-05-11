package com.vgoryashko.hibernate.carsstore.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Class that tests LogoutController.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/19/18
 */
public class LogoutControllerTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    private LogoutController logoutController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(request.getSession(false)).thenReturn(session);
        logoutController = new LogoutController();
    }

    @Test
    public void doGet() throws ServletException, IOException {
        logoutController.doGet(request, response);
        verify(session, atMost(1)).removeAttribute("login");
        verify(session, atMost(1)).invalidate();
        verify(response, atMost(1)).sendRedirect(anyString());
    }
}