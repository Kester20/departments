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

import static util.Constants.Pathways.ROOT_PATH;
import static util.Constants.ServiceConstants.ACTION;

/**
 * @author Arsalan
 */
@WebServlet({"/", "/controller"})
public class Controller extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String actionParameter = req.getParameter(ACTION);
            if(actionParameter != null){
                Action action = ActionFactory.getAction(actionParameter);
                String view = action.execute(req, resp);
                if(view != null){
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher(view);
                    requestDispatcher.forward(req, resp);
                }
            }else{
                RequestDispatcher requestDispatcher = req.getRequestDispatcher(ROOT_PATH);
                requestDispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
