package com.aimprosoft.noormal.controller;

import com.aimprosoft.noormal.action.Action;
import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.exception.ValidationException;
import com.aimprosoft.noormal.util.ExceptionHandler;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.text.ParseException;

import static com.aimprosoft.noormal.util.Constants.Actions.GET_ALL_DEPARTMENTS;
import static com.aimprosoft.noormal.util.Constants.Pathways.INDEX_JSP;

/**
 * @author Arsalan
 */
@Component
public class Portlet extends GenericPortlet {

    private String indexJSP;
    private ApplicationContext applicationContext;

    @Override
    public void init() throws PortletException {
        indexJSP = getInitParameter(INDEX_JSP);
        if (applicationContext == null) {
            applicationContext = PortletApplicationContextUtils.getWebApplicationContext(getPortletContext());
        }
    }

    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        PortletRequestDispatcher portletRequestDispatcher = getPortletContext().getRequestDispatcher(indexJSP);
        portletRequestDispatcher.include(request, response);
    }

    @Override
    public void serveResource(ResourceRequest request, ResourceResponse response) throws PortletException, IOException {
        String resourceId = request.getResourceID();
        Action action;
        try {
            action = (Action) applicationContext.getBean(resourceId);
        } catch (NoSuchBeanDefinitionException e) {
            action = (Action) applicationContext.getBean(GET_ALL_DEPARTMENTS);
        }
        try {
            action.execute(request, response);
        } catch (DaoException | ParseException | ValidationException e) {
            ExceptionHandler.handle(e, request, response);
        }
    }
}
