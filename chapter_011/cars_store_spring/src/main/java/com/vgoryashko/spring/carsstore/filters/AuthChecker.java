package com.vgoryashko.spring.carsstore.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class that implements Filter that checks if an User is authenticated.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 4/11/18
 */
public class AuthChecker implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);

        if (session.getAttribute("login") == null) {
            ((HttpServletResponse) response).sendRedirect(String.format("%s/login.do", req.getContextPath()));
            return;
        } else {
            chain.doFilter(request, response);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
