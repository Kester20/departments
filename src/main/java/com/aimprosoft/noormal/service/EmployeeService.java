package com.aimprosoft.noormal.service;

import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.exception.ValidationException;
import com.aimprosoft.noormal.model.Department;
import com.aimprosoft.noormal.model.Employee;

import java.util.List;

/**
 * @author Arsalan
 */
public interface EmployeeService {

    void saveEmployee(Employee employee) throws DaoException, ValidationException;

    void deleteEmployee(Employee employee) throws DaoException;

    List<Employee> findEmployeesByDepartment(Department department, Integer page) throws DaoException;

    Employee findOne(Long employeeId) throws DaoException;

    Integer getTotalEmployees(Department department) throws DaoException;
}
