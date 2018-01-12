package com.vgoryashko.servlet.crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class that implements servlet that gets an User with a given email from the DB .
 *
 * @author Vlad Goryashko
 * @version 0.12
 * @since 12/01/18
 */
public class GetUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", UserStore.getInstance().getAll());
        req.getRequestDispatcher("/ustorage/WEB-INF/views/Get.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", UserStore.getInstance().getAll());
        req.setAttribute("foundUser", UserStore.getInstance().read(req.getParameter("email")));
        req.getRequestDispatcher("/ustorage/WEB-INF/views/UsersView.jsp").forward(req, resp);
    }
}
