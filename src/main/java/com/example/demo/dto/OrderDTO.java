package com.example.demo.dto;


public class OrderDTO{

    private Integer id;
    private AssetDTO asset;
    private UserDTO buyer;
    private UserDTO seller;
    private String state;
    private String date;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AssetDTO getAsset() {
        return asset;
    }

    public void setAsset(AssetDTO asset) {
        this.asset = asset;
    }

    public UserDTO getBuyer() {
        return buyer;
    }

    public void setBuyer(UserDTO buyer) {
        this.buyer = buyer;
    }

    public UserDTO getSeller() {
        return seller;
    }

    public void setSeller(UserDTO seller) {
        this.seller = seller;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
