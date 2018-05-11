package com.vgoryashko.hibernate.carsstore.controllers;

import com.vgoryashko.hibernate.carsstore.dao.DAOManager;
import com.vgoryashko.hibernate.carsstore.dao.UserDAO;
import com.vgoryashko.hibernate.carsstore.models.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Class that implements controller for registering of users.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/3/18
 */
public class RegisterController extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/RegisterView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("pwd"));
        try {
            long userId = ((UserDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS)).create(user);
            if (userId != 0) {
                resp.sendRedirect(String.format("%s/", req.getContextPath()));
            } else {
                req.setAttribute("error", "User with such login already exists!");
                this.doGet(req, resp);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
