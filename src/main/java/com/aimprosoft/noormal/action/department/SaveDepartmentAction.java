package com.aimprosoft.noormal.action.department;

import com.aimprosoft.noormal.exception.ValidationException;
import com.aimprosoft.noormal.model.Department;
import com.aimprosoft.noormal.service.DepartmentService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author Arsalan
 */
public class SaveDepartmentAction extends ActionSupport {

    @Autowired
    private DepartmentService departmentService;
    private Department department;

    @Override
    public String execute() throws Exception {
        if(department.getDepartmentId() != null && department.getName() == null){
            department = departmentService.findOne(department.getDepartmentId());
        }else{
            try {
                departmentService.saveDepartment(department);
            } catch (ValidationException e) {
                Map<String, String> errors = e.getErrorMap();
                for (String key: errors.keySet()) {
                    addFieldError(key, errors.get(key));
                }
                return Action.ERROR;
            }
        }
        return Action.SUCCESS;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
