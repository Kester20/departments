package action.impl.employee;

import action.Action;
import exception.DaoException;
import util.FormatUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Constants.Pathways.SAVE_EMPLOYEE_PATH;
import static util.Constants.ServiceConstants.DEPARTMENT_ID;
import static util.Constants.ServiceConstants.EMPLOYEE_ID;

/**
 * @author Arsalan
 */
public class GetEmployeeSavePageAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DaoException {
        String depIdParameter = request.getParameter(DEPARTMENT_ID);
        Integer departmentId = FormatUtils.getIntFromString(depIdParameter);
        String empIdParameter = request.getParameter(EMPLOYEE_ID);
        Integer employeeId = FormatUtils.getIntFromString(empIdParameter);
        request.setAttribute(DEPARTMENT_ID, departmentId);
        request.setAttribute(EMPLOYEE_ID, employeeId);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF" + SAVE_EMPLOYEE_PATH + ".jsp");
        requestDispatcher.forward(request, response);
    }
}
