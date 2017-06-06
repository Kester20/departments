package ua.aimprosoft.noormal.action.impl.employee;

import ua.aimprosoft.noormal.exception.DaoException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.aimprosoft.noormal.util.Constants.Pathways.SAVE_EMPLOYEE_PATH;

/**
 * @author Arsalan
 */
public class GetEmployeeSavePageAction extends EmployeeAction {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DaoException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF" + SAVE_EMPLOYEE_PATH + ".jsp");
        requestDispatcher.forward(request, response);
    }
}
