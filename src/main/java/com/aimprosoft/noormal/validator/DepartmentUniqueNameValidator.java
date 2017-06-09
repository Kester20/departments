package com.aimprosoft.noormal.validator;

import net.sf.oval.constraint.CheckWithCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.aimprosoft.noormal.dao.impl.DepartmentDao;
import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.model.Department;

import static com.aimprosoft.noormal.util.Constants.ServiceConstants.NAME;

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
                existedDepartment.getDepartmentId() != department.getDepartmentId()) {
            return false;
        }
        return true;
    }
}
