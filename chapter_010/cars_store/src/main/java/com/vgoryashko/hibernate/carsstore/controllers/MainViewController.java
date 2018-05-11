package com.vgoryashko.hibernate.carsstore.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class that implements controller for MainView.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/7/18
 */
public class MainViewController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/UserMainView.jsp").forward(req, resp);
    }
}
