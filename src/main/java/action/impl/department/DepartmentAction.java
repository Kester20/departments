package action.impl.department;

import action.Action;
import action.ActionFactory;
import exception.DaoException;
import exception.ValidationException;
import model.Department;
import page.Page;
import page.PageFactory;
import service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static util.Constants.ContextConstants.DEPARTMENT_SERVICE;
import static util.Constants.Messages.DEPARTMENT_WITH_THIS_NAME_IS_ALREADY_EXIST;
import static util.Constants.Pathways.CREATE_DEPARTMENT_PATH;
import static util.Constants.Pathways.EDIT_DEPARTMENT_PATH;
import static util.Constants.Pathways.GET_ALL_DEPARTMENTS_PATH;
import static util.Constants.ServiceConstants.DEPARTMENT_ID;
import static util.Constants.ServiceConstants.ERROR_INPUT;
import static util.Constants.ServiceConstants.ERROR_TEXT;
import static util.Constants.ServiceConstants.NAME;

/**
 * @author Arsalan
 */
public class DepartmentAction implements Action {

    private DepartmentService departmentService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (departmentService == null) {
            this.departmentService = (DepartmentService) request.getServletContext().getAttribute(DEPARTMENT_SERVICE);
        }

        String newName = request.getParameter(NAME);
        String idParameter = request.getParameter(DEPARTMENT_ID);
        Integer departmentId = idParameter == null || idParameter.equals("") ? null : Integer.parseInt(idParameter);

        if (departmentId != null) {
            Department department = new Department();
            department.setId(departmentId);
            department.setName(newName);

            try {
                departmentService.updateDepartment(department);
                clearErrors(request);
            } catch (ValidationException e) {
                sendError(request, e);
                Page page = PageFactory.getPage(EDIT_DEPARTMENT_PATH);
                page.show(request, response);
                return;
            } catch (DaoException e) {
                e.printStackTrace();
            }

        } else {
            Department department = new Department();
            department.setName(newName);

            try {
                departmentService.createDepartment(department);
            } catch (ValidationException e) {
                sendError(request, e);
                Page page = PageFactory.getPage(CREATE_DEPARTMENT_PATH);
                page.show(request, response);
                return;
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
        Action action = ActionFactory.getAction(GET_ALL_DEPARTMENTS_PATH);
        action.execute(request, response);
    }

    private void sendError(HttpServletRequest request, Exception exception) throws ServletException, IOException {
        ValidationException validationException = (ValidationException) exception;
        Map<String, String> errors = validationException.getErrorMap();
        for (String errorField: errors.keySet()) {
            String message = errors.get(errorField);
            request.setAttribute(errorField, message);
        }
    }

    private void clearErrors(HttpServletRequest request) {
        request.removeAttribute(ERROR_INPUT);
        request.removeAttribute(ERROR_TEXT);
    }
}
