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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.aimprosoft.noormal.util.Constants.Pathways.DEPARTMENTS_PATH;
import static com.aimprosoft.noormal.util.Constants.Pathways.SAVE_DEPARTMENT_PATH;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.DEPARTMENTS;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.DEPARTMENT_ID;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.NAME;

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
    public String saveDepartment(@RequestParam(NAME) String name,
                                 @RequestParam(DEPARTMENT_ID) Long departmentId) throws DaoException, ValidationException {
        Department department = createDepartment(name, departmentId);
        departmentService.saveDepartment(department);
        return "redirect:/";
    }

    @RequestMapping("/delete")
    public String deleteDepartment(@RequestParam(DEPARTMENT_ID) Long departmentId) throws DaoException {
        Department department = createDepartment(departmentId);
        departmentService.deleteDepartment(department);
        return "redirect:/";
    }

    private Department createDepartment(String name, Long departmentId) {
        Department department = createDepartment(departmentId);
        department.setName(name);
        return department;
    }

    private Department createDepartment(Long departmentId) {
        Department department = new Department();
        department.setId(departmentId);
        return department;
    }

}
