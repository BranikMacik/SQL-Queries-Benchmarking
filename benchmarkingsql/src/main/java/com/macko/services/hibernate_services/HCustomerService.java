package com.macko.services.hibernate_services;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.macko.BenchmarkLogger;
import com.macko.models.Customer;
import com.macko.services.interfaces.ICustomerService;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

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
            BenchmarkLogger.writeResult("getCustomerById | Hibernate", totalTime);

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            DatabaseSessionManager.getInstance().closeSession();
        }

        return customer;
    } 

    /*
     * getAllCustomers method returns all the Customer currently available in the database
     */
    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = null;
        try (Session session = DatabaseSessionManager.getInstance().getSession()) {
            long startTime = System.currentTimeMillis();
            
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
            query.from(Customer.class);
            customers = session.createQuery(query).getResultList();
            
            long endTime = System.currentTimeMillis();

            if (customers.isEmpty()) {
                System.out.println("No data found. ");
            }

            long totalTime = endTime - startTime;
            BenchmarkLogger.writeResult("getAllCustomers | Hibernate", totalTime);

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            DatabaseSessionManager.getInstance().closeSession();
        }

        return customers;
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
            long startTime = System.currentTimeMillis();
            session.beginTransaction();

            session.persist(customer);

            session.getTransaction().commit();
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            BenchmarkLogger.writeResult("saveCustomer | Hibernate", totalTime);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            DatabaseSessionManager.getInstance().closeSession();
        }
    }

    /*
     * updateCustomerFirstName updates the first name property of an existing entry in the database
     */
    @Override
    public void updateCustomerFirstName(long customerId, String firstName) {
        try {
            Session session = DatabaseSessionManager.getInstance().getSession();
            long startTime = System.currentTimeMillis();
            session.beginTransaction();
    
            Customer customer = session.get(Customer.class, customerId);
            if (customer != null) {
                customer.setFirstName(firstName);

                session.merge(customer);

                session.getTransaction().commit();

                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;
                BenchmarkLogger.writeResult("updateCustomerFirstName | Hibernate", totalTime);
            } else {
                System.out.println("No data found for customer with id " + customerId);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            DatabaseSessionManager.getInstance().closeSession();
        }
    }

    @Override
    public void deleteCustomer(long customerId) {
        try {
            Session session = DatabaseSessionManager.getInstance().getSession();
            long startTime = System.currentTimeMillis();
            session.beginTransaction();
    
            // retrieve customer to be deleted
            Customer customer = session.find(Customer.class, customerId);
    
            // delete the customer
            if (customer != null) {
                session.remove(customer);
            } else {
                System.out.println("Customer with id " + customerId + " not found.");
            }
    
            session.getTransaction().commit();
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            BenchmarkLogger.writeResult("deleteCustomer | Hibernate", totalTime);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            DatabaseSessionManager.getInstance().closeSession();
        }
    }
    
}
