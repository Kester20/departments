package com.aimprosoft.noormal.action.impl.employee;

import com.aimprosoft.noormal.action.Action;
import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.model.Department;
import com.aimprosoft.noormal.model.Employee;
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
import java.util.List;

import static com.aimprosoft.noormal.util.Constants.Actions.GET_ALL_EMPLOYEES;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.DEPARTMENT_ID;

/**
 * @author Arsalan
 */
@Component(GET_ALL_EMPLOYEES)
public class GetAllEmployeesAction implements Action {

    private EmployeeService employeeService;

    @Autowired
    public GetAllEmployeesAction(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void execute(ResourceRequest request, ResourceResponse response) throws IOException, DaoException {
        PrintWriter writer = response.getWriter();
        JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
        Long departmentId = FormatUtils.getLongFromString(request.getParameter(DEPARTMENT_ID));
        Department department = new Department();
        department.setDepartmentId(departmentId);
        List<Employee> employees = employeeService.findEmployeesByDepartment(department);
        String json = jsonSerializer.serialize(employees);
        writer.write(json);
    }
}
