package com.vgoryashko.hibernate.carsstore.controllers;

import com.vgoryashko.hibernate.carsstore.dao.AdvertisementDAO;
import com.vgoryashko.hibernate.carsstore.dao.DAOManager;
import com.vgoryashko.hibernate.carsstore.models.items.Advertisement;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.support.membermodification.MemberMatcher.constructor;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DAOManager.class})
@PowerMockIgnore({"javax.management.*"})

/**
 * Class that tests ChangeStatusController.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/19/18
 */
public class ChangeStatusControllerTest {

    @Mock
    private HttpServletRequest requestMock;

    @Mock
    private HttpServletResponse responseMock;

    @Mock
    private AdvertisementDAO advertisementDAOMock;

    private ChangeStatusController changeStatusController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        suppress(constructor(DAOManager.class));
        PowerMockito.mockStatic(DAOManager.class);
        DAOManager daoManagerMock = mock(DAOManager.class);

        when(DAOManager.getInstance()).thenReturn(daoManagerMock);
        when(daoManagerMock.daoFactory(DAOManager.TABLES.ADVERTISEMENTS)).thenReturn(advertisementDAOMock);
        when(requestMock.getParameter("advertId")).thenReturn("1");
        Advertisement advertisement = new Advertisement();
        when(advertisementDAOMock.read(1L)).thenReturn(advertisement);
        when(advertisementDAOMock.update(advertisement)).thenReturn(true);
        changeStatusController = new ChangeStatusController();
    }

    @Test
    public void whenDoPostInvokedThenAdvertUpdateInvokedOneTime() throws Exception {
        changeStatusController.doPost(requestMock, responseMock);
        verify(responseMock, atMost(1)).setStatus(HttpServletResponse.SC_OK);
    }
}