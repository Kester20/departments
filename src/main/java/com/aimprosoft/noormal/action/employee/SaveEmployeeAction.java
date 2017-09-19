package com.aimprosoft.noormal.action.employee;

import com.aimprosoft.noormal.exception.ValidationException;
import com.aimprosoft.noormal.model.Employee;
import com.aimprosoft.noormal.service.EmployeeService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author Arsalan
 */
public class SaveEmployeeAction extends ActionSupport {

    @Autowired
    private EmployeeService employeeService;
    private Employee employee;

    @Override
    public String execute() throws Exception {
        if(employee.getEmployeeId() != null && employee.getName() == null){
            employee = employeeService.findOne(employee.getEmployeeId());
        }else{
            try {
                employeeService.saveEmployee(employee);
            } catch (ValidationException e) {
                Map<String, String> errors = e.getErrorMap();
                for (String key: errors.keySet()) {
                    addFieldError(key, errors.get(key));
                }
                return Action.ERROR;
            }
        }
        return Action.SUCCESS;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
