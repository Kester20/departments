package action.impl.department;

import action.Action;
import action.PageFactory;
import model.Department;
import service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static util.Constants.ContextConstants.DEPARTMENT_SERVICE;
import static util.Constants.Pathways.DEPARTMENTS;

/**
 * @author Arsalan. Created on 29.05.2017.
 */
public class GetAllDepartmentsAction implements Action {

    private DepartmentService departmentService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (departmentService == null) {
            this.departmentService = (DepartmentService) request.getServletContext().getAttribute(DEPARTMENT_SERVICE);
        }
        List<Department> departments = departmentService.getDepartments();
        request.setAttribute(DEPARTMENTS, departments);
        return PageFactory.getPages().get(DEPARTMENTS).execute(request, response);
    }
}
