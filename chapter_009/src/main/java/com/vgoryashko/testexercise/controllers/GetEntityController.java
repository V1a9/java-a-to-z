package com.vgoryashko.testexercise.controllers;

import com.vgoryashko.testexercise.dao.DAOManager;
import com.vgoryashko.testexercise.models.Address;
import com.vgoryashko.testexercise.models.Music;
import com.vgoryashko.testexercise.models.Role;
import com.vgoryashko.testexercise.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Class that implements controller that returns an Entity based on a given ID.
 *
 * @author Vlad Goryashko
 * @version 0.6
 * @since 2/16/18
 */
public class GetEntityController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        String entity = req.getParameter("entity");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        try {
            if (entity.equals("user")) {
                User user = ((User) DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS).read(Long.valueOf(req.getParameter("id"))));
                writer.append(String.format("{ \"id\":\"%d\", \"name\":\"%s\", \"login\":\"%s\", \"password\":\"%s\", \"role\":\"%d\"}",
                        user.getId(), 
                        user.getName(), 
                        user.getLogin(), 
                        user.getPassword(),
                        user.getRole())
                );
                req.setAttribute("user", user);
            } else if (entity.equals("role")) {
                Role role = ((Role) DAOManager.getInstance().daoFactory(DAOManager.TABLES.ROLES).read(Long.valueOf(req.getParameter("id"))));
                writer.append(String.format("{ \"id\":\"%d\", \"name\":\"%s\"}",
                        role.getId(),
                        role.getRoleName())
                );
                req.setAttribute("role", role);
            } else if (entity.equals("address")) {
                Address address = ((Address) DAOManager.getInstance().daoFactory(DAOManager.TABLES.ADDRESSES).read(Long.valueOf(req.getParameter("id"))));
                writer.append(String.format("{ \"id\":\"%d\", \"name\":\"%s\"}",
                        address.getId(),
                        address.getAddress())
                );
                req.setAttribute("address", address);
            } else if (entity.equals("music")) {
                Music music = ((Music) DAOManager.getInstance().daoFactory(DAOManager.TABLES.MUSICS).read(Long.valueOf(req.getParameter("id"))));
                writer.append(String.format("{ \"id\":\"%d\", \"name\":\"%s\"}",
                        music.getId(),
                        music.getMusicGenre())
                );
                req.setAttribute("music", music);
            }
            writer.flush();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
