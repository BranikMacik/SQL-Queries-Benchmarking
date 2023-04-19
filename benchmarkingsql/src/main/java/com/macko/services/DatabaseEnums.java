package com.macko.services;
/*
 * This calss stores the credentials needed to access the database server. By keeping the credentials in a single class, 
 * we can easily update them if they change and ensure that they are used consistently throughout the project. Additionally, 
 * by using an enum to represent the credentials, we can ensure that they are only used in the intended way, and prevent accidental
 * misuse or modification of the credentials. This class should be used by other classes that need to access the database, 
 * but should not be modified directly at runtime.  
 */

public class DatabaseEnums {
    //Database URL used for database connectivity
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/my_benchmarking_database";

    //Enums representing the tables present in the database
    public enum Tables {

    }

    //Enums of the credentials for accessing the database
    public enum Credentials {
        DATABASE_USER ("admin"),
        DATABASE_PASSWORD ("adminadmin");

        public final String label;

        Credentials(String label) {
            this.label = label;
        }
    }
}