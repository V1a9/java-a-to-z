package com.vgoryashko.hibernate.carsstore.controllers;

import com.vgoryashko.hibernate.carsstore.dao.AdvertisementDAO;
import com.vgoryashko.hibernate.carsstore.dao.DAOManager;
import com.vgoryashko.hibernate.carsstore.dao.PartDAO;
import com.vgoryashko.hibernate.carsstore.models.cars.Car;
import com.vgoryashko.hibernate.carsstore.models.items.Advertisement;
import com.vgoryashko.hibernate.carsstore.models.parts.Part;
import com.vgoryashko.hibernate.carsstore.models.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements controller that is responsible for creation of a new Advertisement.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/5/18
 */
public class NewAdvertisementController extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewAdvertisementController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/NewAdvert.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        List<Part> carParts = new ArrayList<>();
        List parts;
        String criteria;
        Part part;

        try {

            Car car = new Car();
            car.setVin(req.getParameter("vin"));
            car.setBrand(req.getParameter("brand"));

            criteria = String.format("from Part p where p.type = 'BODY' and p.description = '%s'", req.getParameter("body"));
            parts = ((PartDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.PARTS)).readByCriteri(criteria);
            part = (Part) parts.get(0);
            carParts.add(part);

            criteria = String.format("from Part p where type = 'ENGINE' and description = '%s'", req.getParameter("engine"));
            parts = ((PartDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.PARTS)).readByCriteri(criteria);
            part = (Part) parts.get(0);
            carParts.add(part);

            criteria = String.format("from Part p where type='TRANSMISSION' and description = '%s'", req.getParameter("transmission"));
            parts = ((PartDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.PARTS)).readByCriteri(criteria);
            part = (Part) parts.get(0);
            carParts.add(part);

            criteria = String.format("from Part p where type='SUSPENSION' and description = '%s'", req.getParameter("suspension"));
            parts = ((PartDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.PARTS)).readByCriteri(criteria);
            part = (Part) parts.get(0);
            carParts.add(part);

            car.setParts(carParts);

            Advertisement advertisement = new Advertisement();
            advertisement.setDescription(req.getParameter("description"));
            advertisement.setPrice(Integer.parseInt(req.getParameter("price")));
            advertisement.setCreated(new Timestamp(System.currentTimeMillis()));

            advertisement.setCar(car);

            String userId = (String) req.getSession(false).getAttribute("userId");
            User user = (User) DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS).read(Long.valueOf(userId));
            advertisement.setUser(user);

            long advertId = ((AdvertisementDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.ADVERTISEMENTS)).create(advertisement);
            req.setAttribute("advertId", advertId);
            req.getRequestDispatcher("/WEB-INF/views/AddPhotoView.jsp").forward(req, resp);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
