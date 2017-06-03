package action.impl.employee;

import exception.DaoException;
import model.Employee;
import service.EmployeeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static util.Constants.ContextConstants.EMPLOYEE_SERVICE;
import static util.Constants.Pathways.EMPLOYEES_PATH;
import static util.Constants.ServiceConstants.EMPLOYEES;

/**
 * @author Arsalan
 */
public class GetAllEmployeesPageAction extends EmployeeAction {

    private EmployeeService employeeService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DaoException {
        if (employeeService == null) {
            this.employeeService = (EmployeeService) request.getServletContext().getAttribute(EMPLOYEE_SERVICE);
        }

        Integer departmentId = getDepartmentIdFromRequest(request);
        List<Employee> employees = employeeService.findEmployeesByDepartment(departmentId);
        request.setAttribute(EMPLOYEES, employees);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF" + EMPLOYEES_PATH + ".jsp");
        requestDispatcher.forward(request, response);
    }
}
