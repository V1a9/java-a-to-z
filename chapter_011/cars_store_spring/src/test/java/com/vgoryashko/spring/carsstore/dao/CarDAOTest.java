package com.vgoryashko.spring.carsstore.dao;

import com.vgoryashko.spring.carsstore.models.cars.Car;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LoggerFactory.class, CarDAO.class})

/**
 * Class that tests CarDAO class.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/3/18
 */
public class CarDAOTest {

    private CarDAO carDAO;

    @Mock
    private DAOHelper<Car> daoHelperMock;

    @Mock
    private Session sessionMock;

    @Mock
    private Transaction transactionMock;

    @Mock
    private Query queryMock;

    private Car car;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(LoggerFactory.class);
        whenNew(DAOHelper.class).withAnyArguments().thenReturn(daoHelperMock);
        when(sessionMock.beginTransaction()).thenReturn(transactionMock);
        when(sessionMock.createQuery("FROM Car ")).thenReturn(queryMock);
        carDAO = new CarDAO(sessionMock);
        car = new Car();
    }

    @Test
    public void whenCreateInvokedThenDAOHelperCreateInvoked() throws Exception {
        when(sessionMock.createQuery("SELECT id FROM Car where vin=:vin")).thenReturn(queryMock);
        when(queryMock.list()).thenReturn(new ArrayList<Car>());
        carDAO.create(car);
        verify(daoHelperMock, times(1)).create(car);
    }

    @Test
    public void whenReadInvokedThenSessionGetInvoked() throws Exception {
        carDAO.read(1L);
        verify(sessionMock, times(1)).get(Car.class, 1L);
    }

    @Test
    public void whenReadAllInvokedThenQueryListInvoked() throws Exception {
        carDAO.readAll();
        verify(queryMock, times(1)).list();
    }

    @Test
    public void whenUpdateInvokedThenDAOHelperUpdateInvoked() throws Exception {
        carDAO.update(car);
        verify(daoHelperMock, times(1)).update(car);
    }

    @Test
    public void whenDeleteInvokedThenDAOHelperDeleteInvoked() throws Exception {
        carDAO.delete(car);
        verify(daoHelperMock, times(1)).delete(car);
    }
    

}