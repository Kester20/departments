package com.aimprosoft.noormal.controller;

import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.exception.ValidationException;
import com.aimprosoft.noormal.model.Department;
import com.aimprosoft.noormal.model.Employee;
import com.aimprosoft.noormal.service.DepartmentService;
import com.aimprosoft.noormal.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

import static com.aimprosoft.noormal.util.Constants.Pathways.EMPLOYEES_PATH;
import static com.aimprosoft.noormal.util.Constants.Pathways.GET_EMPLOYEES_BY_DEPARTMENT;
import static com.aimprosoft.noormal.util.Constants.Pathways.SAVE_EMPLOYEE_PATH;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.AGE;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.DATE_FORMAT;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.DATE_OF_BIRTH;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.DEPARTMENT_ID;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.EMAIL;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.EMPLOYEES;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.EMPLOYEE_FORM;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.EMPLOYEE_ID;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.NAME;

/**
 * @author Arsalan
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;
    private DepartmentService departmentService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @RequestMapping("/getByDepartment")
    public String getByDepartment(ModelMap modelMap, Department department) throws DaoException {
        List<Employee> employees = employeeService.findEmployeesByDepartment(department);
        modelMap.addAttribute(EMPLOYEES, employees);
        return EMPLOYEES_PATH;
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String saveEmployee(ModelMap modelMap) {
        modelMap.addAttribute(EMPLOYEE_FORM, new Employee());
        return SAVE_EMPLOYEE_PATH;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveEmployee(@RequestParam(DEPARTMENT_ID) Long departmentId,
                               @ModelAttribute(EMPLOYEE_FORM) Employee employee) throws DaoException, ValidationException {
        Department department = departmentService.findOne(departmentId);
        if(department != null){
            employee.setDepartment(department);
            employeeService.saveEmployee(employee);
        }
        return "redirect:" + GET_EMPLOYEES_BY_DEPARTMENT + "?departmentId=" + departmentId;
    }

    @RequestMapping("/delete")
    public String deleteEmployee(Employee employee,
                                 @RequestParam(DEPARTMENT_ID) Long departmentId) throws DaoException {
        employeeService.deleteEmployee(employee);
        return "redirect:" + GET_EMPLOYEES_BY_DEPARTMENT + "?departmentId=" + departmentId;
    }
}
