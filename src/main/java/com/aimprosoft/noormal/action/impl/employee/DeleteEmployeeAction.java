package com.aimprosoft.noormal.action.impl.employee;

import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.model.Employee;
import com.aimprosoft.noormal.service.EmployeeService;
import com.aimprosoft.noormal.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Arsalan
 */
@Controller("/deleteEmployee")
public class DeleteEmployeeAction extends EmployeeAction {

    private EmployeeService employeeService;

    @Autowired
    public DeleteEmployeeAction(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DaoException {
        Long departmentId = getDepartmentIdFromRequest(request);
        Employee employee = getEmployeeFromRequest(request);
        employeeService.deleteEmployee(employee);
        response.sendRedirect(Constants.Pathways.GET_ALL_EMPLOYEE_PATH + "?departmentId=" + departmentId);
    }
}
