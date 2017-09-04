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
        public static final String DATE_FORMAT = "yyyy-MM-dd";
    }

    public static class Messages {
        public static final String DEPARTMENT_WITH_THIS_NAME_IS_ALREADY_EXIST = "Department with this name is already exist";
        public static final String EMPLOYEE_WITH_THIS_EMAIL_IS_ALREADY_EXIST = "Employee with this email is already exist";
        public static final String CAN_NOT_SAVE_ENTITY = "Can't save entity";
        public static final String CAN_NOT_DELETE_ENTITY = "Can't delete entity";
        public static final String CAN_NOT_FIND_ENTITIES = "Can't find entities";
        public static final String CAN_NOT_FIND_ENTITY = "Can't find entity";
        public static final String MUST_NOT_BE_EMPTY = "Must not be empty";
        public static final String MUST_BE_LESS_THEN_30 = "Must be less then 30 characters";
        public static final String CAN_NOT_COUNT_ENTITIES = "Can't count entities";
    }
}
