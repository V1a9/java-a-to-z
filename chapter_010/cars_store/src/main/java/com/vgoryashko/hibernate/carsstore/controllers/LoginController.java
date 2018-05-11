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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Class that implements Login controller.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/3/18
 */
public class LoginController extends HttpServlet {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("pass"));
        long userId;
        try {
            userId = ((UserDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS)).validateUser(user);
            if (userId != 0) {
                HttpSession session = req.getSession();
                session.setAttribute("loggedUser", user.getLogin());
                session.setAttribute("userId", String.valueOf(userId));
                resp.sendRedirect(String.format("%s/mainview", req.getContextPath()));
            } else {
                req.setAttribute("error", "Login or password is incorrect.");
                this.doGet(req, resp);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }
}
