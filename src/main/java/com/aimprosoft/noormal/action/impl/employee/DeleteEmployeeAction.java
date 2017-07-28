package com.aimprosoft.noormal.action.impl.employee;

import com.aimprosoft.noormal.action.Action;
import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.model.Employee;
import com.aimprosoft.noormal.service.EmployeeService;
import com.aimprosoft.noormal.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Arsalan
 */
@Controller("/deleteEmployee")
public class DeleteEmployeeAction implements Action {

    private EmployeeService employeeService;

    @Autowired
    public DeleteEmployeeAction(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /*@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DaoException {
        Long departmentId = getDepartmentIdFromRequest(request);
        Employee employee = getEmployeeFromRequest(request);
        employeeService.deleteEmployee(employee);
        response.sendRedirect(Constants.Pathways.GET_ALL_EMPLOYEE_PATH + "?departmentId=" + departmentId);
    }*/

    @Override
    public void execute(ResourceRequest request, ResourceResponse response) throws IOException, DaoException {

    }
}
