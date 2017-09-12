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
import org.springframework.web.bind.annotation.RequestParam;
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
    public void getAllDepartments(ResourceResponse response,
                                  @RequestParam Integer page,
                                  @RequestParam Integer itemsPerPage) throws SystemException, IOException {
        int first = (page - 1) * itemsPerPage;
        int second = first + itemsPerPage;
        List<Department> departments = DepartmentLocalServiceUtil.getDepartments(first, second);
        sendResponse(response, departments);
    }

    @ResourceMapping("saveDepartment")
    public void saveDepartment(ResourceRequest request, ResourceResponse response, DepartmentImpl department) throws SystemException, PortalException, IOException {
        if (request.getMethod().equals("GET")) {
            Department existedDepartment = DepartmentLocalServiceUtil.getDepartment(department.getDepartmentId());
            sendResponse(response, existedDepartment);
        } else {
            DepartmentLocalServiceUtil.updateDepartment(department);
        }
    }

    @ResourceMapping("deleteDepartment")
    public void deleteDepartment(DepartmentImpl department) throws SystemException, PortalException {
        DepartmentLocalServiceUtil.deleteDepartment(department.getDepartmentId());
    }

    @ResourceMapping("getAllEmployees")
    public void getAllEmployees(ResourceResponse response, DepartmentImpl department,
                                @RequestParam Integer page,
                                @RequestParam Integer itemsPerPage) throws IOException, SystemException {
        List<Employee> employees = EmployeeLocalServiceUtil.findByDepartment(department.getDepartmentId());
        sendResponse(response, employees);
    }

    @ResourceMapping("saveEmployee")
    public void saveEmployee(ResourceRequest request, ResourceResponse response, EmployeeImpl employee) throws IOException, SystemException, PortalException {
        if (request.getMethod().equals("GET")) {
            Employee existedEmployee = EmployeeLocalServiceUtil.getEmployee(employee.getEmployeeId());
            sendResponse(response, existedEmployee);
        } else {
            EmployeeLocalServiceUtil.updateEmployee(employee);
        }
    }

    @ResourceMapping("deleteEmployee")
    public void deleteEmployee(EmployeeImpl employee) throws SystemException {
        EmployeeLocalServiceUtil.deleteEmployee(employee);
    }

    @ResourceMapping("getTotalDepartments")
    public void getTotalDepartments(ResourceResponse response) throws IOException, SystemException {
        sendResponse(response, DepartmentLocalServiceUtil.getDepartmentsCount());
    }

    @ResourceMapping("getTotalEmployees")
    public void getTotalEmployees(ResourceResponse response, DepartmentImpl department) throws IOException {
        sendResponse(response, 9);
    }

    @ExceptionHandler(SystemException.class)
    public void handleValidationException(SystemException exception, ResourceResponse response) throws IOException {
        if (exception.getCause() instanceof ValidationException) {
            ValidationException validationException = (ValidationException) exception.getCause();
            Map<String, String> errors = validationException.getErrorMap();
            response.setProperty(ResourceResponse.HTTP_STATUS_CODE, Integer.toString(HttpServletResponse.SC_BAD_REQUEST));
            sendResponse(response, errors);
        }
    }

    private void sendResponse(ResourceResponse response, Object result) throws IOException {
        PrintWriter writer = response.getWriter();
        JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
        writer.write(jsonSerializer.serialize(result));
    }
}
