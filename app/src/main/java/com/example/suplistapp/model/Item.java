package com.example.suplistapp.model;

import java.util.Date;

public class Item
{
    private int id;
    private String productName;
    private String expirationDate;
    private Double productPrice;
    private Integer quantity; //UNITY?????

    public Item(int id, String productName, String expirationDate, Double productPrice, Integer quantity) {
        this.id = id;
        this.productName = productName;
        this.expirationDate = expirationDate;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public Item(String productName, String expirationDate, Double productPrice, Integer quantity) {
        this.productName = productName;
        this.expirationDate = expirationDate;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
