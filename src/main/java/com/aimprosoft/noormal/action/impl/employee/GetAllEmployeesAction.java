package com.aimprosoft.noormal.action.impl.employee;

import com.aimprosoft.noormal.action.Action;
import com.aimprosoft.noormal.servicebuilder.model.Department;
import com.aimprosoft.noormal.servicebuilder.model.Employee;
import com.aimprosoft.noormal.servicebuilder.model.impl.DepartmentImpl;
import com.aimprosoft.noormal.servicebuilder.service.EmployeeLocalServiceUtil;
import com.aimprosoft.noormal.util.FormatUtils;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONSerializer;
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

    @Override
    public void execute(ResourceRequest request, ResourceResponse response) throws IOException, SystemException {
        PrintWriter writer = response.getWriter();
        JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
        Long departmentId = FormatUtils.getLongFromString(request.getParameter(DEPARTMENT_ID));
        Department department = new DepartmentImpl();
        department.setDepartmentId(departmentId);
        List<Employee> employees = EmployeeLocalServiceUtil.findByDepartment(departmentId);
        String json = jsonSerializer.serialize(employees);
        writer.write(json);
    }
}
