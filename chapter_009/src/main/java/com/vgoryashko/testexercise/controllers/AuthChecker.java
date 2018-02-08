package com.vgoryashko.testexercise.controllers;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class that implements Filter that ensures correct authentication.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 2/08/18
 */
public class AuthChecker implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if (session.getAttribute("loggedUser") != null) {
            chain.doFilter(req, resp);
        } else if (req.getRequestURI().contains("/login")) {
            chain.doFilter(req, resp);
        } else {
            resp.sendRedirect(String.format("%s/testexercise/login", req.getContextPath()));
        }
    }

    @Override
    public void destroy() {

    }
}
