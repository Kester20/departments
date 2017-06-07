package ua.aimprosoft.noormal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.aimprosoft.noormal.dao.impl.DepartmentDao;
import ua.aimprosoft.noormal.exception.DaoException;
import ua.aimprosoft.noormal.exception.ValidationException;
import ua.aimprosoft.noormal.model.Department;
import ua.aimprosoft.noormal.service.DepartmentService;
import ua.aimprosoft.noormal.validator.CustomValidator;

import java.util.List;

/**
 * @author Arsalan
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDao departmentDao;

    @Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
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
