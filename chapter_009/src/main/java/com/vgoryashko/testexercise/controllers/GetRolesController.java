package com.vgoryashko.testexercise.controllers;

import com.vgoryashko.testexercise.dao.DAOManager;
import com.vgoryashko.testexercise.models.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * Class that implements controller that retrieves all available Roles.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 2/08/18
 */
public class GetRolesController extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        try {
            List<Role> roles = DAOManager.getInstance().DAOFactory(DAOManager.TABLES.ROLES).readAll();
            long rolesLength = roles.size();
            writer.append("[");
            for (Role role : roles) {
                if (roles.indexOf(role) + 1 == rolesLength) {
                    writer.append(String.format("\"%s\"",  role.getRoleName()));
                } else {
                    writer.append(String.format("\"%s\", ",  role.getRoleName()));
                }
            }
            writer.append("]");
            writer.flush();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
