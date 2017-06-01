package service.impl;

import dao.DaoFactory;
import dao.DepartmentDao;
import dao.EmployeeDao;
import model.Department;
import model.Employee;
import service.EmployeeService;

import java.time.LocalDate;

/**
 * @author Arsalan
 */
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;
    private DepartmentDao departmentDao;

    public EmployeeServiceImpl() {
        this.employeeDao = DaoFactory.getEmployeeDao();
        this.departmentDao = DaoFactory.getDepartmentDao();
    }

    @Override
    public boolean createEmployee(String name, int age, String date, String email, int departmentId) {
        Employee existEmployee = employeeDao.findOneByEmail(email);
        if (existEmployee == null) {
            Department department = departmentDao.findOne(departmentId);
            Employee employee = new Employee();
            employee.setName(name);
            employee.setAge(age);
            LocalDate localDate = LocalDate.parse(date);
            employee.setDateOfBirth(localDate);
            employee.setDepartment(department);
            employee.setEmail(email);
            employeeDao.createEmployee(employee);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateEmployee(int id, String name, int age, String date, String email) {
        Employee existEmployee = employeeDao.findOneByEmail(email);
        if (existEmployee == null || existEmployee.getId() == id) {
            Employee employee = new Employee();
            employee.setId(id);
            employee.setName(name);
            employee.setAge(age);
            LocalDate localDate = LocalDate.parse(date);
            employee.setDateOfBirth(localDate);
            employee.setEmail(email);
            employeeDao.editEmployee(employee);
            return true;
        }
        return false;
    }

    @Override
    public void deleteEmployee(int id) {
        Employee employee = new Employee();
        employee.setId(id);
        employeeDao.deleteEmployee(employee);
    }
}
