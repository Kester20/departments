package action.impl.department;

import action.Action;
import action.ActionFactory;
import exception.DaoException;
import model.Department;
import service.DepartmentService;
import service.impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Constants.ContextConstants.DEPARTMENT_SERVICE;
import static util.Constants.Pathways.GET_ALL_DEPARTMENTS_PATH;
import static util.Constants.ServiceConstants.DEPARTMENT_ID;

/**
 * @author Arsalan
 */
public class DeleteDepartmentAction implements Action {

    private DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (departmentService == null) {
            this.departmentService = (DepartmentService) request.getServletContext().getAttribute(DEPARTMENT_SERVICE);
        }

        String idParameter = request.getParameter(DEPARTMENT_ID);
        Integer departmentId = idParameter == null || idParameter.equals("") ? null : Integer.parseInt(idParameter);
        Department department = new Department();
        department.setId(departmentId);

        try {
            departmentService.deleteDepartment(department);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        Action action = ActionFactory.getAction(GET_ALL_DEPARTMENTS_PATH);
        action.execute(request, response);
    }
}
