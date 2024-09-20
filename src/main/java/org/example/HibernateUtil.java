package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the Configuration object
            Configuration configuration = new Configuration();

            // Database connection settings
            configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/example_hibernate");
            configuration.setProperty("hibernate.connection.username", "root");
            configuration.setProperty("hibernate.connection.password", "Ts123456!");

            // SQL dialect
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

            // Show SQL
            configuration.setProperty("hibernate.show_sql", "true");

            // HBM2DDL
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");

            // Add annotated classes
            configuration.addAnnotatedClass(Employee.class);

            // Build the SessionFactory
            return configuration.buildSessionFactory();

        } catch (Throwable ex) {
            // Log the exception to track it
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Getter for the SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Shutdown the SessionFactory
    public static void shutdown() {
        getSessionFactory().close();
    }
}
