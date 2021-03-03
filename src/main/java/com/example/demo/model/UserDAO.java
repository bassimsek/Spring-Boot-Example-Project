package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "users")
public class UserDAO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "user_id")
    private Integer id;

    @NotNull
    @Column(name = "user_name")
    private String username;

    @JsonIgnore
    @NotNull
    @Column(name = "user_password")
    private String password;

    @NotNull
    @Column(name = "user_address")
    private String address;

    @NotNull
    @Column(name = "user_phone")
    private Integer phone;

    @OneToMany(mappedBy = "owner")
    private List<AssetDAO> onSaleAssets = new ArrayList<AssetDAO>();

    @OneToMany(mappedBy = "buyer")
    private List<OrderDAO> buyOrders = new ArrayList<OrderDAO>();

    @OneToMany(mappedBy = "seller")
    private List<OrderDAO> sellOrders = new ArrayList<OrderDAO>();

    @OneToMany(mappedBy = "user")
    private List<LogDAO> userLogs = new ArrayList<LogDAO>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public List<AssetDAO> getOnSaleAssets() {
        return onSaleAssets;
    }

    public void setOnSaleAssets(List<AssetDAO> onSaleAssets) {
        this.onSaleAssets = onSaleAssets;
    }

    public List<OrderDAO> getBuyOrders() {
        return buyOrders;
    }

    public void setBuyOrders(List<OrderDAO> buyOrders) {
        this.buyOrders = buyOrders;
    }

    public List<OrderDAO> getSellOrders() {
        return sellOrders;
    }

    public void setSellOrders(List<OrderDAO> sellOrders) {
        this.sellOrders = sellOrders;
    }

    public List<LogDAO> getUserLogs() {
        return userLogs;
    }

    public void setUserLogs(List<LogDAO> userLogs) {
        this.userLogs = userLogs;
    }
}

