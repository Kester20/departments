package action.impl.department;

import action.Action;
import action.ActionFactory;
import service.DepartmentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static util.Constants.ContextConstants.DEPARTMENT_SERVICE;
import static util.Constants.PATHWAYS.GET_ALL_DEPARTMENTS_PATH;
import static util.Constants.ServiceConstants.DEPARTMENT_ID;
import static util.Constants.ServiceConstants.NAME;

/**
 * @author Arsalan
 */
public class EditDepartmentAction implements Action {

    private DepartmentService departmentService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (departmentService == null) {
            this.departmentService = (DepartmentService) request.getServletContext().getAttribute(DEPARTMENT_SERVICE);
        }

        String newName = request.getParameter(NAME);
        String idParameter = request.getParameter(DEPARTMENT_ID);
        Integer departmentId = idParameter == null || idParameter.equals("") ? null : Integer.parseInt(idParameter);

        if (departmentId != null) {
            departmentService.updateDepartment(departmentId, newName);
        }
        return ActionFactory.getActions().get(GET_ALL_DEPARTMENTS_PATH).execute(request, response);
    }
}
