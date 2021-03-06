package com.example.demo.controller;

import com.example.demo.converter.UserConverter;
import com.example.demo.model.UserDAO;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



// This endpoints are for normal users(customers) of the product
@RestController
@CrossOrigin()
@RequestMapping("/customer/api/")
public class UserController {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Autowired
    public UserController(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @GetMapping(value = "welcome")
    public String sayWelcome() {
        return "Welcome customer!!!";
    }

    // TO-DO: Add CRUD operations for customers as REST endpoints


}

