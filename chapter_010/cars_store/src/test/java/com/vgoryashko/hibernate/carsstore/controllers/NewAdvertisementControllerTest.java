package com.vgoryashko.hibernate.carsstore.controllers;

import com.vgoryashko.hibernate.carsstore.dao.*;
import com.vgoryashko.hibernate.carsstore.models.items.Advertisement;
import com.vgoryashko.hibernate.carsstore.models.parts.Part;
import com.vgoryashko.hibernate.carsstore.models.users.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.support.membermodification.MemberMatcher.constructor;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DAOManager.class, LoggerFactory.class})
@PowerMockIgnore({"javax.management.*"})

/**
 * Class that tests NewAdvertisementController.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/19/18
 */
public class NewAdvertisementControllerTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private HttpSession session;

    @Mock
    private UserDAO userDAO;

    @Mock
    private CarDAO carDAO;

    @Mock
    private PartDAO partDAO;

    @Mock
    private AdvertisementDAO advertisementDAO;

    private DAOManager daoManager;

    private NewAdvertisementController newAdvertisementController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(LoggerFactory.class);
        suppress(constructor(DAOManager.class));
        PowerMockito.mockStatic(DAOManager.class);
        daoManager = mock(DAOManager.class);
        when(DAOManager.getInstance()).thenReturn(daoManager);

        newAdvertisementController = new NewAdvertisementController();
    }

    @Test
    public void doGet() throws Exception {
        when(request.getRequestDispatcher("/WEB-INF/views/NewAdvert.jsp")).thenReturn(requestDispatcher);
        newAdvertisementController.doGet(request, response);
        verify(requestDispatcher, atMost(1)).forward(request, response);
    }

    @Test
    public void doPost() throws Exception {

        when(request.getParameter("vin")).thenReturn("123");
        when(request.getParameter("brand")).thenReturn("brand");

        when(request.getParameter("body")).thenReturn("body");
        when(request.getParameter("engine")).thenReturn("engine");
        when(request.getParameter("transmission")).thenReturn("transmission");
        when(request.getParameter("suspension")).thenReturn("suspension");

        when(request.getParameter("description")).thenReturn("description");
        when(request.getParameter("price")).thenReturn("1");

        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("userId")).thenReturn("1");
        when(daoManager.daoFactory(DAOManager.TABLES.USERS)).thenReturn(userDAO);
        when(daoManager.daoFactory(DAOManager.TABLES.ADVERTISEMENTS)).thenReturn(advertisementDAO);
        when(daoManager.daoFactory(DAOManager.TABLES.PARTS)).thenReturn(partDAO);
        when(daoManager.daoFactory(DAOManager.TABLES.CARS)).thenReturn(carDAO);

        Part part = new Part();
        part.setType("type");
        part.setDescription("desc");
        List parts = new ArrayList(Arrays.asList(part));

        when(partDAO.readByCriteri(anyString())).thenReturn(parts);

        User user = new User();
        user.setId(1L);
        user.setName("user");
        user.setLogin("login");
        user.setPassword("pwd");

        when(userDAO.read(1L)).thenReturn(user);
        when(advertisementDAO.create(any(Advertisement.class))).thenReturn(1L);
        when(request.getRequestDispatcher("/WEB-INF/views/AddPhotoView.jsp")).thenReturn(requestDispatcher);

        verify(request, atMost(1)).setAttribute("advertId", 1);
        verify(requestDispatcher, atMost(1)).forward(request, response);

    }

}