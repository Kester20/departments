package com.aimprosoft.noormal.action.impl.department;

import com.aimprosoft.noormal.action.Action;
import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.exception.ValidationException;
import com.aimprosoft.noormal.model.Department;
import com.aimprosoft.noormal.service.DepartmentService;
import com.aimprosoft.noormal.util.FormatUtils;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.aimprosoft.noormal.util.Constants.Actions.SAVE_DEPARTMENT;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.DEPARTMENT_ID;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.NAME;

/**
 * @author Arsalan. Created on 02.06.2017.
 */
@Component(value = SAVE_DEPARTMENT)
public class SaveDepartmentAction implements Action {

    private DepartmentService departmentService;

    @Autowired
    public SaveDepartmentAction(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public void execute(ResourceRequest request, ResourceResponse response) throws IOException, DaoException, ValidationException {
        Long departmentId = FormatUtils.getLongFromString(request.getParameter(DEPARTMENT_ID));
        String name = request.getParameter(NAME);

        if (departmentId != null && name == null) {
            PrintWriter writer = response.getWriter();
            JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
            Department department = departmentService.findOne(departmentId);
            String json = jsonSerializer.serialize(department);
            writer.write(json);
        } else {
            Department department = new Department();
            department.setDepartmentId(departmentId);
            department.setName(name);
            departmentService.saveDepartment(department);
        }
    }
}
