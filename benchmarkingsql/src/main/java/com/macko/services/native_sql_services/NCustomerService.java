package com.macko.services.native_sql_services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.macko.BenchmarkLogger;
import com.macko.models.Customer;
import com.macko.services.DatabaseEnums;
import com.macko.services.interfaces.ICustomerService;

public class NCustomerService implements ICustomerService{
    /*
     * Method getCustomerById takes a parameter of UUID (@customerId) which it then inserts into a preparedStatement.
     * The method returns a Customer object if found in the database and null if none found.
     */
    @Override
    public Customer getCustomerById(UUID customerId) {
        try ( Connection connection = DriverManager.getConnection(DatabaseEnums.DB_URL, 
                                                                  DatabaseEnums.Credentials.DATABASE_USER.label, 
                                                                  DatabaseEnums.Credentials.DATABASE_PASSWORD.label)
        ) {
            Customer customer = null;
            String sql = "SELECT * FROM " + DatabaseEnums.Tables.customer + " WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, customerId);
                        
            long startTime = System.currentTimeMillis();
            
            ResultSet result = statement.executeQuery();

            if (!result.next()) {
                System.out.println("No data found. ");
            } else {
                customer = new Customer(customerId, result.getString(2), result.getString(3), result.getString(4));
            }
           
            long endTime = System.currentTimeMillis();

            long totalTime = endTime - startTime;

            //System.out.println("Total execution time: " + totalTime + " ms");
            BenchmarkLogger.writeResult("getCustomerById - Native SQL", totalTime);

            if (customer == null) {
                statement.close();
                connection.close();
                return null;
            } else {
                statement.close();
                connection.close();
                return customer;
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("Something went wrong finding the user in the database.");
            System.out.println(e);
            return null;
        }
    }

    /* 
     * Returns a list of all customers currently persisted in the database
    */
    @Override
    public List<Customer> getAllCustomers() {
        try ( Connection connection = DriverManager.getConnection(DatabaseEnums.DB_URL, 
                                                                  DatabaseEnums.Credentials.DATABASE_USER.label, 
                                                                  DatabaseEnums.Credentials.DATABASE_PASSWORD.label)
        ) {
            String sql = "SELECT * FROM " + DatabaseEnums.Tables.customer;
            PreparedStatement statement = connection.prepareStatement(sql);
            
            ResultSet result = statement.executeQuery();
            List<Customer> customers = new ArrayList<Customer>();

            long startTime = System.currentTimeMillis();

            int index = 0;
            if (!result.next()) {
                System.out.println("No data found. ");
            } else if (result.next()){
                while (result.next()) {
                    UUID uuid = (UUID) result.getObject(1);
                    Customer customer = new Customer(uuid, result.getString(2), result.getString(3), result.getString(3));
                    customers.add(index, customer);
                }
            }
            
            long endTime = System.currentTimeMillis();
            
            long totalTime = endTime - startTime;

            BenchmarkLogger.writeResult("getAllCustomers - Native SQL", totalTime);
            
            statement.close();
            connection.close();
            return customers;
        } catch (SQLException | NullPointerException e) {
            System.out.println("Something went wrong finding the user in the database.");
            System.out.println(e);
            return null;
        }
    }

    /*
     * Method searchCustomerByName searches for all Customer objects that fit the String param @name regardless of whether it is the first or the second name
     */
    @Override
    public List<Customer> searchCustomerByName(String name) {
        try ( Connection connection = DriverManager.getConnection(DatabaseEnums.DB_URL, 
                                                                  DatabaseEnums.Credentials.DATABASE_USER.label, 
                                                                  DatabaseEnums.Credentials.DATABASE_PASSWORD.label)
        ) {
            //Using LIKE operator with wildcard '%' on both sides of the search string will match any string of any length, so that it will match both complete names as well as partial ones.
            String sql = "SELECT * FROM " + DatabaseEnums.Tables.customer + " WHERE first_name LIKE ? OR last_name LIKE ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + name + "%");

            long startTime = System.currentTimeMillis();
            
            ResultSet result = statement.executeQuery();
            List<Customer> customers = new ArrayList<Customer>();

            int index = 0;
            if (!result.next()) {
                System.out.println("No data found. ");
            } else if (result.next()){
                while (result.next()) {
                    UUID uuid = (UUID) result.getObject(1);
                    Customer customer = new Customer(uuid, result.getString(2), result.getString(3), result.getString(3));
                    customers.add(index, customer);
                }
            }
            
            long endTime = System.currentTimeMillis();
            
            long totalTime = endTime - startTime;

            //System.out.println("Total execution time: " + totalTime + " ms");
            BenchmarkLogger.writeResult("searchCustomerByName - Native SQL", totalTime);
            
            statement.close();
            connection.close();
            return customers;
        } catch (SQLException | NullPointerException e) {
            System.out.println("Something went wrong finding the user in the database.");
            System.out.println(e);
            return null;
        }
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

            BenchmarkLogger.writeResult("saveCustomer - Native SQL", totalTime);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong saving the customer. ");
            e.printStackTrace();
        }
    }

    @Override
    public void updateCustomerFirstName(UUID customerId, String firstName) {

    }

    /*
     * Method deleteCustomer deletes a customer object from the database with the UUID of @param customerId
     */
    @Override
    public void deleteCustomer(UUID customerId) {
        try ( Connection connection = DriverManager.getConnection(DatabaseEnums.DB_URL, 
                                                                  DatabaseEnums.Credentials.DATABASE_USER.label, 
                                                                  DatabaseEnums.Credentials.DATABASE_PASSWORD.label)
        ) {
            String sql = "DELETE FROM " + DatabaseEnums.Tables.customer + " WHERE id " + customerId;
            PreparedStatement statement = connection.prepareStatement(sql);
                        
            long startTime = System.currentTimeMillis();
            
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No rows affected.");
            } else if (rowsAffected == 1) {
                System.out.println("Customer successfully deleted.");
            } else {
                System.out.println("Something went wrong and multiple customers were deleted.");
            }
            
            long endTime = System.currentTimeMillis();
            
            long totalTime = endTime - startTime;

            BenchmarkLogger.writeResult("getAllCustomers - Native SQL", totalTime);
            
            statement.close();
            connection.close();
        } catch (SQLException | NullPointerException e) {
            System.out.println("Something went wrong finding the user in the database.");
            System.out.println(e);
        }    
    }
}
