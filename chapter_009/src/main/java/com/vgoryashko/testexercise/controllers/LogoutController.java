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
 * @version 0.4
 * @since 2/08/18
 */
public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession(false).invalidate();
        resp.sendRedirect(String.format("%s/testexercise/login", req.getContextPath()));
    }
}
