package validator;

import dao.DaoFactory;
import dao.impl.EmployeeDao;
import exception.DaoException;
import model.Employee;
import net.sf.oval.constraint.CheckWithCheck;

import static util.Constants.ServiceConstants.EMAIL;

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
