package com.macko;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.macko.models.Customer;
import com.macko.services.hibernate_services.HCustomerService;
import com.macko.services.interfaces.ICustomerService;
import com.macko.services.native_sql_services.NCustomerService;

/**
 * This class is responsible for the benchmarking.
 */
public class Benchmarker 
{
    public static Random rand = new Random();
    private static List<Long> ids = new ArrayList<Long>();
    ////////////////// MAIN ////////////////////

    public static void main( String[] args )
    {
        ICustomerService nCService = new NCustomerService();
        ICustomerService hCService = new HCustomerService();

        //Benchmarker.benchmarkSavingCustomers(5000);
        // Benchmarker.benchmarkGettingAllCustomers(1000);

        List<Long> ids = new ArrayList<>();
        
        int index = 0;
        while (index < 2000) {
            List<Customer> results = nCService.searchCustomerByName(getRandomFirstName());
            for (Customer customer : results) {
                if (!ids.contains(customer.getId()))
                ids.add(customer.getId());
            }            
            index++;
        }

        index = 0;
        while(index < 2000) {
            List<Customer> results = hCService.searchCustomerByName(getRandomFirstName());
            for (Customer customer : results) {
                if (!ids.contains(customer.getId()))
                ids.add(customer.getId());
            }            
            index++;
        }
        /* 
        index = 0;
        while (index < 2000) {
            nCService.getCustomerById(ids.get(rand.nextInt(ids.size())));
            index++;
        }

        index = 0;
        
        while(index < 2000) {
            hCService.getCustomerById(ids.get(rand.nextInt(ids.size())));
            index++;
        }
        */
        
        index = 0;
        while (index < 2000) {
            nCService.deleteCustomer(ids.get(rand.nextInt(ids.size())));
            index++;
        }

        index = 0;
        
        while(index < 2000) {
            hCService.deleteCustomer(ids.get(rand.nextInt(ids.size())));
            index++;
        }
    }

    /////////// BENCHMARKING METHODS //////////////
    
    public static void benchmarkSavingCustomers(int limit) {
        ICustomerService nCService = new NCustomerService();
        ICustomerService hCService = new HCustomerService();

        int currentIndex = 0;
        while (currentIndex < limit) {
            nCService.saveCustomer(createMockCustomer());
            currentIndex++;
        }

        currentIndex = 0;

        while (currentIndex < limit) {
            hCService.saveCustomer(createMockCustomer());
            currentIndex++;
        }
    }

    public static void benchmarkGettingAllCustomers(int methodCalls) {
        ICustomerService nCService = new NCustomerService();
        ICustomerService hCService = new HCustomerService();

        int index = 0;
        while (index < methodCalls) {
            nCService.getAllCustomers();
            index++;
        }

        index = 0;
        
        while(index < methodCalls) {
            hCService.getAllCustomers();
            index++;
        }
    }

    /*
     * It is necessary to run this benchmark along with another that utilizes the generateRandomLong() method
     */
    public static void benchmarkGetCustomerById(int methodCalls, long id) {
        ICustomerService nCService = new NCustomerService();
        ICustomerService hCService = new HCustomerService();

        int index = 0;
        while (index < methodCalls) {
            nCService.getCustomerById(id);
            index++;
        }

        index = 0;
        
        while(index < methodCalls) {
            hCService.getCustomerById(id);
            index++;
        }
    }

    public static void benchmarkSearchCustomerByName(int methodCalls) {
        ICustomerService nCService = new NCustomerService();
        ICustomerService hCService = new HCustomerService();

        int index = 0;
        while (index < methodCalls) {
            nCService.searchCustomerByName(getRandomFirstName());
            index++;
        }

        index = 0;
        
        while(index < methodCalls) {
            hCService.searchCustomerByName(getRandomFirstName());
            index++;
        }
    }

    /*
     * It is necessary to run this along other benchmarking methods
     */
    public static void benchmarkUpdateCustomerFirstName(int methodCalls, long id) {
        ICustomerService nCService = new NCustomerService();
        ICustomerService hCService = new HCustomerService();

        int index = 0;
        while (index < methodCalls) {
            nCService.updateCustomerFirstName(id, Benchmarker.getRandomFirstName());
            index++;
        }

        index = 0;
        
        while(index < methodCalls) {
            hCService.updateCustomerFirstName(id, Benchmarker.getRandomFirstName());
            index++;
        }
    }

    public static void benchmarkDeleteCustomer(int methodCalls, long id) {
        ICustomerService nCService = new NCustomerService();
        ICustomerService hCService = new HCustomerService();

        int index = 0;
        while (index < methodCalls) {
            nCService.deleteCustomer(id);
            index++;
        }

        index = 0;
        
        while(index < methodCalls) {
            hCService.deleteCustomer(id);
            index++;
        }
    }

    //////////////////// HELPER METHODS //////////////////////
    public static synchronized long generateRandomLong() {
        long currentMillis = System.currentTimeMillis();
        long randomBits = rand.nextLong() & 0x3FFFFFFF; // use 30 random bits
        long id = (currentMillis << 30) | randomBits;
        id = id * (-1);
        ids.add(id);
        return id;
    }

    public static long pickRandomId() {
        int index = rand.nextInt(ids.size());
        return ids.get(index);
    }

    public static Customer createMockCustomer() {
        long id = generateRandomLong();
        String fName = getRandomFirstName();
        String lName = getRandomSurname();
        String email = fName + lName + rand.nextInt(100) + "@example.com";
        
        Customer customer = new Customer(id, fName, lName, email);
        return customer;
    }    

    public static String getRandomFirstName() {
        String[] firstNames = {"Oliver", "Emma", "Noah", "Sophia", "Liam", "Ava", "Ethan", "Isabella", "Aiden", 
                                "Mia", "Jackson", "Charlotte", "Lucas", "Amelia", "Mason", "Harper", "Logan", 
                                "Evelyn", "Caleb", "Abigail", "Sebastian", "Emily", "Elijah", "Elizabeth", "Michael", 
                                "Avery", "Gabriel", "Sofia", "Benjamin", "Chloe", "Aarav", "Adelina", "Aliyah", "Amara",
                                "Anais", "Ananya", "Andres", "Aria", "Asher", "Aurelia", "Bao", "Bianca", "Caleb", "Catalina", 
                                "Daria", "Dax", "Elian", "Eliza", "Emiliano", "Enzo", "Esteban", "Felix", "Gabriela", "Gideon", 
                                "Giselle", "Hugo", "Isabela", "Jasper", "Joaquin", "Kai", "Khalil", "Lena", "Levi", "Lila", 
                                "Luciano", "Makai", "Matilda", "Nahla", "Nash", "Niamh", "Nikolai", "Orlando", "Paloma", 
                                "Paxton", "Quinn", "Rohan", "Sarai", "Santino", "Sienna", "Thiago"};
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

}
