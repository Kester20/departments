package com.aimprosoft.noormal.action.employee;

import com.aimprosoft.noormal.model.Employee;
import com.aimprosoft.noormal.service.EmployeeService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Arsalan
 */
public class DeleteEmployee extends ActionSupport {

    @Autowired
    private EmployeeService employeeService;
    private Employee employee;

    @Override
    public String execute() throws Exception {
        employeeService.deleteEmployee(employee);
        return Action.SUCCESS;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
