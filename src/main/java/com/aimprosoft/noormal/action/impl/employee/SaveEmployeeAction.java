package com.aimprosoft.noormal.action.impl.employee;

import com.aimprosoft.noormal.action.Action;
import com.aimprosoft.noormal.exception.ValidationException;
import com.aimprosoft.noormal.servicebuilder.model.Department;
import com.aimprosoft.noormal.servicebuilder.model.Employee;
import com.aimprosoft.noormal.servicebuilder.model.impl.EmployeeImpl;
import com.aimprosoft.noormal.servicebuilder.service.DepartmentLocalServiceUtil;
import com.aimprosoft.noormal.servicebuilder.service.EmployeeLocalServiceUtil;
import com.aimprosoft.noormal.util.FormatUtils;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONSerializer;
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

    @Override
    public void execute(ResourceRequest request, ResourceResponse response) throws IOException, ParseException, ValidationException, SystemException, PortalException {
        Employee employee = getEmployeeFromRequest(request);
        if (employee.getEmployeeId() != 0 && employee.getName() == null) {
            PrintWriter writer = response.getWriter();
            JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
            Employee existedEmployee = EmployeeLocalServiceUtil.getEmployee(employee.getEmployeeId());
            String json = jsonSerializer.serialize(existedEmployee);
            writer.write(json);
        } else {
            EmployeeLocalServiceUtil.updateEmployee(employee);
        }
    }

    private Employee getEmployeeFromRequest(ResourceRequest request) throws ParseException, PortalException, SystemException {
        Department department = getDepartmentFromRequest(request);
        Employee employee = new EmployeeImpl();
        long employeeId = FormatUtils.getLongFromString(request.getParameter(EMPLOYEE_ID));
        String name = request.getParameter(NAME);
        int age = FormatUtils.getIntFromString(request.getParameter(AGE));
        Date date = FormatUtils.getDateFromString(request.getParameter(DATE_OF_BIRTH));
        String email = request.getParameter(EMAIL);
        employee.setEmployeeId(employeeId);
        employee.setName(name);
        employee.setAge(age);
        employee.setDateOfBirth(date);
        employee.setEmail(email);
        if (department != null) {
            employee.setDepartment(department.getDepartmentId());
        }
        return employee;
    }

    private Department getDepartmentFromRequest(ResourceRequest request) throws SystemException, PortalException {
        Department department = null;
        long departmentId = FormatUtils.getLongFromString(request.getParameter(DEPARTMENT_ID));
        if (departmentId != 0) {
            department = DepartmentLocalServiceUtil.getDepartment(departmentId);
        }
        return department;
    }
}

