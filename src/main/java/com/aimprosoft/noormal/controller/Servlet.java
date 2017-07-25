package com.aimprosoft.noormal.controller;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Arsalan
 */
public class Servlet extends GenericPortlet {

    @Override
    public void processAction(ActionRequest request, ActionResponse response) throws PortletException, IOException {
        super.processAction(request, response);
    }

    @Override
    protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        String salutation ="kkokokokioki";
        response.setContentType(request.getResponseContentType());
        PrintWriter writer = response.getWriter();
        writer.write("Hellooo " + salutation);
    }
}
