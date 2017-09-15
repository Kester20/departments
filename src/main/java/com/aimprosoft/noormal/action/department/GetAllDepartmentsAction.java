package com.aimprosoft.noormal.action.department;

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
    private int page;
    private int itemsPerPage;

    @Override
    public String execute() throws Exception {
        //TODO session
        departments = departmentService.findDepartments(page, itemsPerPage);
        return Action.SUCCESS;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }
}
