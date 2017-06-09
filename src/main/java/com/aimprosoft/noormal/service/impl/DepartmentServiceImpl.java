package com.aimprosoft.noormal.service.impl;

import com.aimprosoft.noormal.dao.impl.DepartmentDao;
import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.exception.ValidationException;
import com.aimprosoft.noormal.model.Department;
import com.aimprosoft.noormal.service.DepartmentService;
import com.aimprosoft.noormal.validator.CustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = DaoException.class)
    @Override
    public void saveDepartment(Department department) throws DaoException, ValidationException {
        CustomValidator.validate(department);
        departmentDao.saveEntity(department);
    }

    @Transactional(rollbackFor = DaoException.class)
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
