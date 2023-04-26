package com.macko.service_layer_tests.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.macko.services.DatabaseEnums;

public class HCustomerServiceTest {
    //Fields

    @Before
    public void prepareForTest() {

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
}
