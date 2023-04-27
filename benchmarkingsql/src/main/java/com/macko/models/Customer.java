package com.macko.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/*
 * The purpose of this class is to serve as a mock-up of a real-life class
 * to better simulate a plausible scenario and thus getting more valuable 
 * and relevant information out of it. This class particularly takes on 
 * the role a Customer.   
 */
@Entity
@Table(name = "customer")
public class Customer {
    //Fields
    @Id
    private long id;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "email")
    private String email;

    //Getters
    public long getId() {
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

    @Override
    public String toString() {
        return this.id + ", " + this.firstName + ", " + this.lastName + ", " + this.email;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) obj;
        return this.id == other.id &&
                Objects.equals(this.firstName, other.firstName) &&
                Objects.equals(this.lastName, other.lastName) &&
                Objects.equals(this.email, other.email);
    }

    //Constructors
    /*
     * An empty constructor necessary for Hibernate to create empty objects 
     */
    public Customer() {
    }

    public Customer(long id, String fName, String lName, String email) {
        this.id = id;
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
    }
}
