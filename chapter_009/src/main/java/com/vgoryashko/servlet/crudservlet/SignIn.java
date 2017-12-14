package com.vgoryashko.servlet.crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Class that implements servlet that validates Users.
 *
 * @author Vlad Goryashko
 * @version 0.10
 * @since 12/14/17
 */
public class SignIn extends HttpServlet {

    @Override
    public void init() throws ServletException {
        try {
            UserStore.getInstance().setupPool();
            UserStore.getInstance().init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");
        User user = UserStore.getInstance().read(login);
        if (UserStore.getInstance().isValid(login, pass)) {
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            session.setAttribute("loggedInUser", user);
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "Credentials are invalid.");
            doGet(req, resp);
        }
    }
}
