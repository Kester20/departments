package com.aimprosoft.noormal.servicebuilder.service.persistence;

import com.aimprosoft.noormal.servicebuilder.model.Employee;
import com.aimprosoft.noormal.servicebuilder.service.EmployeeLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Arsalan
 * @generated
 */
public abstract class EmployeeActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public EmployeeActionableDynamicQuery() throws SystemException {
        setBaseLocalService(EmployeeLocalServiceUtil.getService());
        setClass(Employee.class);

        setClassLoader(com.aimprosoft.noormal.servicebuilder.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("employeeId");
    }
}
