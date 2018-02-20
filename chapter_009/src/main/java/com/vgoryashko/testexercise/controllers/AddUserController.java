package com.vgoryashko.testexercise.controllers;

import com.vgoryashko.testexercise.dao.DAOManager;
import com.vgoryashko.testexercise.dao.SQLUserDAO;
import com.vgoryashko.testexercise.models.Address;
import com.vgoryashko.testexercise.models.Entity;
import com.vgoryashko.testexercise.models.Music;
import com.vgoryashko.testexercise.models.Role;
import com.vgoryashko.testexercise.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Class that implements controller that deals with add User view.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 2/16/18
 */
public class AddUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/AddUserView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String[]> parameters = req.getParameterMap();
        Collection<String> musicKeys = parameters.keySet();
        List<Entity> entities = new ArrayList<>();


        User user = new User();
        user.setName(parameters.get("name")[0]);
        user.setLogin(parameters.get("login")[0]);
        user.setPassword(parameters.get("pwd")[0]);

        Address address = new Address();
        address.setAddress(parameters.get("address")[0]);

        Role role = new Role();
        role.setRoleName(parameters.get("role")[0]);

        entities.add(user);
        entities.add(address);
        entities.add(role);

        for (String key : musicKeys) {
            if (key.contains("music")) {
                Music music = new Music();
                music.setMusicGenre(parameters.get(key)[0]);
                entities.add(music);
            }
        }

        try {
            if (!((SQLUserDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS)).add(entities)) {
                req.setAttribute("error", "User with such login already exists.");
                this.doGet(req, resp);
            } else {
                resp.sendRedirect(String.format("%s/testexercise/?entity=user", req.getContextPath()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
