package com.vgoryashko.servlet.crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class that implements the servlet that deletes an User from BD.
 *
 * @author Vlad Goryashko
 * @version 0.6
 * @since 12/5/17
 */
public class DeleteUser extends HttpServlet {

    public DeleteUser() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(String.format("%s/delete.jsp", req.getContextPath()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserStore.getInstance().delete(req.getParameter("email"));
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }
}
