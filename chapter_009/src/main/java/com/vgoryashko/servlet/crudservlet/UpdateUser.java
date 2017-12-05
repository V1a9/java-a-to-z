package com.vgoryashko.servlet.crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class that updates an User with a given email in the DB.
 *
 * @author Vlad Goryashko
 * @version 0.6
 * @since 12/5/17
 */
public class UpdateUser extends HttpServlet {

    private final UserStore userStore = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(String.format("%s/update.jsp", req.getContextPath()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.userStore.update(new User(
                req.getParameter("newName"),
                req.getParameter("newLogin"),
                req.getParameter("email"),
                req.getParameter("newDate"))
        );
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }

}
