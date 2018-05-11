package com.vgoryashko.hibernate.carsstore.controllers;

import com.vgoryashko.hibernate.carsstore.dao.DAOManager;
import com.vgoryashko.hibernate.carsstore.dao.PartDAO;
import com.vgoryashko.hibernate.carsstore.service.FormJosnParts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.json.JsonWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Class that implements controller that retrieves all Brands.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/23/18
 */
public class GetBrandsController extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetBrandsController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/json");

        List parts = null;

        try {
            String criteria = "FROM Part WHERE type='BRAND'";
            parts = ((PartDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.PARTS)).readByCriteri(criteria);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        JsonWriter writer = Json.createWriter(resp.getOutputStream());
        writer.writeArray(new FormJosnParts().returnJson(parts));
    }

}
