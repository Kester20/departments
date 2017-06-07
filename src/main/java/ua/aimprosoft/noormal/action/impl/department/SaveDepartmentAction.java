package ua.aimprosoft.noormal.action.impl.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ua.aimprosoft.noormal.exception.DaoException;
import ua.aimprosoft.noormal.exception.ValidationException;
import ua.aimprosoft.noormal.model.Department;
import ua.aimprosoft.noormal.service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.aimprosoft.noormal.action.ValidationErrorResponder.sendError;
import static ua.aimprosoft.noormal.util.Constants.Pathways.ROOT_PATH;
import static ua.aimprosoft.noormal.util.Constants.Pathways.SAVE_DEPARTMENT_PATH;

/**
 * @author Arsalan
 */
@Controller("/departmentAction")
public class SaveDepartmentAction extends DepartmentAction {

    private DepartmentService departmentService;

    @Autowired
    public SaveDepartmentAction(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DaoException {
        Department department = getDepartmentFromRequest(request);
        try {
            departmentService.saveDepartment(department);
            response.sendRedirect(ROOT_PATH);
        } catch (ValidationException e) {
            sendError(request, response, SAVE_DEPARTMENT_PATH, e);
        }
    }
}
