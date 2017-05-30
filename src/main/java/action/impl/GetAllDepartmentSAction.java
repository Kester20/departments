package action.impl;

import action.Action;
import model.Department;
import service.DepartmentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static util.Constants.ContextConstants.DEPARTMENT_SERVICE;
import static util.Constants.PATHWAYS.DEPARTMENTS;

/**
 * @author Arsalan. Created on 29.05.2017.
 */
public class GetAllDepartmentSAction implements Action {

    private DepartmentService departmentService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if(departmentService == null){
            this.departmentService = (DepartmentService) request.getServletContext().getAttribute(DEPARTMENT_SERVICE);
        }
        List<Department> departments = departmentService.getDepartments();
        request.setAttribute(DEPARTMENTS, departments);
        return "WEB-INF\\" + DEPARTMENTS + ".jsp";
    }
}
