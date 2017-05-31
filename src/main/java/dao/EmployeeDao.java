package dao;

import model.Employee;

/**
 * @author Arsalan
 */
public interface EmployeeDao {

    void createEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(Employee employee);

    Employee findOneByEmail(String email);

}
