package com.aimprosoft.noormal.action.impl.employee;

import com.aimprosoft.noormal.action.Action;
import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.model.Department;
import com.aimprosoft.noormal.model.Employee;
import com.aimprosoft.noormal.service.EmployeeService;
import com.aimprosoft.noormal.util.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;

import static com.aimprosoft.noormal.util.Constants.Actions.DELETE_EMPLOYEE;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.DEPARTMENT_ID;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.EMPLOYEE_ID;

/**
 * @author Arsalan
 */
@Component(DELETE_EMPLOYEE)
public class DeleteEmployeeAction implements Action {

    private EmployeeService employeeService;

    @Autowired
    public DeleteEmployeeAction(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void execute(ResourceRequest request, ResourceResponse response) throws IOException, DaoException {
        Long departmentId = FormatUtils.getLongFromString(request.getParameter(DEPARTMENT_ID));
        Long employeeId = FormatUtils.getLongFromString(request.getParameter(EMPLOYEE_ID));
        Department department = new Department();
        department.setDepartmentId(departmentId);
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setDepartment(department);
        employeeService.deleteEmployee(employee);
    }
}