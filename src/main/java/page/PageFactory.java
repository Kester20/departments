package page;

import javax.servlet.RequestDispatcher;
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
import static util.Constants.Pathways.ROOT_PATH;
import static util.Constants.ServiceConstants.DEPARTMENT_ID;
import static util.Constants.ServiceConstants.EMPLOYEE_ID;

/**
 * @author Arsalan
 */
public class PageFactory {

    private static Map<String, Page> pages = new HashMap<String, Page>() {{
        put(ROOT_PATH, new RootPage());
        put(CREATE_DEPARTMENT_PATH, new GetCreateDepartmentPage());
        put(EDIT_DEPARTMENT_PATH, new GetEditDepartmentPage());
        put(CREATE_EMPLOYEE_PATH, new GetCreateEmployeePage());
        put(EDIT_EMPLOYEE_PATH, new GetEditEmployeePage());
        put(DEPARTMENTS_PATH, new GetAllDepartmentsPage());
        put(EMPLOYEES_PATH, new GetAllEmployeePage());
    }};

    public static Page getPage(String path) {
        return pages.get(path);
    }

    private static class RootPage implements Page {
        @Override
        public void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String path = "WEB-INF" + ROOT_PATH + ".jsp";
            forward(path, request, response);
        }
    }

    private static class GetCreateDepartmentPage implements Page {
        @Override
        public void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String path = "WEB-INF" + CREATE_DEPARTMENT_PATH + ".jsp";
            forward(path, request, response);
        }
    }

    private static class GetEditDepartmentPage implements Page {
        @Override
        public void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Integer departmentId = getItemId(request, DEPARTMENT_ID);
            request.setAttribute(DEPARTMENT_ID, departmentId);
            String path = "WEB-INF" + EDIT_DEPARTMENT_PATH + ".jsp";
            forward(path, request, response);
        }
    }

    private static class GetCreateEmployeePage implements Page {
        @Override
        public void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Integer departmentId = getItemId(request, DEPARTMENT_ID);
            request.setAttribute(DEPARTMENT_ID, departmentId);
            String path = "WEB-INF" + CREATE_EMPLOYEE_PATH + ".jsp";
            forward(path, request, response);
        }
    }

    private static class GetEditEmployeePage implements Page {
        @Override
        public void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Integer employeeId = getItemId(request, EMPLOYEE_ID);
            Integer departmentId = getItemId(request, DEPARTMENT_ID);
            request.setAttribute(EMPLOYEE_ID, employeeId);
            request.setAttribute(DEPARTMENT_ID, departmentId);
            String path = "WEB-INF" + EDIT_EMPLOYEE_PATH + ".jsp";
            forward(path, request, response);
        }
    }

    private static class GetAllDepartmentsPage implements Page {
        @Override
        public void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String path = "WEB-INF" + DEPARTMENTS_PATH + ".jsp";
            forward(path, request, response);
        }
    }

    private static class GetAllEmployeePage implements Page {
        @Override
        public void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String path = "WEB-INF" + EMPLOYEES_PATH + ".jsp";
            forward(path, request, response);
        }
    }

    private static void forward(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

    private static Integer getItemId(HttpServletRequest request, String itemName) {
        String itemId = request.getParameter(itemName);
        return itemId == null ? null : Integer.parseInt(itemId);
    }
}
