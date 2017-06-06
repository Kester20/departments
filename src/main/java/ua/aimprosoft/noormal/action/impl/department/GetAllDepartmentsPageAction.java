package ua.aimprosoft.noormal.action.impl.department;

import ua.aimprosoft.noormal.exception.DaoException;
import ua.aimprosoft.noormal.model.Department;
import ua.aimprosoft.noormal.service.DepartmentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ua.aimprosoft.noormal.util.Constants.ContextConstants.DEPARTMENT_SERVICE;
import static ua.aimprosoft.noormal.util.Constants.Pathways.DEPARTMENTS_PATH;
import static ua.aimprosoft.noormal.util.Constants.ServiceConstants.DEPARTMENTS;

/**
 * @author Arsalan. Created on 29.05.2017.
 */
public class GetAllDepartmentsPageAction extends DepartmentAction {

    private DepartmentService departmentService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DaoException {
        if (departmentService == null) {
            this.departmentService = (DepartmentService) request.getServletContext().getAttribute(DEPARTMENT_SERVICE);
        }

        List<Department> departments = departmentService.findDepartments();
        request.setAttribute(DEPARTMENTS, departments);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF" + DEPARTMENTS_PATH + ".jsp");
        requestDispatcher.forward(request, response);
    }
}
