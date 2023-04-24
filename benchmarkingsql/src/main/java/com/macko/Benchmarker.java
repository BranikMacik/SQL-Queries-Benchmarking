package com.macko;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.UUID;

import com.macko.models.Customer;
import com.macko.services.interfaces.ICustomerService;
import com.macko.services.native_sql_services.NCustomerService;

/**
 * Hello world!
 *
 */
public class Benchmarker 
{
    public static void main( String[] args )
    {
        Customer mockCustomer = mock(Customer.class);
        ICustomerService cNService = new NCustomerService();

        when(mockCustomer.getId()).thenReturn(UUID.randomUUID());
        when(mockCustomer.getFirstName()).thenReturn("John");
        when(mockCustomer.getLastName()).thenReturn("Doe");
        when(mockCustomer.getEmail()).thenReturn("johndoe@example.com");

        cNService.saveCustomer(mockCustomer);

    }
}
