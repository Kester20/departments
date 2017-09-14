package com.aimprosoft.noormal.action;

import com.aimprosoft.noormal.model.Department;
import com.aimprosoft.noormal.service.DepartmentService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Arsalan
 */
public class GetAllDepartmentsAction extends ActionSupport {

    @Autowired
    private DepartmentService departmentService;
    private List<Department> departments;

    @Override
    public String execute() throws Exception {
        departments = departmentService.findDepartments(1, 5);
        return Action.SUCCESS;
    }

    public List<Department> getDepartments() {
        return departments;
    }
}
