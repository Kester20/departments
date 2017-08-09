package com.aimprosoft.noormal.servicebuilder.service.persistence;

import com.aimprosoft.noormal.servicebuilder.model.Employee;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the employee service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Arsalan
 * @see EmployeePersistenceImpl
 * @see EmployeeUtil
 * @generated
 */
public interface EmployeePersistence extends BasePersistence<Employee> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link EmployeeUtil} to access the employee persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the employees where department = &#63;.
    *
    * @param department the department
    * @return the matching employees
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.aimprosoft.noormal.servicebuilder.model.Employee> findByDepartment(
        long department)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the employees where department = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.aimprosoft.noormal.servicebuilder.model.impl.EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param department the department
    * @param start the lower bound of the range of employees
    * @param end the upper bound of the range of employees (not inclusive)
    * @return the range of matching employees
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.aimprosoft.noormal.servicebuilder.model.Employee> findByDepartment(
        long department, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the employees where department = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.aimprosoft.noormal.servicebuilder.model.impl.EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param department the department
    * @param start the lower bound of the range of employees
    * @param end the upper bound of the range of employees (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching employees
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.aimprosoft.noormal.servicebuilder.model.Employee> findByDepartment(
        long department, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first employee in the ordered set where department = &#63;.
    *
    * @param department the department
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching employee
    * @throws com.aimprosoft.noormal.servicebuilder.NoSuchEmployeeException if a matching employee could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.aimprosoft.noormal.servicebuilder.model.Employee findByDepartment_First(
        long department,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.aimprosoft.noormal.servicebuilder.NoSuchEmployeeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first employee in the ordered set where department = &#63;.
    *
    * @param department the department
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching employee, or <code>null</code> if a matching employee could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.aimprosoft.noormal.servicebuilder.model.Employee fetchByDepartment_First(
        long department,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last employee in the ordered set where department = &#63;.
    *
    * @param department the department
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching employee
    * @throws com.aimprosoft.noormal.servicebuilder.NoSuchEmployeeException if a matching employee could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.aimprosoft.noormal.servicebuilder.model.Employee findByDepartment_Last(
        long department,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.aimprosoft.noormal.servicebuilder.NoSuchEmployeeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last employee in the ordered set where department = &#63;.
    *
    * @param department the department
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching employee, or <code>null</code> if a matching employee could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.aimprosoft.noormal.servicebuilder.model.Employee fetchByDepartment_Last(
        long department,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the employees before and after the current employee in the ordered set where department = &#63;.
    *
    * @param employeeId the primary key of the current employee
    * @param department the department
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next employee
    * @throws com.aimprosoft.noormal.servicebuilder.NoSuchEmployeeException if a employee with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.aimprosoft.noormal.servicebuilder.model.Employee[] findByDepartment_PrevAndNext(
        long employeeId, long department,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.aimprosoft.noormal.servicebuilder.NoSuchEmployeeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the employees where department = &#63; from the database.
    *
    * @param department the department
    * @throws SystemException if a system exception occurred
    */
    public void removeByDepartment(long department)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of employees where department = &#63;.
    *
    * @param department the department
    * @return the number of matching employees
    * @throws SystemException if a system exception occurred
    */
    public int countByDepartment(long department)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the employee where email = &#63; or throws a {@link com.aimprosoft.noormal.servicebuilder.NoSuchEmployeeException} if it could not be found.
    *
    * @param email the email
    * @return the matching employee
    * @throws com.aimprosoft.noormal.servicebuilder.NoSuchEmployeeException if a matching employee could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.aimprosoft.noormal.servicebuilder.model.Employee findByEmail(
        java.lang.String email)
        throws com.aimprosoft.noormal.servicebuilder.NoSuchEmployeeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the employee where email = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param email the email
    * @return the matching employee, or <code>null</code> if a matching employee could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.aimprosoft.noormal.servicebuilder.model.Employee fetchByEmail(
        java.lang.String email)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the employee where email = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param email the email
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching employee, or <code>null</code> if a matching employee could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.aimprosoft.noormal.servicebuilder.model.Employee fetchByEmail(
        java.lang.String email, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the employee where email = &#63; from the database.
    *
    * @param email the email
    * @return the employee that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.aimprosoft.noormal.servicebuilder.model.Employee removeByEmail(
        java.lang.String email)
        throws com.aimprosoft.noormal.servicebuilder.NoSuchEmployeeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of employees where email = &#63;.
    *
    * @param email the email
    * @return the number of matching employees
    * @throws SystemException if a system exception occurred
    */
    public int countByEmail(java.lang.String email)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the employee in the entity cache if it is enabled.
    *
    * @param employee the employee
    */
    public void cacheResult(
        com.aimprosoft.noormal.servicebuilder.model.Employee employee);

    /**
    * Caches the employees in the entity cache if it is enabled.
    *
    * @param employees the employees
    */
    public void cacheResult(
        java.util.List<com.aimprosoft.noormal.servicebuilder.model.Employee> employees);

    /**
    * Creates a new employee with the primary key. Does not add the employee to the database.
    *
    * @param employeeId the primary key for the new employee
    * @return the new employee
    */
    public com.aimprosoft.noormal.servicebuilder.model.Employee create(
        long employeeId);

    /**
    * Removes the employee with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param employeeId the primary key of the employee
    * @return the employee that was removed
    * @throws com.aimprosoft.noormal.servicebuilder.NoSuchEmployeeException if a employee with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.aimprosoft.noormal.servicebuilder.model.Employee remove(
        long employeeId)
        throws com.aimprosoft.noormal.servicebuilder.NoSuchEmployeeException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.aimprosoft.noormal.servicebuilder.model.Employee updateImpl(
        com.aimprosoft.noormal.servicebuilder.model.Employee employee)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the employee with the primary key or throws a {@link com.aimprosoft.noormal.servicebuilder.NoSuchEmployeeException} if it could not be found.
    *
    * @param employeeId the primary key of the employee
    * @return the employee
    * @throws com.aimprosoft.noormal.servicebuilder.NoSuchEmployeeException if a employee with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.aimprosoft.noormal.servicebuilder.model.Employee findByPrimaryKey(
        long employeeId)
        throws com.aimprosoft.noormal.servicebuilder.NoSuchEmployeeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the employee with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param employeeId the primary key of the employee
    * @return the employee, or <code>null</code> if a employee with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.aimprosoft.noormal.servicebuilder.model.Employee fetchByPrimaryKey(
        long employeeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the employees.
    *
    * @return the employees
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.aimprosoft.noormal.servicebuilder.model.Employee> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the employees.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.aimprosoft.noormal.servicebuilder.model.impl.EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of employees
    * @param end the upper bound of the range of employees (not inclusive)
    * @return the range of employees
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.aimprosoft.noormal.servicebuilder.model.Employee> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the employees.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.aimprosoft.noormal.servicebuilder.model.impl.EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of employees
    * @param end the upper bound of the range of employees (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of employees
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.aimprosoft.noormal.servicebuilder.model.Employee> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the employees from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of employees.
    *
    * @return the number of employees
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
