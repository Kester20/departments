package action.impl.employee;

import action.Action;
import action.ActionFactory;
import service.DepartmentService;
import service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static util.Constants.ContextConstants.DEPARTMENT_SERVICE;
import static util.Constants.ContextConstants.EMPLOYEE_SERVICE;
import static util.Constants.PATHWAYS.GET_ALL_DEPARTMENTS_PATH;
import static util.Constants.PATHWAYS.GET_ALL_EMPLOYEE_PATH;
import static util.Constants.ServiceConstants.AGE;
import static util.Constants.ServiceConstants.DATE_OF_BIRTH;
import static util.Constants.ServiceConstants.EMPLOYEE_ID;
import static util.Constants.ServiceConstants.ID;
import static util.Constants.ServiceConstants.NAME;

/**
 * @author Arsalan
 */
public class EditEmployeeAction implements Action {

    private EmployeeService employeeService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (employeeService == null) {
            this.employeeService = (EmployeeService) request.getServletContext().getAttribute(EMPLOYEE_SERVICE);
        }

        String idParameter = request.getParameter(EMPLOYEE_ID);
        int id = idParameter == null ? 0 : Integer.parseInt(idParameter);
        String newName = request.getParameter(NAME);
        String ageParameter = request.getParameter(AGE);
        int age = ageParameter == null ? 0 : Integer.parseInt(ageParameter);
        String newDateOfBirth = request.getParameter(DATE_OF_BIRTH);

        employeeService.updateEmployee(id, newName, age, newDateOfBirth);
        return ActionFactory.getActions().get(GET_ALL_EMPLOYEE_PATH).execute(request, response);
    }
}