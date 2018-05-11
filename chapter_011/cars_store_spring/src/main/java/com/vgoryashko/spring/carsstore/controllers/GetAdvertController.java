package com.vgoryashko.spring.carsstore.controllers;

import com.vgoryashko.spring.carsstore.dao.AdvertisementDAO;
import com.vgoryashko.spring.carsstore.dao.DAOManager;
import com.vgoryashko.spring.carsstore.models.items.Advertisement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * Class that implements controller that returns a newly created Advertisement.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 4/11/18
 */
@Controller
public class GetAdvertController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetAdvertController.class);

    @GetMapping(path = "/getadvert.do")
    @ResponseBody
    protected Advertisement getAdvert(@RequestParam("id") String id) {

        Advertisement advertisement = null;

        long advertId = Long.valueOf(id);

        try {
            advertisement = ((AdvertisementDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.ADVERTISEMENTS)).read(advertId);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return advertisement;
    }
}
