package service.impl;

import dao.DaoFactory;
import dao.DepartmentDao;
import model.Department;
import model.Employee;
import service.DepartmentService;

import java.util.List;

/**
 * @author Arsalan
 */
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDao departmentDao;

    public DepartmentServiceImpl(DaoFactory daoFactory) {
        this.departmentDao = daoFactory.getDepartmentDao();
    }

    @Override
    public void createDepartment(String name) {
        Department department = new Department();
        department.setName(name);
        departmentDao.createDepartment(department);
    }

    @Override
    public void updateDepartment(int id, String name) {
        Department department = new Department();
        department.setId(id);
        department.setName(name);
        departmentDao.updateDepartment(department);
    }

    @Override
    public void deleteDepartment(int id) {
        Department department = new Department();
        department.setId(id);
        departmentDao.deleteDepartment(department);
    }

    @Override
    public List<Employee> showEmployees(int id) {
        Department department = new Department();
        department.setId(id);
        return departmentDao.showEmployees(department);
    }
}
