package com.vgoryashko.spring.carsstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Class that implements controller for MainView.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 4/11/18
 */
@Controller
public class MainViewController {

    @GetMapping(path = "/mainview.do")
    protected String mainView() {
        return "UserMainView";
    }
}
