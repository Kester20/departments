package com.aimprosoft.noormal.action.impl.employee;

import com.aimprosoft.noormal.util.Constants;
import com.aimprosoft.noormal.util.FormatUtils;
import com.aimprosoft.noormal.action.Action;
import com.aimprosoft.noormal.model.Employee;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Arsalan. Created on 03.06.2017.
 */
public abstract class EmployeeAction implements Action {

    Long getDepartmentIdFromRequest(HttpServletRequest request) {
        String depIdParameter = request.getParameter(Constants.ServiceConstants.DEPARTMENT_ID);
        return FormatUtils.getLongFromString(depIdParameter);
    }

    Employee getEmployeeFromRequest(HttpServletRequest request) {
        String idParameter = request.getParameter(Constants.ServiceConstants.EMPLOYEE_ID);
        Long employeeId = FormatUtils.getLongFromString(idParameter);
        String newName = request.getParameter(Constants.ServiceConstants.NAME);
        String ageParameter = request.getParameter(Constants.ServiceConstants.AGE);
        Integer age = FormatUtils.getIntFromString(ageParameter);
        String newDateOfBirth = request.getParameter(Constants.ServiceConstants.DATE_OF_BIRTH);
        String email = request.getParameter(Constants.ServiceConstants.EMAIL);
        Date date = FormatUtils.getDateFromString(newDateOfBirth);
        Employee employee = new Employee(employeeId, newName, age, date, email);
        return employee;
    }
}
