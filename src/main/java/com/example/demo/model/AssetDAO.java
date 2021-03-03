package com.example.demo.model;


import com.sun.istack.NotNull;

import javax.persistence.*;


@Entity
@Table(name = "on_sale_assets")
public class AssetDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "asset_type")
    private String assetType;

    @NotNull
    @Column(name = "asset_name")
    private String assetName;

    @NotNull
    @Column(name = "price")
    private float price;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserDAO owner;

    @OneToOne(mappedBy = "asset")
    private OrderDAO order;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public UserDAO getOwner() {
        return owner;
    }

    public void setOwner(UserDAO owner) {
        this.owner = owner;
    }

    public OrderDAO getOrder() {
        return order;
    }

    public void setOrder(OrderDAO order) {
        this.order = order;
    }
}

