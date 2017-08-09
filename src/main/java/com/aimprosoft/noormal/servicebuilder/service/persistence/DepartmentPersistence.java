package com.aimprosoft.noormal.servicebuilder.service.persistence;

import com.aimprosoft.noormal.servicebuilder.model.Department;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the department service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Arsalan
 * @see DepartmentPersistenceImpl
 * @see DepartmentUtil
 * @generated
 */
public interface DepartmentPersistence extends BasePersistence<Department> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link DepartmentUtil} to access the department persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns the department where name = &#63; or throws a {@link com.aimprosoft.noormal.servicebuilder.NoSuchDepartmentException} if it could not be found.
    *
    * @param name the name
    * @return the matching department
    * @throws com.aimprosoft.noormal.servicebuilder.NoSuchDepartmentException if a matching department could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.aimprosoft.noormal.servicebuilder.model.Department findByName(
        java.lang.String name)
        throws com.aimprosoft.noormal.servicebuilder.NoSuchDepartmentException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the department where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param name the name
    * @return the matching department, or <code>null</code> if a matching department could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.aimprosoft.noormal.servicebuilder.model.Department fetchByName(
        java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the department where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param name the name
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching department, or <code>null</code> if a matching department could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.aimprosoft.noormal.servicebuilder.model.Department fetchByName(
        java.lang.String name, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the department where name = &#63; from the database.
    *
    * @param name the name
    * @return the department that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.aimprosoft.noormal.servicebuilder.model.Department removeByName(
        java.lang.String name)
        throws com.aimprosoft.noormal.servicebuilder.NoSuchDepartmentException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of departments where name = &#63;.
    *
    * @param name the name
    * @return the number of matching departments
    * @throws SystemException if a system exception occurred
    */
    public int countByName(java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the department in the entity cache if it is enabled.
    *
    * @param department the department
    */
    public void cacheResult(
        com.aimprosoft.noormal.servicebuilder.model.Department department);

    /**
    * Caches the departments in the entity cache if it is enabled.
    *
    * @param departments the departments
    */
    public void cacheResult(
        java.util.List<com.aimprosoft.noormal.servicebuilder.model.Department> departments);

    /**
    * Creates a new department with the primary key. Does not add the department to the database.
    *
    * @param departmentId the primary key for the new department
    * @return the new department
    */
    public com.aimprosoft.noormal.servicebuilder.model.Department create(
        long departmentId);

    /**
    * Removes the department with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param departmentId the primary key of the department
    * @return the department that was removed
    * @throws com.aimprosoft.noormal.servicebuilder.NoSuchDepartmentException if a department with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.aimprosoft.noormal.servicebuilder.model.Department remove(
        long departmentId)
        throws com.aimprosoft.noormal.servicebuilder.NoSuchDepartmentException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.aimprosoft.noormal.servicebuilder.model.Department updateImpl(
        com.aimprosoft.noormal.servicebuilder.model.Department department)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the department with the primary key or throws a {@link com.aimprosoft.noormal.servicebuilder.NoSuchDepartmentException} if it could not be found.
    *
    * @param departmentId the primary key of the department
    * @return the department
    * @throws com.aimprosoft.noormal.servicebuilder.NoSuchDepartmentException if a department with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.aimprosoft.noormal.servicebuilder.model.Department findByPrimaryKey(
        long departmentId)
        throws com.aimprosoft.noormal.servicebuilder.NoSuchDepartmentException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the department with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param departmentId the primary key of the department
    * @return the department, or <code>null</code> if a department with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.aimprosoft.noormal.servicebuilder.model.Department fetchByPrimaryKey(
        long departmentId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the departments.
    *
    * @return the departments
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.aimprosoft.noormal.servicebuilder.model.Department> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the departments.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.aimprosoft.noormal.servicebuilder.model.impl.DepartmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of departments
    * @param end the upper bound of the range of departments (not inclusive)
    * @return the range of departments
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.aimprosoft.noormal.servicebuilder.model.Department> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the departments.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.aimprosoft.noormal.servicebuilder.model.impl.DepartmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of departments
    * @param end the upper bound of the range of departments (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of departments
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.aimprosoft.noormal.servicebuilder.model.Department> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the departments from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of departments.
    *
    * @return the number of departments
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
