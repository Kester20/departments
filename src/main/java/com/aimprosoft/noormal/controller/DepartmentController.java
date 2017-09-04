package com.aimprosoft.noormal.controller;

import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.exception.ValidationException;
import com.aimprosoft.noormal.model.Department;
import com.aimprosoft.noormal.service.DepartmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.aimprosoft.noormal.util.Constants.ServiceConstants.DEPARTMENT_ID;

/**
 * @author Arsalan
 */
@Controller
@RequestMapping({"/", "/department"})
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping
    public String getRootPage() {
        return "/index";
    }

    @ResponseBody
    @RequestMapping("/getAll/{page}")
    public List<Department> getAllDepartments(@PathVariable Integer page) throws DaoException, JsonProcessingException {
        return departmentService.findDepartments(page);
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public Department saveDepartment(@RequestParam(DEPARTMENT_ID) Long departmentId) throws DaoException {
        return departmentService.findOne(departmentId);
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void saveDepartment(Department department) throws DaoException, ValidationException {
        departmentService.saveDepartment(department);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public void deleteDepartment(Department department) throws DaoException {
        departmentService.deleteDepartment(department);
    }

    @ResponseBody
    @RequestMapping("/getTotal")
    public Integer getTotalDepartments() throws DaoException {
        return departmentService.getTotalDepartments();
    }
}
