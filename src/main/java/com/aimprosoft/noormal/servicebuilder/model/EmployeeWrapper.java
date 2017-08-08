package com.aimprosoft.noormal.servicebuilder.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Employee}.
 * </p>
 *
 * @author Arsalan
 * @see Employee
 * @generated
 */
public class EmployeeWrapper implements Employee, ModelWrapper<Employee> {
    private Employee _employee;

    public EmployeeWrapper(Employee employee) {
        _employee = employee;
    }

    @Override
    public Class<?> getModelClass() {
        return Employee.class;
    }

    @Override
    public String getModelClassName() {
        return Employee.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("employeeId", getEmployeeId());
        attributes.put("name", getName());
        attributes.put("age", getAge());
        attributes.put("dateOfBirth", getDateOfBirth());
        attributes.put("email", getEmail());
        attributes.put("department", getDepartment());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long employeeId = (Long) attributes.get("employeeId");

        if (employeeId != null) {
            setEmployeeId(employeeId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        Integer age = (Integer) attributes.get("age");

        if (age != null) {
            setAge(age);
        }

        Date dateOfBirth = (Date) attributes.get("dateOfBirth");

        if (dateOfBirth != null) {
            setDateOfBirth(dateOfBirth);
        }

        String email = (String) attributes.get("email");

        if (email != null) {
            setEmail(email);
        }

        Long department = (Long) attributes.get("department");

        if (department != null) {
            setDepartment(department);
        }
    }

    /**
    * Returns the primary key of this employee.
    *
    * @return the primary key of this employee
    */
    @Override
    public long getPrimaryKey() {
        return _employee.getPrimaryKey();
    }

    /**
    * Sets the primary key of this employee.
    *
    * @param primaryKey the primary key of this employee
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _employee.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the employee ID of this employee.
    *
    * @return the employee ID of this employee
    */
    @Override
    public long getEmployeeId() {
        return _employee.getEmployeeId();
    }

    /**
    * Sets the employee ID of this employee.
    *
    * @param employeeId the employee ID of this employee
    */
    @Override
    public void setEmployeeId(long employeeId) {
        _employee.setEmployeeId(employeeId);
    }

    /**
    * Returns the name of this employee.
    *
    * @return the name of this employee
    */
    @Override
    public java.lang.String getName() {
        return _employee.getName();
    }

    /**
    * Sets the name of this employee.
    *
    * @param name the name of this employee
    */
    @Override
    public void setName(java.lang.String name) {
        _employee.setName(name);
    }

    /**
    * Returns the age of this employee.
    *
    * @return the age of this employee
    */
    @Override
    public int getAge() {
        return _employee.getAge();
    }

    /**
    * Sets the age of this employee.
    *
    * @param age the age of this employee
    */
    @Override
    public void setAge(int age) {
        _employee.setAge(age);
    }

    /**
    * Returns the date of birth of this employee.
    *
    * @return the date of birth of this employee
    */
    @Override
    public java.util.Date getDateOfBirth() {
        return _employee.getDateOfBirth();
    }

    /**
    * Sets the date of birth of this employee.
    *
    * @param dateOfBirth the date of birth of this employee
    */
    @Override
    public void setDateOfBirth(java.util.Date dateOfBirth) {
        _employee.setDateOfBirth(dateOfBirth);
    }

    /**
    * Returns the email of this employee.
    *
    * @return the email of this employee
    */
    @Override
    public java.lang.String getEmail() {
        return _employee.getEmail();
    }

    /**
    * Sets the email of this employee.
    *
    * @param email the email of this employee
    */
    @Override
    public void setEmail(java.lang.String email) {
        _employee.setEmail(email);
    }

    /**
    * Returns the department of this employee.
    *
    * @return the department of this employee
    */
    @Override
    public long getDepartment() {
        return _employee.getDepartment();
    }

    /**
    * Sets the department of this employee.
    *
    * @param department the department of this employee
    */
    @Override
    public void setDepartment(long department) {
        _employee.setDepartment(department);
    }

    @Override
    public boolean isNew() {
        return _employee.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _employee.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _employee.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _employee.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _employee.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _employee.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _employee.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _employee.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _employee.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _employee.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _employee.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new EmployeeWrapper((Employee) _employee.clone());
    }

    @Override
    public int compareTo(
        com.aimprosoft.noormal.servicebuilder.model.Employee employee) {
        return _employee.compareTo(employee);
    }

    @Override
    public int hashCode() {
        return _employee.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.aimprosoft.noormal.servicebuilder.model.Employee> toCacheModel() {
        return _employee.toCacheModel();
    }

    @Override
    public com.aimprosoft.noormal.servicebuilder.model.Employee toEscapedModel() {
        return new EmployeeWrapper(_employee.toEscapedModel());
    }

    @Override
    public com.aimprosoft.noormal.servicebuilder.model.Employee toUnescapedModel() {
        return new EmployeeWrapper(_employee.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _employee.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _employee.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _employee.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof EmployeeWrapper)) {
            return false;
        }

        EmployeeWrapper employeeWrapper = (EmployeeWrapper) obj;

        if (Validator.equals(_employee, employeeWrapper._employee)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public Employee getWrappedEmployee() {
        return _employee;
    }

    @Override
    public Employee getWrappedModel() {
        return _employee;
    }

    @Override
    public void resetOriginalValues() {
        _employee.resetOriginalValues();
    }
}
