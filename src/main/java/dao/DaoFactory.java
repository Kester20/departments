package dao;

import dao.impl.DepartmentDaoImpl;
import dao.impl.EmployeeDaoImpl;

import javax.sql.DataSource;

/**
 * @author Arsalan
 */
public class DaoFactory {

    private static DepartmentDao departmentDao;
    private static EmployeeDao employeeDao;

    private DaoFactory(){};

    public static void initDaoFactory(DataSource dataSource) {
        departmentDao = new DepartmentDaoImpl(dataSource);
        employeeDao = new EmployeeDaoImpl(dataSource);
    }

    public static DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public static EmployeeDao getEmployeeDao() {
        return employeeDao;
    }
}
