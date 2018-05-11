package com.vgoryashko.hibernate.carsstore.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Class that tests MainViewController.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/19/18
 */
public class MainViewControllerTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    private MainViewController mainViewController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mainViewController = new MainViewController();
        when(request.getRequestDispatcher("/WEB-INF/views/UserMainView.jsp")).thenReturn(requestDispatcher);
    }

    @Test
    public void doGet() throws Exception {

        mainViewController.doGet(request, response);
        verify(requestDispatcher, atMost(1)).forward(request, response);

    }

}