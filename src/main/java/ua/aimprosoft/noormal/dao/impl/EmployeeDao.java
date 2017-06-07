package ua.aimprosoft.noormal.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import ua.aimprosoft.noormal.dao.HibernateSessionFactory;
import ua.aimprosoft.noormal.exception.DaoException;
import ua.aimprosoft.noormal.model.Department;
import ua.aimprosoft.noormal.model.Employee;

import java.util.List;

import static ua.aimprosoft.noormal.util.Constants.DbConstants.DEPARTMENT;

/**
 * @author Arsalan
 */
public class EmployeeDao extends CrudDao<Employee> {

    public EmployeeDao(Class<Employee> type) {
        super(type);
    }

    public List<Employee> findEmployeesByDepartment(Department department) throws DaoException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Employee.class);
            return criteria.add(Restrictions.eq(DEPARTMENT, department)).list();
        }
    }
}
