package com.aimprosoft.noormal.dao;

import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.model.Department;
import com.aimprosoft.noormal.model.Employee;

import java.util.List;

/**
 * @author Arsalan
 */
public interface EmployeeDao extends Dao<Employee> {

    List<Employee> findEmployeesByDepartment(Department department) throws DaoException;
}
