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
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * Class that implements controller that get view info.
 *
 * @author Vlad Goryashko
 * @version 0.6
 * @since 2/16/18
 */
public class ShowController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        try {
            if (req.getParameter("dataType").equals("user")) {
                List<User> users = ((SQLUserDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS)).readAll();
                long usersSize = users.size();
                writer.append("[");
                for (User user : users) {
                    if (users.indexOf(user) + 1 == usersSize) {
                        writer.append(String.format("{\"id\":\"%d\", \"name\":\"%s\", \"login\":\"%s\", \"password\":\"%s\", \"role\":\"%d\"}", user.getId(), user.getName(), user.getLogin(), user.getPassword(), user.getRole()));
                    } else {
                        writer.append(String.format("{\"id\":\"%d\", \"name\":\"%s\", \"login\":\"%s\", \"password\":\"%s\", \"role\":\"%d\"}, ", user.getId(), user.getName(), user.getLogin(), user.getPassword(), user.getRole()));
                    }
                }
                writer.append("]");
                writer.flush();
            } else if (req.getParameter("dataType").equals("role")) {
                List<Role> roles = ((SQLRoleDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.ROLES)).readAll();
                long roleSize = roles.size();
                writer.append("[");
                for (Role role : roles) {
                    if (roles.indexOf(role) + 1 == roleSize) {
                        writer.append(String.format("{\"id\":\"%d\", \"role\":\"%s\"}", role.getId(), role.getRoleName()));
                    } else {
                        writer.append(String.format("{\"id\":\"%d\", \"role\":\"%s\"}, ", role.getId(), role.getRoleName()));
                    }
                }
                writer.append("]");
                writer.flush();
            } else if (req.getParameter("dataType").equals("address")) {
                List<Address> addresses = ((SQLAddressDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.ADDRESSES)).readAll();
                long addressesSize = addresses.size();
                writer.append("[");
                for (Address address : addresses) {
                    if (addresses.indexOf(address) + 1 == addressesSize) {
                        writer.append(String.format("{\"id\":\"%d\", \"address\":\"%s\"}", address.getId(), address.getAddress()));
                    } else {
                        writer.append(String.format("{\"id\":\"%d\", \"address\":\"%s\"}, ", address.getId(), address.getAddress()));
                    }
                }
                writer.append("]");
                writer.flush();
            } else if (req.getParameter("dataType").equals("music")) {
                List<Music> musics = ((SQLMusicDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.MUSICS)).readAll();
                long musicSize = musics.size();
                writer.append("[");
                for (Music music: musics) {
                    if (musics.indexOf(music) + 1 == musicSize) {
                        writer.append(String.format("{\"id\":\"%d\", \"music\":\"%s\"}", music.getId(), music.getMusicGenre()));
                    } else {
                        writer.append(String.format("{\"id\":\"%d\", \"music\":\"%s\"}, ", music.getId(), music.getMusicGenre()));
                    }
                }
                writer.append("]");
                writer.flush();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
