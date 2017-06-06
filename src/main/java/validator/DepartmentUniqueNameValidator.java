package validator;

import dao.DaoFactory;
import dao.impl.DepartmentDao;
import exception.DaoException;
import model.Department;
import net.sf.oval.constraint.CheckWithCheck;

import static util.Constants.Messages.CAN_NOT_FIND_ENTITY;
import static util.Constants.ServiceConstants.NAME;

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
