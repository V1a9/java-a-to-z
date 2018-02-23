package com.vgoryashko.hibernate.todo.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Class that
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 2/23/18
 */
public class TaskStatusControllerTest {

    @Mock
    private HttpServletRequest requestMock;

    @Mock
    private HttpServletResponse responseMock;

    @Mock
    private ServletContext contextMock;

    @Mock
    private SessionFactory sessionFactoryMock;

    @Mock
    private Session sessionMock;

    @Mock
    private org.hibernate.query.Query queryMock;

    @Mock
    private Transaction transactionMock;

    private TaskStatusController taskStatusController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        taskStatusController = new TaskStatusController();
        when(requestMock.getServletContext()).thenReturn(contextMock);
        when(contextMock.getAttribute("factory")).thenReturn(sessionFactoryMock);
        when(sessionFactoryMock.openSession()).thenReturn(sessionMock);
        when(sessionMock.createQuery(anyString())).thenReturn(queryMock);
        when(requestMock.getParameter("id")).thenReturn("1");
        when(sessionMock.getTransaction()).thenReturn(transactionMock);
    }

    @Test
    public void whenDoPostInvokedThenStatusModifiedAndHttpStatusOkSent() throws Exception {
        taskStatusController.doPost(requestMock, responseMock);
        verify(sessionMock, times(1)).close();
        verify(responseMock, times(1)).setStatus(HttpServletResponse.SC_OK);
    }

}