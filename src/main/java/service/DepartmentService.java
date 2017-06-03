package service;

import exception.DaoException;
import exception.ValidationException;
import model.Department;

import java.util.List;

/**
 * @author Arsalan
 */
public interface DepartmentService {

    void saveDepartment(Department department) throws DaoException, ValidationException;

    void deleteDepartment(Department department) throws DaoException;

    List<Department> findDepartments() throws DaoException;

    Department findOne(Integer departmentId) throws DaoException;

}
