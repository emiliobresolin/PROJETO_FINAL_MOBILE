package com.example.suplistapp.model;

public class Item
{
    private Integer id;
    private String productName;
    private String expirationDate;
    private Double productPrice;
    private Integer quantity;
    private String listType;

    public Item(Integer id, String productName, String expirationDate, Double productPrice, Integer quantity, String listType) {
        this.id = id;
        this.productName = productName;
        this.expirationDate = expirationDate;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.listType = listType;
    }

    public Item(String productName, String expirationDate, Double productPrice, Integer quantity, String listType) {
        this.productName = productName;
        this.expirationDate = expirationDate;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.listType = listType;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public Integer getId() {
        return id;
    }

    public String getIdString() {
        return id.toString();
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

    @Override
    public String toString() {
        return "Item{" +
            "id=" + id +
            ", productName='" + productName + '\'' +
            ", expirationDate='" + expirationDate + '\'' +
            ", productPrice=" + productPrice +
            ", quantity=" + quantity +
            ", listType='" + listType + '\'' +
            '}';
    }
}
