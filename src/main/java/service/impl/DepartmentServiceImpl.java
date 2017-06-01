package service.impl;

import dao.DaoFactory;
import dao.DepartmentDao;
import model.Department;
import model.Employee;
import service.DepartmentService;
import validator.DepartmentValidator;

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
    public boolean createDepartment(String name) {
        Department department = new Department();
        department.setName(name);
        if (validateDepartment(department)) {
            departmentDao.createDepartment(department);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateDepartment(Integer id, String name) {
        Department department = new Department();
        department.setId(id);
        department.setName(name);
        if (validateDepartment(department)) {
            departmentDao.editDepartment(department);
            return true;
        }
        return false;
    }

    @Override
    public void deleteDepartment(Integer id) {
        Department department = new Department();
        department.setId(id);
        departmentDao.deleteDepartment(department);
    }

    @Override
    public List<Employee> getEmployees(Integer id) {
        Department department = new Department();
        department.setId(id);
        return departmentDao.getEmployees(department);
    }

    @Override
    public boolean validateDepartment(Department department) {
        return DepartmentValidator.validate(department);
    }

    @Override
    public List<Department> getDepartments() {
        return departmentDao.getDepartments();
    }
}
