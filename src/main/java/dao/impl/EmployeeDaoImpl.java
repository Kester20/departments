package dao.impl;

import dao.EmployeeDao;
import entity.Employee;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static util.Constants.QueryConstants.CREATE_EMPLOYEE;
import static util.Constants.QueryConstants.DELETE_EMPLOYEE;
import static util.Constants.QueryConstants.UPDATE_EMPLOYEE;

/**
 * @author Arsalan
 */
public class EmployeeDaoImpl implements EmployeeDao {

    private DataSource dataSource;

    public EmployeeDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createEmployee(Employee employee) {
        String sql = CREATE_EMPLOYEE;
        Connection connection = null;
        PreparedStatement prStatement = null;

        try {
            connection = dataSource.getConnection();
            prStatement = connection.prepareStatement(sql);
            prStatement.setString(1, employee.getName());
            prStatement.setInt(2, employee.getAge());
            prStatement.setObject(3, Date.valueOf(employee.getDateOfBirth()));
            prStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prStatement != null) {
                    prStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        String sql = UPDATE_EMPLOYEE;
        Connection connection = null;
        PreparedStatement prStatement = null;

        try {
            connection = dataSource.getConnection();
            prStatement = connection.prepareStatement(sql);
            prStatement.setString(1, employee.getName());
            prStatement.setInt(2, employee.getAge());
            prStatement.setObject(3, Date.valueOf(employee.getDateOfBirth()));
            prStatement.setInt(4, employee.getId());
            prStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prStatement != null) {
                    prStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteEmployee(Employee employee) {
        String sql = DELETE_EMPLOYEE;
        Connection connection = null;
        PreparedStatement prStatement = null;

        try {
            connection = dataSource.getConnection();
            prStatement = connection.prepareStatement(sql);
            prStatement.setInt(1, employee.getId());
            prStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(prStatement != null){
                    prStatement.close();
                }
                if(connection != null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
