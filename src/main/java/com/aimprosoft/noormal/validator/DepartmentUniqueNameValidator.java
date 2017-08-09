package com.aimprosoft.noormal.validator;

import com.aimprosoft.noormal.servicebuilder.NoSuchDepartmentException;
import com.aimprosoft.noormal.servicebuilder.model.Department;
import com.aimprosoft.noormal.servicebuilder.service.DepartmentLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import net.sf.oval.constraint.CheckWithCheck;
import org.springframework.stereotype.Component;

/**
 * @author Arsalan
 */

@Component
public class DepartmentUniqueNameValidator implements CheckWithCheck.SimpleCheck {

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate) {
        boolean result;
        Department department = (Department) validatedObject;
        String departmentName = (String) valueToValidate;
        Department existedDepartment;
        try {
            existedDepartment = DepartmentLocalServiceUtil.findByName(departmentName);
            result = existedDepartment.getDepartmentId() == department.getDepartmentId();
        } catch (NoSuchDepartmentException e) {
            result = true;
        } catch (SystemException e) {
            result = false;
        }
        return result;
    }
}

