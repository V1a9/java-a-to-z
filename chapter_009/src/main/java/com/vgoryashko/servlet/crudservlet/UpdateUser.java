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
 * @version 0.7
 * @since 12/9/17
 */
public class UpdateUser extends HttpServlet {

    private final UserStore userStore = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", UserStore.getInstance().getAll());
        req.getRequestDispatcher("/WEB-INF/views/Update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.userStore.update(new User(
                req.getParameter("newName"),
                req.getParameter("newLogin"),
                req.getParameter("email"),
                req.getParameter("newDate"))
        );
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }

}
