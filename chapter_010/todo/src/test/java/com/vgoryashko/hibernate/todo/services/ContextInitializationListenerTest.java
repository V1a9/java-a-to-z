package com.vgoryashko.hibernate.todo.services;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.support.membermodification.MemberMatcher;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.whenNew;
import static org.powermock.api.support.membermodification.MemberMatcher.constructor;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ContextInitializationListener.class)

/**
 * Class that tests ContextInitializationListener class.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 2/23/18
 */
public class ContextInitializationListenerTest {

    @Mock
    private ServletContextEvent servletContextEventMock;

    @Mock
    private SessionFactory sessionFactoryMock;

    @Mock
    private ServletContext servletContextMock;

    @Mock
    private Configuration configurationMock;

    private ContextInitializationListener contextInitializationListener;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        contextInitializationListener = new ContextInitializationListener();
    }

    @Test
    public void whenContextInitializedInvokedThenSessionFactoryCreatedAndAttributeSetInContext() throws Exception {

        /*Suppress all constructors in Configuration.class*/
        MemberModifier.suppress(MemberMatcher.constructorsDeclaredIn(Configuration.class));
        /*Return Configuration mock only for default constructor*/
        whenNew(Configuration.class).withNoArguments().thenReturn(configurationMock);
        when(configurationMock.configure()).thenReturn(configurationMock);
        when(configurationMock.buildSessionFactory()).thenReturn(sessionFactoryMock);
        when(servletContextEventMock.getServletContext()).thenReturn(servletContextMock);
        contextInitializationListener.contextInitialized(servletContextEventMock);
        verify(servletContextMock, times(1)).setAttribute("factory", sessionFactoryMock);
    }

    @Test
    public void whenContextDestroyedInvokedThenSessionFactoryClosed() throws Exception {

        when(servletContextEventMock.getServletContext()).thenReturn(servletContextMock);
        when(servletContextMock.getAttribute("factory")).thenReturn(sessionFactoryMock);
        contextInitializationListener.contextDestroyed(servletContextEventMock);
        verify(sessionFactoryMock, times(1)).close();

    }

}