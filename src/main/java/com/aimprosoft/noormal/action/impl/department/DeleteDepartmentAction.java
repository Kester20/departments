package com.aimprosoft.noormal.action.impl.department;

import com.aimprosoft.noormal.action.Action;
import com.aimprosoft.noormal.servicebuilder.model.Department;
import com.aimprosoft.noormal.servicebuilder.model.impl.DepartmentImpl;
import com.aimprosoft.noormal.servicebuilder.service.DepartmentLocalServiceUtil;
import com.aimprosoft.noormal.util.FormatUtils;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
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

    @Override
    public void execute(ResourceRequest request, ResourceResponse response) throws IOException, SystemException, PortalException {
        long departmentId = FormatUtils.getLongFromString(request.getParameter(DEPARTMENT_ID));
        Department department = new DepartmentImpl();
        department.setDepartmentId(departmentId);
        DepartmentLocalServiceUtil.deleteDepartment(departmentId);
    }
}
