package ua.aimprosoft.noormal.dao;

import org.springframework.stereotype.Component;
import ua.aimprosoft.noormal.dao.impl.DepartmentDao;
import ua.aimprosoft.noormal.dao.impl.EmployeeDao;
import ua.aimprosoft.noormal.model.Department;
import ua.aimprosoft.noormal.model.Employee;

import javax.annotation.PostConstruct;

/**
 * @author Arsalan
 */
@Component
public class DaoFactory {

    private static DepartmentDao departmentDao;
    private static EmployeeDao employeeDao;

    private DaoFactory() {
    }

    @PostConstruct
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
