package com.macko.services.interfaces;

import java.util.List;

import com.macko.models.Customer;

public interface ICustomerService {

    Customer getCustomerById(long customerId);
    
    List<Customer> getAllCustomers();

    List<Customer> searchCustomerByName(String name);
    
    void saveCustomer(Customer customer);

    void updateCustomerFirstName(long customerId, String fName);

    void deleteCustomer(long customerId);
}
