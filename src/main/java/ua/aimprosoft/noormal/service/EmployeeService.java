package ua.aimprosoft.noormal.service;

import ua.aimprosoft.noormal.exception.DaoException;
import ua.aimprosoft.noormal.exception.ValidationException;
import ua.aimprosoft.noormal.model.Department;
import ua.aimprosoft.noormal.model.Employee;

import java.util.List;

/**
 * @author Arsalan
 */
public interface EmployeeService {

    void saveEmployee(Employee employee) throws DaoException, ValidationException;

    void deleteEmployee(Employee employee) throws DaoException;

    List<Employee> findEmployeesByDepartment(Department department) throws DaoException;
}
