package com.aimprosoft.noormal.util;

/**
 * @author Arsalan
 */
public class Constants {

    public static class DbConstants {
        public static final String DEPARTMENT = "department";
        public static final String EMPLOYEE = "employee";
    }

    public static class ServiceConstants {
        public static final String DEPARTMENT_ID = "departmentId";
        public static final String EMPLOYEE_ID = "employeeId";
        public static final String EMAIL = "email";
        public static final String NAME = "name";
        public static final String AGE = "age";
        public static final String DATE_OF_BIRTH = "dateOfBirth";
        public static final String DEPARTMENTS = "departments";
        public static final String EMPLOYEES = "employees";
        public static final String ERROR_MAP = "errorMap";
        public static final String DATE_FORMAT = "yyyy-MM-dd";
        public static final String EMPLOYEE_FORM = "employeeForm";
    }

    public static class Pathways {
        public static final String SAVE_DEPARTMENT_PATH = "/saveDepartment";
        public static final String GET_EMPLOYEES_BY_DEPARTMENT = "/employee/getByDepartment";
        public static final String SAVE_EMPLOYEE_PATH = "/saveEmployee";
        public static final String DEPARTMENTS_PATH = "/departments";
        public static final String EMPLOYEES_PATH = "/employees";
        public static final String ERROR_PAGE_PATH = "/errorPage";
        public static final String INDEX_JSP = "index-jsp";
    }

    public static class Actions {
        public static final String GET_ALL_DEPARTMENTS = "getAllDepartments";
        public static final String DELETE_DEPARTMENT = "deleteDepartment";
        public static final String SAVE_DEPARTMENT = "saveDepartment";
        public static final String GET_ALL_EMPLOYEES = "getAllEmployees";
        public static final String DELETE_EMPLOYEE = "deleteEmployee";
        public static final String SAVE_EMPLOYEE = "saveEmployee";
    }

    public static class Messages {
        public static final String DEPARTMENT_WITH_THIS_NAME_IS_ALREADY_EXIST = "Department with this name is already exist";
        public static final String EMPLOYEE_WITH_THIS_EMAIL_IS_ALREADY_EXIST = "Employee with this email is already exist";
        public static final String CAN_NOT_SAVE_ENTITY = "Can't save entity";
        public static final String CAN_NOT_DELETE_ENTITY = "Can't delete entity";
        public static final String CAN_NOT_FIND_ENTITIES = "Can't find entities";
        public static final String CAN_NOT_FIND_ENTITY = "Can't find entity";
        public static final String MUST_NOT_BE_EMPTY = "Must not be empty";
        public static final String ERROR_MESSAGE = "errorMessage";
        public static final String MUST_BE_LESS_THEN_30 = "Must be less then 30 characters";
    }
}
