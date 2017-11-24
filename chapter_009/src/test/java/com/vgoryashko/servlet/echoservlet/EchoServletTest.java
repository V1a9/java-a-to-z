package com.vgoryashko.servlet.echoservlet;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class that
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 11/22/17
 */
public class EchoServletTest {

    private EchoServlet echoServlet;
    private HttpServletResponse response;
    private MockHttpServletRequest request;

    @Before
    public void init() {

        echoServlet = new EchoServlet();
        request = new MockHttpServletRequest();
        response = mock(HttpServletResponse.class);

    }

    @Test
    public void doGet() throws Exception {

        request.addHeader("EchoServlet", "/echo");
        echoServlet.doGet(request, response);


    }

}