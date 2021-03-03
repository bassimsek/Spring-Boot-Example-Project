package com.example.demo.repository;


import com.example.demo.model.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<UserDAO, Integer> {

    UserDAO findByUsername(String username);
    List<UserDAO> findAll();
    UserDAO findById(int id);
    void deleteById(int id);
}