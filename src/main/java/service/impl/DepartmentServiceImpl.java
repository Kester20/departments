package service.impl;

import dao.DaoFactory;
import dao.DepartmentDao;
import exception.DaoException;
import exception.ValidationException;
import model.Department;
import service.DepartmentService;
import validator.CustomValidator;

import java.util.List;

/**
 * @author Arsalan
 */
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDao departmentDao;

    public DepartmentServiceImpl() {
        this.departmentDao = DaoFactory.getDepartmentDao();
    }

    @Override
    public void saveDepartment(Department department) throws DaoException, ValidationException {
        CustomValidator.validate(department);
        departmentDao.saveDepartment(department);
    }

    @Override
    public void deleteDepartment(Department department) throws DaoException {
        departmentDao.deleteDepartment(department);
    }

    @Override
    public Department findOne(Integer departmentId) throws DaoException {
        return departmentDao.findOne(departmentId);
    }

    @Override
    public List<Department> getDepartments() throws DaoException {
        return departmentDao.getDepartments();
    }
}
