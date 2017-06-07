package ua.aimprosoft.noormal.action.impl.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ua.aimprosoft.noormal.exception.DaoException;
import ua.aimprosoft.noormal.model.Employee;
import ua.aimprosoft.noormal.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.aimprosoft.noormal.util.Constants.Pathways.GET_ALL_EMPLOYEE_PATH;

/**
 * @author Arsalan
 */
@Controller("deleteEmployeeAction")
public class DeleteEmployeeAction extends EmployeeAction {

    private EmployeeService employeeService;

    @Autowired
    public DeleteEmployeeAction(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DaoException {
        Long departmentId = getDepartmentIdFromRequest(request);
        Employee employee = getEmployeeFromRequest(request);
        employeeService.deleteEmployee(employee);
        response.sendRedirect(GET_ALL_EMPLOYEE_PATH + "?departmentId=" + departmentId);
    }
}
