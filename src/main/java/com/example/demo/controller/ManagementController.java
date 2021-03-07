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



// This endpoints are for management operations and can be accessed only by managers and administrators.
@RestController
@CrossOrigin()
@RequestMapping("/management/api/")
public class ManagementController {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Autowired
    public ManagementController(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @GetMapping(value = "welcome")
    public String sayWelcome() {
        return "Welcome management!!!";
    }


    @GetMapping(value = "users")
    public ResponseEntity<?> getAllUsers() {
        List<UserDAO> userDAOS = userRepository.findAll();
        return ResponseEntity.ok().body(userConverter.daoToDto(userDAOS));
    }
}
