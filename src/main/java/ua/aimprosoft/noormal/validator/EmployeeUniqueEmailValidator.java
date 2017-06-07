package ua.aimprosoft.noormal.validator;

import net.sf.oval.constraint.CheckWithCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.aimprosoft.noormal.dao.impl.EmployeeDao;
import ua.aimprosoft.noormal.exception.DaoException;
import ua.aimprosoft.noormal.model.Employee;

import static ua.aimprosoft.noormal.util.Constants.ServiceConstants.EMAIL;

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
            existedEmployee = employeeDao.findOneByCriteria(EMAIL, email);
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
