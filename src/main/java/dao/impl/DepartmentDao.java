package dao.impl;

import model.Department;

/**
 * @author Arsalan
 */
public class DepartmentDao extends CrudDao<Department> {

    public DepartmentDao(Class<Department> type) {
        super(type);
    }
}
