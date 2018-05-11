package com.vgoryashko.hibernate.carsstore.dao;

import com.vgoryashko.hibernate.carsstore.models.cars.Car;
import com.vgoryashko.hibernate.carsstore.models.items.Advertisement;
import com.vgoryashko.hibernate.carsstore.models.parts.Part;
import com.vgoryashko.hibernate.carsstore.models.users.User;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class that implements integration tests of AdvertisementDAO.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/29/18
 */
public class AdvertisementDAOIntegrationTest {

    private Advertisement advertisement;

    private User user;

    private Car car;

    private Part part;

    @Before
    public void setUp() throws SQLException {
        advertisement = new Advertisement();
        user = new User();
        car = new Car();
        part = new Part();

        user.setName("User");
        user.setLogin("user");
        user.setPassword("user");

        part.setType("BODY");
        part.setDescription("SEDAN");

        car.setVin("123123123");
        car.setBrand("BMW");
        car.getParts().add(part);

        advertisement.setCar(car);
        advertisement.setDescription("Description");
        advertisement.setCreated(new Timestamp(System.currentTimeMillis()));
        advertisement.setSold(false);
        advertisement.setPrice(10000);
        advertisement.setUser(user);

        DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS).create(user);
        DAOManager.getInstance().daoFactory(DAOManager.TABLES.PARTS).create(part);
        DAOManager.getInstance().daoFactory(DAOManager.TABLES.ADVERTISEMENTS).create(advertisement);
    }

    @Test
    public void whenCreateMethodInvokedThenNewAdvertisementCreatedInTheDB() throws SQLException {
        assertTrue(DAOManager.getInstance().daoFactory(DAOManager.TABLES.ADVERTISEMENTS).readAll().size() == 1);
    }

    @Test
    public void whenDeleteInvokedThenAdvertisementDeleted() throws SQLException {
        DAOManager.getInstance().daoFactory(DAOManager.TABLES.ADVERTISEMENTS).delete(advertisement);
        assertTrue(DAOManager.getInstance().daoFactory(DAOManager.TABLES.ADVERTISEMENTS).readAll().size() == 0);
    }

    @Test
    public void whenUpdateInvokedThenAdvertisementUpdayed() throws SQLException {
        Advertisement persistent = (Advertisement) DAOManager.getInstance().daoFactory(DAOManager.TABLES.ADVERTISEMENTS).read(1);
        persistent.setPrice(15000);
        DAOManager.getInstance().daoFactory(DAOManager.TABLES.ADVERTISEMENTS).update(persistent);
        persistent = (Advertisement) DAOManager.getInstance().daoFactory(DAOManager.TABLES.ADVERTISEMENTS).read(1);
        assertEquals(persistent.getPrice(), 15000);
    }

    @Test
    public void whenReadAllInvokedThenListOfAdvertsReturned() throws SQLException {
        assertTrue(DAOManager.getInstance().daoFactory(DAOManager.TABLES.ADVERTISEMENTS).readAll().size() == 1);
    }
}
