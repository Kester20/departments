package ua.aimprosoft.noormal.controller;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import ua.aimprosoft.noormal.action.Action;
import ua.aimprosoft.noormal.action.ActionFactory;
import ua.aimprosoft.noormal.exception.DaoException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.aimprosoft.noormal.util.Constants.Pathways.ROOT_PATH;

/**
 * @author Arsalan
 */
@Component("controller")
public class Controller implements HttpRequestHandler {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        Action action;
        try{
            action = (Action) applicationContext.getBean(uri);
        }catch (NoSuchBeanDefinitionException e){
            action = (Action) applicationContext.getBean(ROOT_PATH);
        }
        try {
            action.execute(request, response);
        } catch (DaoException e) {
            ErrorPageResponder.proceed(request, response, e);
        }
    }
}
