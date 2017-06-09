package com.aimprosoft.noormal.controller;

import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.exception.ValidationException;
import com.aimprosoft.noormal.model.Department;
import com.aimprosoft.noormal.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static com.aimprosoft.noormal.util.Constants.Pathways.DEPARTMENTS_PATH;
import static com.aimprosoft.noormal.util.Constants.Pathways.SAVE_DEPARTMENT_PATH;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.DEPARTMENTS;

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
    public String getAllDepartments(ModelMap modelMap) throws DaoException {
        List<Department> departments = departmentService.findDepartments();
        modelMap.addAttribute(DEPARTMENTS, departments);
        return DEPARTMENTS_PATH;
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String saveDepartment() {
        return SAVE_DEPARTMENT_PATH;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveDepartment(Department department) throws DaoException, ValidationException {
        departmentService.saveDepartment(department);
        return "redirect:/";
    }

    @RequestMapping("/delete")
    public String deleteDepartment(Department department) throws DaoException {
        departmentService.deleteDepartment(department);
        return "redirect:/";
    }
}
