package com.aimprosoft.noormal.servicebuilder.model;

import com.aimprosoft.noormal.servicebuilder.service.ClpSerializer;
import com.aimprosoft.noormal.servicebuilder.service.EmployeeLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class EmployeeClp extends BaseModelImpl<Employee> implements Employee {
    private long _employeeId;
    private String _name;
    private int _age;
    private Date _dateOfBirth;
    private String _email;
    private long _department;
    private BaseModel<?> _employeeRemoteModel;
    private Class<?> _clpSerializerClass = com.aimprosoft.noormal.servicebuilder.service.ClpSerializer.class;

    public EmployeeClp() {
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
    public long getPrimaryKey() {
        return _employeeId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setEmployeeId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _employeeId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
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

    @Override
    public long getEmployeeId() {
        return _employeeId;
    }

    @Override
    public void setEmployeeId(long employeeId) {
        _employeeId = employeeId;

        if (_employeeRemoteModel != null) {
            try {
                Class<?> clazz = _employeeRemoteModel.getClass();

                Method method = clazz.getMethod("setEmployeeId", long.class);

                method.invoke(_employeeRemoteModel, employeeId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public void setName(String name) {
        _name = name;

        if (_employeeRemoteModel != null) {
            try {
                Class<?> clazz = _employeeRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_employeeRemoteModel, name);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getAge() {
        return _age;
    }

    @Override
    public void setAge(int age) {
        _age = age;

        if (_employeeRemoteModel != null) {
            try {
                Class<?> clazz = _employeeRemoteModel.getClass();

                Method method = clazz.getMethod("setAge", int.class);

                method.invoke(_employeeRemoteModel, age);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getDateOfBirth() {
        return _dateOfBirth;
    }

    @Override
    public void setDateOfBirth(Date dateOfBirth) {
        _dateOfBirth = dateOfBirth;

        if (_employeeRemoteModel != null) {
            try {
                Class<?> clazz = _employeeRemoteModel.getClass();

                Method method = clazz.getMethod("setDateOfBirth", Date.class);

                method.invoke(_employeeRemoteModel, dateOfBirth);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEmail() {
        return _email;
    }

    @Override
    public void setEmail(String email) {
        _email = email;

        if (_employeeRemoteModel != null) {
            try {
                Class<?> clazz = _employeeRemoteModel.getClass();

                Method method = clazz.getMethod("setEmail", String.class);

                method.invoke(_employeeRemoteModel, email);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getDepartment() {
        return _department;
    }

    @Override
    public void setDepartment(long department) {
        _department = department;

        if (_employeeRemoteModel != null) {
            try {
                Class<?> clazz = _employeeRemoteModel.getClass();

                Method method = clazz.getMethod("setDepartment", long.class);

                method.invoke(_employeeRemoteModel, department);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getEmployeeRemoteModel() {
        return _employeeRemoteModel;
    }

    public void setEmployeeRemoteModel(BaseModel<?> employeeRemoteModel) {
        _employeeRemoteModel = employeeRemoteModel;
    }

    public Object invokeOnRemoteModel(String methodName,
        Class<?>[] parameterTypes, Object[] parameterValues)
        throws Exception {
        Object[] remoteParameterValues = new Object[parameterValues.length];

        for (int i = 0; i < parameterValues.length; i++) {
            if (parameterValues[i] != null) {
                remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
            }
        }

        Class<?> remoteModelClass = _employeeRemoteModel.getClass();

        ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

        Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].isPrimitive()) {
                remoteParameterTypes[i] = parameterTypes[i];
            } else {
                String parameterTypeName = parameterTypes[i].getName();

                remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
            }
        }

        Method method = remoteModelClass.getMethod(methodName,
                remoteParameterTypes);

        Object returnValue = method.invoke(_employeeRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            EmployeeLocalServiceUtil.addEmployee(this);
        } else {
            EmployeeLocalServiceUtil.updateEmployee(this);
        }
    }

    @Override
    public Employee toEscapedModel() {
        return (Employee) ProxyUtil.newProxyInstance(Employee.class.getClassLoader(),
            new Class[] { Employee.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        EmployeeClp clone = new EmployeeClp();

        clone.setEmployeeId(getEmployeeId());
        clone.setName(getName());
        clone.setAge(getAge());
        clone.setDateOfBirth(getDateOfBirth());
        clone.setEmail(getEmail());
        clone.setDepartment(getDepartment());

        return clone;
    }

    @Override
    public int compareTo(Employee employee) {
        long primaryKey = employee.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof EmployeeClp)) {
            return false;
        }

        EmployeeClp employee = (EmployeeClp) obj;

        long primaryKey = employee.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    public Class<?> getClpSerializerClass() {
        return _clpSerializerClass;
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{employeeId=");
        sb.append(getEmployeeId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", age=");
        sb.append(getAge());
        sb.append(", dateOfBirth=");
        sb.append(getDateOfBirth());
        sb.append(", email=");
        sb.append(getEmail());
        sb.append(", department=");
        sb.append(getDepartment());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.aimprosoft.noormal.servicebuilder.model.Employee");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>employeeId</column-name><column-value><![CDATA[");
        sb.append(getEmployeeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>age</column-name><column-value><![CDATA[");
        sb.append(getAge());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>dateOfBirth</column-name><column-value><![CDATA[");
        sb.append(getDateOfBirth());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>email</column-name><column-value><![CDATA[");
        sb.append(getEmail());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>department</column-name><column-value><![CDATA[");
        sb.append(getDepartment());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
