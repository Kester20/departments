package action.impl.department;

import exception.DaoException;
import exception.ValidationException;
import model.Department;
import service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Constants.ContextConstants.DEPARTMENT_SERVICE;
import static util.Constants.Pathways.ROOT_PATH;
import static util.Constants.Pathways.SAVE_DEPARTMENT_PATH;

/**
 * @author Arsalan
 */
public class SaveDepartmentAction extends DepartmentAction {

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
            sendError(request, response, SAVE_DEPARTMENT_PATH, e);
        }
        response.sendRedirect(ROOT_PATH);
    }
}
