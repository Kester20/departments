package ua.aimprosoft.noormal.action.impl.department;

import ua.aimprosoft.noormal.action.Action;
import ua.aimprosoft.noormal.model.Department;
import ua.aimprosoft.noormal.util.FormatUtils;

import javax.servlet.http.HttpServletRequest;

import static ua.aimprosoft.noormal.util.Constants.ServiceConstants.DEPARTMENT_ID;
import static ua.aimprosoft.noormal.util.Constants.ServiceConstants.NAME;

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
