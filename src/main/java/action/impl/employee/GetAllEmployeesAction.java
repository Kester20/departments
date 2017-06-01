package action.impl.employee;

import action.Action;
import exception.DaoException;
import model.Department;
import model.Employee;
import page.Page;
import page.PageFactory;
import service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static util.Constants.ContextConstants.DEPARTMENT_SERVICE;
import static util.Constants.ContextConstants.EMPLOYEE_SERVICE;
import static util.Constants.Pathways.EMPLOYEES_PATH;
import static util.Constants.ServiceConstants.DEPARTMENT_ID;
import static util.Constants.ServiceConstants.EMPLOYEES;

/**
 * @author Arsalan
 */
public class GetAllEmployeesAction implements Action {

    private EmployeeService employeeService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (employeeService == null) {
            this.employeeService = (EmployeeService) request.getServletContext().getAttribute(EMPLOYEE_SERVICE);
        }

        String idParameter = request.getParameter(DEPARTMENT_ID);
        Integer departmentId = idParameter == null ? null : Integer.parseInt(idParameter);

        Department department = new Department();
        department.setId(departmentId);

        List<Employee> employees = null;
        try {
            employees = employeeService.getEmployeesFromDepartment(department);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        request.setAttribute(DEPARTMENT_ID, departmentId);
        request.setAttribute(EMPLOYEES, employees);

        Page page = PageFactory.getPage(EMPLOYEES_PATH);
        page.show(request, response);
    }
}
