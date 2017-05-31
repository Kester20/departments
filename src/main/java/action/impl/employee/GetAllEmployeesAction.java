package action.impl.employee;

import action.Action;
import action.PageFactory;
import model.Employee;
import service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static util.Constants.ContextConstants.DEPARTMENT_SERVICE;
import static util.Constants.Pathways.EMPLOYEES;
import static util.Constants.ServiceConstants.DEPARTMENT_ID;

/**
 * @author Arsalan
 */
public class GetAllEmployeesAction implements Action {

    private DepartmentService departmentService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (departmentService == null) {
            this.departmentService = (DepartmentService) request.getServletContext().getAttribute(DEPARTMENT_SERVICE);
        }

        String idParameter = request.getParameter(DEPARTMENT_ID);
        Integer departmentId = idParameter == null ? null : Integer.parseInt(idParameter);
        List<Employee> employees = departmentService.getEmployees(departmentId);
        request.setAttribute(DEPARTMENT_ID, departmentId);
        request.setAttribute(EMPLOYEES, employees);
        return PageFactory.getPages().get(EMPLOYEES).execute(request, response);
    }
}
