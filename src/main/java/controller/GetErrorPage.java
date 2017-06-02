package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static util.Constants.Messages.ERROR_CODE;
import static util.Constants.Messages.ERROR_MESSAGE;
import static util.Constants.Messages.EXCEPTION;
import static util.Constants.Messages.NOT_FOUND;
import static util.Constants.Messages.SERVER_ERROR;
import static util.Constants.Pathways.ERROR_PAGE_PATH;

/**
 * @author Arsalan
 */
public class GetErrorPage {

    private static Map<String, Integer> errors = new HashMap<String, Integer>() {{
        put(NOT_FOUND, 404);
        put(SERVER_ERROR, 500);
    }};

    public static void proceed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Exception exception = (Exception) request.getAttribute(EXCEPTION);
        String errorMessage = exception.getMessage();
        Integer errorCode = errors.get(errorMessage);
        if (errorCode == null) {
            errorCode = 500;
        }
        request.setAttribute(ERROR_CODE, errorCode);
        request.setAttribute(ERROR_MESSAGE, errorMessage);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF" + ERROR_PAGE_PATH + ".jsp");
        requestDispatcher.forward(request, response);
    }
}
