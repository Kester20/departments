package action.impl.employee;

import action.Action;
import action.ActionFactory;
import exception.DaoException;
import model.Employee;
import service.EmployeeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Constants.ContextConstants.EMPLOYEE_SERVICE;
import static util.Constants.Messages.EXCEPTION;
import static util.Constants.Pathways.ERROR_PAGE_PATH;
import static util.Constants.Pathways.GET_ALL_EMPLOYEE_PATH;
import static util.Constants.ServiceConstants.DEPARTMENT_ID;
import static util.Constants.ServiceConstants.EMPLOYEE_ID;

/**
 * @author Arsalan
 */
public class DeleteEmployeeAction implements Action {

    private EmployeeService employeeService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeService == null) {
            this.employeeService = (EmployeeService) request.getServletContext().getAttribute(EMPLOYEE_SERVICE);
        }

        String idParameter = request.getParameter(EMPLOYEE_ID);
        Integer employeeId = idParameter == null ? null : Integer.parseInt(idParameter);

        String depIdParameter = request.getParameter(DEPARTMENT_ID);
        Integer departmentId = depIdParameter == null || depIdParameter.equals("") ? null : Integer.parseInt(depIdParameter);
        request.setAttribute(DEPARTMENT_ID, departmentId);

        Employee employee = new Employee();
        employee.setId(employeeId);

        try {
            employeeService.deleteEmployee(employee);
        } catch (DaoException e) {
            request.setAttribute(EXCEPTION, e);
            Action action = ActionFactory.getAction(ERROR_PAGE_PATH);
            action.execute(request, response);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(GET_ALL_EMPLOYEE_PATH);
        requestDispatcher.forward(request, response);
    }
}
