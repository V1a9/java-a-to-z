package com.vgoryashko.spring.carsstore.controllers;

import com.vgoryashko.spring.carsstore.dao.DAOManager;
import com.vgoryashko.spring.carsstore.dao.UserDAO;
import com.vgoryashko.spring.carsstore.models.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;

/**
 * Class that implements controller for registering of users.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 4/11/18
 */
@Controller
public class RegisterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

    @GetMapping(path = "/register.do")
    protected String getRegisterView() {
        return "RegisterView";
    }

    @PostMapping(path = "/register.do")
    protected String registerUser(@ModelAttribute User user, Model model) {

        String result = null;

        try {
            long userId = ((UserDAO) DAOManager.getInstance().daoFactory(DAOManager.TABLES.USERS)).create(user);
            if (userId != 0) {
                result = "redirect:/";
            } else {
                model.addAttribute("error", "User with such login already exists!");
                result = "RegisterView";
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return result;
    }
}
