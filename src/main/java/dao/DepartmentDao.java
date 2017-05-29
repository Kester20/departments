package dao;

import entity.Department;
import entity.Employee;

import java.util.List;

/**
 * @author Arsalan
 */
public interface DepartmentDao {

    void createDepartment(Department department);

    void updateDepartment(Department department);

    void deleteDepartment(Department department);

    List<Employee> showEmployees(Department department);

}
