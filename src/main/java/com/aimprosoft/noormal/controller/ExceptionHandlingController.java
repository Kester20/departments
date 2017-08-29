package com.aimprosoft.noormal.controller;

import com.aimprosoft.noormal.exception.ValidationException;
import com.aimprosoft.noormal.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.aimprosoft.noormal.util.Constants.Messages.ERROR_MESSAGE;
import static com.aimprosoft.noormal.util.Constants.Pathways.ERROR_PAGE_PATH;
import static com.aimprosoft.noormal.util.Constants.Pathways.SAVE_DEPARTMENT_PATH;
import static com.aimprosoft.noormal.util.Constants.Pathways.SAVE_EMPLOYEE_PATH;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.EMPLOYEE_FORM;
import static com.aimprosoft.noormal.util.Constants.ServiceConstants.ERROR_MAP;

/**
 * @author Arsalan
 */
@ControllerAdvice
public class ExceptionHandlingController {

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleError(Exception exception) {
        return exception.getMessage();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public Map<String, String> handleError(ValidationException exception) {
        Map<String, String> errors = exception.getErrorMap();
        return errors;
    }
}