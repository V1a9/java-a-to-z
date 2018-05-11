package com.vgoryashko.spring.carsstore.dao;

import com.vgoryashko.spring.carsstore.models.items.Advertisement;
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

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LoggerFactory.class, AdvertisementDAO.class})

/**
 * Class that tests AdvertisementDAO class.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/3/18
 */
public class AdvertisementDAOTest {

    private AdvertisementDAO advertisementDAO;

    @Mock
    private DAOHelper<Advertisement> daoHelperMock;

    @Mock
    private Session sessionMock;

    @Mock
    private Transaction transactionMock;

    @Mock
    private Query queryMock;

    private Advertisement advertisement;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(LoggerFactory.class);
        whenNew(DAOHelper.class).withAnyArguments().thenReturn(daoHelperMock);
        when(sessionMock.beginTransaction()).thenReturn(transactionMock);
        when(sessionMock.createQuery("FROM Advertisement")).thenReturn(queryMock);
        advertisementDAO = new AdvertisementDAO(sessionMock);
        advertisement = new Advertisement();
    }

    @Test
    public void whenCreateInvokedThenDAOHelperCreateInvoked() throws Exception {
        advertisementDAO.create(advertisement);
        verify(daoHelperMock, times(1)).create(advertisement);
    }

    @Test
    public void whenReadInvokedThenSessionGetInvoked() throws Exception {
        advertisementDAO.read(1L);
        verify(sessionMock, times(1)).get(Advertisement.class, 1L);
    }

    @Test
    public void whenReadAllInvokedThenQueryListInvoked() throws Exception {
        advertisementDAO.readAll();
        verify(queryMock, times(1)).list();
    }

    @Test
    public void whenUpdateInvokedThenDAOHelperUpdateInvoked() throws Exception {
        advertisementDAO.update(advertisement);
        verify(daoHelperMock, times(1)).update(advertisement);
    }

    @Test
    public void whenDeleteInvokedThenDAOHelperDeleteInvoked() throws Exception {
        advertisementDAO.delete(advertisement);
        verify(daoHelperMock, times(1)).delete(advertisement);
    }

}