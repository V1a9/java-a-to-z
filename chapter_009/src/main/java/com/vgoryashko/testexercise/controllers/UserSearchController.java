package com.vgoryashko.testexercise.controllers;

import com.vgoryashko.testexercise.dao.DAOManager;
import com.vgoryashko.testexercise.dao.SQLUserDAO;
import com.vgoryashko.testexercise.models.Address;
import com.vgoryashko.testexercise.models.Music;
import com.vgoryashko.testexercise.models.Role;
import com.vgoryashko.testexercise.models.User;
import com.vgoryashko.testexercise.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Class that implements controller that ensures a customer search based on one of the criteria.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 2/08/18
 */
public class UserSearchController extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("entity", req.getParameter("entity"));
        req.getRequestDispatcher("/WEB-INF/views/FindView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String entity = req.getParameter("entity");
        List<User> users = null;
        try {
            if (entity.equals("address")) {
                Address address = new Address();
                address.setAddress(req.getParameter("address"));
                users = ((UserRepository) DAOManager.getInstance().DAOFactory(DAOManager.TABLES.USERS)).find(address);
            } else if (entity.equals("role")) {
                Role role = new Role();
                role.setRoleName(req.getParameter("role"));
                users = ((UserRepository) DAOManager.getInstance().DAOFactory(DAOManager.TABLES.USERS)).find(role);
            } else if (entity.equals("music")) {
                Music music = new Music();
                music.setMusicGenre(req.getParameter("music"));
                users = ((UserRepository) DAOManager.getInstance().DAOFactory(DAOManager.TABLES.USERS)).find(music);
            }

            if (users != null && users.size() > 0) {
                req.setAttribute("users", users);
            } else {
                req.setAttribute("error", String.format("There is no such %s or users with such %S.", entity.toUpperCase(), entity.toUpperCase()));
            }

            req.getRequestDispatcher("/WEB-INF/views/FindResultsView.jsp").forward(req, resp);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
