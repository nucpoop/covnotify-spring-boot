package com.nucpoop.covserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("./user/login");
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login.html";
    }

    @RequestMapping("/sign-up")
    public String signUp() {
        return "signup.html";
    }

    
}
