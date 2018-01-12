package com.vgoryashko.servlet.crudservlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mock.web.DelegatingServletOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Class that tests GetCities servlet.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 1/12/18
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserStore.class)

public class GetCitiesTest {

    @Mock
    private HttpServletRequest requestMock;

    @Mock
    private HttpServletResponse responseMock;

    @Test
    public void whenDoGetInvokedThenAllCountriesReturned() throws Exception {

        new GetCountriesTest().setUp();

        DelegatingServletOutputStream outputStream = new DelegatingServletOutputStream(new ByteArrayOutputStream());
        when(requestMock.getParameter("country")).thenReturn("Canada");
        when(UserStore.getInstance().getCities(anyString())).thenReturn(new ArrayList<>(Arrays.asList("Toronto", "Ottawa")));
        when(responseMock.getOutputStream()).thenReturn(outputStream);

        new GetCities().doPost(requestMock, responseMock);

        ByteArrayOutputStream stream = (ByteArrayOutputStream) outputStream.getTargetStream();

        assertThat(stream.toByteArray(), is("[\"Toronto\", \"Ottawa\"]".getBytes()));

    }

}