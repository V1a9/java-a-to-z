package com.vgoryashko.servlet.echoservlet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.DelegatingServletOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Class that tests simple servlet.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 11/26/17
 */
public class EchoServletTest {

    private EchoServlet echoServlet;

    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpServletRequest request;

    @Before
    public void init() {
        echoServlet = new EchoServlet();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doGet() throws Exception {

        DelegatingServletOutputStream outputStream = new DelegatingServletOutputStream(new ByteArrayOutputStream());
        when(response.getOutputStream()).thenReturn(outputStream);

        echoServlet.doGet(request, response);

        ByteArrayOutputStream stream = (ByteArrayOutputStream) outputStream.getTargetStream();

        assertThat(stream.toByteArray(), is("hello world".getBytes()));
    }
}