package com.macko.services.native_sql_services;

import java.util.List;
import java.util.UUID;

import com.macko.models.Customer;
import com.macko.services.interfaces.ICustomerService;

public class NCustomerService implements ICustomerService{

    @Override
    public Customer getCustomerById(UUID customerId) {
        throw new UnsupportedOperationException("Unimplemented method 'getCustomerById'");
    }

    @Override
    public List<Customer> getAllCustomers() {
        throw new UnsupportedOperationException("Unimplemented method 'getAllCustomers'");
    }

    @Override
    public List<Customer> searchCustomerByName(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'searchCustomerByName'");
    }

    @Override
    public void saveCustomer(Customer customer) {
        throw new UnsupportedOperationException("Unimplemented method 'saveCustomer'");
    }

    @Override
    public void updateCustomerFirstName(UUID customerId) {
        throw new UnsupportedOperationException("Unimplemented method 'updateCustomerFirstName'");
    }

    @Override
    public void deleteCustomer(UUID custolerId) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteCustomer'");
    }
    
}
