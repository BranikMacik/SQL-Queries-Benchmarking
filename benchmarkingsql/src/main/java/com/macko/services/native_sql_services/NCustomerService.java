package com.macko.services.native_sql_services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import com.macko.models.Customer;
import com.macko.services.DatabaseEnums;
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

    /*
     * saveCustomer method utilizes PreparedStatement and an SQL String to execute the query. 
     * The String is filled with all relevant values and before the statement is executed, a timestamp is saved into
     * the @startTime variable. After the execution, another timestamp is saved and the total time is calculated and printed. 
     */
    @Override
    public void saveCustomer(Customer customer) {
        try (Connection connection = DriverManager.getConnection(DatabaseEnums.DB_URL, DatabaseEnums.Credentials.DATABASE_USER.label, DatabaseEnums.Credentials.DATABASE_PASSWORD.label)) {
            String SQL_message_information = "INSERT INTO " + DatabaseEnums.Tables.customer + " VALUES (?, ?, ?, ?) ON CONFLICT (id) DO NOTHING";    
            PreparedStatement statement = connection.prepareStatement(SQL_message_information);
            statement.setObject(1, customer.getId());
            statement.setString(2, customer.getFirstName());
            statement.setString(3, customer.getLastName());
            statement.setString(4, customer.getEmail());

            long startTime = System.currentTimeMillis();

            int rowsAffected = statement.executeUpdate();
            
            long endTime = System.currentTimeMillis();

            if (rowsAffected == 0) {
                //Throw exception
                System.out.println("Something went wrong saving the customer. " + rowsAffected + " rows affected.");
            } else {
                System.out.print(rowsAffected + " rows have been inserted. ");
            }

            long totalTime = endTime - startTime;

            System.out.println("Total execution time: " + totalTime + " ms");

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong saving the customer. ");
            e.printStackTrace();
        }
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
