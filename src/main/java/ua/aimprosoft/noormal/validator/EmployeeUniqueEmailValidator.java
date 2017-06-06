package ua.aimprosoft.noormal.validator;

import ua.aimprosoft.noormal.dao.DaoFactory;
import ua.aimprosoft.noormal.dao.impl.EmployeeDao;
import ua.aimprosoft.noormal.exception.DaoException;
import ua.aimprosoft.noormal.model.Employee;
import net.sf.oval.constraint.CheckWithCheck;

import static ua.aimprosoft.noormal.util.Constants.ServiceConstants.EMAIL;

/**
 * @author Arsalan
 */
public class EmployeeUniqueEmailValidator implements CheckWithCheck.SimpleCheck {

    private EmployeeDao employeeDao = DaoFactory.getEmployeeDao();

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
