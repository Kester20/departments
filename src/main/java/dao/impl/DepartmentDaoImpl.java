package dao.impl;

import dao.DepartmentDao;
import exception.DaoException;
import model.Department;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static util.Constants.Messages.CAN_NOT_CREATE_DEPARTMENT;
import static util.Constants.Messages.CAN_NOT_DELETE_DEPARTMENT;
import static util.Constants.Messages.CAN_NOT_EDIT_DEPARTMENT;
import static util.Constants.Messages.CAN_NOT_FIND_DEPARTMENT;
import static util.Constants.Messages.CAN_NOT_GET_DEPARTMENTS;
import static util.Constants.QueryConstants.CREATE_DEPARTMENT;
import static util.Constants.QueryConstants.DELETE_DEPARTMENT;
import static util.Constants.QueryConstants.FIND_DEPARTMENT;
import static util.Constants.QueryConstants.FIND_DEPARTMENT_BY_NAME;
import static util.Constants.QueryConstants.GET_DEPARTMENTS;
import static util.Constants.QueryConstants.EDIT_DEPARTMENT;

/**
 * @author Arsalan
 */
public class DepartmentDaoImpl implements DepartmentDao {

    private DataSource dataSource;

    public DepartmentDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void saveDepartment(Department department) throws DaoException {
        String sql;
        if(department.getId() == null){
            sql = CREATE_DEPARTMENT;
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement prStatement = connection.prepareStatement(sql)) {
                prStatement.setString(1, department.getName());
                prStatement.execute();
            } catch (SQLException e) {
                throw new DaoException(CAN_NOT_CREATE_DEPARTMENT);
            }
        }else{
            sql = EDIT_DEPARTMENT;
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement prStatement = connection.prepareStatement(sql)) {
                prStatement.setString(1, department.getName());
                prStatement.setInt(2, department.getId());
                prStatement.execute();
            } catch (SQLException e) {
                throw new DaoException(CAN_NOT_EDIT_DEPARTMENT);
            }
        }
    }

    @Override
    public void deleteDepartment(Department department) throws DaoException {
        String sql = DELETE_DEPARTMENT;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement prStatement = connection.prepareStatement(sql)) {
            prStatement.setInt(1, department.getId());
            prStatement.execute();
        } catch (SQLException e) {
            throw new DaoException(CAN_NOT_DELETE_DEPARTMENT);
        }
    }

    @Override
    public List<Department> getDepartments() throws DaoException {
        String sql = GET_DEPARTMENTS;
        List<Department> departments = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Department department = new Department(id, name);
                departments.add(department);
            }
        } catch (SQLException e) {
            throw new DaoException(CAN_NOT_GET_DEPARTMENTS);
        }
        return departments;
    }

    @Override
    public Department findOne(Integer id) throws DaoException {
        String sql = FIND_DEPARTMENT;
        Department department = new Department();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement prStatement = connection.prepareStatement(sql)) {
            prStatement.setInt(1, id);
            prStatement.execute();
            ResultSet resultSet = prStatement.getResultSet();
            if (resultSet.next()) {
                int idDepartment = resultSet.getInt(1);
                String name = resultSet.getString(2);
                department.setId(idDepartment);
                department.setName(name);
            }
        } catch (SQLException e) {
            throw new DaoException(CAN_NOT_FIND_DEPARTMENT);
        }
        return department;
    }

    @Override
    public Department findOneByName(String departmentName) throws DaoException {
        String sql = FIND_DEPARTMENT_BY_NAME;
        Department department = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement prStatement = connection.prepareStatement(sql)) {
            prStatement.setString(1, departmentName);
            prStatement.execute();
            ResultSet resultSet = prStatement.getResultSet();
            if (resultSet.next()) {
                int idDepartment = resultSet.getInt(1);
                String name = resultSet.getString(2);
                department = new Department();
                department.setId(idDepartment);
                department.setName(name);
            }
        } catch (SQLException e) {
            throw new DaoException(CAN_NOT_FIND_DEPARTMENT);
        }
        return department;
    }
}
