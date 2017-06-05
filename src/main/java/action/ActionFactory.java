package action;


import action.impl.department.DeleteDepartmentAction;
import action.impl.department.GetAllDepartmentsPageAction;
import action.impl.department.GetDepartmentSavePageAction;
import action.impl.department.SaveDepartmentAction;
import action.impl.employee.DeleteEmployeeAction;
import action.impl.employee.GetAllEmployeesPageAction;
import action.impl.employee.GetEmployeeSavePageAction;
import action.impl.employee.SaveEmployeeAction;

import java.util.HashMap;
import java.util.Map;

import static util.Constants.Pathways.DELETE_DEPARTMENT_PATH;
import static util.Constants.Pathways.DELETE_EMPLOYEE_PATH;
import static util.Constants.Pathways.DEPARTMENT_ACTION_PATH;
import static util.Constants.Pathways.EMPLOYEE_ACTION_PATH;
import static util.Constants.Pathways.GET_ALL_EMPLOYEE_PATH;
import static util.Constants.Pathways.ROOT_PATH;
import static util.Constants.Pathways.SAVE_DEPARTMENT_PATH;
import static util.Constants.Pathways.SAVE_EMPLOYEE_PATH;

/**
 * @author Arsalan
 */
public class ActionFactory {

    private static Action defaultAction = new GetAllDepartmentsPageAction();

    private static Map<String, Action> actions = new HashMap<String, Action>() {{
        put(ROOT_PATH, defaultAction);
        put(DEPARTMENT_ACTION_PATH, new SaveDepartmentAction());
        put(DELETE_DEPARTMENT_PATH, new DeleteDepartmentAction());

        put(SAVE_DEPARTMENT_PATH, new GetDepartmentSavePageAction());
        put(SAVE_EMPLOYEE_PATH, new GetEmployeeSavePageAction());

        put(GET_ALL_EMPLOYEE_PATH, new GetAllEmployeesPageAction());
        put(EMPLOYEE_ACTION_PATH, new SaveEmployeeAction());
        put(DELETE_EMPLOYEE_PATH, new DeleteEmployeeAction());
    }};

    public static Action getDefaultAction() {
        return defaultAction;
    }

    public static Action getAction(String action) {
        return actions.get(action);
    }
}
