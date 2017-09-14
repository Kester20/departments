package com.aimprosoft.noormal.servicebuilder.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;


public class EmployeeFinderUtil {
    private static EmployeeFinder _finder;

    public static java.util.List<com.aimprosoft.noormal.servicebuilder.model.Employee> findByDepartment(
        com.aimprosoft.noormal.servicebuilder.model.Department department,
        int start, int end) {
        return getFinder().findByDepartment(department, start, end);
    }

    public static java.lang.Integer getCountByDepartment(
        com.aimprosoft.noormal.servicebuilder.model.Department department) {
        return getFinder().getCountByDepartment(department);
    }

    public static EmployeeFinder getFinder() {
        if (_finder == null) {
            _finder = (EmployeeFinder) PortletBeanLocatorUtil.locate(com.aimprosoft.noormal.servicebuilder.service.ClpSerializer.getServletContextName(),
                    EmployeeFinder.class.getName());

            ReferenceRegistry.registerReference(EmployeeFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(EmployeeFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(EmployeeFinderUtil.class, "_finder");
    }
}
