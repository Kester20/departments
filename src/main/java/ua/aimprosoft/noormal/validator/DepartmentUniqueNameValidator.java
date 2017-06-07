package ua.aimprosoft.noormal.validator;

import net.sf.oval.constraint.CheckWithCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ua.aimprosoft.noormal.config.ApplicationConfig;
import ua.aimprosoft.noormal.dao.DaoFactory;
import ua.aimprosoft.noormal.dao.impl.DepartmentDao;
import ua.aimprosoft.noormal.exception.DaoException;
import ua.aimprosoft.noormal.model.Department;

import static ua.aimprosoft.noormal.util.Constants.ServiceConstants.NAME;

/**
 * @author Arsalan
 */
public class DepartmentUniqueNameValidator implements CheckWithCheck.SimpleCheck {

    private static DepartmentDao departmentDao = initDao();

    private static DepartmentDao initDao(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        return ctx.getBean(DepartmentDao.class);
    }

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
