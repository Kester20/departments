package com.aimprosoft.noormal.action;

import com.aimprosoft.noormal.exception.DaoException;
import com.aimprosoft.noormal.exception.ValidationException;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.text.ParseException;

/**
 * @author Arsalan
 */
public interface Action {

    void execute(ResourceRequest request, ResourceResponse response) throws DaoException, IOException, ValidationException, ParseException;
}
