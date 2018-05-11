package com.vgoryashko.testexercise.controllers;

import com.vgoryashko.testexercise.dao.DAOManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Class that implements controller for delete operation.
 *
 * @author Vlad Goryashko
 * @version 0.6
 * @since 2/16/18
 */
public class DeleteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("entity", req.getParameter("entity"));
        req.getRequestDispatcher("/WEB-INF/views/DeleteView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String entity = req.getParameter("entity");

        if (entity != null && entity.equals("user")) {
            try {
                DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS).delete(Long.valueOf(req.getParameter("id")));
                resp.sendRedirect(String.format("%s/testexercise/?entity=user", req.getContextPath()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (entity != null && entity.equals("role")) {
            try {
                DAOManager.getInstance().daoFactory(DAOManager.TABLES.ROLES).delete(Long.valueOf(req.getParameter("id")));
                resp.sendRedirect(String.format("%s/testexercise/?entity=role", req.getContextPath()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (entity != null && entity.equals("music")) {
            try {
                DAOManager.getInstance().daoFactory(DAOManager.TABLES.MUSICS).delete(Long.valueOf(req.getParameter("id")));
                resp.sendRedirect(String.format("%s/testexercise/?entity=music", req.getContextPath()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (entity != null && entity.equals("address")) {
            try {
                DAOManager.getInstance().daoFactory(DAOManager.TABLES.ADDRESSES).delete(Long.valueOf(req.getParameter("id")));
                resp.sendRedirect(String.format("%s/testexercise/?entity=address", req.getContextPath()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
