package ua.aimprosoft.noormal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.aimprosoft.noormal.dao.impl.EmployeeDao;
import ua.aimprosoft.noormal.exception.DaoException;
import ua.aimprosoft.noormal.exception.ValidationException;
import ua.aimprosoft.noormal.model.Department;
import ua.aimprosoft.noormal.model.Employee;
import ua.aimprosoft.noormal.service.EmployeeService;
import ua.aimprosoft.noormal.validator.CustomValidator;

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
