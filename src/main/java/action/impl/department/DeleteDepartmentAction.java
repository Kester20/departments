package action.impl.department;

import action.Action;
import action.ActionFactory;
import service.DepartmentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static util.Constants.ContextConstants.DEPARTMENT_SERVICE;
import static util.Constants.PATHWAYS.GET_ALL_DEPARTMENTS_PATH;
import static util.Constants.ServiceConstants.DEPARTMENT_ID;

/**
 * @author Arsalan
 */
public class DeleteDepartmentAction implements Action {

    private DepartmentService departmentService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (departmentService == null) {
            this.departmentService = (DepartmentService) request.getServletContext().getAttribute(DEPARTMENT_SERVICE);
        }

        String idParameter = request.getParameter(DEPARTMENT_ID);
        int id = idParameter == null ? 0 : Integer.parseInt(idParameter);
        departmentService.deleteDepartment(id);
        return ActionFactory.getActions().get(GET_ALL_DEPARTMENTS_PATH).execute(request, response);
    }
}
