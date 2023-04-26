package com.macko.services.hibernate_services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DatabaseSessionManager {
    private Session currentSession;
    private SessionFactory sessionFactory;
    private static DatabaseSessionManager sessionManager;

    protected void setUp() throws Exception {
        // A SessionFactory only set up once for an application
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.XML") // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
        }
        
    }

    // Private constructor following the Singleton pattern
    private DatabaseSessionManager() {
        try {
            setUp();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize DatabaseSessionManager", e);
        }
    }

    // getInstance method for retrieving the singleton
    public static DatabaseSessionManager getInstance() {
        if (sessionManager == null) {
            sessionManager = new DatabaseSessionManager();
        }

        return sessionManager;
    }

    /*
     * getSession method to retrieve the current session that is opened
     * If no session is open, a new session is created and returned
     */
    public Session getSession() {
        if (currentSession == null || !currentSession.isOpen()) {
            currentSession = sessionFactory.openSession();
        }
        return currentSession;
    }

    // closeSession method checks for an open session and closes it
    public void closeSession() {
        Session session = sessionFactory.getCurrentSession();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}
