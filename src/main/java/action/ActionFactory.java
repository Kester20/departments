package action;

import action.impl.CreateDepartmentAction;
import action.impl.CreateEmployeeAction;
import action.impl.DeleteDepartmentAction;
import action.impl.DeleteEmployeeAction;
import action.impl.UpdateDepartmentAction;
import action.impl.UpdateEmployeeAction;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Arsalan
 */
public class ActionFactory {

    private static Map<String, Action> actions = new HashMap<String, Action>(){{
        put("POST/createDepartment", new CreateDepartmentAction());
        put("POST/updateDepartment", new UpdateDepartmentAction());
        put("POST/deleteDepartment", new DeleteDepartmentAction());
        put("POST/createEmployee", new CreateEmployeeAction());
        put("POST/updateEmployee", new UpdateEmployeeAction());
        put("POST/deleteEmployee", new DeleteEmployeeAction());
    }};

    public static Action getAction(HttpServletRequest request) {
        return actions.get(request.getMethod() + request.getPathInfo());
    }
}
