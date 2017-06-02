package action;


import action.impl.department.DeleteDepartmentAction;
import action.impl.department.DepartmentAction;
import action.impl.department.GetAllDepartmentsAction;
import action.impl.department.GetDepartmentPagesAction;
import action.impl.employee.DeleteEmployeeAction;
import action.impl.employee.EmployeeAction;
import action.impl.employee.GetAllEmployeesAction;
import action.impl.employee.GetEmployeePagesAction;

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
import static util.Constants.Pathways.GET_ALL_EMPLOYEE_PATH;
import static util.Constants.Pathways.ROOT_PATH;

/**
 * @author Arsalan
 */
public class ActionFactory {

    private static Action defaultAction = new GetAllDepartmentsAction();

    private static Map<String, Action> actions = new HashMap<String, Action>() {{
        put(ROOT_PATH, defaultAction);
        put(DEPARTMENT_ACTION_PATH, new DepartmentAction());
        put(DELETE_DEPARTMENT_PATH, new DeleteDepartmentAction());

        put(CREATE_DEPARTMENT_PATH, GetDepartmentPagesAction.getInstance());
        put(EDIT_DEPARTMENT_PATH, GetDepartmentPagesAction.getInstance());
        put(CREATE_EMPLOYEE_PATH, GetEmployeePagesAction.getInstance());
        put(EDIT_EMPLOYEE_PATH, GetEmployeePagesAction.getInstance());

        put(GET_ALL_EMPLOYEE_PATH, new GetAllEmployeesAction());
        put(EMPLOYEE_ACTION_PATH, new EmployeeAction());
        put(DELETE_EMPLOYEE_PATH, new DeleteEmployeeAction());
    }};

    public static Action getDefaultAction() {
        return defaultAction;
    }

    public static Action getAction(String action) {
        return actions.get(action);
    }

    public static Map<String, Action> getActions() {
        return actions;
    }
}
