package service.impl;

import dao.DaoFactory;
import dao.EmployeeDao;
import exception.DaoException;
import exception.ValidationException;
import model.Employee;
import service.EmployeeService;
import validator.CustomValidator;

import java.util.List;

/**
 * @author Arsalan
 */
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    public EmployeeServiceImpl() {
        this.employeeDao = DaoFactory.getEmployeeDao();
    }

    @Override
    public boolean saveEmployee(Employee employee) throws DaoException, ValidationException {
        CustomValidator.validate(employee);
        employeeDao.saveEmployee(employee);
        return true;
    }

    @Override
    public void deleteEmployee(Employee employee) throws DaoException {
        employeeDao.deleteEmployee(employee);
    }

    @Override
    public List<Employee> findEmployeesByDepartment(Integer departmentId) throws DaoException {
        return employeeDao.findEmployeesByDepartment(departmentId);
    }
}
