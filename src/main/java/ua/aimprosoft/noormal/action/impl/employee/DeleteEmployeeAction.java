package ua.aimprosoft.noormal.action.impl.employee;

import ua.aimprosoft.noormal.exception.DaoException;
import ua.aimprosoft.noormal.model.Employee;
import ua.aimprosoft.noormal.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.aimprosoft.noormal.util.Constants.ContextConstants.EMPLOYEE_SERVICE;
import static ua.aimprosoft.noormal.util.Constants.Pathways.GET_ALL_EMPLOYEE_PATH;

/**
 * @author Arsalan
 */
public class DeleteEmployeeAction extends EmployeeAction {

    private EmployeeService employeeService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DaoException {
        if (employeeService == null) {
            this.employeeService = (EmployeeService) request.getServletContext().getAttribute(EMPLOYEE_SERVICE);
        }

        Long departmentId = getDepartmentIdFromRequest(request);
        Employee employee = getEmployeeFromRequest(request);
        employeeService.deleteEmployee(employee);

        response.sendRedirect(GET_ALL_EMPLOYEE_PATH + "?departmentId=" + departmentId);
    }
}
