package com.aimprosoft.noormal.dao.impl;

import com.aimprosoft.noormal.dao.HibernateSessionFactory;
import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.model.Department;
import com.aimprosoft.noormal.model.Employee;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.aimprosoft.noormal.util.Constants.DbConstants.DEPARTMENT;

/**
 * @author Arsalan
 */
@Repository
public class EmployeeDao extends CrudDao<Employee> {

    public List<Employee> findEmployeesByDepartment(Department department) throws DaoException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Criteria criteria = session.createCriteria(Employee.class);
            return criteria.add(Restrictions.eq(DEPARTMENT, department)).list();
        }
    }
}
