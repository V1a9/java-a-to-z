package com.vgoryashko.testexercise.controllers;

import com.vgoryashko.testexercise.dao.DAOManager;
import com.vgoryashko.testexercise.dao.SQLUserDAO;
import com.vgoryashko.testexercise.models.*;

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
 * Class that implements controller that retrieves a user with all linked data.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 2/16/18
 */
public class GetUserAllController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/json");

        try {
            List<Entity> entities = ((SQLUserDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS)).get(Long.valueOf(req.getParameter("id")));

            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            int index = 0;
            for (Entity e : entities) {
                if (e instanceof User) {
                    arrayBuilder.add(Json.createObjectBuilder()
                            .add("id", ((User) entities.get(index)).getId())
                            .add("name", ((User) entities.get(index)).getName())
                            .add("login", ((User) entities.get(index)).getLogin())
                            .add("password", ((User) entities.get(index)).getPassword())
                    );
                    index++;
                } else if (e instanceof Address) {
                    arrayBuilder.add(Json.createObjectBuilder()
                            .add("id", ((Address) entities.get(index)).getId())
                            .add("address", ((Address) entities.get(index)).getAddress())
                    );
                    index++;
                } else if (e instanceof Role) {
                    arrayBuilder.add(Json.createObjectBuilder()
                            .add("id", ((Role) entities.get(index)).getId())
                            .add("role", ((Role) entities.get(index)).getRoleName())
                    );
                    index++;
                } else if (e instanceof Music) {
                    arrayBuilder.add(Json.createObjectBuilder()
                            .add("id", ((Music) entities.get(index)).getId())
                            .add("music", ((Music) entities.get(index)).getMusicGenre())
                    );
                    index++;
                }
            }

            JsonArray jsonArray = arrayBuilder.build();
            JsonWriter writer = Json.createWriter(resp.getOutputStream());
            writer.writeArray(jsonArray);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
