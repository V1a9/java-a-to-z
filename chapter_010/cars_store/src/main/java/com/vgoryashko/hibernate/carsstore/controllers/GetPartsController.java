package com.vgoryashko.hibernate.carsstore.controllers;

import com.vgoryashko.hibernate.carsstore.dao.DAOManager;
import com.vgoryashko.hibernate.carsstore.models.parts.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Class that implements controller that retrieves parts available based on a given type.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/6/18
 */
public class GetPartsController extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetPartsController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");

        List parts = null;

        try {

            parts = DAOManager.getInstance().daoFactory(DAOManager.TABLES.PARTS).readAll();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        if (parts != null) {
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for (Object o : parts) {
                Part part = (Part) o;
                arrayBuilder.add(Json.createObjectBuilder()
                        .add("type", (part.getType()))
                        .add("desc", (part.getDescription())
                        )
                );
            }

            JsonArray jsonArray = arrayBuilder.build();
            JsonWriter writer = Json.createWriter(resp.getOutputStream());
            writer.writeArray(jsonArray);
        }
    }
}
