package service;

import exception.DaoException;
import exception.ValidationException;
import model.Department;
import model.Employee;

import java.util.List;

/**
 * @author Arsalan
 */
public interface EmployeeService {

    boolean createEmployee(Employee employee) throws DaoException, ValidationException;

    boolean updateEmployee(Employee employee) throws DaoException, ValidationException;

    void deleteEmployee(Employee employee) throws DaoException;

    List<Employee> getEmployeesFromDepartment(Department department) throws DaoException;
}
