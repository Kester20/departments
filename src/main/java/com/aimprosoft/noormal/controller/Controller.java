package com.aimprosoft.noormal.controller;

import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.util.Constants;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import com.aimprosoft.noormal.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
            action = (Action) applicationContext.getBean(Constants.Pathways.ROOT_PATH);
        }
        try {
            action.execute(request, response);
        } catch (DaoException e) {
            ErrorPageResponder.proceed(request, response, e);
        }
    }
}
