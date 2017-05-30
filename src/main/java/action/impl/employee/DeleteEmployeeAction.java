package action.impl.employee;

import action.Action;
import action.ActionFactory;
import service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static util.Constants.ContextConstants.EMPLOYEE_SERVICE;
import static util.Constants.Pathways.GET_ALL_EMPLOYEE_PATH;
import static util.Constants.ServiceConstants.DEPARTMENT_ID;
import static util.Constants.ServiceConstants.EMPLOYEE_ID;

/**
 * @author Arsalan
 */
public class DeleteEmployeeAction implements Action {

    private EmployeeService employeeService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(employeeService == null){
            this.employeeService = (EmployeeService) request.getServletContext().getAttribute(EMPLOYEE_SERVICE);
        }

        String idParameter = request.getParameter(EMPLOYEE_ID);
        Integer id = idParameter == null ? null : Integer.parseInt(idParameter);

        String depIdParameter = request.getParameter(DEPARTMENT_ID);
        Integer departmentId = depIdParameter == null ? null : Integer.parseInt(depIdParameter);
        request.setAttribute(DEPARTMENT_ID, departmentId);

        employeeService.deleteEmployee(id);
        return ActionFactory.getActions().get(GET_ALL_EMPLOYEE_PATH).execute(request, response);
    }
}
