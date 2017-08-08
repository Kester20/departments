package com.aimprosoft.noormal.servicebuilder.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Arsalan
 * @generated
 */
public class DepartmentSoap implements Serializable {
    private long _departmentId;
    private String _name;

    public DepartmentSoap() {
    }

    public static DepartmentSoap toSoapModel(Department model) {
        DepartmentSoap soapModel = new DepartmentSoap();

        soapModel.setDepartmentId(model.getDepartmentId());
        soapModel.setName(model.getName());

        return soapModel;
    }

    public static DepartmentSoap[] toSoapModels(Department[] models) {
        DepartmentSoap[] soapModels = new DepartmentSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static DepartmentSoap[][] toSoapModels(Department[][] models) {
        DepartmentSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new DepartmentSoap[models.length][models[0].length];
        } else {
            soapModels = new DepartmentSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static DepartmentSoap[] toSoapModels(List<Department> models) {
        List<DepartmentSoap> soapModels = new ArrayList<DepartmentSoap>(models.size());

        for (Department model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new DepartmentSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _departmentId;
    }

    public void setPrimaryKey(long pk) {
        setDepartmentId(pk);
    }

    public long getDepartmentId() {
        return _departmentId;
    }

    public void setDepartmentId(long departmentId) {
        _departmentId = departmentId;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }
}
