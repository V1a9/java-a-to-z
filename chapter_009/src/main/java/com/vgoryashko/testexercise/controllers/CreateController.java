package com.vgoryashko.testexercise.controllers;

import com.vgoryashko.testexercise.dao.*;
import com.vgoryashko.testexercise.models.Address;
import com.vgoryashko.testexercise.models.Music;
import com.vgoryashko.testexercise.models.Role;
import com.vgoryashko.testexercise.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class that implements servlet that Create controller.
 *
 * @author Vlad Goryashko
 * @version 0.5
 * @since 2/16/18
 */
public class CreateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("entity", req.getParameter("entity"));
        req.getRequestDispatcher("/WEB-INF/views/CreateView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameters = req.getParameterMap();

        if (parameters.size() == 4) {
            User user = new User();
            user.setName(parameters.get("name")[0]);
            user.setLogin(parameters.get("login")[0]);
            user.setPassword(parameters.get("pwd")[0]);
            String roleName = parameters.get("role")[0];
            try {
                List<Role> roleList = ((SQLRoleDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.ROLES)).readAll();
                long roleId = 0;
                for (Role role : roleList) {
                    if (role.getRoleName().equals(roleName)) {
                        roleId = role.getId();
                    }
                }
                user.setRole(roleId);
                ((SQLUserDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS)).create(user);
                resp.sendRedirect(String.format("%s/testexercise/?entity=user", req.getContextPath()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Set<String> keys = parameters.keySet();
            if (keys.contains("role")) {
                Role role = new Role(parameters.get("role")[0]);
                try {
                    ((SQLRoleDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.ROLES)).create(role);
                    resp.sendRedirect(String.format("%s/testexercise/?entity=role", req.getContextPath()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (keys.contains("music")) {
                Music music = new Music(parameters.get("music")[0]);
                try {
                    ((SQLMusicDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.MUSICS)).create(music);
                    resp.sendRedirect(String.format("%s/testexercise/?entity=music", req.getContextPath()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (keys.contains("address")) {
                Address address = new Address(parameters.get("address")[0]);
                try {
                    ((SQLAddressDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.ADDRESSES)).create(address);
                    resp.sendRedirect(String.format("%s/testexercise/?entity=address", req.getContextPath()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
