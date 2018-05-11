package com.vgoryashko.spring.carsstore.controllers;

import com.vgoryashko.spring.carsstore.dao.AdvertisementDAO;
import com.vgoryashko.spring.carsstore.dao.DAOManager;
import com.vgoryashko.spring.carsstore.dao.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 * Class that implements controller that retrieves all adverts of a particular User.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 4/10/18
 */
@Controller
public class GetUserAdvertsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetUserAdvertsController.class);

    @GetMapping(path = "/useradverts.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List getAdverts(HttpServletRequest req) {

        List advertisements = null;

        HttpSession session = req.getSession(false);
        long userId = 0;

        if (session != null && session.getAttribute("userId") != null) {
            userId = Long.parseLong((String) session.getAttribute("userId"));
        }

        String showActive = req.getParameter("showActive");

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

        return advertisements;
    }
}
