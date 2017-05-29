package dao.impl;

import dao.EmployeeDao;

import javax.sql.DataSource;

/**
 * @author Arsalan
 */
public class EmployeeDaoImpl implements EmployeeDao {

    private DataSource dataSource;

    public EmployeeDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
