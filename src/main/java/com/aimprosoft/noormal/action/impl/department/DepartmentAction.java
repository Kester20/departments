package com.aimprosoft.noormal.action.impl.department;

import com.aimprosoft.noormal.model.Department;
import com.aimprosoft.noormal.util.FormatUtils;
import com.aimprosoft.noormal.action.Action;

import javax.servlet.http.HttpServletRequest;

import static com.aimprosoft.noormal.util.Constants.ServiceConstants.DEPARTMENT_ID;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.NAME;

/**
 * @author Arsalan. Created on 03.06.2017.
 */
public abstract class DepartmentAction implements Action {

    Department getDepartmentFromRequest(HttpServletRequest request) {
        String newName = request.getParameter(NAME);
        String idParameter = request.getParameter(DEPARTMENT_ID);
        Long departmentId = FormatUtils.getLongFromString(idParameter);
        Department department = new Department();
        department.setId(departmentId);
        department.setName(newName);
        return department;
    }
}
