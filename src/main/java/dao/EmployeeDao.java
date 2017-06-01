package dao;

import exception.BusinessException;
import model.Employee;

/**
 * @author Arsalan
 */
public interface EmployeeDao {

    void createEmployee(Employee employee) throws BusinessException;

    void editEmployee(Employee employee) throws BusinessException;

    void deleteEmployee(Employee employee) throws BusinessException;

    Employee findOneByEmail(String email) throws BusinessException;

}
