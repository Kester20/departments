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

    void saveEmployee(Employee employee) throws DaoException, ValidationException;

    void deleteEmployee(Employee employee) throws DaoException;

    List<Employee> findEmployeesByDepartment(Department department) throws DaoException;
}
