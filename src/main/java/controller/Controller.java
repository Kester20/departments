package controller;

import action.Action;
import action.ActionFactory;
import exception.DaoException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.Constants.Messages.EXCEPTION;

/**
 * @author Arsalan
 */
@WebServlet("/")
public class Controller extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        Action action = ActionFactory.getAction(uri);
        if (action == null) {
            action = ActionFactory.getDefaultAction();
        }
        try {
            action.execute(request, response);
        } catch (DaoException e) {
            request.setAttribute(EXCEPTION, e);
            GetErrorPage.proceed(request, response);
        }
    }
}
