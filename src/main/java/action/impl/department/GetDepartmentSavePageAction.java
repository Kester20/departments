package action.impl.department;

import action.Action;
import util.FormatUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Constants.Pathways.SAVE_DEPARTMENT_PATH;
import static util.Constants.ServiceConstants.DEPARTMENT_ID;

/**
 * @author Arsalan. Created on 02.06.2017.
 */
public class GetDepartmentSavePageAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String depIdParameter = request.getParameter(DEPARTMENT_ID);
        Integer departmentId = FormatUtils.getIntFromString(depIdParameter);
        request.setAttribute(DEPARTMENT_ID, departmentId);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF" + SAVE_DEPARTMENT_PATH + ".jsp");
        requestDispatcher.forward(request, response);
    }
}
