package com.vgoryashko.hibernate.carsstore.dao;

import com.vgoryashko.hibernate.carsstore.models.parts.Part;
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
@PrepareForTest({LoggerFactory.class, PartDAO.class})

/**
 * Class that tests Part class.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/3/18
 */
public class PartDAOTest {

    private PartDAO partDAO;

    @Mock
    private DAOHelper<Part> daoHelperMock;

    @Mock
    private Session sessionMock;

    @Mock
    private Transaction transactionMock;

    @Mock
    private Query queryMock;

    private Part part;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(LoggerFactory.class);
        whenNew(DAOHelper.class).withAnyArguments().thenReturn(daoHelperMock);
        when(sessionMock.beginTransaction()).thenReturn(transactionMock);
        when(sessionMock.createQuery("FROM Part ")).thenReturn(queryMock);
        partDAO = new PartDAO(sessionMock);
        part = new Part();
        part.setType("Engine");
        part.setDescription("Desc");
    }

    @Test
    public void whenCreateInvokedThenDAOHelperCreateInvoked() throws Exception {
        partDAO.create(part);
        verify(daoHelperMock, times(1)).create(part);
    }

    @Test
    public void whenReadInvokedThenSessionGetInvoked() throws Exception {
        partDAO.read(1L);
        verify(sessionMock, times(1)).get(Part.class, 1L);
    }

    @Test
    public void whenReadAllInvokedThenQueryListInvoked() throws Exception {
        partDAO.readAll();
        verify(queryMock, times(1)).list();
    }

    @Test
    public void whenUpdateInvokedThenDAOHelperUpdateInvoked() throws Exception {
        partDAO.update(part);
        verify(daoHelperMock, times(1)).update(part);
    }

    @Test
    public void whenDeleteInvokedThenDAOHelperDeleteInvoked() throws Exception {
        partDAO.delete(part);
        verify(daoHelperMock, times(1)).delete(part);
    }

}