package ua.aimprosoft.noormal.action.impl.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ua.aimprosoft.noormal.exception.DaoException;
import ua.aimprosoft.noormal.model.Department;
import ua.aimprosoft.noormal.model.Employee;
import ua.aimprosoft.noormal.service.EmployeeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ua.aimprosoft.noormal.util.Constants.Pathways.EMPLOYEES_PATH;
import static ua.aimprosoft.noormal.util.Constants.ServiceConstants.EMPLOYEES;

/**
 * @author Arsalan
 */
@Controller("getAllEmployeesPageAction")
public class GetAllEmployeesPageAction extends EmployeeAction {

    private EmployeeService employeeService;

    @Autowired
    public GetAllEmployeesPageAction(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DaoException {
        Long departmentId = getDepartmentIdFromRequest(request);
        Department department = new Department();
        department.setId(departmentId);
        List<Employee> employees = employeeService.findEmployeesByDepartment(department);
        request.setAttribute(EMPLOYEES, employees);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF" + EMPLOYEES_PATH + ".jsp");
        requestDispatcher.forward(request, response);
    }
}
