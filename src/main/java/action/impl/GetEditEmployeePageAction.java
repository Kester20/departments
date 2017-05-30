package action.impl;

import action.Action;
import action.ActionFactory;
import service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static util.Constants.ContextConstants.EMPLOYEE_SERVICE;
import static util.Constants.PATHWAYS.EDIT_EMPLOYEE;
import static util.Constants.PATHWAYS.GET_ALL_DEPARTMENTS_PATH;
import static util.Constants.PATHWAYS.GET_ALL_EMPLOYEE_PATH;
import static util.Constants.ServiceConstants.DEPARTMENT_ID;
import static util.Constants.ServiceConstants.EMPLOYEE_ID;
import static util.Constants.ServiceConstants.GET_PAGE;
import static util.Constants.ServiceConstants.ID;

/**
 * @author Arsalan
 */
public class GetEditEmployeePageAction implements Action {

    private EmployeeService employeeService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (employeeService == null) {
            this.employeeService = (EmployeeService) request.getServletContext().getAttribute(EMPLOYEE_SERVICE);
        }

        String idParameter = request.getParameter(EMPLOYEE_ID);
        int id = idParameter == null ? 0 : Integer.parseInt(idParameter);

        String depIdParameter = request.getParameter(DEPARTMENT_ID);
        int departmentId = depIdParameter == null ? 0 : Integer.parseInt(depIdParameter);

        String getPage = request.getParameter(GET_PAGE);
        if (getPage != null) {
            request.setAttribute(EMPLOYEE_ID, id);
            request.setAttribute(DEPARTMENT_ID, departmentId);
            return "WEB-INF\\" + EDIT_EMPLOYEE + ".jsp";
        }
        return ActionFactory.getActions().get(GET_ALL_EMPLOYEE_PATH).execute(request, response);
    }
}
