package com.aimprosoft.noormal.controller;

import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.exception.ValidationException;
import com.aimprosoft.noormal.model.Department;
import com.aimprosoft.noormal.model.Employee;
import com.aimprosoft.noormal.service.DepartmentService;
import com.aimprosoft.noormal.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.aimprosoft.noormal.util.Constants.Pathways.GET_EMPLOYEES_BY_DEPARTMENT;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.DEPARTMENT_ID;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.EMPLOYEE_ID;

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

    @ResponseBody
    @RequestMapping("/getByDepartment")
    public List<Employee> getByDepartment(Department department) throws DaoException {
        return employeeService.findEmployeesByDepartment(department);
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public Employee saveEmployee(@RequestParam(EMPLOYEE_ID) Long employeeId) throws DaoException {
        return employeeService.findOne(employeeId);
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void saveEmployee(Employee employee,
                               @RequestParam(DEPARTMENT_ID) Long departmentId) throws DaoException, ValidationException {
        Department department = departmentService.findOne(departmentId);
        if (department != null) {
            employee.setDepartment(department);
            employeeService.saveEmployee(employee);
        }
    }

    @ResponseBody
    @RequestMapping("/delete")
    public void deleteEmployee(Employee employee) throws DaoException {
        employeeService.deleteEmployee(employee);
    }
}
