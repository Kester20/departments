package action.impl.department;

import action.Action;
import model.Department;
import page.Page;
import page.PageFactory;
import service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static util.Constants.ContextConstants.DEPARTMENT_SERVICE;
import static util.Constants.Pathways.DEPARTMENTS_PATH;
import static util.Constants.ServiceConstants.DEPARTMENTS;

/**
 * @author Arsalan. Created on 29.05.2017.
 */
public class GetAllDepartmentsAction implements Action {

    private DepartmentService departmentService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (departmentService == null) {
            this.departmentService = (DepartmentService) request.getServletContext().getAttribute(DEPARTMENT_SERVICE);
        }
        List<Department> departments = departmentService.getDepartments();
        request.setAttribute(DEPARTMENTS, departments);
        Page page = PageFactory.getPage(DEPARTMENTS_PATH);
        page.show(request, response);
    }
}
