package com.macko.models;

import java.time.LocalDate;
/*
 * The purpose of this class is to serve as a mock-up of a real-life class
 * to better simulate a plausible scenario and thus getting more valuable 
 * and relevant information out of it. This class particularly takes on 
 * the role a Order.   
 */

public class Order {
    //Fields
    private long id;
    
    private Customer customer;
    
    //orderDate represents the date the order was issued
    private LocalDate orderDate;

    private double orderAmount;

    //Getters
    public long getId() {
        return this.id;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public LocalDate getOrderDate() {
        return this.orderDate;
    }

    public double getOrderAmount() {
        return this.orderAmount;
    }

    //Setters
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderAmount(double amount) {
        this.orderAmount = amount;
    }

    //Constructors
    /*
     * An empty constructor for Hibernate
     */
    public Order() {
    }

    public Order(long id, Customer customer, LocalDate orderDate, double amount) {
        this.id = id;
        this.customer = customer;
        this.orderDate = orderDate;
        this.orderAmount = amount;
    }
}