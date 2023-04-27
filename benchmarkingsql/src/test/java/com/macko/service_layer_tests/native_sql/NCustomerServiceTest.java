package com.macko.service_layer_tests.native_sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.macko.Benchmarker;
import com.macko.models.Customer;
import com.macko.services.DatabaseEnums;
import com.macko.services.interfaces.ICustomerService;
import com.macko.services.native_sql_services.NCustomerService;

public class NCustomerServiceTest {
    //Fields
    ICustomerService cService;

    @Before
    public void prepareForTest() {
        this.cService = new NCustomerService();
    }
    
    @Test
    public void testConnectionToDatabase() {
        try (Connection connection = DriverManager.getConnection(DatabaseEnums.DB_URL, 
                                                                 DatabaseEnums.Credentials.DATABASE_USER.label, 
                                                                 DatabaseEnums.Credentials.DATABASE_PASSWORD.label)) {
            System.out.println("Connected");

            connection.close();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testGetCustomerById() {
        Customer customer = cService.getCustomerById(1172076883381449323L);
        long id = 1172076883381449323L;
        Customer actualCustomer = new Customer(id, "Orlando", "Green", "OrlandoGreen95@example.com");
        boolean result = actualCustomer.equals(customer);
        System.out.println(result);
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> customers = cService.getAllCustomers();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    @Test
    public void testSearchCustomerByName() {
        List<Customer> customers = cService.searchCustomerByName("Caleb");
        for (Customer customer : customers) {
            System.out.println(customer);
        }

        List<Customer> otherCustomers = cService.searchCustomerByName("White");
        for (Customer customer : otherCustomers) {
            System.out.println(customer);
        }
    }

    @Test 
    public void testSaveCustomer() {

    }

    @Test 
    public void testUpdateCustomerFirstName() {
        Customer randomCustomer = Benchmarker.createMockCustomer();
        cService.saveCustomer(randomCustomer);
        cService.updateCustomerFirstName(randomCustomer.getId(), "Mstislav");
        Customer ourCustomer = new Customer(randomCustomer.getId(), "Mstislav", randomCustomer.getLastName(), randomCustomer.getEmail());
        Customer compCustomer = cService.getCustomerById(randomCustomer.getId());
        System.out.println(ourCustomer.equals(compCustomer));
        cService.deleteCustomer(randomCustomer.getId());
    }

    @Test 
    public void testDeleteCustomer() {
        Customer randomCustomer = Benchmarker.createMockCustomer();
        cService.saveCustomer(randomCustomer);
        cService.deleteCustomer(randomCustomer.getId());
        assertNull(cService.getCustomerById(randomCustomer.getId()));
    }

    /*      // USE WITH CAUTION  
    @After
    public void cleanDatabase() {
        try ( Connection connection = DriverManager.getConnection(DatabaseEnums.DB_URL, 
                                                                  DatabaseEnums.Credentials.DATABASE_USER.label, 
                                                                  DatabaseEnums.Credentials.DATABASE_PASSWORD.label)
        ) {
            String sql = "DELETE * FROM " + DatabaseEnums.Tables.customer;
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.close();
            connection.close();
        } catch (SQLException | NullPointerException e) {
            System.out.println("Something went wrong finding the user in the database.");
            e.printStackTrace();;
        }
    }
    */
}
