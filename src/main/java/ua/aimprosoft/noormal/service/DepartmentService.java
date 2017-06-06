package ua.aimprosoft.noormal.service;

import ua.aimprosoft.noormal.exception.DaoException;
import ua.aimprosoft.noormal.exception.ValidationException;
import ua.aimprosoft.noormal.model.Department;

import java.util.List;

/**
 * @author Arsalan
 */
public interface DepartmentService {

    void saveDepartment(Department department) throws DaoException, ValidationException;

    void deleteDepartment(Department department) throws DaoException;

    List<Department> findDepartments() throws DaoException;

    Department findOne(Long departmentId) throws DaoException;

}
