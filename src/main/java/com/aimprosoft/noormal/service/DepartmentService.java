package com.aimprosoft.noormal.service;

import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.exception.ValidationException;
import com.aimprosoft.noormal.model.Department;

import java.util.List;

/**
 * @author Arsalan
 */
public interface DepartmentService {

    void saveDepartment(Department department) throws DaoException, ValidationException;

    void deleteDepartment(Department department) throws DaoException;

    List<Department> findDepartments(Integer page) throws DaoException;

    Department findOne(Long departmentId) throws DaoException;

    Integer getTotalDepartments() throws DaoException;
}
