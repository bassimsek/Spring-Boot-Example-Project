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


@RestController
@CrossOrigin()
@RequestMapping("api/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;


    @GetMapping(value = "/welcome")
    public String sayWelcome() {
        return "Welcome!!!";
    }


    @GetMapping(value = "/users")
    public ResponseEntity<?> getAllUsers() {
        List<UserDAO> userDAOS = userRepository.findAll();
        return ResponseEntity.ok().body(userConverter.daoToDto(userDAOS));
    }
}
