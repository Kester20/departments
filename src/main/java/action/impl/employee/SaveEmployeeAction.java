package action.impl.employee;

import action.Action;
import exception.DaoException;
import exception.ValidationException;
import model.Department;
import model.Employee;
import service.DepartmentService;
import service.EmployeeService;
import util.FormatUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import static util.Constants.ContextConstants.DEPARTMENT_SERVICE;
import static util.Constants.ContextConstants.EMPLOYEE_SERVICE;
import static util.Constants.Pathways.GET_ALL_EMPLOYEE_PATH;
import static util.Constants.Pathways.SAVE_EMPLOYEE_PATH;
import static util.Constants.ServiceConstants.AGE;
import static util.Constants.ServiceConstants.DATE_OF_BIRTH;
import static util.Constants.ServiceConstants.DEPARTMENT_ID;
import static util.Constants.ServiceConstants.EMAIL;
import static util.Constants.ServiceConstants.EMPLOYEE_ID;
import static util.Constants.ServiceConstants.ERROR_INPUT;
import static util.Constants.ServiceConstants.ERROR_MAP;
import static util.Constants.ServiceConstants.NAME;

/**
 * @author Arsalan
 */
public class SaveEmployeeAction implements Action {

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

        Integer departmentId = getDepartmentIdFromRequest(request);
        Employee employee = getEmployeeFromRequest(request);
        Department department = departmentService.findOne(departmentId);
        employee.setDepartmentId(department.getId());

        try {
            employeeService.saveEmployee(employee);
        } catch (ValidationException e) {
            sendError(request, response, employee.getName(), employee.getAge(), String.valueOf(employee.getDateOfBirth()),
                    employee.getEmail(), SAVE_EMPLOYEE_PATH, e);
            return;
        }

        response.sendRedirect(GET_ALL_EMPLOYEE_PATH + "?departmentId=" + departmentId);
    }

    private Integer getDepartmentIdFromRequest(HttpServletRequest request) {
        String depIdParameter = request.getParameter(DEPARTMENT_ID);
        return FormatUtils.getIntFromString(depIdParameter);
    }

    private Employee getEmployeeFromRequest(HttpServletRequest request) {
        String idParameter = request.getParameter(EMPLOYEE_ID);
        Integer employeeId = FormatUtils.getIntFromString(idParameter);
        String newName = request.getParameter(NAME);
        String ageParameter = request.getParameter(AGE);
        Integer age = FormatUtils.getIntFromString(ageParameter);
        String newDateOfBirth = request.getParameter(DATE_OF_BIRTH);
        String email = request.getParameter(EMAIL);
        LocalDate localDate = FormatUtils.getDateFromString(newDateOfBirth);
        Employee employee = new Employee(employeeId, newName, age, localDate, email);
        return employee;
    }

    private void sendError(HttpServletRequest request, HttpServletResponse response, String name, Integer age, String date,
                           String email, String path, Exception exception) throws ServletException, IOException {
        ValidationException validationException = (ValidationException) exception;
        Map<String, String> errors = validationException.getErrorMap();
        request.setAttribute(ERROR_MAP, errors);
        request.setAttribute(NAME + ERROR_INPUT, name);
        request.setAttribute(AGE + ERROR_INPUT, age);
        request.setAttribute(DATE_OF_BIRTH + ERROR_INPUT, date);
        request.setAttribute(EMAIL + ERROR_INPUT, email);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }
}
