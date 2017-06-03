package dao;

import exception.DaoException;
import model.Department;
import model.Employee;

import java.util.List;

/**
 * @author Arsalan
 */
public interface EmployeeDao {

    void saveEmployee(Employee employee) throws DaoException;

    void deleteEmployee(Employee employee) throws DaoException;

    Employee findOneByEmail(String email) throws DaoException;

    List<Employee> findEmployeesByDepartment(Integer departmentId) throws DaoException;

}
