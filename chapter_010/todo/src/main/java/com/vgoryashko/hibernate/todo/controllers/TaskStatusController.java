package com.vgoryashko.hibernate.todo.controllers;

import com.vgoryashko.hibernate.todo.models.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class that implements controller that stores a task status value in DB.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 2/23/18
 */
public class TaskStatusController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext context = req.getServletContext();
        Session session = ((SessionFactory) context.getAttribute("factory")).openSession();

        String hql = "UPDATE Item set done = :done WHERE id = :item_id";
        Query query = session.createQuery(hql);
        query.setParameter("done", Boolean.parseBoolean(req.getParameter("done")));
        query.setParameter("item_id", Integer.valueOf(req.getParameter("id")));

        session.beginTransaction();
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
