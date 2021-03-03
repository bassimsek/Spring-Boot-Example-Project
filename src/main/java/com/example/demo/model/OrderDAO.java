package com.example.demo.model;


import com.sun.istack.NotNull;

import javax.persistence.*;


@Entity
@Table(name = "orders")
public class OrderDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "asset_id", referencedColumnName = "id")
    private AssetDAO asset;

    @ManyToOne()
    @JoinColumn(name = "buyer_id", referencedColumnName = "user_id")
    private UserDAO buyer;

    @ManyToOne()
    @JoinColumn(name = "seller_id", referencedColumnName = "user_id")
    private UserDAO seller;


    @NotNull
    @Column(name = "state")
    private String state;

    @NotNull
    @Column(name = "date")
    private String date;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AssetDAO getAsset() {
        return asset;
    }

    public void setAsset(AssetDAO asset) {
        this.asset = asset;
    }

    public UserDAO getBuyer() {
        return buyer;
    }

    public void setBuyer(UserDAO buyer) {
        this.buyer = buyer;
    }

    public UserDAO getSeller() {
        return seller;
    }

    public void setSeller(UserDAO seller) {
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

