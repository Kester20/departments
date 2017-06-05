package dao;

import model.Department;
import model.Employee;

/**
 * @author Arsalan
 */
public class DaoFactory {

    private static DepartmentDao departmentDao;
    private static EmployeeDao employeeDao;

    private DaoFactory() {
    }

    public static void initDaoFactory() {
        departmentDao = new DepartmentDao(Department.class);
        employeeDao = new EmployeeDao(Employee.class);
    }

    public static DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public static EmployeeDao getEmployeeDao() {
        return employeeDao;
    }
}
