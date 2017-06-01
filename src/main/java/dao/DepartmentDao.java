package dao;

import exception.DaoException;
import model.Department;

import java.util.List;

/**
 * @author Arsalan
 */
public interface DepartmentDao {

    void createDepartment(Department department) throws DaoException;

    void editDepartment(Department department) throws DaoException;

    void deleteDepartment(Department department) throws DaoException;

    List<Department> getDepartments() throws DaoException;

    Department findOne(Integer id) throws DaoException;

    Department findOneByName(String name) throws DaoException;
}
