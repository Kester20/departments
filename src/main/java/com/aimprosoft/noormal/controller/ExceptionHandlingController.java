package com.aimprosoft.noormal.controller;

import com.aimprosoft.noormal.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.aimprosoft.noormal.util.Constants.Messages.ERROR_CODE;
import static com.aimprosoft.noormal.util.Constants.Messages.ERROR_MESSAGE;
import static com.aimprosoft.noormal.util.Constants.Messages.NOT_FOUND;
import static com.aimprosoft.noormal.util.Constants.Messages.SERVER_ERROR;
import static com.aimprosoft.noormal.util.Constants.Pathways.ERROR_PAGE_PATH;
import static com.aimprosoft.noormal.util.Constants.Pathways.SAVE_DEPARTMENT_PATH;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.ERROR_MAP;

/**
 * @author Arsalan
 */
@ControllerAdvice
public class ExceptionHandlingController {

    private Map<String, Integer> errors = new HashMap<String, Integer>() {{
        put(NOT_FOUND, 404);
        put(SERVER_ERROR, 500);
    }};

    @ExceptionHandler(Exception.class)
    public ModelAndView sendError(Exception exception) {
        String errorMessage = exception.getMessage();
        Integer errorCode = errors.get(errorMessage);
        if (errorCode == null) {
            errorCode = 500;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(ERROR_CODE, errorCode);
        modelAndView.addObject(ERROR_MESSAGE, errorMessage);
        modelAndView.setViewName(ERROR_PAGE_PATH);
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ModelAndView handleError(HttpServletRequest request, ValidationException exception) {
        ModelAndView modelAndView = new ModelAndView();
        Map<String, String> errors = exception.getErrorMap();
        modelAndView.addObject(ERROR_MAP, errors);
        modelAndView.setViewName(SAVE_DEPARTMENT_PATH);
        return modelAndView;
    }
}
