package com.aimprosoft.noormal.servicebuilder.model.impl;

import com.aimprosoft.noormal.servicebuilder.model.Department;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Department in entity cache.
 *
 * @author Arsalan
 * @see Department
 * @generated
 */
public class DepartmentCacheModel implements CacheModel<Department>,
    Externalizable {
    public long departmentId;
    public String name;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(5);

        sb.append("{departmentId=");
        sb.append(departmentId);
        sb.append(", name=");
        sb.append(name);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public Department toEntityModel() {
        DepartmentImpl departmentImpl = new DepartmentImpl();

        departmentImpl.setDepartmentId(departmentId);

        if (name == null) {
            departmentImpl.setName(StringPool.BLANK);
        } else {
            departmentImpl.setName(name);
        }

        departmentImpl.resetOriginalValues();

        return departmentImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        departmentId = objectInput.readLong();
        name = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(departmentId);

        if (name == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(name);
        }
    }
}
