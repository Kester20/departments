package validator;

import dao.DaoFactory;
import dao.DepartmentDao;
import model.Department;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;

import java.util.List;

/**
 * @author Arsalan
 */
public class DepartmentValidator extends AbstractAnnotationCheck<UniqueDepartmentName> {

    private static Validator validator = new Validator();
    private DepartmentDao departmentDao = DaoFactory.getDepartmentDao();

    public static boolean validate(Department department) {
        List<ConstraintViolation> violations = validator.validate(department);
        if (violations.size() > 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate, OValContext oValContext, Validator validator) throws OValException {
        Department department = (Department) validatedObject;
        String departmentName = (String) valueToValidate;
        Department existedDepartment = departmentDao.findOneByName(departmentName);
        if (existedDepartment != null && existedDepartment.getName().equals(departmentName)
                && existedDepartment.getId() != department.getId()) {
            return false;
        }
        return true;
    }
}
