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

import static com.aimprosoft.noormal.util.Constants.Messages.ERROR_MESSAGE;
import static com.aimprosoft.noormal.util.Constants.Pathways.ERROR_PAGE_PATH;
import static com.aimprosoft.noormal.util.Constants.Pathways.SAVE_DEPARTMENT_PATH;
import static com.aimprosoft.noormal.util.Constants.Pathways.SAVE_EMPLOYEE_PATH;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.ERROR_MAP;

/**
 * @author Arsalan
 */
@ControllerAdvice
public class ExceptionHandlingController {

    private Map<String, String> paths = new HashMap<String, String>() {{
        put("/department/save", SAVE_DEPARTMENT_PATH);
        put("/employee/save", SAVE_EMPLOYEE_PATH);
    }};

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(Exception exception) {
        String errorMessage = exception.getMessage();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(ERROR_MESSAGE, errorMessage);
        modelAndView.setViewName(ERROR_PAGE_PATH);
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ModelAndView handleError(HttpServletRequest request, ValidationException exception) {
        String uri = request.getRequestURI();
        ModelAndView modelAndView = new ModelAndView();
        Map<String, String> errors = exception.getErrorMap();
        modelAndView.addObject(ERROR_MAP, errors);
        modelAndView.setViewName(paths.get(uri));
        return modelAndView;
    }
}
