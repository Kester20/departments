package com.aimprosoft.noormal.dao.impl;

import com.aimprosoft.noormal.dao.EmployeeDao;
import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.model.Department;
import com.aimprosoft.noormal.model.Employee;
import com.aimprosoft.noormal.util.Constants;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.aimprosoft.noormal.util.Constants.DbConstants.DEPARTMENT;

/**
 * @author Arsalan
 */
@Repository
public class EmployeeDaoImpl extends CrudDao<Employee> implements EmployeeDao {

    @Override
    public List<Employee> findEmployeesByDepartment(Department department, Integer page, Integer itemsPerPage) throws DaoException {
        try {
            Session session = getCurrentSession();
            Criteria criteria = session.createCriteria(Employee.class);
            return criteria.add(Restrictions.eq(DEPARTMENT, department)).setFirstResult((page - 1) * itemsPerPage).setMaxResults(itemsPerPage).list();
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public Integer getTotalEmployeesInDepartment(Department department) throws DaoException {
        try {
            Session session = getCurrentSession();
            Criteria criteria = session.createCriteria(Employee.class);
            criteria.add(Restrictions.eq(DEPARTMENT, department));
            return ((Long)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
        } catch (Exception e) {
            throw new DaoException(Constants.Messages.CAN_NOT_COUNT_ENTITIES);
        }
    }
}
