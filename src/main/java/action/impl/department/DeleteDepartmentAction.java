package action.impl.department;

import action.Action;
import exception.DaoException;
import model.Department;
import service.DepartmentService;
import service.impl.DepartmentServiceImpl;
import util.FormatUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Constants.ContextConstants.DEPARTMENT_SERVICE;
import static util.Constants.Pathways.ROOT_PATH;
import static util.Constants.ServiceConstants.DEPARTMENT_ID;

/**
 * @author Arsalan
 */
public class DeleteDepartmentAction implements Action {

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

    private Department getDepartmentFromRequest(HttpServletRequest request) {
        String idParameter = request.getParameter(DEPARTMENT_ID);
        Integer departmentId = FormatUtils.getIntFromString(idParameter);
        Department department = new Department();
        department.setId(departmentId);
        return department;
    }
}
