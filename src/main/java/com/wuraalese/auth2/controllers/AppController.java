package com.wuraalese.auth2.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

    // AUTHENTICATION - this is when you allow someone to use something
    // AUTHORIZATION - this is when you ask someone if they have an authority to use something

    @RequestMapping("")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username and password");
        }
        return "index";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username and password");
        }
        System.out.println("I never got here~~~~~~~~~~~~~~~~>");
        return "redirect:/rest/secured/all";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')") // this means authorize this method to called only when the user has the role 'ADMIN'
    @RequestMapping("rest/secured/all")
    public String securedHello() {
        return "admin";
    }
// if you try to view the admin page without logging in then it [by default] redirects to the log in page
// if you log in with a non existing credentials then it seats on that page and throw an error
// if you successfully log in and you try to access admin page then it throws 403 page

    @RequestMapping(value="/403")
    public String accessDenied() {
        return "403";
    }


}
