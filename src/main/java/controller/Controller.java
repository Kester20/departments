package controller;

import action.Action;
import action.ActionFactory;
import exception.NotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Constants.Messages.EXCEPTION;
import static util.Constants.Messages.NOT_FOUND;
import static util.Constants.Pathways.ERROR_PAGE_PATH;

/**
 * @author Arsalan
 */
@WebServlet("/")
public class Controller extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        Action action = ActionFactory.getAction(uri);
        if (action != null) {
            action.execute(req, resp);
        } else {
            try {
                throw new NotFoundException(NOT_FOUND);
            } catch (NotFoundException e) {
                req.setAttribute(EXCEPTION, e);
                Action errorAction = ActionFactory.getAction(ERROR_PAGE_PATH);
                errorAction.execute(req, resp);
            }
        }
    }
}
