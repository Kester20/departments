package com.aimprosoft.noormal.action.impl.employee;

import com.aimprosoft.noormal.action.Action;
import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.service.DepartmentService;
import com.aimprosoft.noormal.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author Arsalan
 */
@Controller("/employeeAction")
public class SaveEmployeeAction implements Action {

    private EmployeeService employeeService;
    private DepartmentService departmentService;

    @Autowired
    public SaveEmployeeAction(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    /*@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DaoException {
        Long departmentId = getDepartmentIdFromRequest(request);
        Employee employee = getEmployeeFromRequest(request);
        Department department = departmentService.findOne(departmentId);
        employee.setDepartment(department);

        try {
            employeeService.saveEmployee(employee);
        } catch (ValidationException e) {
            sendError(request, response, SAVE_EMPLOYEE_PATH, e);
            return;
        }
        response.sendRedirect(GET_ALL_EMPLOYEE_PATH + "?departmentId=" + departmentId);
    }*/

    @Override
    public void execute(ResourceRequest request, ResourceResponse response) throws IOException, DaoException {

    }
}
