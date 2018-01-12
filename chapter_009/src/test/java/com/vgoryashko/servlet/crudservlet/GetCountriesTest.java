package com.vgoryashko.servlet.crudservlet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mock.web.DelegatingServletOutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.support.membermodification.MemberMatcher.constructor;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

/**
 * Class that tests GetCountries servlet.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 1/12/18
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserStore.class)

public class GetCountriesTest {

    @Mock
    private HttpServletRequest requestMock;

    @Mock
    private HttpServletResponse responseMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        suppress(constructor(UserStore.class));
        PowerMockito.mockStatic(UserStore.class);
        UserStore userStoreMock = mock(UserStore.class);
        when(UserStore.getInstance()).thenReturn(userStoreMock);
    }

    @Test
    public void whenDoGetInvokedThenAllCountriesReturned() throws Exception {

        DelegatingServletOutputStream outputStream = new DelegatingServletOutputStream(new ByteArrayOutputStream());
        when(UserStore.getInstance().getCountries()).thenReturn(new ArrayList<>(Arrays.asList("Canada", "USA")));
        when(responseMock.getOutputStream()).thenReturn(outputStream);

        new GetCountries().doGet(requestMock, responseMock);

        ByteArrayOutputStream stream = (ByteArrayOutputStream) outputStream.getTargetStream();

        assertThat(stream.toByteArray(), is("[\"Canada\", \"USA\"]".getBytes()));

    }

}