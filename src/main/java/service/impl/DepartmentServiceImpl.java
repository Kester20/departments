package service.impl;

import dao.DaoFactory;
import dao.impl.DepartmentDao;
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

    private DepartmentDao departmentDao = DaoFactory.getDepartmentDao();

    public DepartmentServiceImpl() {
        this.departmentDao = DaoFactory.getDepartmentDao();
    }

    @Override
    public void saveDepartment(Department department) throws DaoException, ValidationException {
        CustomValidator.validate(department);
        departmentDao.saveEntity(department);
    }

    @Override
    public void deleteDepartment(Department department) throws DaoException {
        departmentDao.deleteEntity(department);
    }

    @Override
    public Department findOne(Long departmentId) throws DaoException {
        return departmentDao.findOne(departmentId);
    }

    @Override
    public List<Department> findDepartments() throws DaoException {
        return departmentDao.findAllEntities();
    }
}
