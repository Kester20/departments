package com.aimprosoft.noormal.action.impl.employee;

import com.aimprosoft.noormal.action.Action;
import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.servicebuilder.model.Department;
import com.aimprosoft.noormal.servicebuilder.model.Employee;
import com.aimprosoft.noormal.servicebuilder.model.impl.DepartmentImpl;
import com.aimprosoft.noormal.servicebuilder.model.impl.EmployeeImpl;
import com.aimprosoft.noormal.servicebuilder.service.EmployeeLocalServiceUtil;
import com.aimprosoft.noormal.util.FormatUtils;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.stereotype.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;

import static com.aimprosoft.noormal.util.Constants.Actions.DELETE_EMPLOYEE;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.DEPARTMENT_ID;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.EMPLOYEE_ID;


/**
 * @author Arsalan
 */

@Component(DELETE_EMPLOYEE)
public class DeleteEmployeeAction implements Action {

    @Override
    public void execute(ResourceRequest request, ResourceResponse response) throws IOException, DaoException, SystemException {
        Long departmentId = FormatUtils.getLongFromString(request.getParameter(DEPARTMENT_ID));
        Long employeeId = FormatUtils.getLongFromString(request.getParameter(EMPLOYEE_ID));
        Department department = new DepartmentImpl();
        department.setDepartmentId(departmentId);
        Employee employee = new EmployeeImpl();
        employee.setEmployeeId(employeeId);
        employee.setDepartment(department.getDepartmentId());
        EmployeeLocalServiceUtil.deleteEmployee(employee);
    }
}

