package ua.aimprosoft.noormal.action;


import org.springframework.stereotype.Component;
import ua.aimprosoft.noormal.action.impl.department.DeleteDepartmentAction;
import ua.aimprosoft.noormal.action.impl.department.GetAllDepartmentsPageAction;
import ua.aimprosoft.noormal.action.impl.department.GetDepartmentSavePageAction;
import ua.aimprosoft.noormal.action.impl.department.SaveDepartmentAction;
import ua.aimprosoft.noormal.action.impl.employee.DeleteEmployeeAction;
import ua.aimprosoft.noormal.action.impl.employee.GetAllEmployeesPageAction;
import ua.aimprosoft.noormal.action.impl.employee.GetEmployeeSavePageAction;
import ua.aimprosoft.noormal.action.impl.employee.SaveEmployeeAction;

import java.util.HashMap;
import java.util.Map;

import static ua.aimprosoft.noormal.util.Constants.Pathways.DELETE_DEPARTMENT_PATH;
import static ua.aimprosoft.noormal.util.Constants.Pathways.DELETE_EMPLOYEE_PATH;
import static ua.aimprosoft.noormal.util.Constants.Pathways.DEPARTMENT_ACTION_PATH;
import static ua.aimprosoft.noormal.util.Constants.Pathways.EMPLOYEE_ACTION_PATH;
import static ua.aimprosoft.noormal.util.Constants.Pathways.GET_ALL_EMPLOYEE_PATH;
import static ua.aimprosoft.noormal.util.Constants.Pathways.ROOT_PATH;
import static ua.aimprosoft.noormal.util.Constants.Pathways.SAVE_DEPARTMENT_PATH;
import static ua.aimprosoft.noormal.util.Constants.Pathways.SAVE_EMPLOYEE_PATH;

/**
 * @author Arsalan
 */
@Component
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
