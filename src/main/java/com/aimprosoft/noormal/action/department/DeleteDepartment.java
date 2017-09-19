package com.aimprosoft.noormal.action.department;

import com.aimprosoft.noormal.model.Department;
import com.aimprosoft.noormal.service.DepartmentService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Arsalan
 */
public class DeleteDepartment extends ActionSupport {

    @Autowired
    private DepartmentService departmentService;
    private Department department;

    @Override
    public String execute() throws Exception {
        departmentService.deleteDepartment(department);
        return Action.SUCCESS;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
