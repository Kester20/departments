package action.impl.employee;

import action.Action;
import exception.DaoException;
import util.FormatUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static util.Constants.Pathways.CREATE_EMPLOYEE_PATH;
import static util.Constants.Pathways.EDIT_EMPLOYEE_PATH;
import static util.Constants.ServiceConstants.DEPARTMENT_ID;
import static util.Constants.ServiceConstants.EMPLOYEE_ID;

/**
 * @author Arsalan
 */
public class GetEmployeePagesAction implements Action {

    private static GetEmployeePagesAction instance;

    private GetEmployeePagesAction() {
    }

    private static Map<String, String> paths = new HashMap<String, String>() {{
        put(CREATE_EMPLOYEE_PATH, "WEB-INF" + CREATE_EMPLOYEE_PATH + ".jsp");
        put(EDIT_EMPLOYEE_PATH, "WEB-INF" + EDIT_EMPLOYEE_PATH + ".jsp");
    }};

    public static GetEmployeePagesAction getInstance() {
        if (instance == null) {
            instance = new GetEmployeePagesAction();
        }
        return instance;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DaoException {
        String depIdParameter = request.getParameter(DEPARTMENT_ID);
        Integer departmentId = FormatUtils.getIntFromString(depIdParameter);
        String empIdParameter = request.getParameter(EMPLOYEE_ID);
        Integer employeeId = FormatUtils.getIntFromString(empIdParameter);
        request.setAttribute(DEPARTMENT_ID, departmentId);
        request.setAttribute(EMPLOYEE_ID, employeeId);
        String uri = request.getRequestURI();
        String path = paths.get(uri);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }
}
