package com.macko.models;

import java.util.UUID;

public class Product {
    //Fields
    private UUID id;
    private String productName;
    private String description;
    private double price;

    //Getters
    public UUID getId() {
        return this.id;
    }

    public String getProductName() {
        return this.productName;
    }

    public String getDescription() {
        return this.description;
    }

    public double getPrice() {
        return this.price;
    }

    //Setters
    public void setProductName(String name) {
        this.productName = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //Constructor
    public Product(UUID id, String productName, String description, double price) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.price = price;
    }
}
