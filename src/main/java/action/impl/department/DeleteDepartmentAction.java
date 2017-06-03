package action.impl.department;

import exception.DaoException;
import model.Department;
import service.DepartmentService;
import service.impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Constants.ContextConstants.DEPARTMENT_SERVICE;
import static util.Constants.Pathways.ROOT_PATH;

/**
 * @author Arsalan
 */
public class DeleteDepartmentAction extends DepartmentAction {

    private DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DaoException {
        if (departmentService == null) {
            this.departmentService = (DepartmentService) request.getServletContext().getAttribute(DEPARTMENT_SERVICE);
        }

        Department department = getDepartmentFromRequest(request);
        departmentService.deleteDepartment(department);
        response.sendRedirect(ROOT_PATH);
    }
}
