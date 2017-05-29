package controller;

import action.Action;
import action.ActionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Arsalan
 */
public class DepartmentController extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Action action = ActionFactory.getAction(req);
            String view = action.execute(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
