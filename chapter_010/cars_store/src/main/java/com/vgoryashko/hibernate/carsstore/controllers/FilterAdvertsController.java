package com.vgoryashko.hibernate.carsstore.controllers;

import com.vgoryashko.hibernate.carsstore.dao.AdvertisementDAO;
import com.vgoryashko.hibernate.carsstore.dao.DAOManager;
import com.vgoryashko.hibernate.carsstore.models.items.Advertisement;
import com.vgoryashko.hibernate.carsstore.service.FormJsonAdvert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class that implement controller that returns all adverts with photos.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/23/18
 */
public class FilterAdvertsController extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterAdvertsController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");

        StringBuilder stringBuilder = new StringBuilder("SELECT a FROM Advertisement a");
        StringBuilder stringBuilder1Brands = new StringBuilder();
        Set keys = req.getParameterMap().keySet();

        boolean today = false;

        if (keys.contains("photos")) {
            stringBuilder.append(" INNER JOIN Photo p ON a.id = p.advertisement.id");
        }

        if (keys.contains("today")) {
            stringBuilder.append(" where");
            stringBuilder.append(" day(a.created) = day(current_date) AND"
                    + " month(a.created) = month(current_date) AND"
                    + " year(a.created) = year(current_date)");
            today = true;
        }

        for (Object object : keys) {
            String key = (String) object;
            if (!key.equals("photos") && !key.equals("today")) {
                if (stringBuilder1Brands.length() == 0) {
                    stringBuilder1Brands.append(String.format(" a.car.brand = '%s'", key));
                } else {
                    stringBuilder1Brands.append(String.format(" OR a.car.brand = '%s'", key));
                }
            }
        }

        if (today && stringBuilder1Brands.length() != 0) {
            stringBuilder.append(" AND (");
            stringBuilder.append(stringBuilder1Brands.append(")"));
        } else {
            if (stringBuilder1Brands.length() != 0) {
                stringBuilder.append(" where").append(stringBuilder1Brands);
            }
        }

        List<Advertisement> advertisements = null;

        String criteria = stringBuilder.toString();

        try {
            advertisements = (List<Advertisement>) ((AdvertisementDAO) (DAOManager.getInstance().daoFactory(DAOManager.TABLES.ADVERTISEMENTS))).readByCriteria(criteria);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        JsonWriter writer = Json.createWriter(resp.getOutputStream());
        writer.writeArray(new FormJsonAdvert().returnArray(advertisements));
    }
}
