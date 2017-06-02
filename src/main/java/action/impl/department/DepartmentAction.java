package action.impl.department;

import action.Action;
import exception.DaoException;
import exception.ValidationException;
import model.Department;
import service.DepartmentService;
import util.FormatUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static util.Constants.ContextConstants.DEPARTMENT_SERVICE;
import static util.Constants.Pathways.ROOT_PATH;
import static util.Constants.Pathways.SAVE_DEPARTMENT_PATH;
import static util.Constants.ServiceConstants.DEPARTMENT_ID;
import static util.Constants.ServiceConstants.ERROR_INPUT;
import static util.Constants.ServiceConstants.NAME;

/**
 * @author Arsalan
 */
public class DepartmentAction implements Action {

    private DepartmentService departmentService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DaoException {
        if (departmentService == null) {
            this.departmentService = (DepartmentService) request.getServletContext().getAttribute(DEPARTMENT_SERVICE);
        }

        Department department = getDepartmentFromRequest(request);

        try {
            departmentService.saveDepartment(department);
        } catch (ValidationException e) {
            String wrongName = department.getName();
            sendError(request, response, wrongName, SAVE_DEPARTMENT_PATH, e);
            return;
        }
        response.sendRedirect(ROOT_PATH);
    }

    private Department getDepartmentFromRequest(HttpServletRequest request) {
        String newName = request.getParameter(NAME);
        String idParameter = request.getParameter(DEPARTMENT_ID);
        Integer departmentId = FormatUtils.getIntFromString(idParameter);
        Department department = new Department();
        department.setId(departmentId);
        department.setName(newName);
        return department;
    }

    private void sendError(HttpServletRequest request, HttpServletResponse response, String name, String path,
                           Exception exception) throws ServletException, IOException {
        ValidationException validationException = (ValidationException) exception;
        Map<String, String> errors = validationException.getErrorMap();
        for (String errorField : errors.keySet()) {
            String message = errors.get(errorField);
            request.setAttribute(errorField, message);
        }
        request.setAttribute(NAME + ERROR_INPUT, name);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }
}
