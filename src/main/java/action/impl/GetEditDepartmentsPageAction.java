package action.impl;

import action.Action;
import action.ActionFactory;
import service.DepartmentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static util.Constants.ContextConstants.DEPARTMENT_SERVICE;
import static util.Constants.PATHWAYS.EDIT_DEPARTMENT;
import static util.Constants.PATHWAYS.GET_ALL_DEPARTMENTS_PATH;
import static util.Constants.ServiceConstants.GET_PAGE;
import static util.Constants.ServiceConstants.ID;

/**
 * @author Arsalan
 */
public class GetEditDepartmentsPageAction implements Action {

    private DepartmentService departmentService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (departmentService == null) {
            this.departmentService = (DepartmentService) request.getServletContext().getAttribute(DEPARTMENT_SERVICE);
        }

        String idParameter = request.getParameter(ID);
        int id = idParameter == null ? 0 : Integer.parseInt(idParameter);
        String getPage = request.getParameter(GET_PAGE);
        if (getPage != null) {
            request.setAttribute(ID, id);
            return "WEB-INF\\" + EDIT_DEPARTMENT + ".jsp";
        }
        return ActionFactory.getActions().get(GET_ALL_DEPARTMENTS_PATH).execute(request, response);
    }
}
