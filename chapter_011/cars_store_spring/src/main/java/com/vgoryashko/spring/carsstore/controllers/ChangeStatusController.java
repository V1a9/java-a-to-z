package com.vgoryashko.spring.carsstore.controllers;

import com.vgoryashko.spring.carsstore.dao.AdvertisementDAO;
import com.vgoryashko.spring.carsstore.dao.DAOManager;
import com.vgoryashko.spring.carsstore.models.items.Advertisement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Class that implements controller that changes a status of a given advert.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 4/11/18
 */
@Controller
public class ChangeStatusController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeStatusController.class);

    @GetMapping(path = "/change.do")
    protected void changeStatus(@RequestParam("advertId") String id, HttpServletResponse response) {
        long advertId = Long.parseLong(id);
        try {
            Advertisement advertisement = ((AdvertisementDAO) DAOManager.getInstance()
                    .daoFactory(DAOManager.TABLES.ADVERTISEMENTS)).read(advertId);
            advertisement.setSold(!advertisement.isSold());
            ((AdvertisementDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.ADVERTISEMENTS)).update(advertisement);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            LOGGER.error(e.getMessage(), e);
        }
    }
}
