package com.aimprosoft.noormal.servicebuilder.service.persistence;

public interface EmployeeFinder {
    public java.util.List<com.aimprosoft.noormal.servicebuilder.model.Employee> findByDepartment(
        com.aimprosoft.noormal.servicebuilder.model.Department department,
        int start, int end);

    public java.lang.Integer getCountByDepartment(
        com.aimprosoft.noormal.servicebuilder.model.Department department);
}
