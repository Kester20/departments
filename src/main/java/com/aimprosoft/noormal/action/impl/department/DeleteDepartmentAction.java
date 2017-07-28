package com.aimprosoft.noormal.action.impl.department;

import com.aimprosoft.noormal.action.Action;
import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.model.Department;
import com.aimprosoft.noormal.service.DepartmentService;
import com.aimprosoft.noormal.util.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;

import static com.aimprosoft.noormal.util.Constants.Actions.DELETE_DEPARTMENT;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.DEPARTMENT_ID;

/**
 * @author Arsalan
 */
@Component(DELETE_DEPARTMENT)
public class DeleteDepartmentAction implements Action {

    private DepartmentService departmentService;

    @Autowired
    public DeleteDepartmentAction(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public void execute(ResourceRequest request, ResourceResponse response) throws IOException, DaoException {
        Long departmentId = FormatUtils.getLongFromString(request.getParameter(DEPARTMENT_ID));
        Department department = new Department();
        department.setDepartmentId(departmentId);
        departmentService.deleteDepartment(department);
    }
}
