package action;

import action.impl.GetErrorPageAction;
import action.impl.GetItemPageAction;
import action.impl.department.DeleteDepartmentAction;
import action.impl.department.DepartmentAction;
import action.impl.department.GetAllDepartmentsAction;
import action.impl.employee.DeleteEmployeeAction;
import action.impl.employee.EmployeeAction;
import action.impl.employee.GetAllEmployeesAction;

import java.util.HashMap;
import java.util.Map;

import static util.Constants.Pathways.CREATE_DEPARTMENT_PATH;
import static util.Constants.Pathways.CREATE_EMPLOYEE_PATH;
import static util.Constants.Pathways.DELETE_DEPARTMENT_PATH;
import static util.Constants.Pathways.DELETE_EMPLOYEE_PATH;
import static util.Constants.Pathways.DEPARTMENT_ACTION_PATH;
import static util.Constants.Pathways.EDIT_DEPARTMENT_PATH;
import static util.Constants.Pathways.EDIT_EMPLOYEE_PATH;
import static util.Constants.Pathways.EMPLOYEE_ACTION_PATH;
import static util.Constants.Pathways.ERROR_PAGE_PATH;
import static util.Constants.Pathways.GET_ALL_EMPLOYEE_PATH;
import static util.Constants.Pathways.ROOT_PATH;

/**
 * @author Arsalan
 */
public class ActionFactory {

    private static Map<String, Action> actions = new HashMap<String, Action>() {{
        put(ROOT_PATH, new GetAllDepartmentsAction());
        put(DEPARTMENT_ACTION_PATH, new DepartmentAction());
        put(DELETE_DEPARTMENT_PATH, new DeleteDepartmentAction());

        put(CREATE_DEPARTMENT_PATH, GetItemPageAction.getInstance());
        put(EDIT_DEPARTMENT_PATH, GetItemPageAction.getInstance());
        put(CREATE_EMPLOYEE_PATH, GetItemPageAction.getInstance());
        put(EDIT_EMPLOYEE_PATH, GetItemPageAction.getInstance());

        put(GET_ALL_EMPLOYEE_PATH, new GetAllEmployeesAction());
        put(EMPLOYEE_ACTION_PATH, new EmployeeAction());
        put(DELETE_EMPLOYEE_PATH, new DeleteEmployeeAction());

        put(ERROR_PAGE_PATH, new GetErrorPageAction());
    }};

    public static Action getAction(String action) {
        return actions.get(action);
    }

    public static Map<String, Action> getActions() {
        return actions;
    }
}
