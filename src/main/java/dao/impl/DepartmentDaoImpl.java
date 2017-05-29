package dao.impl;

import dao.DepartmentDao;
import entity.Department;
import entity.Employee;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static util.Constants.QueryConstants.CREATE_DEPARTMENT;

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
                prStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateDepartment(Department department) {

    }

    @Override
    public void deleteDepartment(Department department) {

    }

    @Override
    public List<Employee> showEmployees() {
        return null;
    }
}
