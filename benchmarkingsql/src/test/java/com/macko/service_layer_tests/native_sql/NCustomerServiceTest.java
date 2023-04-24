package com.macko.service_layer_tests.native_sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.macko.services.DatabaseEnums;

public class NCustomerServiceTest {
    //Fields

    @Before
    public void prepareForTest() {

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
}
