package com.vgoryashko.hibernate.carsstore.controllers;

import com.vgoryashko.hibernate.carsstore.dao.AdvertisementDAO;
import com.vgoryashko.hibernate.carsstore.dao.DAOManager;
import com.vgoryashko.hibernate.carsstore.models.cars.Car;
import com.vgoryashko.hibernate.carsstore.models.items.Advertisement;
import com.vgoryashko.hibernate.carsstore.models.items.Photo;
import com.vgoryashko.hibernate.carsstore.models.parts.Part;
import com.vgoryashko.hibernate.carsstore.models.users.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberMatcher;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DAOManager.class, LoggerFactory.class})
@PowerMockIgnore({"javax.management.*"})

/**
 * Class that tests GetUserAdvertsController.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/19/18
 */
public class GetUserAdvertsControllerTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private AdvertisementDAO advertisementDAO;

    @Mock
    private HttpSession session;

    @Mock
    private ServletOutputStream outputStream;

    private GetUserAdvertsController getUserAdvertsController;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(LoggerFactory.class);

        PowerMockito.suppress(MemberMatcher.constructor(DAOManager.class));
        PowerMockito.mockStatic(DAOManager.class);
        DAOManager daoManager = mock(DAOManager.class);
        when(DAOManager.getInstance()).thenReturn(daoManager);

        when(daoManager.daoFactory(DAOManager.TABLES.ADVERTISEMENTS)).thenReturn(advertisementDAO);

        User user = new User();
        Part part = new Part();
        part.setType("type");
        part.setDescription("desc");

        Car car = new Car();
        car.getParts().add(part);
        car.setVin("123123123");
        car.setBrand("Brand");

        Advertisement advertisement = new Advertisement();
        advertisement.setId(1L);
        advertisement.setPrice(10);
        advertisement.setSold(false);
        advertisement.setDescription("desc");
        advertisement.setCreated(new Timestamp(System.currentTimeMillis()));
        advertisement.setCar(car);
        advertisement.setUser(user);

        Photo photo = new Photo();
        photo.setId(1L);
        photo.setFileName("file");
        photo.setAdvertisement(advertisement);

        List advertisements = new ArrayList(Arrays.asList(advertisement));

        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("userId")).thenReturn("1");
        when(request.getParameter("showActive")).thenReturn("true");

        when(advertisementDAO.readNotSold()).thenReturn(advertisements);
        when(response.getOutputStream()).thenReturn(outputStream);

        getUserAdvertsController = new GetUserAdvertsController();
    }

    @Test
    public void whenDoGetInvokedThenGetOutputStreamInvoked() throws Exception {
        getUserAdvertsController.doGet(request, response);
        verify(response, atMost(1)).getOutputStream();
    }

}