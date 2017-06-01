package dao.impl;

import dao.EmployeeDao;
import exception.DaoException;
import model.Department;
import model.Employee;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static util.Constants.Messages.CAN_NOT_CREATE_EMPLOYEE;
import static util.Constants.Messages.CAN_NOT_DELETE_EMPLOYEE;
import static util.Constants.Messages.CAN_NOT_EDIT_EMPLOYEE;
import static util.Constants.Messages.CAN_NOT_FIND_EMPLOYEE;
import static util.Constants.Messages.CAN_NOT_GET_EMPLOYEES;
import static util.Constants.QueryConstants.CREATE_EMPLOYEE;
import static util.Constants.QueryConstants.DELETE_EMPLOYEE;
import static util.Constants.QueryConstants.FIND_EMPLOYEE_BY_EMAIL;
import static util.Constants.QueryConstants.GET_DEPARTMENT_EMPLOYEES;
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
    public void createEmployee(Employee employee) throws DaoException {
        String sql = CREATE_EMPLOYEE;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement prStatement = connection.prepareStatement(sql)) {
            prStatement.setString(1, employee.getName());
            prStatement.setInt(2, employee.getAge());
            prStatement.setObject(3, Date.valueOf(employee.getDateOfBirth()));
            prStatement.setString(4, employee.getEmail());
            prStatement.setInt(5, employee.getDepartment().getId());
            prStatement.execute();
        } catch (SQLException e) {
            throw new DaoException(CAN_NOT_CREATE_EMPLOYEE);
        }
    }

    @Override
    public void editEmployee(Employee employee) throws DaoException {
        String sql = UPDATE_EMPLOYEE;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement prStatement = connection.prepareStatement(sql)) {
            prStatement.setString(1, employee.getName());
            prStatement.setInt(2, employee.getAge());
            prStatement.setObject(3, Date.valueOf(employee.getDateOfBirth()));
            prStatement.setString(4, employee.getEmail());
            prStatement.setInt(5, employee.getId());
            prStatement.execute();
        } catch (SQLException e) {
            throw new DaoException(CAN_NOT_EDIT_EMPLOYEE);
        }
    }

    @Override
    public void deleteEmployee(Employee employee) throws DaoException {
        String sql = DELETE_EMPLOYEE;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement prStatement = connection.prepareStatement(sql)) {
            prStatement.setInt(1, employee.getId());
            prStatement.execute();
        } catch (SQLException e) {
            throw new DaoException(CAN_NOT_DELETE_EMPLOYEE);
        }
    }

    @Override
    public Employee findOneByEmail(String employeeEmail) throws DaoException {
        String sql = FIND_EMPLOYEE_BY_EMAIL;
        Employee employee = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement prStatement = connection.prepareStatement(sql)) {
            prStatement.setString(1, employeeEmail);
            prStatement.execute();
            ResultSet resultSet = prStatement.getResultSet();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                LocalDate date = resultSet.getDate(4).toLocalDate();
                String email = resultSet.getString(5);
                employee = new Employee(id, name, age, date, email);
            }

        } catch (SQLException e) {
            throw new DaoException(CAN_NOT_FIND_EMPLOYEE);
        }
        return employee;
    }

    @Override
    public List<Employee> getEmployeesFromDepartment(Department department) throws DaoException {
        String sql = GET_DEPARTMENT_EMPLOYEES;
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement prStatement = connection.prepareStatement(sql)) {
            prStatement.setInt(1, department.getId());
            prStatement.execute();
            ResultSet resultSet = prStatement.getResultSet();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                LocalDate date = resultSet.getDate(4).toLocalDate();
                String email = resultSet.getString(5);
                Employee employee = new Employee(id, name, age, date, email);
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new DaoException(CAN_NOT_GET_EMPLOYEES);
        }
        return employees;
    }
}
