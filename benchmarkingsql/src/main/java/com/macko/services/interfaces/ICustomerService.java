package com.macko.services.interfaces;

import java.util.List;
import java.util.UUID;

import com.macko.models.Customer;

public interface ICustomerService {

    Customer getCustomerById(UUID customerId);
    
    List<Customer> getAllCustomers();

    List<Customer> searchCustomerByName(String name);
    
    void saveCustomer(Customer customer);

    void updateCustomerFirstName(UUID customerId);

    void deleteCustomer(UUID custolerId);
}
