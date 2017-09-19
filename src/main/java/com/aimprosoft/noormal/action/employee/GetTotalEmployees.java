package com.aimprosoft.noormal.action.employee;

import com.aimprosoft.noormal.model.Department;
import com.aimprosoft.noormal.service.EmployeeService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Arsalan
 */
public class GetTotalEmployees extends ActionSupport {

    @Autowired
    private EmployeeService employeeService;
    private int totalEmployees;
    private Department department;

    @Override
    public String execute() throws Exception {
        totalEmployees = employeeService.getTotalEmployees(department);
        return Action.SUCCESS;
    }

    public int getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(int totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
