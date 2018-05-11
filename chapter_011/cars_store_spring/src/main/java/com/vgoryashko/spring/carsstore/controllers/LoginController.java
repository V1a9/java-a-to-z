package com.vgoryashko.spring.carsstore.controllers;

import com.vgoryashko.spring.carsstore.dao.DAOManager;
import com.vgoryashko.spring.carsstore.dao.UserDAO;
import com.vgoryashko.spring.carsstore.models.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * Class that implements Login controller.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 11/4/18
 */
@Controller
public class LoginController {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @GetMapping(path = "/login.do")
    protected String login() {
        return "LoginView";
    }

    @PostMapping(path = "/login.do")
    protected String loginUser(Model model, HttpServletRequest req) {

        String result = null;

        User user = new User();
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("pass"));
        long userId;
        try {
            userId = ((UserDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS)).validateUser(user);
            if (userId != 0) {
                HttpSession session = req.getSession();
                session.setAttribute("loggedUser", user.getLogin());
                session.setAttribute("userId", String.valueOf(userId));
                result = "UserMainView";
            } else {
                model.addAttribute("error", "Login or password is incorrect.");
                result = "LoginView";
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return result;
    }
}
