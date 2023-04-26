package com.macko.services.hibernate_services;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.macko.BenchmarkLogger;
import com.macko.models.Customer;
import com.macko.services.interfaces.ICustomerService;

public class HCustomerService implements ICustomerService{

    @Override
    public Customer getCustomerById(long customerId) {
        Customer customer = null;
        try (Session session = DatabaseSessionManager.getInstance().getSession()) { 
            long startTime = System.currentTimeMillis();
            customer = session.get(Customer.class, customerId);                    
            long endTime = System.currentTimeMillis();
            
            if (customer == null) {
                System.out.println("No data found. ");
            }

            long totalTime = endTime - startTime;
            BenchmarkLogger.writeResult("getCustomerById - Hibernate", totalTime);

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            DatabaseSessionManager.getInstance().closeSession();
        }

        return customer;
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
     * saveCustomer method saves a customer to the database 
     */
    @Override
    public void saveCustomer(Customer customer) {
        try {
            Session session = DatabaseSessionManager.getInstance().getSession();
            session.beginTransaction();

            session.persist(customer);

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            DatabaseSessionManager.getInstance().closeSession();
        }
    }

    @Override
    public void updateCustomerFirstName(long customerId, String firstName) {
        throw new UnsupportedOperationException("Unimplemented method 'updateCustomerFirstName'");
    }

    @Override
    public void deleteCustomer(long customerId) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteCustomer'");
    }
    
}
