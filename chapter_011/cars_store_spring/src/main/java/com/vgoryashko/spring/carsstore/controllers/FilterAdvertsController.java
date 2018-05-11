package com.vgoryashko.spring.carsstore.controllers;

import com.vgoryashko.spring.carsstore.dao.AdvertisementDAO;
import com.vgoryashko.spring.carsstore.dao.DAOManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Class that implement controller that returns all adverts with photos.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 4/10/18
 */

@Controller
public class FilterAdvertsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterAdvertsController.class);

    @GetMapping(path = "/filter.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody List filterAdverts(HttpServletRequest req) {

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

        List advertisements = null;

        String criteria = stringBuilder.toString();

        try {
            advertisements = ((AdvertisementDAO) (DAOManager.getInstance()
                    .daoFactory(DAOManager.TABLES.ADVERTISEMENTS))).readByCriteria(criteria);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return advertisements;
    }
}
