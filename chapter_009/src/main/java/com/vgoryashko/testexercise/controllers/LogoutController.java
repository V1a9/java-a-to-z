package com.vgoryashko.testexercise.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class that implements Logout controller.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 1/30/18
 */
public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession(false).invalidate();
        resp.sendRedirect(String.format("%s/testexercise/login", req.getContextPath()));
    }
}
