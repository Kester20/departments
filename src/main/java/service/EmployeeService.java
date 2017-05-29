package service;

/**
 * @author Arsalan
 */
public interface EmployeeService {

    void createEmployee(String name, int age, String date);

    void updateEmployee(int id, String name, int age, String date);

    void deleteEmployee(int id);
}
