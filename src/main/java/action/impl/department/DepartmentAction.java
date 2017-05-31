package action.impl.department;

import action.Action;
import action.ActionFactory;
import action.PageFactory;
import service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (departmentService == null) {
            this.departmentService = (DepartmentService) request.getServletContext().getAttribute(DEPARTMENT_SERVICE);
        }

        String newName = request.getParameter(NAME);
        String idParameter = request.getParameter(DEPARTMENT_ID);
        Integer departmentId = idParameter == null || idParameter.equals("") ? null : Integer.parseInt(idParameter);

        if (departmentId != null) {
            boolean departmentEdited = departmentService.updateDepartment(departmentId, newName);
            if(sendError(departmentEdited, request, newName)){
                return PageFactory.getPages().get(EDIT_DEPARTMENT_PATH).execute(request, response);
            }
        }else{
            boolean departmentAdded = departmentService.createDepartment(newName);
            if(sendError(departmentAdded, request, newName)){
                return PageFactory.getPages().get(CREATE_DEPARTMENT_PATH).execute(request, response);
            }
        }
        return ActionFactory.getActions().get(GET_ALL_DEPARTMENTS_PATH).execute(request, response);
    }

    private boolean sendError(boolean criteria, HttpServletRequest request, String name) throws ServletException, IOException {
        if (!criteria){
            request.setAttribute(ERROR_INPUT, name);
            request.setAttribute(ERROR_TEXT, DEPARTMENT_WITH_THIS_NAME_IS_ALREADY_EXIST);
            return true;
        }else {
            request.removeAttribute(ERROR_INPUT);
            request.removeAttribute(ERROR_TEXT);
        }
        return false;
    }
}
