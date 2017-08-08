package com.aimprosoft.noormal.servicebuilder.service.persistence;

import com.aimprosoft.noormal.servicebuilder.model.Department;
import com.aimprosoft.noormal.servicebuilder.service.DepartmentLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Arsalan
 * @generated
 */
public abstract class DepartmentActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public DepartmentActionableDynamicQuery() throws SystemException {
        setBaseLocalService(DepartmentLocalServiceUtil.getService());
        setClass(Department.class);

        setClassLoader(com.aimprosoft.noormal.servicebuilder.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("departmentId");
    }
}
