package validator;

import dao.DaoFactory;
import dao.EmployeeDao;
import model.Employee;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;

import java.util.List;

/**
 * @author Arsalan
 */
public class EmployeeValidator extends AbstractAnnotationCheck<UniqueDepartmentName> {

    private static Validator validator = new Validator();
    private EmployeeDao employeeDao = DaoFactory.getEmployeeDao();

    public static boolean validate(Employee employee) {
        List<ConstraintViolation> violations = validator.validate(employee);
        if (violations.size() > 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate, OValContext oValContext, Validator validator) throws OValException {
        Employee employee = (Employee) validatedObject;
        String email = (String) valueToValidate;
        Employee existedEmployee = employeeDao.findOneByEmail(email);
        if (existedEmployee != null && existedEmployee.getEmail().equals(email)
                && existedEmployee.getId() != employee.getId()) {
            return false;
        }
        return true;
    }
}
