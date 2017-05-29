package dao.impl;

import dao.DepartmentDao;
import entity.Department;
import entity.Employee;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static util.Constants.QueryConstants.CREATE_DEPARTMENT;
import static util.Constants.QueryConstants.DELETE_DEPARTMENT;
import static util.Constants.QueryConstants.SHOW_DEPARTMENT_EMPLOYEES;
import static util.Constants.QueryConstants.UPDATE_DEPARTMENT;

/**
 * @author Arsalan
 */
public class DepartmentDaoImpl implements DepartmentDao {

    private DataSource dataSource;

    public DepartmentDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createDepartment(Department department) {
        String sql = CREATE_DEPARTMENT;
        Connection connection = null;
        PreparedStatement prStatement = null;

        try {
            connection = dataSource.getConnection();
            prStatement = connection.prepareStatement(sql);
            prStatement.setString(1, department.getName());
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
    public void updateDepartment(Department department) {
        String sql = UPDATE_DEPARTMENT;
        Connection connection = null;
        PreparedStatement prStatement = null;

        try {
            connection = dataSource.getConnection();
            prStatement = connection.prepareStatement(sql);
            prStatement.setString(1, department.getName());
            prStatement.setInt(2, department.getId());
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
    public void deleteDepartment(Department department) {
        String sql = DELETE_DEPARTMENT;
        Connection connection = null;
        PreparedStatement prStatement = null;

        try {
            connection = dataSource.getConnection();
            prStatement = connection.prepareStatement(sql);
            prStatement.setInt(1, department.getId());
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
    public List<Employee> showEmployees(Department department) {
        String sql = SHOW_DEPARTMENT_EMPLOYEES;
        Connection connection = null;
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        List<Employee> employees = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            prStatement = connection.prepareStatement(sql);
            prStatement.setInt(1, department.getId());
            prStatement.execute();
            resultSet = prStatement.getResultSet();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                LocalDate date = resultSet.getObject(4, LocalDate.class);
                Employee employee = new Employee(id, name, age, date);
                employees.add(employee);
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
        return employees;
    }
}
