package dao;

import exception.BusinessException;
import model.Department;
import model.Employee;

import java.util.List;

/**
 * @author Arsalan
 */
public interface DepartmentDao {

    void createDepartment(Department department) throws BusinessException;

    void editDepartment(Department department) throws BusinessException;

    void deleteDepartment(Department department) throws BusinessException;

    List<Employee> getEmployees(Department department) throws BusinessException;

    List<Department> getDepartments() throws BusinessException;

    Department findOne(Integer id) throws BusinessException;

    Department findOneByName(String name) throws BusinessException;
}
