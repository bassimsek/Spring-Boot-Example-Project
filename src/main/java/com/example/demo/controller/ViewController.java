package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


// Example controller for working with Thymeleaf HTML templates
@Controller
@RequestMapping("/")
public class ViewController {

    @GetMapping("login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("greeting")
    public String sayWelcome() {
        return "welcome";
    }

}



