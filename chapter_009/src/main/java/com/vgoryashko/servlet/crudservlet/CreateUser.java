package com.vgoryashko.servlet.crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class that implements servlet that create a new User.
 *
 * @author Vlad Goryashko
 * @version 0.12
 * @since 12/01/18
 */
public class CreateUser extends HttpServlet {

    public CreateUser() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", UserStore.getInstance().getAll());
        req.getRequestDispatcher("/ustorage/WEB-INF/views/New.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean validInput = true;

        String[] userInput = new String[]{
                req.getParameter("name"),
                req.getParameter("role"),
                req.getParameter("login"),
                req.getParameter("password"),
                req.getParameter("email"),
                req.getParameter("country"),
                req.getParameter("city"),
                req.getParameter("date")
        };

        boolean[] validationResult = new Validator(userInput).validate();

        for (int i = 0; i < validationResult.length; i++) {
            if (!validationResult[i]) {
                validInput = false;
                userInput[i] = "";
            }
        }

        if (!validInput) {
            resp.setContentType("text/json");
            req.setAttribute("userInput", userInput);
            req.getRequestDispatcher("/ustorage/WEB-INF/views/New.jsp").forward(req, resp);

        } else {
            resp.setContentType("text/html");
            UserStore.getInstance().create(
                    new User(
                            userInput[0],
                            userInput[1],
                            userInput[2],
                            userInput[3],
                            userInput[4],
                            userInput[5],
                            userInput[6],
                            userInput[7]
                    ));
            req.setAttribute("users", UserStore.getInstance().getAll());
            req.getRequestDispatcher("/ustorage/WEB-INF/views/UsersView.jsp").forward(req, resp);
        }
    }
}
