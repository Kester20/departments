package action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static util.Constants.Pathways.CREATE_DEPARTMENT_PATH;
import static util.Constants.Pathways.CREATE_EMPLOYEE_PATH;
import static util.Constants.Pathways.DEPARTMENTS_PATH;
import static util.Constants.Pathways.EDIT_DEPARTMENT_PATH;
import static util.Constants.Pathways.EDIT_EMPLOYEE_PATH;
import static util.Constants.Pathways.EMPLOYEES_PATH;
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
        put(DEPARTMENTS_PATH, new GetAllDepartmentsPageAction());
        put(EMPLOYEES_PATH, new GetAllEmployeePageAction());
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
            return "WEB-INF" + CREATE_DEPARTMENT_PATH + ".jsp";
        }
    }

    private static class GetEditDepartmentPageAction implements Action {
        @Override
        public String execute(HttpServletRequest request, HttpServletResponse response) {
            Integer departmentId = getItemId(request, DEPARTMENT_ID);
            request.setAttribute(DEPARTMENT_ID, departmentId);
            return "WEB-INF" + EDIT_DEPARTMENT_PATH + ".jsp";
        }
    }

    private static class GetCreateEmployeePageAction implements Action {
        @Override
        public String execute(HttpServletRequest request, HttpServletResponse response) {
            Integer departmentId = getItemId(request, DEPARTMENT_ID);
            request.setAttribute(DEPARTMENT_ID, departmentId);
            return "WEB-INF" + CREATE_EMPLOYEE_PATH + ".jsp";
        }
    }

    private static class GetEditEmployeePageAction implements Action {
        @Override
        public String execute(HttpServletRequest request, HttpServletResponse response) {
            Integer employeeId = getItemId(request, EMPLOYEE_ID);
            Integer departmentId = getItemId(request, DEPARTMENT_ID);
            request.setAttribute(EMPLOYEE_ID, employeeId);
            request.setAttribute(DEPARTMENT_ID, departmentId);
            return "WEB-INF" + EDIT_EMPLOYEE_PATH + ".jsp";
        }
    }

    private static class GetAllDepartmentsPageAction implements Action {
        @Override
        public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            return "WEB-INF" + DEPARTMENTS_PATH + ".jsp";
        }
    }

    private static class GetAllEmployeePageAction implements Action {
        @Override
        public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            return "WEB-INF" + EMPLOYEES_PATH + ".jsp";
        }
    }

    private static Integer getItemId(HttpServletRequest request, String itemName) {
        String itemId = request.getParameter(itemName);
        return itemId == null ? null : Integer.parseInt(itemId);
    }
}
