package com.macko.models;

import java.util.UUID;
/*
 * The purpose of this class is to serve as a mock-up of a real-life class
 * to better simulate a plausible scenario and thus getting more valuable 
 * and relevant information out of it. This class particularly takes on 
 * the role a Customer.   
 */

public class Customer {
    //Fields
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;

    //Getters
    public UUID getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    //Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //Constructor
    public Customer(UUID id, String fName, String lName, String email) {
        this.id = id;
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
    }
}
