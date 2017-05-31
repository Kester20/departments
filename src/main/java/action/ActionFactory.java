package action;

import action.impl.department.CreateDepartmentAction;
import action.impl.department.DeleteDepartmentAction;
import action.impl.department.EditDepartmentAction;
import action.impl.department.GetAllDepartmentsAction;
import action.impl.employee.CreateEmployeeAction;
import action.impl.employee.DeleteEmployeeAction;
import action.impl.employee.EditEmployeeAction;
import action.impl.employee.GetAllEmployeesAction;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static util.Constants.Pathways.CREATE_DEPARTMENT_PATH;
import static util.Constants.Pathways.CREATE_EMPLOYEE_PATH;
import static util.Constants.Pathways.DELETE_DEPARTMENT_PATH;
import static util.Constants.Pathways.DELETE_EMPLOYEE_PATH;
import static util.Constants.Pathways.EDIT_DEPARTMENT_PATH;
import static util.Constants.Pathways.EDIT_EMPLOYEE_PATH;
import static util.Constants.Pathways.GET_ALL_DEPARTMENTS_PATH;
import static util.Constants.Pathways.GET_ALL_EMPLOYEE_PATH;
import static util.Constants.ServiceConstants.ACTION;
import static util.Constants.ServiceConstants.GET_PAGE;

/**
 * @author Arsalan
 */
public class ActionFactory {

    private static Map<String, Action> actions = new HashMap<String, Action>() {{
        put(GET_ALL_DEPARTMENTS_PATH, new GetAllDepartmentsAction());
        put(CREATE_DEPARTMENT_PATH, new CreateDepartmentAction());
        put(EDIT_DEPARTMENT_PATH, new EditDepartmentAction());
        put(DELETE_DEPARTMENT_PATH, new DeleteDepartmentAction());

        put(GET_PAGE, new PageFactory());

        put(GET_ALL_EMPLOYEE_PATH, new GetAllEmployeesAction());
        put(CREATE_EMPLOYEE_PATH, new CreateEmployeeAction());
        put(EDIT_EMPLOYEE_PATH, new EditEmployeeAction());
        put(DELETE_EMPLOYEE_PATH, new DeleteEmployeeAction());
    }};

    public static Action getAction(HttpServletRequest request) {
        return actions.get(request.getParameter(ACTION));
    }

    public static Map<String, Action> getActions() {
        return actions;
    }
}
