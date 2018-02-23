package com.vgoryashko.hibernate.todo.controllers;

import com.vgoryashko.hibernate.todo.models.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.json.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Class that implements the controller for the operations with Task.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 2/21/18
 */
public class TaskController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/json");
        ServletContext context = req.getServletContext();

        Session session = ((SessionFactory) context.getAttribute("factory")).openSession();
        session.getTransaction();

        /*Setting query string*/
        String hql = "FROM Item";

        /*Creating Query object*/
        Query query = session.createQuery(hql);

        /*Retrieving all Items from the table*/
        List items = query.list();

        /*Creating JsonArrayBuilder for JSON response*/
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for (Object obj : items) {
            Item item = (Item) obj;
            jsonArrayBuilder.add(Json.createObjectBuilder()
            .add("id", item.getId())
                    .add("desc", item.getDesc())
                    .add("created", String.valueOf(item.getCreated()))
                    .add("done", item.getDone())
            );
        }

        JsonArray jsonArray = jsonArrayBuilder.build();

        JsonWriter writer = Json.createWriter(resp.getOutputStream());
        writer.writeArray(jsonArray);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/json");
        ServletContext context = req.getServletContext();
        Session session = ((SessionFactory) context.getAttribute("factory")).openSession();
        session.beginTransaction();
        Item item = new Item();
        item.setDesc(req.getParameter("desc"));
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        item.setDone(Boolean.parseBoolean(req.getParameter("done")));
        session.save(item);
        session.getTransaction().commit();
        session.close();
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
