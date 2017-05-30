package service;

import model.Department;
import model.Employee;

import java.util.List;

/**
 * @author Arsalan
 */
public interface DepartmentService {

    boolean createDepartment(String name);

    boolean updateDepartment(int id, String name);

    void deleteDepartment(int id);

    List<Employee> getEmployees(int id);

    List<Department> getDepartments();
}
