package com.aimprosoft.noormal.servicebuilder.service.impl;

import com.aimprosoft.noormal.servicebuilder.NoSuchEmployeeException;
import com.aimprosoft.noormal.servicebuilder.model.Employee;
import com.aimprosoft.noormal.servicebuilder.service.base.EmployeeLocalServiceBaseImpl;
import com.aimprosoft.noormal.servicebuilder.service.persistence.EmployeePersistenceImpl;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * The implementation of the employee local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.aimprosoft.noormal.servicebuilder.service.EmployeeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Arsalan
 * @see com.aimprosoft.noormal.servicebuilder.service.base.EmployeeLocalServiceBaseImpl
 * @see com.aimprosoft.noormal.servicebuilder.service.EmployeeLocalServiceUtil
 */
public class EmployeeLocalServiceImpl extends EmployeeLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.aimprosoft.noormal.servicebuilder.service.EmployeeLocalServiceUtil} to access the employee local service.
     */
    public List<Employee> findByDepartment(long departmentId) throws SystemException {
        return employeePersistence.findByDepartment(departmentId);
    }

    public Employee findByEmail(String email) throws NoSuchEmployeeException, SystemException {
        return employeePersistence.findByEmail(email);
    }
}
