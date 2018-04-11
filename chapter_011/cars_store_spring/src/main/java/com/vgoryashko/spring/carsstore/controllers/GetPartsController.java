package com.vgoryashko.spring.carsstore.controllers;

import com.vgoryashko.spring.carsstore.dao.DAOManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;

/**
 * Class that implements controller that retrieves parts available based on a given type.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 4/11/18
 */
@Controller
public class GetPartsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetPartsController.class);

    @GetMapping(path = "/parts.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    protected List getParts() {
        List parts = null;

        try {
            parts = DAOManager.getInstance().daoFactory(DAOManager.TABLES.PARTS).readAll();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return parts;
    }
}
