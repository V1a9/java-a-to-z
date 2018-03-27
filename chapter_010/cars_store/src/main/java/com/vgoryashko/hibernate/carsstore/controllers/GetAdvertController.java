package com.vgoryashko.hibernate.carsstore.controllers;

import com.vgoryashko.hibernate.carsstore.dao.AdvertisementDAO;
import com.vgoryashko.hibernate.carsstore.dao.DAOManager;
import com.vgoryashko.hibernate.carsstore.models.cars.Car;
import com.vgoryashko.hibernate.carsstore.models.items.Advertisement;
import com.vgoryashko.hibernate.carsstore.models.items.Photo;
import com.vgoryashko.hibernate.carsstore.models.parts.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Class that implements controller that returns a newly created Advertisement.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/15/18
 */
public class GetAdvertController extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetAdvertController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/json");
        JsonObjectBuilder advertBuilder = Json.createObjectBuilder();
        JsonArrayBuilder arrayBuilderParts = Json.createArrayBuilder();
        JsonArrayBuilder arrayBuilderPhotos = Json.createArrayBuilder();
        long advertId = Long.valueOf(req.getParameter("id"));
        Advertisement advertisement = null;

        try {
            advertisement = ((AdvertisementDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.ADVERTISEMENTS)).read(advertId);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        if (advertisement != null) {

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
                Photo photo = (Photo) ob;
                arrayBuilderPhotos.add(photo.getFileName());
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

            JsonObject jsonObject = advertBuilder.build();
            JsonWriter writer = Json.createWriter(resp.getOutputStream());
            writer.writeObject(jsonObject);
        }
    }
}
