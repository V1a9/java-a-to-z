package com.vgoryashko.servlet.crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class that implements servlet that works with user interface.
 *
 * @author Vlad Goryashko
 * @version 0.9
 * @since 12/12/17
 */
public class UsersView extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", UserStore.getInstance().getAll());
        req.getRequestDispatcher("/WEB-INF/views/UsersView.jsp").forward(req, resp);
    }
}
