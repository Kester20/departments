package action.impl.department;

import action.Action;
import util.FormatUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static util.Constants.Pathways.CREATE_DEPARTMENT_PATH;
import static util.Constants.Pathways.EDIT_DEPARTMENT_PATH;
import static util.Constants.ServiceConstants.DEPARTMENT_ID;
import static util.Constants.ServiceConstants.EMPLOYEE_ID;

/**
 * @author Arsalan. Created on 02.06.2017.
 */
public class GetDepartmentPagesAction implements Action {

    private static GetDepartmentPagesAction instance;

    private GetDepartmentPagesAction() {
    }

    private static Map<String, String> paths = new HashMap<String, String>() {{
        put(CREATE_DEPARTMENT_PATH, "WEB-INF" + CREATE_DEPARTMENT_PATH + ".jsp");
        put(EDIT_DEPARTMENT_PATH, "WEB-INF" + EDIT_DEPARTMENT_PATH + ".jsp");
    }};

    public static GetDepartmentPagesAction getInstance() {
        if (instance == null) {
            instance = new GetDepartmentPagesAction();
        }
        return instance;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String depIdParameter = request.getParameter(DEPARTMENT_ID);
        Integer departmentId = FormatUtils.getIntFromString(depIdParameter);
        request.setAttribute(DEPARTMENT_ID, departmentId);
        String uri = request.getRequestURI();
        String path = paths.get(uri);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }
}
