package com.vgoryashko.hibernate.todo.controllers;

import com.vgoryashko.hibernate.todo.models.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.DelegatingServletOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Class that tests TaskController.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 2/23/18
 */
public class TaskControllerTest {

    private TaskController taskController;

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

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.taskController = new TaskController();
        when(requestMock.getServletContext()).thenReturn(contextMock);
        when(contextMock.getAttribute("factory")).thenReturn(sessionFactoryMock);
        when(sessionFactoryMock.openSession()).thenReturn(sessionMock);
    }

    @Test
    public void whenDoGetInvokedThenCorrectJsonObjectReturned() throws Exception {
        Item item = new Item();
        item.setId(1);
        item.setDesc("Task");
        item.setCreated(new Timestamp(0));
        item.setDone(false);

        List<Item> items = new ArrayList<>(Arrays.asList(item));

        when(sessionMock.createQuery(anyString())).thenReturn(queryMock);

        when(queryMock.list()).thenReturn(items);

        /*Class from Spring framework*/
        DelegatingServletOutputStream outputStream = new DelegatingServletOutputStream(new ByteArrayOutputStream());
        when(responseMock.getOutputStream()).thenReturn(outputStream);

        ByteArrayOutputStream stream = (ByteArrayOutputStream) outputStream.getTargetStream();

        taskController.doGet(requestMock, responseMock);
        assertThat(stream.toByteArray(), is("[{\"id\":1,\"desc\":\"Task\",\"created\":\"1970-01-01 03:00:00.0\",\"done\":false}]".getBytes()));
    }

    @Test
    public void whenDoPostInvokedThenItemAddedAndRedirectionSentToRoot() throws Exception {

        when(sessionMock.getTransaction()).thenReturn(transactionMock);
        taskController.doPost(requestMock, responseMock);
        verify(sessionMock, times(1)).save(any(Item.class));
    }

}