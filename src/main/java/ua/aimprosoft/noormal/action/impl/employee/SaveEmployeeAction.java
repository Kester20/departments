package ua.aimprosoft.noormal.action.impl.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ua.aimprosoft.noormal.exception.DaoException;
import ua.aimprosoft.noormal.exception.ValidationException;
import ua.aimprosoft.noormal.model.Department;
import ua.aimprosoft.noormal.model.Employee;
import ua.aimprosoft.noormal.service.DepartmentService;
import ua.aimprosoft.noormal.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.aimprosoft.noormal.action.ValidationErrorResponder.sendError;
import static ua.aimprosoft.noormal.util.Constants.Pathways.GET_ALL_EMPLOYEE_PATH;
import static ua.aimprosoft.noormal.util.Constants.Pathways.SAVE_EMPLOYEE_PATH;

/**
 * @author Arsalan
 */
@Controller("saveEmployeeAction")
public class SaveEmployeeAction extends EmployeeAction {

    private EmployeeService employeeService;
    private DepartmentService departmentService;

    @Autowired
    public SaveEmployeeAction(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DaoException {
        Long departmentId = getDepartmentIdFromRequest(request);
        Employee employee = getEmployeeFromRequest(request);
        Department department = departmentService.findOne(departmentId);
        employee.setDepartment(department);

        try {
            employeeService.saveEmployee(employee);
        } catch (ValidationException e) {
            sendError(request, response, SAVE_EMPLOYEE_PATH, e);
            return;
        }
        response.sendRedirect(GET_ALL_EMPLOYEE_PATH + "?departmentId=" + departmentId);
    }
}
