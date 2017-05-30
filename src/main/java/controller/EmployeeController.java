package controller;

import action.Action;
import action.ActionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Arsalan
 */
@WebServlet("/employee_controller")
public class EmployeeController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Action action = ActionFactory.getAction(req);
            String view = action.execute(req, resp);
            if(view != null){
                RequestDispatcher requestDispatcher = req.getRequestDispatcher(view);
                requestDispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
