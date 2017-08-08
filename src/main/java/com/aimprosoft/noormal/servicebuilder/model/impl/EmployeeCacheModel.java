package com.aimprosoft.noormal.servicebuilder.model.impl;

import com.aimprosoft.noormal.servicebuilder.model.Employee;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Employee in entity cache.
 *
 * @author Arsalan
 * @see Employee
 * @generated
 */
public class EmployeeCacheModel implements CacheModel<Employee>, Externalizable {
    public long employeeId;
    public String name;
    public int age;
    public long dateOfBirth;
    public String email;
    public long department;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{employeeId=");
        sb.append(employeeId);
        sb.append(", name=");
        sb.append(name);
        sb.append(", age=");
        sb.append(age);
        sb.append(", dateOfBirth=");
        sb.append(dateOfBirth);
        sb.append(", email=");
        sb.append(email);
        sb.append(", department=");
        sb.append(department);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public Employee toEntityModel() {
        EmployeeImpl employeeImpl = new EmployeeImpl();

        employeeImpl.setEmployeeId(employeeId);

        if (name == null) {
            employeeImpl.setName(StringPool.BLANK);
        } else {
            employeeImpl.setName(name);
        }

        employeeImpl.setAge(age);

        if (dateOfBirth == Long.MIN_VALUE) {
            employeeImpl.setDateOfBirth(null);
        } else {
            employeeImpl.setDateOfBirth(new Date(dateOfBirth));
        }

        if (email == null) {
            employeeImpl.setEmail(StringPool.BLANK);
        } else {
            employeeImpl.setEmail(email);
        }

        employeeImpl.setDepartment(department);

        employeeImpl.resetOriginalValues();

        return employeeImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        employeeId = objectInput.readLong();
        name = objectInput.readUTF();
        age = objectInput.readInt();
        dateOfBirth = objectInput.readLong();
        email = objectInput.readUTF();
        department = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(employeeId);

        if (name == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(name);
        }

        objectOutput.writeInt(age);
        objectOutput.writeLong(dateOfBirth);

        if (email == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(email);
        }

        objectOutput.writeLong(department);
    }
}
