package com.aimprosoft.noormal.controller;

import com.aimprosoft.noormal.exception.ValidationException;
import com.aimprosoft.noormal.servicebuilder.model.Department;
import com.aimprosoft.noormal.servicebuilder.model.Employee;
import com.aimprosoft.noormal.servicebuilder.model.impl.DepartmentImpl;
import com.aimprosoft.noormal.servicebuilder.model.impl.EmployeeImpl;
import com.aimprosoft.noormal.servicebuilder.service.DepartmentLocalServiceUtil;
import com.aimprosoft.noormal.servicebuilder.service.EmployeeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONSerializer;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.aimprosoft.noormal.util.Constants.ServiceConstants.DATE_FORMAT;

/**
 * @author Arsalan
 */
@Controller
@RequestMapping("VIEW")
public class Portlet {

    @RenderMapping
    public String getRootPage() {
        return "index";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @ResourceMapping("getAllDepartments")
    public void getAllDepartments(ResourceResponse response) throws SystemException, IOException {
        PrintWriter writer = response.getWriter();
        JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
        List<Department> departments = DepartmentLocalServiceUtil.getDepartments(0, Integer.MAX_VALUE);
        writer.write(jsonSerializer.serialize(departments));
    }

    @ResourceMapping("saveDepartment")
    public void saveDepartment(ResourceRequest request, ResourceResponse response, DepartmentImpl department) throws SystemException, PortalException, IOException {
        if (request.getMethod().equals("GET")) {
            PrintWriter writer = response.getWriter();
            JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
            Department existedDepartment = DepartmentLocalServiceUtil.getDepartment(department.getDepartmentId());
            writer.write(jsonSerializer.serialize(existedDepartment));
        } else {
            DepartmentLocalServiceUtil.updateDepartment(department);
        }
    }

    @ResourceMapping("deleteDepartment")
    public void deleteDepartment(DepartmentImpl department) throws SystemException, PortalException {
        DepartmentLocalServiceUtil.deleteDepartment(department.getDepartmentId());
    }

    @ExceptionHandler(SystemException.class)
    public void handleValidationException(SystemException exception, ResourceResponse response) throws IOException {
        if (exception.getCause() instanceof ValidationException) {
            ValidationException validationException = (ValidationException) exception.getCause();
            PrintWriter writer = response.getWriter();
            Map<String, String> errors = validationException.getErrorMap();
            JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
            response.setProperty(ResourceResponse.HTTP_STATUS_CODE, Integer.toString(HttpServletResponse.SC_BAD_REQUEST));
            writer.write(jsonSerializer.serialize(errors));
        }
    }

    @ResourceMapping("getAllEmployees")
    public void getAllEmployees(ResourceResponse response, DepartmentImpl department) throws IOException, SystemException {
        PrintWriter writer = response.getWriter();
        JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
        List<Employee> employees = EmployeeLocalServiceUtil.findByDepartment(department.getDepartmentId());
        writer.write(jsonSerializer.serialize(employees));
    }

    @ResourceMapping("saveEmployee")
    public void saveEmployee(ResourceRequest request, ResourceResponse response, EmployeeImpl employee) throws IOException, SystemException, PortalException {
        if (request.getMethod().equals("GET")) {
            PrintWriter writer = response.getWriter();
            JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
            Employee existedEmployee = EmployeeLocalServiceUtil.getEmployee(employee.getEmployeeId());
            writer.write(jsonSerializer.serialize(existedEmployee));
        } else {
            EmployeeLocalServiceUtil.updateEmployee(employee);
        }
    }

    @ResourceMapping("deleteEmployee")
    public void deleteEmployee(DepartmentImpl department, EmployeeImpl employee) throws SystemException {
        employee.setDepartment(department.getDepartmentId());
        EmployeeLocalServiceUtil.deleteEmployee(employee);
    }
}
