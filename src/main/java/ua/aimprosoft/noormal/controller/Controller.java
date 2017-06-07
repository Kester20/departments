package ua.aimprosoft.noormal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.aimprosoft.noormal.action.Action;
import ua.aimprosoft.noormal.action.ActionFactory;
import ua.aimprosoft.noormal.exception.DaoException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Arsalan
 */
@WebServlet("/")
public class Controller extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionFactory actionFactory = (ActionFactory) request.getServletContext().getAttribute("a");
        String uri = request.getRequestURI();
        Action action = actionFactory.getAction(uri);
        if (action == null) {
            action = actionFactory.getDefaultAction();
        }
        try {
            action.execute(request, response);
        } catch (DaoException e) {
            ErrorPageResponder.proceed(request, response, e);
        }
    }
}
