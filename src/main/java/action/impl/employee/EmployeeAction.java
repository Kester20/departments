package action.impl.employee;

import action.Action;
import action.ActionFactory;
import exception.DaoException;
import exception.ValidationException;
import model.Department;
import model.Employee;
import service.DepartmentService;
import service.EmployeeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import static util.Constants.ContextConstants.DEPARTMENT_SERVICE;
import static util.Constants.ContextConstants.EMPLOYEE_SERVICE;
import static util.Constants.Messages.EXCEPTION;
import static util.Constants.Pathways.CREATE_EMPLOYEE_PATH;
import static util.Constants.Pathways.EDIT_EMPLOYEE_PATH;
import static util.Constants.Pathways.ERROR_PAGE_PATH;
import static util.Constants.Pathways.GET_ALL_EMPLOYEE_PATH;
import static util.Constants.ServiceConstants.AGE;
import static util.Constants.ServiceConstants.DATE_OF_BIRTH;
import static util.Constants.ServiceConstants.DEPARTMENT_ID;
import static util.Constants.ServiceConstants.EMAIL;
import static util.Constants.ServiceConstants.EMPLOYEE_ID;
import static util.Constants.ServiceConstants.ERROR_INPUT;
import static util.Constants.ServiceConstants.NAME;

/**
 * @author Arsalan
 */
public class EmployeeAction implements Action {

    private EmployeeService employeeService;
    private DepartmentService departmentService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeService == null) {
            this.employeeService = (EmployeeService) request.getServletContext().getAttribute(EMPLOYEE_SERVICE);
        }
        if (departmentService == null) {
            this.departmentService = (DepartmentService) request.getServletContext().getAttribute(DEPARTMENT_SERVICE);
        }

        String idParameter = request.getParameter(EMPLOYEE_ID);
        Integer employeeId = idParameter == null || idParameter.equals("") ? null : Integer.parseInt(idParameter);
        String newName = request.getParameter(NAME);
        String ageParameter = request.getParameter(AGE);
        Integer age = ageParameter == null || ageParameter.equals("") ? null : Integer.parseInt(ageParameter);
        String newDateOfBirth = request.getParameter(DATE_OF_BIRTH);
        String email = request.getParameter(EMAIL);
        LocalDate localDate = LocalDate.parse(newDateOfBirth);
        String depIdParameter = request.getParameter(DEPARTMENT_ID);
        Integer departmentId = depIdParameter == null || depIdParameter.equals("") ? null : Integer.parseInt(depIdParameter);
        request.setAttribute(DEPARTMENT_ID, departmentId);


        Employee employee = new Employee(employeeId, newName, age, localDate, email);

        try {
            if (employeeId != null) {
                try {
                    employeeService.updateEmployee(employee);
                } catch (ValidationException e) {
                    sendError(request, response, newName, age, newDateOfBirth, email, EDIT_EMPLOYEE_PATH, e);
                    return;
                }
            } else {
                try {
                    Department department = departmentService.findOne(departmentId);
                    employee.setDepartment(department);
                    employeeService.createEmployee(employee);
                } catch (ValidationException e) {
                    sendError(request, response, newName, age, newDateOfBirth, email, CREATE_EMPLOYEE_PATH, e);
                    return;
                }
            }
        } catch (DaoException e) {
            request.setAttribute(EXCEPTION, e);
            Action action = ActionFactory.getAction(ERROR_PAGE_PATH);
            action.execute(request, response);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(GET_ALL_EMPLOYEE_PATH);
        requestDispatcher.forward(request, response);
    }

    private void sendError(HttpServletRequest request, HttpServletResponse response, String name, Integer age, String date,
                           String email, String path, Exception exception) throws ServletException, IOException {
        ValidationException validationException = (ValidationException) exception;
        Map<String, String> errors = validationException.getErrorMap();
        for (String errorField : errors.keySet()) {
            String message = errors.get(errorField);
            request.setAttribute(errorField, message);
        }
        request.setAttribute(NAME + ERROR_INPUT, name);
        request.setAttribute(AGE + ERROR_INPUT, age);
        request.setAttribute(DATE_OF_BIRTH + ERROR_INPUT, date);
        request.setAttribute(EMAIL + ERROR_INPUT, email);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }
}
