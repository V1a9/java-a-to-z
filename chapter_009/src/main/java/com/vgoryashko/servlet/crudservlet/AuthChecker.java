package com.vgoryashko.servlet.crudservlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class that implements filter for authorization of users.
 *
 * @author Vlad Goryashko
 * @version 0.10
 * @since 12/14/17
 */
public class AuthChecker implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;

        if (request.getRequestURI().contains("/sign")) {
            chain.doFilter(req, resp);
        } else {
            HttpSession session = request.getSession();
            if (session.getAttribute("login") == null) {
                ((HttpServletResponse) resp).sendRedirect(String.format("%s/sign", request.getContextPath()));
                return;
            }
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
