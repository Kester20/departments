package action.impl.department;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Constants.Pathways.SAVE_DEPARTMENT_PATH;

/**
 * @author Arsalan. Created on 02.06.2017.
 */
public class GetDepartmentSavePageAction extends DepartmentAction {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF" + SAVE_DEPARTMENT_PATH + ".jsp");
        requestDispatcher.forward(request, response);
    }
}
