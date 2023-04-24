package com.macko;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
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
    public static Random rand = new Random();
    
    public static String getRandomFirstName() {
        String[] firstNames = {"Oliver", "Emma", "Noah", "Sophia", "Liam", "Ava", "Ethan", "Isabella", "Aiden", 
                                "Mia", "Jackson", "Charlotte", "Lucas", "Amelia", "Mason", "Harper", "Logan", 
                                "Evelyn", "Caleb", "Abigail", "Sebastian", "Emily", "Elijah", "Elizabeth", "Michael", 
                                "Avery", "Gabriel", "Sofia", "Benjamin", "Chloe"};
        List<String> firstNamesList = new ArrayList<>(Arrays.asList(firstNames));
        int index = rand.nextInt(firstNamesList.size());
        return firstNamesList.get(index);
    }

    public static String getRandomSurname() {
        String[] surnames = {"Smith", "Johnson", "Brown", "Garcia", "Davis", "Rodriguez", "Miller", "Martinez",
                             "Taylor", "Wilson", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin",
                             "Thompson", "Moore", "Young", "Allen", "King", "Wright", "Scott", "Green", "Baker",
                             "Adams", "Nelson", "Carter", "Mitchell", "Perez", "Roberts", "Turner", "Phillips",
                             "Campbell", "Parker", "Evans", "Edwards", "Collins", "Stewart", "Sanchez"};
        List<String> surnameList = new ArrayList<>(Arrays.asList(surnames));
        int index = rand.nextInt(surnameList.size());
        return surnameList.get(index);
    }

    public static Customer createMockCustomer() {
        Customer mockCustomer = mock(Customer.class);
        
        String fName = getRandomFirstName();
        String lName = getRandomSurname();
        String email = fName + lName + rand.nextInt(100) + "@example.com";
        
        when(mockCustomer.getId()).thenReturn(UUID.randomUUID());
        when(mockCustomer.getFirstName()).thenReturn(fName);
        when(mockCustomer.getLastName()).thenReturn(lName);
        when(mockCustomer.getEmail()).thenReturn(email);
        
        return mockCustomer;
    }    
    
    public static void main( String[] args )
    {
        ICustomerService cNService = new NCustomerService();
        
        cNService.saveCustomer(createMockCustomer());
        cNService.getAllCustomers();
    }
}
