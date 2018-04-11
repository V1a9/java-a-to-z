package com.vgoryashko.spring.carsstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class that implements Logout controller.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 4/11/18
 */
@Controller
public class LogoutController {

    @GetMapping(path = "/logout.do")
    protected String logout(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        session.removeAttribute("login");
        session.invalidate();
        return "redirect:index.jsp";
    }
}
