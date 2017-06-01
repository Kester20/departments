package dao;

import exception.DaoException;
import model.Department;
import model.Employee;

import java.util.List;

/**
 * @author Arsalan
 */
public interface EmployeeDao {

    void createEmployee(Employee employee) throws DaoException;

    void editEmployee(Employee employee) throws DaoException;

    void deleteEmployee(Employee employee) throws DaoException;

    Employee findOneByEmail(String email) throws DaoException;

    List<Employee> getEmployeesFromDepartment(Department department) throws DaoException;

}
