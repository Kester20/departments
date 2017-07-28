package com.aimprosoft.noormal.action.impl.department;

import com.aimprosoft.noormal.action.Action;
import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.model.Department;
import com.aimprosoft.noormal.service.DepartmentService;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static com.aimprosoft.noormal.util.Constants.Actions.GET_ALL_DEPARTMENTS;

/**
 * @author Arsalan. Created on 29.05.2017.
 */
@Component(value = GET_ALL_DEPARTMENTS)
public class GetAllDepartmentsAction implements Action {

    private DepartmentService departmentService;

    @Autowired
    public GetAllDepartmentsAction(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public void execute(ResourceRequest request, ResourceResponse response) throws DaoException, IOException {
        PrintWriter writer = response.getWriter();
        JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
        List<Department> departments = departmentService.findDepartments();
        String json = jsonSerializer.serialize(departments);
        writer.write(json);
    }
}
