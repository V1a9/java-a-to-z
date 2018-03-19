package com.vgoryashko.hibernate.carsstore.controllers;

import com.vgoryashko.hibernate.carsstore.dao.AdvertisementDAO;
import com.vgoryashko.hibernate.carsstore.dao.DAOManager;
import com.vgoryashko.hibernate.carsstore.models.cars.Car;
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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

import static org.mockito.Mockito.*;
import static org.powermock.api.support.membermodification.MemberMatcher.constructor;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DAOManager.class, LoggerFactory.class})
@PowerMockIgnore({"javax.management.*"})

/**
 * Class that tests GetAdvertController.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/19/18
 */
public class GetAdvertControllerTest {

    @Mock
    private HttpServletRequest requestMock;

    @Mock
    private HttpServletResponse responseMock;

    @Mock
    private AdvertisementDAO advertisementDAOMock;

    @Mock
    private ServletOutputStream outputStreamMock;

    private GetAdvertController getAdvertController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(LoggerFactory.class);

        suppress(constructor(DAOManager.class));
        PowerMockito.mockStatic(DAOManager.class);
        DAOManager daoManagerMock = mock(DAOManager.class);
        when(DAOManager.getInstance()).thenReturn(daoManagerMock);

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
        advertisement.getPhotos().add("photo");

        when(requestMock.getParameter("id")).thenReturn("1");
        when(daoManagerMock.daoFactory(DAOManager.TABLES.ADVERTISEMENTS)).thenReturn(advertisementDAOMock);
        when(advertisementDAOMock.read(1L)).thenReturn(advertisement);
        when(responseMock.getOutputStream()).thenReturn(outputStreamMock);

        getAdvertController = new GetAdvertController();
    }

    @Test
    public void doGet() throws Exception {
        getAdvertController.doGet(requestMock, responseMock);
        verify(responseMock, atMost(1)).getOutputStream();
    }

}