package action.impl.department;

import action.impl.AbstractAction;
import model.Department;
import util.FormatUtils;

import javax.servlet.http.HttpServletRequest;

import static util.Constants.ServiceConstants.DEPARTMENT_ID;
import static util.Constants.ServiceConstants.NAME;

/**
 * @author Arsalan. Created on 03.06.2017.
 */
public abstract class DepartmentAction extends AbstractAction {

    Department getDepartmentFromRequest(HttpServletRequest request) {
        String newName = request.getParameter(NAME);
        String idParameter = request.getParameter(DEPARTMENT_ID);
        Integer departmentId = FormatUtils.getIntFromString(idParameter);
        Department department = new Department();
        department.setId(departmentId);
        department.setName(newName);
        return department;
    }
}
