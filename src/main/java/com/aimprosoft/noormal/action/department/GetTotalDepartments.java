package com.aimprosoft.noormal.action.department;

import com.aimprosoft.noormal.service.DepartmentService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Arsalan
 */
public class GetTotalDepartments extends ActionSupport {

    @Autowired
    private DepartmentService departmentService;
    private int totalDepartments;

    @Override
    public String execute() throws Exception {
        totalDepartments = departmentService.getTotalDepartments();
        return Action.SUCCESS;
    }

    public int getTotalDepartments() {
        return totalDepartments;
    }

    public void setTotalDepartments(int totalDepartments) {
        this.totalDepartments = totalDepartments;
    }
}
