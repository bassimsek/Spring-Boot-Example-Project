package com.example.demo.repository;


import com.example.demo.model.OrderDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderDAO, Integer> {

    List<OrderDAO> findAll();
    List<OrderDAO> findAllByBuyerId(int buyerId);
    List<OrderDAO> findAllBySellerId(int sellerId);
}
