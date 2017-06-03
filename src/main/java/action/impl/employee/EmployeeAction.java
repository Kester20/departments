package action.impl.employee;

import action.impl.AbstractAction;
import exception.DaoException;
import model.Employee;
import util.FormatUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import static util.Constants.ServiceConstants.AGE;
import static util.Constants.ServiceConstants.DATE_OF_BIRTH;
import static util.Constants.ServiceConstants.DEPARTMENT_ID;
import static util.Constants.ServiceConstants.EMAIL;
import static util.Constants.ServiceConstants.EMPLOYEE_ID;
import static util.Constants.ServiceConstants.NAME;

/**
 * @author Arsalan. Created on 03.06.2017.
 */
public abstract class EmployeeAction extends AbstractAction {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DaoException {

    }

    Integer getDepartmentIdFromRequest(HttpServletRequest request) {
        String depIdParameter = request.getParameter(DEPARTMENT_ID);
        return FormatUtils.getIntFromString(depIdParameter);
    }

    Employee getEmployeeFromRequest(HttpServletRequest request) {
        String idParameter = request.getParameter(EMPLOYEE_ID);
        Integer employeeId = FormatUtils.getIntFromString(idParameter);
        String newName = request.getParameter(NAME);
        String ageParameter = request.getParameter(AGE);
        Integer age = FormatUtils.getIntFromString(ageParameter);
        String newDateOfBirth = request.getParameter(DATE_OF_BIRTH);
        String email = request.getParameter(EMAIL);
        LocalDate localDate = FormatUtils.getDateFromString(newDateOfBirth);
        Employee employee = new Employee(employeeId, newName, age, localDate, email);
        return employee;
    }
}
