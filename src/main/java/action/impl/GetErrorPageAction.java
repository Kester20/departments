package action.impl;

import action.Action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static util.Constants.Messages.ERROR_CODE;
import static util.Constants.Messages.ERROR_MESSAGE;
import static util.Constants.Messages.NOT_FOUND;
import static util.Constants.Messages.SERVER_ERROR;
import static util.Constants.Pathways.ERROR_PAGE_PATH;

/**
 * @author Arsalan
 */
public class GetErrorPageAction implements Action {

    private static Map<Integer, String> errors = new HashMap<Integer, String>(){{
        put(404, NOT_FOUND);
        put(500, SERVER_ERROR);
    }};

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer errorCode = (Integer) request.getAttribute(ERROR_CODE);
        String errorMessage = errors.get(errorCode);
        request.setAttribute(ERROR_CODE, errorCode);
        request.setAttribute(ERROR_MESSAGE, errorMessage);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF" + ERROR_PAGE_PATH + ".jsp");
        requestDispatcher.forward(request, response);
    }
}
