package com.macko.service_layer_tests.hibernate;

import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.macko.Benchmarker;
import com.macko.models.Customer;
import com.macko.services.DatabaseEnums;
import com.macko.services.hibernate_services.HCustomerService;
import com.macko.services.interfaces.ICustomerService;

public class HCustomerServiceTest {
    //Fields
    ICustomerService cService;

    @Before
    public void prepareForTest() {
        this.cService = new HCustomerService();
    }

    /*
     * The following test checks whether the connection to the database can be established.
     */
    @Test
    public void testConnectionToDatabase() {
        try (Connection connection = DriverManager.getConnection(DatabaseEnums.HDB_URL, 
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
        Customer customer = cService.getCustomerById(1172062573148475799L);
        Customer toCompare = new Customer(1172062573148475799L, "Mia", "Martinez", "MiaMartinez83@example.com");
        System.out.println(customer.equals(toCompare));
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
        List<Customer> customers = cService.searchCustomerByName("Hugo");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    @Test 
    public void testSaveCustomer() {

    }

    @Test 
    public void testUpdateCustomerFirstName() {
        Customer customer = Benchmarker.createMockCustomer();
        cService.saveCustomer(customer);
        cService.updateCustomerFirstName(customer.getId(), "Salvador");
        Customer ourCustomer = cService.getCustomerById(customer.getId());
        System.out.println(customer.equals(ourCustomer)); //Should be false
        cService.deleteCustomer(customer.getId());
    }

    @Test 
    public void testDeleteCustomer() {
        Customer customer = Benchmarker.createMockCustomer();
        cService.saveCustomer(customer);
        cService.deleteCustomer(customer.getId());
        assertNull(cService.getCustomerById(customer.getId()));
    }
}
