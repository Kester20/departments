package action.impl.employee;

import action.Action;
import action.ActionFactory;
import exception.DaoException;
import exception.ValidationException;
import model.Department;
import model.Employee;
import page.Page;
import page.PageFactory;
import service.DepartmentService;
import service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import static util.Constants.ContextConstants.DEPARTMENT_SERVICE;
import static util.Constants.ContextConstants.EMPLOYEE_SERVICE;
import static util.Constants.Pathways.CREATE_EMPLOYEE_PATH;
import static util.Constants.Pathways.EDIT_EMPLOYEE_PATH;
import static util.Constants.Pathways.GET_ALL_EMPLOYEE_PATH;
import static util.Constants.ServiceConstants.AGE;
import static util.Constants.ServiceConstants.DATE_OF_BIRTH;
import static util.Constants.ServiceConstants.DEPARTMENT_ID;
import static util.Constants.ServiceConstants.EMAIL;
import static util.Constants.ServiceConstants.EMPLOYEE_ID;
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

        if (employeeId != null) {
            Employee employee = new Employee();
            employee.setId(employeeId);
            employee.setName(newName);
            employee.setAge(age);
            LocalDate localDate = LocalDate.parse(newDateOfBirth);
            employee.setDateOfBirth(localDate);
            employee.setEmail(email);

            try {
                employeeService.updateEmployee(employee);
            } catch (ValidationException e) {
                sendError(request, e);
                Page page = PageFactory.getPage(EDIT_EMPLOYEE_PATH);
                page.show(request, response);
                return;
            } catch (DaoException e) {
                e.printStackTrace();
            }

        } else {
            String depIdParameter = request.getParameter(DEPARTMENT_ID);
            Integer departmentId = depIdParameter == null || depIdParameter.equals("") ? null : Integer.parseInt(depIdParameter);
            request.setAttribute(DEPARTMENT_ID, departmentId);

            Department department = null;
            try {
                department = departmentService.findOne(departmentId);
            } catch (DaoException e) {
                e.printStackTrace();
            }

            Employee employee = new Employee();
            employee.setName(newName);
            employee.setAge(age);
            LocalDate localDate = LocalDate.parse(newDateOfBirth);
            employee.setDateOfBirth(localDate);
            employee.setDepartment(department);
            employee.setEmail(email);

            try {
                employeeService.createEmployee(employee);
            } catch (ValidationException e) {
                sendError(request, e);
                Page page = PageFactory.getPage(CREATE_EMPLOYEE_PATH);
                page.show(request, response);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
        Action action = ActionFactory.getAction(GET_ALL_EMPLOYEE_PATH);
        action.execute(request, response);
    }

    private void sendError(HttpServletRequest request, Exception exception) throws ServletException, IOException {
        ValidationException validationException = (ValidationException) exception;
        Map<String, String> errors = validationException.getErrorMap();
        for (String errorField : errors.keySet()) {
            String message = errors.get(errorField);
            request.setAttribute(errorField, message);
        }
    }
}
