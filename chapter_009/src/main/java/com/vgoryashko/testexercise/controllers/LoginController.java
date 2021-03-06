package com.vgoryashko.testexercise.controllers;

import com.vgoryashko.testexercise.dao.DAOManager;
import com.vgoryashko.testexercise.dao.SQLRoleDAO;
import com.vgoryashko.testexercise.dao.SQLUserDAO;
import com.vgoryashko.testexercise.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Class that implements a controller for Login page.
 *
 * @author Vlad Goryashko
 * @version 0.6
 * @since 2/16/18
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        User user = new User();
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("pass"));

        try {
            long userId = ((SQLUserDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS)).validateUser(user);
            if (userId > 0) {
                user = ((SQLUserDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS)).read(userId);
                HttpSession session = req.getSession(false);
                session.setAttribute("loggedUserRole", ((SQLRoleDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.ROLES)).read(user.getRole()).getRoleName().toUpperCase());
                session.setAttribute("loggedUser", user);
                req.getRequestDispatcher("/WEB-INF/views/MainView.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", "Wrong credentials.");
                req.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
