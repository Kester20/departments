package service.impl;

import dao.DaoFactory;
import dao.EmployeeDao;
import exception.DaoException;
import exception.ValidationException;
import model.Department;
import model.Employee;
import service.EmployeeService;
import validator.CustomValidator;

import java.util.List;

/**
 * @author Arsalan
 */
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao = DaoFactory.getEmployeeDao();

    public EmployeeServiceImpl() {
        this.employeeDao = DaoFactory.getEmployeeDao();
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
