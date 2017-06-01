package service;

import model.Department;
import model.Employee;

import java.util.List;

/**
 * @author Arsalan
 */
public interface DepartmentService {

    boolean createDepartment(String name);

    boolean updateDepartment(Integer id, String name);

    void deleteDepartment(Integer id);

    List<Employee> getEmployees(Integer id);

    List<Department> getDepartments();

    boolean validateDepartment(Department department);
}
