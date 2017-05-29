package dao;

import dao.impl.DepartmentDaoImpl;
import dao.impl.EmployeeDaoImpl;

import javax.sql.DataSource;

/**
 * @author Arsalan
 */
public class DaoFactory {

    private DepartmentDao departmentDao;
    private EmployeeDao employeeDao;

    public DaoFactory(DataSource dataSource) {
        this.departmentDao = new DepartmentDaoImpl(dataSource);
        this.employeeDao = new EmployeeDaoImpl(dataSource);
    }
}
