package ua.aimprosoft.noormal.validator;

import ua.aimprosoft.noormal.dao.DaoFactory;
import ua.aimprosoft.noormal.dao.impl.DepartmentDao;
import ua.aimprosoft.noormal.exception.DaoException;
import ua.aimprosoft.noormal.model.Department;
import net.sf.oval.constraint.CheckWithCheck;

import static ua.aimprosoft.noormal.util.Constants.ServiceConstants.NAME;

/**
 * @author Arsalan
 */
public class DepartmentUniqueNameValidator implements CheckWithCheck.SimpleCheck {

    private DepartmentDao departmentDao = DaoFactory.getDepartmentDao();

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate) {
        Department department = (Department) validatedObject;
        String departmentName = (String) valueToValidate;
        Department existedDepartment = null;

        try {
            existedDepartment = departmentDao.findOneByCriteria(NAME, departmentName);
        } catch (DaoException e){
            e.printStackTrace();
        }

        if (existedDepartment != null && existedDepartment.getName().equals(departmentName) &&
                existedDepartment.getId() != department.getId()) {
            return false;
        }
        return true;
    }
}
