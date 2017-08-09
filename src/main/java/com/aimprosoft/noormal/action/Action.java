package com.aimprosoft.noormal.action;

import com.aimprosoft.noormal.exception.ValidationException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.text.ParseException;

/**
 * @author Arsalan
 */
public interface Action {

    void execute(ResourceRequest request, ResourceResponse response) throws IOException, ValidationException, ParseException, SystemException, PortalException;
}
