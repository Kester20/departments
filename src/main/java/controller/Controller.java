package controller;

import action.Action;
import action.ActionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Constants.Messages.ERROR_CODE;
import static util.Constants.Pathways.ERROR_PAGE_PATH;
import static util.Constants.Pathways.ROOT_PATH;

/**
 * @author Arsalan
 */
@WebServlet("/")
public class Controller extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        Action action = ActionFactory.getAction(uri);
        if(action != null){
            action.execute(req, resp);
        }else{
            req.setAttribute(ERROR_CODE, 404);
            Action errorAction = ActionFactory.getAction(ERROR_PAGE_PATH);
            errorAction.execute(req, resp);
        }
    }
}
