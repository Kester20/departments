package service;

/**
 * @author Arsalan
 */
public interface EmployeeService {

    boolean createEmployee(String name, int age, String date, String email, int departmentId);

    boolean updateEmployee(int id, String name, int age, String date, String email);

    void deleteEmployee(int id);
}
