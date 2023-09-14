package com.uahb.m1stic.project.gestionScolarite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/login")
@Controller
public class LogingController {

    @GetMapping("/login")
    public String loginPage(){
        return "auth-login";
    }

    @GetMapping("/home")
    public String loginSubmit(){
        return "landing_page";
    }
}
