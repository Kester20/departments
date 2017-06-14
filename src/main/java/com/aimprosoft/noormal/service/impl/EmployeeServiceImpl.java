package com.aimprosoft.noormal.service.impl;

import com.aimprosoft.noormal.dao.impl.EmployeeDao;
import com.aimprosoft.noormal.exception.ValidationException;
import com.aimprosoft.noormal.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.model.Employee;
import com.aimprosoft.noormal.service.EmployeeService;
import com.aimprosoft.noormal.validator.CustomValidator;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = DaoException.class)
    @Override
    public void saveEmployee(Employee employee) throws DaoException, ValidationException {
        CustomValidator.validate(employee);
        employeeDao.saveEntity(employee);
    }

    @Transactional(rollbackFor = DaoException.class)
    @Override
    public void deleteEmployee(Employee employee) throws DaoException {
        employeeDao.deleteEntity(employee);
    }

    @Override
    public List<Employee> findEmployeesByDepartment(Department department) throws DaoException {
        return employeeDao.findEmployeesByDepartment(department);
    }

    @Override
    public Employee findOne(Long employeeId) throws DaoException {
        return employeeDao.findOne(employeeId);
    }
}
