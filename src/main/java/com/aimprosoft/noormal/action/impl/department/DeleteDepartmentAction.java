package com.aimprosoft.noormal.action.impl.department;

import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.model.Department;
import com.aimprosoft.noormal.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.aimprosoft.noormal.service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Arsalan
 */
@Controller("/deleteDepartment")
public class DeleteDepartmentAction extends DepartmentAction {

    private DepartmentService departmentService;

    @Autowired
    public DeleteDepartmentAction(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DaoException {
        Department department = getDepartmentFromRequest(request);
        departmentService.deleteDepartment(department);
        response.sendRedirect(Constants.Pathways.ROOT_PATH);
    }
}
