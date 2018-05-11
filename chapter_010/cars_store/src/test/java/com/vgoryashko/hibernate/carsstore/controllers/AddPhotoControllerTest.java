package com.vgoryashko.hibernate.carsstore.controllers;

import com.vgoryashko.hibernate.carsstore.dao.AdvertisementDAO;
import com.vgoryashko.hibernate.carsstore.dao.DAOManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.*;
import static org.powermock.api.support.membermodification.MemberMatcher.constructor;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

@RunWith(PowerMockRunner.class)
@PrepareForTest({AddPhotoController.class, DAOManager.class, AdvertisementDAO.class, LoggerFactory.class})
@PowerMockIgnore({"javax.management.*"})
@SuppressStaticInitializationFor("com.vgoryashko.hibernate.carsstore.dao.DAOManager")

/**
 * Class that tests AddPhotoController.class.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/18/18
 */
public class AddPhotoControllerTest {

    private AddPhotoController addPhotoController;

    @Mock
    private Logger loggerPhotoMock;

    @Mock
    private Logger loggerAdvertMock;

    @Mock
    private HttpServletRequest requestMock;

    @Mock
    private HttpServletResponse responseMock;

    @Mock
    private ServletContext servletContextMock;

    @Mock
    private AdvertisementDAO advertisementDAOMock;

    @Mock
    private DAOManager daoManagerMock;

    @Mock
    private RequestDispatcher requestDispatcherMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        suppress(constructor(DAOManager.class));
        PowerMockito.mockStatic(DAOManager.class);
        PowerMockito.mockStatic(LoggerFactory.class);
        when(LoggerFactory.getLogger(AddPhotoController.class)).thenReturn(loggerPhotoMock);
        when(LoggerFactory.getLogger(AdvertisementDAO.class)).thenReturn(loggerAdvertMock);
        when(requestMock.getServletContext()).thenReturn(servletContextMock);
        addPhotoController = new AddPhotoController();
    }

    @Test
    public void whenDoPostInvokedThenPhotoIsAddedToAdvert() throws SQLException, ServletException, IOException {
        //TODO finalize test of the controller
        when(requestMock.getParameter("advertId")).thenReturn("1");
        when(daoManagerMock.daoFactory(DAOManager.TABLES.ADVERTISEMENTS)).thenReturn(advertisementDAOMock);
        when(requestMock.getRequestDispatcher("/WEB-INF/views/AddPhotoView.jsp")).thenReturn(requestDispatcherMock);
        addPhotoController.doPost(requestMock, responseMock);
        verify(requestDispatcherMock, atMost(1)).forward(requestMock, responseMock);
    }
}