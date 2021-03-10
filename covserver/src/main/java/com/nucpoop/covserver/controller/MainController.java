package com.nucpoop.covserver.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nucpoop.covserver.model.User;
import com.nucpoop.covserver.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String home() {
        return "/user/main";
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("./user/login");
    }

    @PostMapping("/loginComplete")
    public ModelAndView loginComplete(User user, HttpServletResponse response, HttpSession session) throws Exception {
        PrintWriter out = response.getWriter();
        User passuser = userService.selectUserByID(user.getUserID());

        if (passuser == null) {
            out.println("<script>");
            out.println("alert('Invalid User');");
            out.println("history.go(-1);");
            out.println("</script>");
        } else {
            if (!passuser.getUserPasswd().equals(user.getUserPasswd())) {
                out.println("<script>");
                out.println("alert('Password not matched!');");
                out.println("history.back();");
                out.println("</script>");
            } else {
                return new ModelAndView("./user/login-complete");
            }
        }
        return null;
    }

    @PostMapping("/signupComplete")
    public ModelAndView signupComplete(User user, HttpServletResponse response, HttpSession session) throws Exception{

        PrintWriter out = response.getWriter();
        if(userService.selectUserByID(user.getUserID()) == null){
            userService.insertUser(user);
            return new ModelAndView("./user/signup-complete");
        }else{
            out.println("<script>");
            out.println("alert('Already exist ID!');");
            out.println("history.back();");
            out.println("</script>");
        }
        
        return null;
    }

    @RequestMapping("/signup")
    public ModelAndView signup(){
        return new ModelAndView("./user/signup");
    }
}