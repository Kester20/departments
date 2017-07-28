package com.aimprosoft.noormal.action.impl.employee;

import com.aimprosoft.noormal.action.Action;
import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.exception.ValidationException;
import com.aimprosoft.noormal.model.Department;
import com.aimprosoft.noormal.model.Employee;
import com.aimprosoft.noormal.service.DepartmentService;
import com.aimprosoft.noormal.service.EmployeeService;
import com.aimprosoft.noormal.util.FormatUtils;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;

import static com.aimprosoft.noormal.util.Constants.Actions.SAVE_EMPLOYEE;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.AGE;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.DATE_OF_BIRTH;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.DEPARTMENT_ID;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.EMAIL;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.EMPLOYEE_ID;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.NAME;

/**
 * @author Arsalan
 */
@Component(SAVE_EMPLOYEE)
public class SaveEmployeeAction implements Action {

    private EmployeeService employeeService;
    private DepartmentService departmentService;

    @Autowired
    public SaveEmployeeAction(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @Override
    public void execute(ResourceRequest request, ResourceResponse response) throws IOException, DaoException, ParseException, ValidationException {
        Employee employee = getEmployeeFromRequest(request);
        if (employee.getEmployeeId() != null && employee.getName() == null) {
            PrintWriter writer = response.getWriter();
            JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
            Employee existedEmployee = employeeService.findOne(employee.getEmployeeId());
            String json = jsonSerializer.serialize(existedEmployee);
            writer.write(json);
        } else {
            employeeService.saveEmployee(employee);
        }
    }

    private Employee getEmployeeFromRequest(ResourceRequest request) throws ParseException, DaoException {
        Department department = getDepartmentFromRequest(request);
        Employee employee = new Employee();
        Long employeeId = FormatUtils.getLongFromString(request.getParameter(EMPLOYEE_ID));
        String name = request.getParameter(NAME);
        Integer age = FormatUtils.getIntFromString(request.getParameter(AGE));
        Date date = FormatUtils.getDateFromString(request.getParameter(DATE_OF_BIRTH));
        String email = request.getParameter(EMAIL);
        employee.setEmployeeId(employeeId);
        employee.setName(name);
        employee.setAge(age);
        employee.setDateOfBirth(date);
        employee.setEmail(email);
        employee.setDepartment(department);
        return employee;
    }

    private Department getDepartmentFromRequest(ResourceRequest request) throws DaoException {
        Department department = null;
        Long departmentId = FormatUtils.getLongFromString(request.getParameter(DEPARTMENT_ID));
        if (departmentId != null) {
            department = departmentService.findOne(departmentId);
        }
        return department;
    }
}
