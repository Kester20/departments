package com.aimprosoft.noormal.action.impl.department;

import com.aimprosoft.noormal.action.ValidationErrorResponder;
import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.exception.ValidationException;
import com.aimprosoft.noormal.model.Department;
import com.aimprosoft.noormal.service.DepartmentService;
import com.aimprosoft.noormal.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
            response.sendRedirect(Constants.Pathways.ROOT_PATH);
        } catch (ValidationException e) {
            ValidationErrorResponder.sendError(request, response, Constants.Pathways.SAVE_DEPARTMENT_PATH, e);
        }
    }
}
