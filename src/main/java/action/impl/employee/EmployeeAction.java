package action.impl.employee;

import action.Action;
import action.ActionFactory;
import page.Page;
import page.PageFactory;
import service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Constants.ContextConstants.EMPLOYEE_SERVICE;
import static util.Constants.Messages.EMPLOYEE_WITH_THIS_EMAIL_IS_ALREADY_EXIST;
import static util.Constants.Pathways.CREATE_EMPLOYEE_PATH;
import static util.Constants.Pathways.EDIT_EMPLOYEE_PATH;
import static util.Constants.Pathways.GET_ALL_EMPLOYEE_PATH;
import static util.Constants.ServiceConstants.AGE;
import static util.Constants.ServiceConstants.DATE_OF_BIRTH;
import static util.Constants.ServiceConstants.DEPARTMENT_ID;
import static util.Constants.ServiceConstants.EMAIL;
import static util.Constants.ServiceConstants.EMPLOYEE_ID;
import static util.Constants.ServiceConstants.ERROR_INPUT;
import static util.Constants.ServiceConstants.ERROR_TEXT;
import static util.Constants.ServiceConstants.NAME;

/**
 * @author Arsalan
 */
public class EmployeeAction implements Action {

    private EmployeeService employeeService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeService == null) {
            this.employeeService = (EmployeeService) request.getServletContext().getAttribute(EMPLOYEE_SERVICE);
        }

        String idParameter = request.getParameter(EMPLOYEE_ID);
        Integer employeeId = idParameter == null || idParameter.equals("") ? null : Integer.parseInt(idParameter);
        String newName = request.getParameter(NAME);
        String ageParameter = request.getParameter(AGE);
        Integer age = ageParameter == null || ageParameter.equals("") ? null : Integer.parseInt(ageParameter);
        String newDateOfBirth = request.getParameter(DATE_OF_BIRTH);
        String email = request.getParameter(EMAIL);

        if (employeeId != null) {
            boolean employeeEdited = employeeService.updateEmployee(employeeId, newName, age, newDateOfBirth, email);
            if (hasError(employeeEdited, request, newName, age, newDateOfBirth, email)) {
                Page page = PageFactory.getPage(EDIT_EMPLOYEE_PATH);
                page.show(request, response);
                return;
            }
        } else {
            String depIdParameter = request.getParameter(DEPARTMENT_ID);
            Integer departmentId = depIdParameter == null || depIdParameter.equals("") ? null : Integer.parseInt(depIdParameter);
            request.setAttribute(DEPARTMENT_ID, departmentId);

            boolean employeeCreated = employeeService.createEmployee(newName, age, newDateOfBirth, email, departmentId);
            if (hasError(employeeCreated, request, newName, age, newDateOfBirth, email)) {
                Page page = PageFactory.getPage(CREATE_EMPLOYEE_PATH);
                page.show(request, response);
                return;
            }
        }
        Action action = ActionFactory.getAction(GET_ALL_EMPLOYEE_PATH);
        action.execute(request, response);
    }

    private boolean hasError(boolean criteria, HttpServletRequest request, String name, Integer age,
                             String dateOfBirth, String email) throws ServletException, IOException {
        if (!criteria) {
            request.setAttribute(ERROR_INPUT, email);
            request.setAttribute(NAME, name);
            request.setAttribute(AGE, age);
            request.setAttribute(DATE_OF_BIRTH, dateOfBirth);
            request.setAttribute(ERROR_TEXT, EMPLOYEE_WITH_THIS_EMAIL_IS_ALREADY_EXIST);
            return true;
        } else {
            request.removeAttribute(ERROR_INPUT);
            request.removeAttribute(NAME);
            request.removeAttribute(AGE);
            request.removeAttribute(DATE_OF_BIRTH);
            request.removeAttribute(ERROR_TEXT);
        }
        return false;
    }
}
