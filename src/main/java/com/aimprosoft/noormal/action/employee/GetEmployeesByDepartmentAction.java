package com.aimprosoft.noormal.action.employee;

import com.aimprosoft.noormal.model.Department;
import com.aimprosoft.noormal.model.Employee;
import com.aimprosoft.noormal.service.EmployeeService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Arsalan
 */
public class GetEmployeesByDepartmentAction extends ActionSupport{

    @Autowired
    private EmployeeService employeeService;
    private List<Employee> employees;
    private int page;
    private int itemsPerPage;
    private Department department;

    @Override
    public String execute() throws Exception {
        employees = employeeService.findEmployeesByDepartment(department, page, itemsPerPage);
        return Action.SUCCESS;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
