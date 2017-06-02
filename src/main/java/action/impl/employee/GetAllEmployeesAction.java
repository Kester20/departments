package action.impl.employee;

import action.Action;
import action.ActionFactory;
import exception.DaoException;
import model.Department;
import model.Employee;
import service.EmployeeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static util.Constants.ContextConstants.EMPLOYEE_SERVICE;
import static util.Constants.Messages.ERROR_CODE;
import static util.Constants.Pathways.EMPLOYEES_PATH;
import static util.Constants.Pathways.ERROR_PAGE_PATH;
import static util.Constants.ServiceConstants.DEPARTMENT_ID;
import static util.Constants.ServiceConstants.EMPLOYEES;

/**
 * @author Arsalan
 */
public class GetAllEmployeesAction implements Action {

    private EmployeeService employeeService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeService == null) {
            this.employeeService = (EmployeeService) request.getServletContext().getAttribute(EMPLOYEE_SERVICE);
        }

        String idParameter = request.getParameter(DEPARTMENT_ID);
        Integer departmentId = idParameter == null || idParameter.equals("") ? null : Integer.parseInt(idParameter);

        Department department = new Department();
        department.setId(departmentId);

        List<Employee> employees = null;
        try {
            employees = employeeService.getEmployeesFromDepartment(department);
        } catch (DaoException e) {
            request.setAttribute(ERROR_CODE, 500);
            Action action = ActionFactory.getAction(ERROR_PAGE_PATH);
            action.execute(request, response);
        }

        request.setAttribute(DEPARTMENT_ID, departmentId);
        request.setAttribute(EMPLOYEES, employees);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF" + EMPLOYEES_PATH + ".jsp");
        requestDispatcher.forward(request, response);
    }
}
