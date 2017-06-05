package action;

import exception.ValidationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static util.Constants.ServiceConstants.ERROR_MAP;

/**
 * @author Arsalan. Created on 04.06.2017.
 */
public class ValidationErrorResponder {

    public static void sendError(HttpServletRequest request, HttpServletResponse response, String path, Exception exception) throws ServletException, IOException {
        ValidationException validationException = (ValidationException) exception;
        Map<String, String> errors = validationException.getErrorMap();
        request.setAttribute(ERROR_MAP, errors);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }
}
