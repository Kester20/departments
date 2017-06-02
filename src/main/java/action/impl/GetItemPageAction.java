package action.impl;

import action.Action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static util.Constants.Pathways.CREATE_DEPARTMENT_PATH;
import static util.Constants.Pathways.CREATE_EMPLOYEE_PATH;
import static util.Constants.Pathways.EDIT_DEPARTMENT_PATH;
import static util.Constants.Pathways.EDIT_EMPLOYEE_PATH;
import static util.Constants.ServiceConstants.DEPARTMENT_ID;
import static util.Constants.ServiceConstants.EMPLOYEE_ID;

/**
 * @author Arsalan. Created on 02.06.2017.
 */
public class GetItemPageAction implements Action {

    private GetItemPageAction() {
    }

    ;
    private static GetItemPageAction instance;
    private static Map<String, String> paths = new HashMap<String, String>() {{
        put(CREATE_DEPARTMENT_PATH, "WEB-INF" + CREATE_DEPARTMENT_PATH + ".jsp");
        put(EDIT_DEPARTMENT_PATH, "WEB-INF" + EDIT_DEPARTMENT_PATH + ".jsp");
        put(CREATE_EMPLOYEE_PATH, "WEB-INF" + CREATE_EMPLOYEE_PATH + ".jsp");
        put(EDIT_EMPLOYEE_PATH, "WEB-INF" + EDIT_EMPLOYEE_PATH + ".jsp");
    }};

    public static GetItemPageAction getInstance() {
        if (instance == null) {
            instance = new GetItemPageAction();
        }
        return instance;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String depIdParameter = request.getParameter(DEPARTMENT_ID);
        Integer departmentId = depIdParameter == null || depIdParameter.equals("") ? null : Integer.parseInt(depIdParameter);
        String empIdParameter = request.getParameter(EMPLOYEE_ID);
        Integer employeeId = empIdParameter == null || empIdParameter.equals("") ? null : Integer.parseInt(empIdParameter);
        request.setAttribute(DEPARTMENT_ID, departmentId);
        request.setAttribute(EMPLOYEE_ID, employeeId);
        String uri = request.getRequestURI();
        String path = paths.get(uri);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }
}
