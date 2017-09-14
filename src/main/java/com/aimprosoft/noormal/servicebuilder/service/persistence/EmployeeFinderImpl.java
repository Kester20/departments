package com.aimprosoft.noormal.servicebuilder.service.persistence;

import com.aimprosoft.noormal.servicebuilder.model.Department;
import com.aimprosoft.noormal.servicebuilder.model.Employee;
import com.aimprosoft.noormal.servicebuilder.model.impl.EmployeeImpl;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Arsalan
 */
public class EmployeeFinderImpl extends BasePersistenceImpl<Employee> implements EmployeeFinder {

    private static String FIND_EMPLOYEES = EmployeeFinderImpl.class.getName() + ".findByDepartment";
    private static String GET_COUNT = EmployeeFinderImpl.class.getName() + ".getCountByDepartment";

    public List<Employee> findByDepartment(Department department, int start, int end) {
        Session session = openSession();
        String sql = CustomSQLUtil.get(FIND_EMPLOYEES);
        SQLQuery query = session.createSQLQuery(sql);
        query.setCacheable(false);
        query.addEntity("Employee", EmployeeImpl.class);
        QueryPos qPos = QueryPos.getInstance(query);
        qPos.add(department.getDepartmentId());
        return (List<Employee>) QueryUtil.list(query, getDialect(), start, end);
    }

    public Integer getCountByDepartment(Department department) {
        Session session = openSession();
        String sql = CustomSQLUtil.get(GET_COUNT);
        SQLQuery query = session.createSQLQuery(sql);
        query.setCacheable(false);
        QueryPos qPos = QueryPos.getInstance(query);
        qPos.add(department.getDepartmentId());
        return ((BigInteger) query.uniqueResult()).intValue();
    }

}
