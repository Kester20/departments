package com.aimprosoft.noormal.util;

import com.aimprosoft.noormal.exception.ValidationException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONSerializer;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author Arsalan
 */
public class ExceptionHandler {

    public static void handle(Exception exception, ResourceRequest request, ResourceResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
        String json;

        if (exception instanceof ValidationException) {
            ValidationException validationException = (ValidationException) exception;
            response.setProperty(ResourceResponse.HTTP_STATUS_CODE, Integer.toString(HttpServletResponse.SC_BAD_REQUEST));
            Map<String, String> errors = validationException.getErrorMap();
            json = jsonSerializer.serialize(errors);
        } else {
            response.setProperty(ResourceResponse.HTTP_STATUS_CODE, Integer.toString(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));
            String message = "INTERNAL SERVER ERROR!";
            json = jsonSerializer.serialize(message);
        }

        writer.write(json);
    }
}
