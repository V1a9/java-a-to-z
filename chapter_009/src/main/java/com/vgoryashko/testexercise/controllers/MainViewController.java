package com.vgoryashko.testexercise.controllers;

import com.vgoryashko.testexercise.dao.DAOManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Class that implements controller that manages main view data.
 *
 * @author Vlad Goryashko
 * @version 0.5
 * @since 2/16/18
 */
public class MainViewController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String entity = req.getParameter("entity"); 
            if (entity != null && entity.equals("user")) {
                req.setAttribute("user", DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS).readAll());
                req.setAttribute("role", DAOManager.getInstance().daoFactory(DAOManager.TABLES.ROLES).readAll());
            } else if (entity != null && entity.equals("role")) {
                req.setAttribute("role", DAOManager.getInstance().daoFactory(DAOManager.TABLES.ROLES).readAll());
            } else if (entity != null && entity.equals("address")) {
                req.setAttribute("address", DAOManager.getInstance().daoFactory(DAOManager.TABLES.ADDRESSES).readAll());
            } else if (entity != null && entity.equals("music")) {
                req.setAttribute("music", DAOManager.getInstance().daoFactory(DAOManager.TABLES.MUSICS).readAll());
            } else {
                req.setAttribute("user", DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS).readAll());
                req.setAttribute("role", DAOManager.getInstance().daoFactory(DAOManager.TABLES.ROLES).readAll());
            }
            req.getRequestDispatcher("/WEB-INF/views/MainView.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
