package action.impl.employee;

import action.Action;
import action.ActionFactory;
import action.impl.PageFactory;
import service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static util.Constants.ContextConstants.EMPLOYEE_SERVICE;
import static util.Constants.Messages.DEPARTMENT_WITH_THIS_NAME_IS_ALREADY_EXIST;
import static util.Constants.Messages.EMPLOYEE_WITH_THIS_EMAIL_IS_ALREADY_EXIST;
import static util.Constants.Pathways.CREATE_DEPARTMENT_PATH;
import static util.Constants.Pathways.CREATE_EMPLOYEE_PATH;
import static util.Constants.Pathways.GET_ALL_EMPLOYEE_PATH;
import static util.Constants.ServiceConstants.AGE;
import static util.Constants.ServiceConstants.DATE_OF_BIRTH;
import static util.Constants.ServiceConstants.DEPARTMENT_ID;
import static util.Constants.ServiceConstants.EMAIL;
import static util.Constants.ServiceConstants.ERROR_INPUT;
import static util.Constants.ServiceConstants.ERROR_TEXT;
import static util.Constants.ServiceConstants.NAME;

/**
 * @author Arsalan
 */
public class CreateEmployeeAction implements Action {

    private EmployeeService employeeService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeService == null) {
            this.employeeService = (EmployeeService) request.getServletContext().getAttribute(EMPLOYEE_SERVICE);
        }

        String newName = request.getParameter(NAME);
        String ageParameter = request.getParameter(AGE);
        Integer age = ageParameter == null ? null : Integer.parseInt(ageParameter);
        String newDateOfBirth = request.getParameter(DATE_OF_BIRTH);
        String email = request.getParameter(EMAIL);

        String depIdParameter = request.getParameter(DEPARTMENT_ID);
        Integer departmentId = depIdParameter == null ? null : Integer.parseInt(depIdParameter);
        request.setAttribute(DEPARTMENT_ID, departmentId);

        boolean employeeAdded = employeeService.createEmployee(newName, age, newDateOfBirth, email, departmentId);
        if(!employeeAdded){
            request.setAttribute(ERROR_INPUT, email);
            request.setAttribute(NAME, newName);
            request.setAttribute(AGE, age);
            request.setAttribute(DATE_OF_BIRTH, newDateOfBirth);
            request.setAttribute(ERROR_TEXT, EMPLOYEE_WITH_THIS_EMAIL_IS_ALREADY_EXIST);
            return PageFactory.getPages().get(CREATE_EMPLOYEE_PATH).execute(request, response);
        }else{
            request.removeAttribute(ERROR_INPUT);
            request.removeAttribute(NAME);
            request.removeAttribute(AGE);
            request.removeAttribute(DATE_OF_BIRTH);
            request.removeAttribute(ERROR_TEXT);
        }
        return ActionFactory.getActions().get(GET_ALL_EMPLOYEE_PATH).execute(request, response);
    }
}
