package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        // Obtain SessionFactory and open a Session
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        // Begin Transaction
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            // Create and save new Employees
            Employee emp1 = new Employee("John Doe", "Engineering", 75000);
            Employee emp2 = new Employee("Jane Smith", "Marketing", 65000);
            Employee emp3 = new Employee("Mike Johnson", "Engineering", 80000);

            session.save(emp1);
            session.save(emp2);
            session.save(emp3);

            // Commit Transaction
            transaction.commit();
            System.out.println("Employees saved successfully!");

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        // Open a new Session for querying
        Session newSession = sessionFactory.openSession();
        Transaction newTransaction = null;
        try {
            newTransaction = newSession.beginTransaction();

            // HQL Query: Fetch all employees from Engineering department
            String hql = "FROM Employee E WHERE E.department = :dept";
            Query<Employee> query = newSession.createQuery(hql, Employee.class);
            query.setParameter("dept", "Engineering");
            List<Employee> engineeringEmployees = query.list();

            System.out.println("Engineering Department Employees:");
            for (Employee emp : engineeringEmployees) {
                System.out.println(emp);
            }

            // Another HQL Query: Fetch employees with salary greater than 70000
            String hqlSalary = "FROM Employee E WHERE E.salary > :salary";
            Query<Employee> salaryQuery = newSession.createQuery(hqlSalary, Employee.class);
            salaryQuery.setParameter("salary", 70000.0);
            List<Employee> highEarners = salaryQuery.list();

            System.out.println("\nEmployees with Salary > 70000:");
            for (Employee emp : highEarners) {
                System.out.println(emp);
            }

            newTransaction.commit();

        } catch (Exception e) {
            if (newTransaction != null) newTransaction.rollback();
            e.printStackTrace();
        } finally {
            newSession.close();
        }

        // Shutdown the SessionFactory
        HibernateUtil.shutdown();
    }
}
