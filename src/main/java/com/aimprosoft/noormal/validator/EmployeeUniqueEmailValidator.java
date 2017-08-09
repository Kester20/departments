package com.aimprosoft.noormal.validator;

import com.aimprosoft.noormal.servicebuilder.NoSuchEmployeeException;
import com.aimprosoft.noormal.servicebuilder.model.Employee;
import com.aimprosoft.noormal.servicebuilder.service.EmployeeLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import net.sf.oval.constraint.CheckWithCheck;
import org.springframework.stereotype.Component;

/**
 * @author Arsalan
 */

@Component
public class EmployeeUniqueEmailValidator implements CheckWithCheck.SimpleCheck {

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate) {
        boolean result;
        Employee employee = (Employee) validatedObject;
        String email = (String) valueToValidate;
        Employee existedEmployee;
        try {
            existedEmployee = EmployeeLocalServiceUtil.findByEmail(email);
            result = (existedEmployee.getEmployeeId() == employee.getEmployeeId());
        } catch (NoSuchEmployeeException e) {
            result = true;
        } catch (SystemException e) {
            result = false;
        }
        return result;
    }
}

