package com.aimprosoft.noormal.servicebuilder.model;

import com.aimprosoft.noormal.servicebuilder.service.ClpSerializer;
import com.aimprosoft.noormal.servicebuilder.service.DepartmentLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;


public class DepartmentClp extends BaseModelImpl<Department>
    implements Department {
    private long _departmentId;
    private String _name;
    private BaseModel<?> _departmentRemoteModel;
    private Class<?> _clpSerializerClass = com.aimprosoft.noormal.servicebuilder.service.ClpSerializer.class;

    public DepartmentClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return Department.class;
    }

    @Override
    public String getModelClassName() {
        return Department.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _departmentId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setDepartmentId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _departmentId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("departmentId", getDepartmentId());
        attributes.put("name", getName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long departmentId = (Long) attributes.get("departmentId");

        if (departmentId != null) {
            setDepartmentId(departmentId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }
    }

    @Override
    public long getDepartmentId() {
        return _departmentId;
    }

    @Override
    public void setDepartmentId(long departmentId) {
        _departmentId = departmentId;

        if (_departmentRemoteModel != null) {
            try {
                Class<?> clazz = _departmentRemoteModel.getClass();

                Method method = clazz.getMethod("setDepartmentId", long.class);

                method.invoke(_departmentRemoteModel, departmentId);
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

        if (_departmentRemoteModel != null) {
            try {
                Class<?> clazz = _departmentRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_departmentRemoteModel, name);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getDepartmentRemoteModel() {
        return _departmentRemoteModel;
    }

    public void setDepartmentRemoteModel(BaseModel<?> departmentRemoteModel) {
        _departmentRemoteModel = departmentRemoteModel;
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

        Class<?> remoteModelClass = _departmentRemoteModel.getClass();

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

        Object returnValue = method.invoke(_departmentRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            DepartmentLocalServiceUtil.addDepartment(this);
        } else {
            DepartmentLocalServiceUtil.updateDepartment(this);
        }
    }

    @Override
    public Department toEscapedModel() {
        return (Department) ProxyUtil.newProxyInstance(Department.class.getClassLoader(),
            new Class[] { Department.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        DepartmentClp clone = new DepartmentClp();

        clone.setDepartmentId(getDepartmentId());
        clone.setName(getName());

        return clone;
    }

    @Override
    public int compareTo(Department department) {
        long primaryKey = department.getPrimaryKey();

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

        if (!(obj instanceof DepartmentClp)) {
            return false;
        }

        DepartmentClp department = (DepartmentClp) obj;

        long primaryKey = department.getPrimaryKey();

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
        StringBundler sb = new StringBundler(5);

        sb.append("{departmentId=");
        sb.append(getDepartmentId());
        sb.append(", name=");
        sb.append(getName());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(10);

        sb.append("<model><model-name>");
        sb.append("com.aimprosoft.noormal.servicebuilder.model.Department");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>departmentId</column-name><column-value><![CDATA[");
        sb.append(getDepartmentId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
