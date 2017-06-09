package com.aimprosoft.noormal.service.impl;

import com.aimprosoft.noormal.dao.impl.EmployeeDao;
import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.exception.ValidationException;
import com.aimprosoft.noormal.model.Department;
import com.aimprosoft.noormal.model.Employee;
import com.aimprosoft.noormal.service.EmployeeService;
import com.aimprosoft.noormal.validator.CustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Arsalan
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public void saveEmployee(Employee employee) throws DaoException, ValidationException {
        CustomValidator.validate(employee);
        employeeDao.saveEntity(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) throws DaoException {
        employeeDao.deleteEntity(employee);
    }

    @Override
    public List<Employee> findEmployeesByDepartment(Department department) throws DaoException {
        return employeeDao.findEmployeesByDepartment(department);
    }
}
