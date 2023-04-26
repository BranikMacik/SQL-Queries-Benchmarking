package com.macko;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.macko.models.Customer;
import com.macko.services.hibernate_services.HCustomerService;
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

    public static synchronized long generateRandomLong() {
        long currentMillis = System.currentTimeMillis();
        long randomBits = rand.nextLong() & 0x3FFFFFFF; // use 30 random bits
        long id = (currentMillis << 30) | randomBits;
        id = id * (-1);
        return id;
    }

    public static Customer createMockCustomer() {
        long id = generateRandomLong();
        String fName = getRandomFirstName();
        String lName = getRandomSurname();
        String email = fName + lName + rand.nextInt(100) + "@example.com";
        
        Customer customer = new Customer(id, fName, lName, email);
        return customer;
    }    
    
    public static void main( String[] args )
    {
        ICustomerService nCService = new NCustomerService();
        ICustomerService hCService = new HCustomerService();
        
        nCService.saveCustomer(createMockCustomer());
        nCService.getAllCustomers();
        nCService.searchCustomerByName(getRandomFirstName());
        nCService.searchCustomerByName(getRandomSurname());

        hCService.saveCustomer(createMockCustomer());
        //hCService.getCustomerById(null);
    }
}
