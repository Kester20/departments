package action;

import action.impl.CreateDepartmentAction;
import action.impl.CreateEmployeeAction;
import action.impl.DeleteDepartmentAction;
import action.impl.DeleteEmployeeAction;
import action.impl.GetAllDepartmentSAction;
import action.impl.GetAllEmployeesAction;
import action.impl.GetEditDepartmentsPageAction;
import action.impl.GetEditEmployeePageAction;
import action.impl.EditDepartmentAction;
import action.impl.EditEmployeeAction;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static util.Constants.PATHWAYS.CREATE_DEPARTMENT_PATH;
import static util.Constants.PATHWAYS.CREATE_EMPLOYEE_PATH;
import static util.Constants.PATHWAYS.DELETE_DEPARTMENT_PATH;
import static util.Constants.PATHWAYS.DELETE_EMPLOYEE_PATH;
import static util.Constants.PATHWAYS.EDIT_DEPARTMENT_PATH;
import static util.Constants.PATHWAYS.EDIT_EMPLOYEE_PATH;
import static util.Constants.PATHWAYS.GET_ALL_DEPARTMENTS_PATH;
import static util.Constants.PATHWAYS.GET_ALL_EMPLOYEE_PATH;
import static util.Constants.PATHWAYS.GET_EDIT_DEPARTMENT_PAGE_PATH;
import static util.Constants.PATHWAYS.GET_EDIT_EMPLOYEE_PAGE_PATH;
import static util.Constants.ServiceConstants.ACTION;

/**
 * @author Arsalan
 */
public class ActionFactory {

    private static Map<String, Action> actions = new HashMap<String, Action>(){{
        put(GET_ALL_DEPARTMENTS_PATH, new GetAllDepartmentSAction());
        put(GET_ALL_EMPLOYEE_PATH, new GetAllEmployeesAction());

        put(GET_EDIT_DEPARTMENT_PAGE_PATH, new GetEditDepartmentsPageAction());
        put(GET_EDIT_EMPLOYEE_PAGE_PATH, new GetEditEmployeePageAction());

        put(CREATE_DEPARTMENT_PATH, new CreateDepartmentAction());
        put(EDIT_DEPARTMENT_PATH, new EditDepartmentAction());
        put(DELETE_DEPARTMENT_PATH, new DeleteDepartmentAction());

        put(CREATE_EMPLOYEE_PATH, new CreateEmployeeAction());
        put(EDIT_EMPLOYEE_PATH, new EditEmployeeAction());
        put(DELETE_EMPLOYEE_PATH, new DeleteEmployeeAction());
    }};

    public static Action getAction(HttpServletRequest request) {
        return actions.get(request.getMethod() + request.getParameter(ACTION));
    }

    public static Map<String, Action> getActions() {
        return actions;
    }
}
