package com.vgoryashko.hibernate.carsstore.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class that implements Logout controller.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/03/18
 */
public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        session.removeAttribute("login");
        session.invalidate();
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
