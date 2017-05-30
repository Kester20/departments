package action.impl;

import action.Action;
import model.Employee;
import service.DepartmentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static util.Constants.ContextConstants.DEPARTMENT_SERVICE;
import static util.Constants.PATHWAYS.EMPLOYEES;
import static util.Constants.ServiceConstants.DEPARTMENT_ID;

/**
 * @author Arsalan
 */
public class GetAllEmployeesAction implements Action {

    private DepartmentService departmentService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (departmentService == null) {
            this.departmentService = (DepartmentService) request.getServletContext().getAttribute(DEPARTMENT_SERVICE);
        }

        String idParameter = request.getParameter(DEPARTMENT_ID);
        int id = idParameter == null ? 0 : Integer.parseInt(idParameter);
        List<Employee> employees = departmentService.getEmployees(id);
        request.setAttribute(DEPARTMENT_ID, id);
        request.setAttribute(EMPLOYEES, employees);
        return "WEB-INF\\" + EMPLOYEES + ".jsp";
    }
}
