package com.vgoryashko.servlet.crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class that implements the servlet that deletes an User from BD.
 *
 * @author Vlad Goryashko
 * @version 0.12
 * @since 12/01/18
 */
public class DeleteUser extends HttpServlet {

    public DeleteUser() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", UserStore.getInstance().getAll());
        req.getRequestDispatcher("/ustorage/WEB-INF/views/Delete.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserStore.getInstance().delete(req.getParameter("email"));
        req.setAttribute("users", UserStore.getInstance().getAll());
        HttpSession session = req.getSession();
        if (!((User) session.getAttribute("loggedInUser")).getRole().equals("Admin")) {
            session.invalidate();
            resp.sendRedirect(String.format("%s/ustorage", req.getContextPath()));
        } else {
            req.getRequestDispatcher("/ustorage/WEB-INF/views/UsersView.jsp").forward(req, resp);
        }
    }
}
