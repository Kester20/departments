package ua.aimprosoft.noormal.validator;

import net.sf.oval.constraint.CheckWithCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.aimprosoft.noormal.dao.impl.DepartmentDao;
import ua.aimprosoft.noormal.exception.DaoException;
import ua.aimprosoft.noormal.model.Department;

import static ua.aimprosoft.noormal.util.Constants.ServiceConstants.NAME;

/**
 * @author Arsalan
 */
@Component
public class DepartmentUniqueNameValidator implements CheckWithCheck.SimpleCheck {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate) {
        Department department = (Department) validatedObject;
        String departmentName = (String) valueToValidate;
        Department existedDepartment = null;

        try {
            existedDepartment = departmentDao.findOneByCriteria(NAME, departmentName);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        if (existedDepartment != null && existedDepartment.getName().equals(departmentName) &&
                existedDepartment.getId() != department.getId()) {
            return false;
        }
        return true;
    }
}
