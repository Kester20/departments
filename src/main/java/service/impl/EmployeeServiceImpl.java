package service.impl;

import dao.DaoFactory;
import dao.EmployeeDao;
import model.Employee;
import service.EmployeeService;

import java.time.LocalDate;

/**
 * @author Arsalan
 */
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(DaoFactory daoFactory) {
        this.employeeDao = daoFactory.getEmployeeDao();
    }

    @Override
    public void createEmployee(String name, int age, String date) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setAge(age);
        LocalDate localDate = LocalDate.parse(date);
        employee.setDateOfBirth(localDate);
        employeeDao.createEmployee(employee);
    }

    @Override
    public void updateEmployee(int id, String name, int age, String date) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setAge(age);
        LocalDate localDate = LocalDate.parse(date);
        employee.setDateOfBirth(localDate);
        employeeDao.updateEmployee(employee);
    }

    @Override
    public void deleteEmployee(int id) {
        Employee employee = new Employee();
        employee.setId(id);
        employeeDao.deleteEmployee(employee);
    }
}
