package org.example;



import javax.persistence.*;

@Entity
@Table(name = "employees") // Maps to the 'employees' table in the database
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
    @Column(name = "id") // Maps to the 'id' column
    private int id;

    @Column(name = "name", nullable = false) // Maps to the 'name' column, not nullable
    private String name;

    @Column(name = "department") // Maps to the 'department' column
    private String department;

    @Column(name = "salary") // Maps to the 'salary' column
    private double salary;

    // Constructors
    public Employee() {}

    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    // No setter for 'id' since it's auto-generated

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // toString() for easy printing
    @Override
    public String toString() {
        return "Employee { " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                " }";
    }
}
