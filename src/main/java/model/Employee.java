package model;

import java.time.LocalDate;

/**
 * @author Arsalan
 */
public class Employee {

    private int id;
    private String name;
    private int age;
    private LocalDate dateOfBirth;

    public Employee(){

    }

    public Employee(int id, String name, int age, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != employee.id) return false;
        if (age != employee.age) return false;
        if (!name.equals(employee.name)) return false;
        return dateOfBirth.equals(employee.dateOfBirth);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + age;
        result = 31 * result + dateOfBirth.hashCode();
        return result;
    }


}
