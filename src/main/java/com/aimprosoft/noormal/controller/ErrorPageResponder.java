package com.aimprosoft.noormal.controller;

import com.aimprosoft.noormal.util.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Arsalan
 */
public class ErrorPageResponder {

    private static Map<String, Integer> errors = new HashMap<String, Integer>() {{
        put(Constants.Messages.NOT_FOUND, 404);
        put(Constants.Messages.SERVER_ERROR, 500);
    }};

    public static void proceed(HttpServletRequest request, HttpServletResponse response, Exception exception)
            throws ServletException, IOException {
        String errorMessage = exception.getMessage();
        Integer errorCode = errors.get(errorMessage);
        if (errorCode == null) {
            errorCode = 500;
        }
        request.setAttribute(Constants.Messages.ERROR_CODE, errorCode);
        request.setAttribute(Constants.Messages.ERROR_MESSAGE, errorMessage);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF" + Constants.Pathways.ERROR_PAGE_PATH + ".jsp");
        requestDispatcher.forward(request, response);
    }
}
