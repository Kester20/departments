package action.impl.employee;

import exception.DaoException;
import exception.ValidationException;
import model.Department;
import model.Employee;
import service.DepartmentService;
import service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static action.ValidationErrorResponder.sendError;
import static util.Constants.ContextConstants.DEPARTMENT_SERVICE;
import static util.Constants.ContextConstants.EMPLOYEE_SERVICE;
import static util.Constants.Pathways.GET_ALL_EMPLOYEE_PATH;
import static util.Constants.Pathways.SAVE_EMPLOYEE_PATH;

/**
 * @author Arsalan
 */
public class SaveEmployeeAction extends EmployeeAction {

    private EmployeeService employeeService;
    private DepartmentService departmentService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DaoException {
        if (employeeService == null) {
            this.employeeService = (EmployeeService) request.getServletContext().getAttribute(EMPLOYEE_SERVICE);
        }
        if (departmentService == null) {
            this.departmentService = (DepartmentService) request.getServletContext().getAttribute(DEPARTMENT_SERVICE);
        }

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
    }
}
