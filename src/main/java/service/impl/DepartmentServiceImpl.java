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
    public boolean createDepartment(String name) {
        Department existDepartment = departmentDao.findOneByName(name);
        if(existDepartment == null){
            Department department = new Department();
            department.setName(name);
            departmentDao.createDepartment(department);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateDepartment(int id, String name) {
        Department existDepartment = departmentDao.findOneByName(name);
        if(existDepartment == null || existDepartment.getId() == id){
            Department department = new Department();
            department.setId(id);
            department.setName(name);
            departmentDao.updateDepartment(department);
            return true;
        }
        return false;
    }

    @Override
    public void deleteDepartment(int id) {
        Department department = new Department();
        department.setId(id);
        departmentDao.deleteDepartment(department);
    }

    @Override
    public List<Employee> getEmployees(int id) {
        Department department = new Department();
        department.setId(id);
        return departmentDao.getEmployees(department);
    }

    @Override
    public List<Department> getDepartments() {
        return departmentDao.getDepartments();
    }
}
