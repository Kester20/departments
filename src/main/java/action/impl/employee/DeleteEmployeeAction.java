package action.impl.employee;

import action.Action;
import exception.DaoException;
import model.Employee;
import service.EmployeeService;
import util.FormatUtils;

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
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DaoException {
        if (employeeService == null) {
            this.employeeService = (EmployeeService) request.getServletContext().getAttribute(EMPLOYEE_SERVICE);
        }

        Integer departmentId = getDepartmentIdFromRequest(request);
        Employee employee = getEmployeeFromRequest(request);
        employeeService.deleteEmployee(employee);

        response.sendRedirect(GET_ALL_EMPLOYEE_PATH + "?departmentId=" + departmentId);
    }

    private Employee getEmployeeFromRequest(HttpServletRequest request) {
        String idParameter = request.getParameter(EMPLOYEE_ID);
        Integer employeeId = FormatUtils.getIntFromString(idParameter);
        Employee employee = new Employee();
        employee.setId(employeeId);
        return employee;
    }

    private Integer getDepartmentIdFromRequest(HttpServletRequest request) {
        String depIdParameter = request.getParameter(DEPARTMENT_ID);
        return FormatUtils.getIntFromString(depIdParameter);
    }
}
