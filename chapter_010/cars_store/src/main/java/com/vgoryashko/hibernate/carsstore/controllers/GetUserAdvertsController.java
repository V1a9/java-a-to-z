package com.vgoryashko.hibernate.carsstore.controllers;

import com.vgoryashko.hibernate.carsstore.dao.AdvertisementDAO;
import com.vgoryashko.hibernate.carsstore.dao.DAOManager;
import com.vgoryashko.hibernate.carsstore.dao.UserDAO;
import com.vgoryashko.hibernate.carsstore.service.FormJsonAdvert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Class that implements controller that retrieves all adverts of a particular User.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/7/18
 */
public class GetUserAdvertsController extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetUserAdvertsController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");

        HttpSession session = req.getSession(false);
        long userId = 0;

        if (session.getAttribute("userId") != null) {
            userId = Long.parseLong((String) session.getAttribute("userId"));
        }

        String showActive = req.getParameter("showActive");

        List advertisements = null;

        if (userId == 0 || (showActive != null && showActive.equals("true"))) {
            try {
                advertisements = ((AdvertisementDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.ADVERTISEMENTS)).readNotSold();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } else {
            try {
                advertisements = ((UserDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS)).read(userId).getAdvertisements();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        JsonWriter writer = Json.createWriter(resp.getOutputStream());
        writer.writeArray(new FormJsonAdvert().returnArray(advertisements));
    }
}
