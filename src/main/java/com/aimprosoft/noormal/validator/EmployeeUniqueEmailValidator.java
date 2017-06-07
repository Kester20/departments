package com.aimprosoft.noormal.validator;

import com.aimprosoft.noormal.dao.impl.EmployeeDao;
import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.util.Constants;
import net.sf.oval.constraint.CheckWithCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.aimprosoft.noormal.model.Employee;

/**
 * @author Arsalan
 */
@Component
public class EmployeeUniqueEmailValidator implements CheckWithCheck.SimpleCheck {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate) {
        Employee employee = (Employee) validatedObject;
        String email = (String) valueToValidate;
        Employee existedEmployee = null;

        try {
            existedEmployee = employeeDao.findOneByCriteria(Constants.ServiceConstants.EMAIL, email);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        if (existedEmployee != null && existedEmployee.getEmail().equals(email)
                && existedEmployee.getId() != employee.getId()) {
            return false;
        }
        return true;
    }
}
