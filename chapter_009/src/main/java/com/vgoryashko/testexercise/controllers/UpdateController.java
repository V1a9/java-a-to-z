package com.vgoryashko.testexercise.controllers;

import com.vgoryashko.testexercise.dao.*;
import com.vgoryashko.testexercise.models.Address;
import com.vgoryashko.testexercise.models.Music;
import com.vgoryashko.testexercise.models.Role;
import com.vgoryashko.testexercise.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class that implements controller for update operations.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 2/08/18
 */
public class UpdateController extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("entity", req.getParameter("entity"));
        req.getRequestDispatcher("/WEB-INF/views/UpdateView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String entity = req.getParameter("entity");

        try {
            if (entity.equals("user")) {
                User user = new User();
                Role role = new Role();
                role.setRoleName(req.getParameter("role"));
                long roleId = ((SQLRoleDAO) DAOManager.getInstance().DAOFactory(DAOManager.TABLES.ROLES)).exists(role);
                user.setLogin(req.getParameter("login"));
                user.setName(req.getParameter("name"));
                user.setPassword(req.getParameter("pwd"));
                user.setRole(roleId);
                ((SQLUserDAO) DAOManager.getInstance().DAOFactory(DAOManager.TABLES.USERS)).update(user, Long.valueOf(req.getParameter("id")));
                resp.sendRedirect(String.format("%s/testexercise/?entity=user", req.getContextPath()));
            } else if (entity.equals("role")) {
                Role role = new Role();
                role.setRoleName(req.getParameter("role"));
                ((SQLRoleDAO) DAOManager.getInstance().DAOFactory(DAOManager.TABLES.ROLES)).update(role, Long.valueOf(req.getParameter("id")));
                resp.sendRedirect(String.format("%s/testexercise/?entity=role", req.getContextPath()));
            } else if (entity.equals("address")) {
                Address address = new Address();
                address.setAddress(req.getParameter("address"));
                ((SQLAddressDAO) DAOManager.getInstance().DAOFactory(DAOManager.TABLES.ADDRESSES)).update(address, Long.valueOf(req.getParameter("id")));
                resp.sendRedirect(String.format("%s/testexercise/?entity=address", req.getContextPath()));
            } else if (entity.equals("music")) {
                Music music = new Music();
                music.setMusicGenre(req.getParameter("music"));
                ((SQLMusicDAO) DAOManager.getInstance().DAOFactory(DAOManager.TABLES.MUSICS)).update(music, Long.valueOf(req.getParameter("id")));
                resp.sendRedirect(String.format("%s/testexercise/?entity=music", req.getContextPath()));
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }
}
