package com.vgoryashko.hibernate.carsstore.controllers;

import com.vgoryashko.hibernate.carsstore.dao.DAOManager;
import com.vgoryashko.hibernate.carsstore.dao.PartDAO;
import com.vgoryashko.hibernate.carsstore.models.parts.Part;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.powermock.api.support.membermodification.MemberMatcher.constructor;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DAOManager.class, LoggerFactory.class})
@PowerMockIgnore({"javax.management.*"})

/**
 * Class that tests GetPartsController.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/19/18
 */
public class GetPartsControllerTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private PartDAO partDAO;

    @Mock
    private ServletOutputStream outputStream;

    private GetPartsController getPartsController;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(LoggerFactory.class);

        suppress(constructor(DAOManager.class));
        PowerMockito.mockStatic(DAOManager.class);
        DAOManager daoManager = mock(DAOManager.class);
        when(DAOManager.getInstance()).thenReturn(daoManager);
        when(daoManager.daoFactory(DAOManager.TABLES.PARTS)).thenReturn(partDAO);

        Part part = new Part();
        part.setType("type");
        part.setDescription("desc");

        List parts = new ArrayList(Arrays.asList(part));
        when(partDAO.readAll()).thenReturn(parts);

        when(response.getOutputStream()).thenReturn(outputStream);

        getPartsController = new GetPartsController();
    }

    @Test
    public void whenDoGetInvokedThenGetOutputStreamInvoked() throws Exception {
        getPartsController.doGet(request, response);
        verify(response, atMost(1)).getOutputStream();
    }

}