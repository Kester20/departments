package action.impl;

import action.Action;

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
import static util.Constants.ServiceConstants.PAGE;

/**
 * @author Arsalan
 */
public class PageFactory implements Action {

    private static Map<String, Action> pages = new HashMap<String, Action>() {{
        put(CREATE_DEPARTMENT_PATH, new GetCreateDepartmentPageAction());
        put(EDIT_DEPARTMENT_PATH, new GetEditDepartmentPageAction());
        put(CREATE_EMPLOYEE_PATH, new GetCreateEmployeePageAction());
        put(EDIT_EMPLOYEE_PATH, new GetEditEmployeePageAction());
    }};

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter(PAGE);
        return pages.get(page).execute(request, response);
    }

    public static Map<String, Action> getPages() {
        return pages;
    }

    private static class GetCreateDepartmentPageAction implements Action {
        @Override
        public String execute(HttpServletRequest request, HttpServletResponse response) {
            return "WEB-INF\\" + CREATE_DEPARTMENT_PATH + ".jsp";
        }
    }

    private static class GetEditDepartmentPageAction implements Action {
        @Override
        public String execute(HttpServletRequest request, HttpServletResponse response) {
            String idParameter = request.getParameter(DEPARTMENT_ID);
            Integer departmentId = idParameter == null || idParameter.equals("") ? null : Integer.parseInt(idParameter);
            request.setAttribute(DEPARTMENT_ID, departmentId);
            return "WEB-INF\\" + EDIT_DEPARTMENT_PATH + ".jsp";
        }
    }

    private static class GetCreateEmployeePageAction implements Action {
        @Override
        public String execute(HttpServletRequest request, HttpServletResponse response) {
            String depIdParameter = request.getParameter(DEPARTMENT_ID);
            Integer departmentId = depIdParameter == null ? null : Integer.parseInt(depIdParameter);

            request.setAttribute(DEPARTMENT_ID, departmentId);
            return "WEB-INF\\" + CREATE_EMPLOYEE_PATH + ".jsp";
        }
    }

    private static class GetEditEmployeePageAction implements Action {
        @Override
        public String execute(HttpServletRequest request, HttpServletResponse response) {
            String idParameter = request.getParameter(EMPLOYEE_ID);
            Integer id = idParameter == null ? null : Integer.parseInt(idParameter);

            String depIdParameter = request.getParameter(DEPARTMENT_ID);
            Integer departmentId = depIdParameter == null ? null : Integer.parseInt(depIdParameter);

            request.setAttribute(EMPLOYEE_ID, id);
            request.setAttribute(DEPARTMENT_ID, departmentId);
            return "WEB-INF\\" + EDIT_EMPLOYEE_PATH + ".jsp";
        }
    }

}
