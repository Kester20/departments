package dao;

import dao.CrudDao;
import dao.HibernateSessionFactory;
import exception.DaoException;
import model.Department;
import model.Employee;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static util.Constants.DbConstants.DEPARTMENT;

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
