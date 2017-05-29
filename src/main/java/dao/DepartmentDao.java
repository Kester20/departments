package dao;

import model.Department;
import model.Employee;

import java.util.List;

/**
 * @author Arsalan
 */
public interface DepartmentDao {

    void createDepartment(Department department);

    void updateDepartment(Department department);

    void deleteDepartment(Department department);

    List<Employee> showEmployees(Department department);

    List<Department> getDepartments();
}
