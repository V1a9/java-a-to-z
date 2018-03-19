package com.vgoryashko.hibernate.carsstore.controllers;

import com.vgoryashko.hibernate.carsstore.dao.AdvertisementDAO;
import com.vgoryashko.hibernate.carsstore.dao.DAOManager;
import com.vgoryashko.hibernate.carsstore.dao.UserDAO;
import com.vgoryashko.hibernate.carsstore.models.cars.Car;
import com.vgoryashko.hibernate.carsstore.models.items.Advertisement;
import com.vgoryashko.hibernate.carsstore.models.parts.Part;
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

        if (advertisements != null) {
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            JsonArrayBuilder arrayBuilderParts = Json.createArrayBuilder();
            JsonObjectBuilder advertBuilder = Json.createObjectBuilder();
            JsonArrayBuilder arrayBuilderPhotos = Json.createArrayBuilder();

            for (Object o : advertisements) {
                Advertisement advertisement = (Advertisement) o;
                Car car = advertisement.getCar();
                List parts = car.getParts();
                List photos = advertisement.getPhotos();

                for (Object ob : parts) {
                    Part part = (Part) ob;
                    arrayBuilderParts.add(Json.createObjectBuilder()
                            .add("type", part.getType())
                            .add("desc", part.getDescription()));
                }

                for (Object ob : photos) {
                    String photo = (String) ob;
                    arrayBuilderPhotos.add(photo);
                }

                advertBuilder.add("advert", Json.createObjectBuilder()
                        .add("id", advertisement.getId())
                        .add("desc", advertisement.getDescription())
                        .add("price", advertisement.getPrice())
                        .add("sold", advertisement.isSold())
                        .add("created", advertisement.getCreated().getTime()))
                        .add("photos", Json.createArrayBuilder()
                                .add(arrayBuilderPhotos))
                        .add("car", Json.createObjectBuilder()
                                .add("vin", car.getVin())
                                .add("brand", car.getBrand()))
                        .add("parts", Json.createArrayBuilder()
                                .add(arrayBuilderParts));

                arrayBuilder.add(advertBuilder);
            }

            JsonArray jsonArray = arrayBuilder.build();
            JsonWriter writer = Json.createWriter(resp.getOutputStream());
            writer.writeArray(jsonArray);
        }
    }
}
