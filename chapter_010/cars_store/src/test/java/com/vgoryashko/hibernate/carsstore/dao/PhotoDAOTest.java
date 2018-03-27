package com.vgoryashko.hibernate.carsstore.dao;

import com.vgoryashko.hibernate.carsstore.models.items.Advertisement;
import com.vgoryashko.hibernate.carsstore.models.items.Photo;
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
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LoggerFactory.class, PhotoDAO.class})

/**
 * Class that
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/27/18
 */
public class PhotoDAOTest {

    @Mock
    private DAOHelper<Photo> daoHelperMock;

    @Mock
    private Session sessionMock;

    @Mock
    private Transaction transactionMock;

    @Mock
    private Query queryMock;

    private PhotoDAO photoDAO;

    private Photo photo;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(LoggerFactory.class);
        whenNew(DAOHelper.class).withAnyArguments().thenReturn(daoHelperMock);
        when(sessionMock.beginTransaction()).thenReturn(transactionMock);
        photoDAO = new PhotoDAO(sessionMock);
        photo = new Photo();
        photo.setId(1L);
        photo.setAdvertisement(new Advertisement());
        photo.setFileName("file");
    }

    @Test
    public void whenCreateInvokedThenNewPhotoCreateInvoked() throws Exception {
        photoDAO.create(photo);
        verify(daoHelperMock, atMost(1)).create(photo);
    }

    @Test
    public void whenReadInvokedThenPhotoGetInvoked() throws Exception {
        photoDAO.read(1L);
        verify(sessionMock, times(1)).get(Photo.class, 1L);
    }

    @Test
    public void readAll() throws Exception {
        when(sessionMock.createQuery("FROM Photo ")).thenReturn(queryMock);
        photoDAO.readAll();
        verify(queryMock, times(1)).list();
    }

    @Test
    public void update() throws Exception {
        photoDAO.update(photo);
        verify(daoHelperMock, times(1)).update(photo);
    }

    @Test
    public void delete() throws Exception {
        photoDAO.delete(photo);
        verify(daoHelperMock, times(1)).delete(photo);
    }

}