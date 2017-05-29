package dao;

import entity.Employee;

/**
 * @author Arsalan
 */
public interface EmployeeDao {

    void createEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(Employee employee);

}
