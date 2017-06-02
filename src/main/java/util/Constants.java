package util;

/**
 * @author Arsalan
 */
public class Constants {

    public static class DbConstants {
        public static final String DATA_SOURCE_LOOKUP = "java:comp/env/jdbc/departments";
    }

    public static class QueryConstants {
        public static final String CREATE_DEPARTMENT = "INSERT INTO `department` (`id`, `name`) VALUES (NULL, ?)";
        public static final String EDIT_DEPARTMENT = "UPDATE `department` SET `name` = ? WHERE `department`.`id` = ? ";
        public static final String DELETE_DEPARTMENT = "DELETE FROM `department` WHERE `department`.`id` = ? ";
        public static final String GET_DEPARTMENT_EMPLOYEES = "SELECT * FROM employee WHERE employee.department = ?";
        public static final String GET_DEPARTMENTS = "SELECT * FROM `department`";
        public static final String FIND_DEPARTMENT = "SELECT * FROM `department` WHERE `department`.`id` = ?";
        public static final String FIND_DEPARTMENT_BY_NAME = "SELECT * FROM `department` WHERE `department`.`name` = ?";

        public static final String UPDATE_EMPLOYEE = "UPDATE `employee` SET `name` = ? , `age` = ?, " +
                "`date_of_birth` = ?, `email` = ? WHERE `employee`.`id` = ? ";
        public static final String CREATE_EMPLOYEE = "INSERT INTO `employee` (`id`, `name`, `age`, `date_of_birth`, `email`, `department`) " +
                "VALUES (NULL, ?, ?, ?, ?, ?)";
        public static final String DELETE_EMPLOYEE = "DELETE FROM `employee` WHERE `employee`.`id` = ? ";
        public static final String FIND_EMPLOYEE_BY_EMAIL = "SELECT * FROM `employee` WHERE `employee`.`email` = ?";
    }

    public static class ContextConstants {
        public static final String DEPARTMENT_SERVICE = "departmentService";
        public static final String EMPLOYEE_SERVICE = "employeeService";
    }

    public static class ServiceConstants {
        public static final String DEPARTMENT_ID = "departmentId";
        public static final String EMPLOYEE_ID = "employeeId";
        public static final String EMAIL = "email";
        public static final String NAME = "name";
        public static final String AGE = "age";
        public static final String DATE_OF_BIRTH = "dateOfBirth";
        public static final String ERROR_INPUT = "ErrorInput";
        public static final String DEPARTMENTS = "departments";
        public static final String EMPLOYEES = "employees";
    }

    public static class Pathways {
        public static final String ROOT_PATH = "/";
        public static final String DEPARTMENT_ACTION_PATH = "/departmentAction";
        public static final String CREATE_DEPARTMENT_PATH = "/createDepartment";
        public static final String DELETE_DEPARTMENT_PATH = "/deleteDepartment";
        public static final String EDIT_DEPARTMENT_PATH = "/editDepartment";

        public static final String GET_ALL_EMPLOYEE_PATH = "/getEmployees";
        public static final String EMPLOYEE_ACTION_PATH = "/employeeAction";
        public static final String CREATE_EMPLOYEE_PATH = "/createEmployee";
        public static final String EDIT_EMPLOYEE_PATH = "/editEmployee";
        public static final String DELETE_EMPLOYEE_PATH = "/deleteEmployee";

        public static final String DEPARTMENTS_PATH = "/departments";
        public static final String EMPLOYEES_PATH = "/employees";

        public static final String ERROR_PAGE_PATH = "/errorPage";
    }

    public static class Messages {
        public static final String DEPARTMENT_WITH_THIS_NAME_IS_ALREADY_EXIST = "Department with this name is already exist";
        public static final String EMPLOYEE_WITH_THIS_EMAIL_IS_ALREADY_EXIST = "Employee with this email is already exist";
        public static final String CAN_NOT_CREATE_DEPARTMENT = "Can't create department";
        public static final String CAN_NOT_EDIT_DEPARTMENT = "Can't edit department";
        public static final String CAN_NOT_DELETE_DEPARTMENT = "Can't delete department";
        public static final String CAN_NOT_GET_DEPARTMENTS = "Can't get departments";
        public static final String CAN_NOT_FIND_DEPARTMENT = "Can't find department";
        public static final String CAN_NOT_CREATE_EMPLOYEE = "Can't create employee";
        public static final String CAN_NOT_EDIT_EMPLOYEE = "Can't edit employee";
        public static final String CAN_NOT_DELETE_EMPLOYEE = "Can't delete employee";
        public static final String CAN_NOT_GET_EMPLOYEES = "Can't get employees";
        public static final String CAN_NOT_FIND_EMPLOYEE = "Can't find employee";
        public static final String MUST_NOT_BE_EMPTY = "Must not be empty";
        public static final String NOT_FOUND = "Not found";
        public static final String SERVER_ERROR = "Server error";
        public static final String ERROR_CODE = "errorCode";
        public static final String ERROR_MESSAGE = "errorMessage";
        public static final String EXCEPTION = "exception";
    }
}
