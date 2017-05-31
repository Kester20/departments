package dao.impl;

import dao.EmployeeDao;
import model.Department;
import model.Employee;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static util.Constants.QueryConstants.CREATE_EMPLOYEE;
import static util.Constants.QueryConstants.DELETE_EMPLOYEE;
import static util.Constants.QueryConstants.FIND_DEPARTMENT_BY_NAME;
import static util.Constants.QueryConstants.FIND_EMPLOYEE_BY_EMAIL;
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
            prStatement.setString(4, employee.getEmail());
            prStatement.setInt(5, employee.getDepartment().getId());
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
            prStatement.setString(4, employee.getEmail());
            prStatement.setInt(5, employee.getId());
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

    @Override
    public Employee findOneByEmail(String employeeEmail) {
        String sql = FIND_EMPLOYEE_BY_EMAIL;
        Connection connection = null;
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        Employee employee = null;

        try {
            connection = dataSource.getConnection();
            prStatement = connection.prepareStatement(sql);
            prStatement.setString(1, employeeEmail);
            prStatement.execute();
            resultSet = prStatement.getResultSet();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                LocalDate date = resultSet.getDate(4).toLocalDate();
                String email = resultSet.getString(5);
                employee = new Employee(id, name, age, date, email);
            }

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
                if(resultSet != null){
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employee;
    }
}
