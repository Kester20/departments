package dao.impl;

import dao.DepartmentDao;
import exception.BusinessException;
import model.Department;
import model.Employee;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static util.Constants.Messages.CAN_NOT_CLOSE_RESOURCE;
import static util.Constants.Messages.CAN_NOT_CREATE_DEPARTMENT;
import static util.Constants.Messages.CAN_NOT_DELETE_DEPARTMENT;
import static util.Constants.Messages.CAN_NOT_EDIT_DEPARTMENT;
import static util.Constants.Messages.CAN_NOT_FIND_DEPARTMENT;
import static util.Constants.Messages.CAN_NOT_GET_DEPARTMENTS;
import static util.Constants.Messages.CAN_NOT_GET_EMPLOYEES;
import static util.Constants.QueryConstants.CREATE_DEPARTMENT;
import static util.Constants.QueryConstants.DELETE_DEPARTMENT;
import static util.Constants.QueryConstants.FIND_DEPARTMENT;
import static util.Constants.QueryConstants.FIND_DEPARTMENT_BY_NAME;
import static util.Constants.QueryConstants.GET_DEPARTMENTS;
import static util.Constants.QueryConstants.GET_DEPARTMENT_EMPLOYEES;
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
    public void createDepartment(Department department) throws BusinessException {
        String sql = CREATE_DEPARTMENT;
        Connection connection = null;
        PreparedStatement prStatement = null;

        try {
            connection = dataSource.getConnection();
            prStatement = connection.prepareStatement(sql);
            prStatement.setString(1, department.getName());
            prStatement.execute();
        } catch (SQLException e) {
            throw new BusinessException(CAN_NOT_CREATE_DEPARTMENT);
        } finally {
            try {
                if (prStatement != null) {
                    prStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new BusinessException(CAN_NOT_CLOSE_RESOURCE);
            }
        }
    }

    @Override
    public void editDepartment(Department department) throws BusinessException {
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
            throw new BusinessException(CAN_NOT_EDIT_DEPARTMENT);
        } finally {
            try {
                if (prStatement != null) {
                    prStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new BusinessException(CAN_NOT_CLOSE_RESOURCE);
            }
        }
    }

    @Override
    public void deleteDepartment(Department department) throws BusinessException {
        String sql = DELETE_DEPARTMENT;
        Connection connection = null;
        PreparedStatement prStatement = null;

        try {
            connection = dataSource.getConnection();
            prStatement = connection.prepareStatement(sql);
            prStatement.setInt(1, department.getId());
            prStatement.execute();
        } catch (SQLException e) {
            throw new BusinessException(CAN_NOT_DELETE_DEPARTMENT);
        } finally {
            try {
                if (prStatement != null) {
                    prStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new BusinessException(CAN_NOT_CLOSE_RESOURCE);
            }
        }
    }

    @Override
    public List<Employee> getEmployees(Department department) throws BusinessException {
        String sql = GET_DEPARTMENT_EMPLOYEES;
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
                LocalDate date = resultSet.getDate(4).toLocalDate();
                String email = resultSet.getString(5);
                Employee employee = new Employee(id, name, age, date, email);
                employees.add(employee);
            }

        } catch (SQLException e) {
            throw new BusinessException(CAN_NOT_GET_EMPLOYEES);
        } finally {
            try {
                if (prStatement != null) {
                    prStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new BusinessException(CAN_NOT_CLOSE_RESOURCE);
            }
        }
        return employees;
    }

    @Override
    public List<Department> getDepartments() throws BusinessException {
        String sql = GET_DEPARTMENTS;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Department> departments = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            statement.execute(sql);
            resultSet = statement.getResultSet();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Department department = new Department(id, name);
                departments.add(department);
            }

        } catch (SQLException e) {
            throw new BusinessException(CAN_NOT_GET_DEPARTMENTS);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new BusinessException(CAN_NOT_CLOSE_RESOURCE);
            }
        }
        return departments;
    }

    @Override
    public Department findOne(Integer id) throws BusinessException {
        String sql = FIND_DEPARTMENT;
        Connection connection = null;
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        Department department = new Department();

        try {
            connection = dataSource.getConnection();
            prStatement = connection.prepareStatement(sql);
            prStatement.setInt(1, id);
            prStatement.execute();
            resultSet = prStatement.getResultSet();
            if (resultSet.next()) {
                int idDepartment = resultSet.getInt(1);
                String name = resultSet.getString(2);
                department.setId(idDepartment);
                department.setName(name);
            }

        } catch (SQLException e) {
            throw new BusinessException(CAN_NOT_FIND_DEPARTMENT);
        } finally {
            try {
                if (prStatement != null) {
                    prStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new BusinessException(CAN_NOT_CLOSE_RESOURCE);
            }
        }
        return department;
    }

    @Override
    public Department findOneByName(String departmentName) throws BusinessException {
        String sql = FIND_DEPARTMENT_BY_NAME;
        Connection connection = null;
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        Department department = null;

        try {
            connection = dataSource.getConnection();
            prStatement = connection.prepareStatement(sql);
            prStatement.setString(1, departmentName);
            prStatement.execute();
            resultSet = prStatement.getResultSet();
            if (resultSet.next()) {
                int idDepartment = resultSet.getInt(1);
                String name = resultSet.getString(2);
                department = new Department();
                department.setId(idDepartment);
                department.setName(name);
            }

        } catch (SQLException e) {
            throw new BusinessException(CAN_NOT_FIND_DEPARTMENT);
        } finally {
            try {
                if (prStatement != null) {
                    prStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new BusinessException(CAN_NOT_CLOSE_RESOURCE);
            }
        }
        return department;
    }
}
