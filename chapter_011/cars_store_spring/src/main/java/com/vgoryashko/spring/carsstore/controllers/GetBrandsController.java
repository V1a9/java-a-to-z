package com.vgoryashko.spring.carsstore.controllers;

import com.vgoryashko.spring.carsstore.dao.DAOManager;
import com.vgoryashko.spring.carsstore.dao.PartDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;

/**
 * Class that implements controller that retrieves all Brands.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 4/10/18
 */
@Controller
public class GetBrandsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetBrandsController.class);

    @GetMapping(path = "/brands.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List getBrands() {
        List parts = null;

        try {
            String criteria = "FROM Part WHERE type='BRAND'";
            parts = ((PartDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.PARTS)).readByCriteri(criteria);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return parts;
    }
}
