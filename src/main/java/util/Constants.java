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
        public static final String UPDATE_DEPARTMENT = "UPDATE `department` SET `name` = ? WHERE `department`.`id` = ? ";
        public static final String DELETE_DEPARTMENT = "DELETE FROM `department` WHERE `department`.`id` = ? ";
        public static final String GET_DEPARTMENT_EMPLOYEES = "SELECT * FROM employee WHERE employee.department = ?";
        public static final String GET_DEPARTMENTS = "SELECT * FROM `department`";
        public static final String FIND_DEPARTMENT = "SELECT * FROM `department` WHERE `department`.`id` = ?";

        public static final String UPDATE_EMPLOYEE = "UPDATE `employee` SET `name` = ? , `age` = ?, " +
                "`date_of_birth` = ? WHERE `employee`.`id` = ? ";
        public static final String CREATE_EMPLOYEE = "INSERT INTO `employee` (`id`, `name`, `age`, `date_of_birth`, `department`) " +
                "VALUES (NULL, ?, ?, ?, ?)";
        public static final String DELETE_EMPLOYEE = "DELETE FROM `employee` WHERE `employee`.`id` = ? ";
    }

    public static class ContextConstants {
        public static final String DEPARTMENT_SERVICE = "departmentService";
        public static final String EMPLOYEE_SERVICE = "employeeService";
    }

    public static class ServiceConstants {
        public static final String ACTION = "action";
        public static final String DEPARTMENT_ID = "departmentId";
        public static final String EMPLOYEE_ID = "employeeId";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String AGE = "age";
        public static final String DATE_OF_BIRTH = "dateOfBirth";
        public static final String GET_PAGE = "/getPage";
        public static final String PAGE = "page";
    }

    public static class PATHWAYS {
        public static final String GET_ALL_DEPARTMENTS_PATH = "/getDepartments";
        public static final String CREATE_DEPARTMENT_PATH = "/createDepartment";
        public static final String DELETE_DEPARTMENT_PATH = "/deleteDepartment";
        public static final String EDIT_DEPARTMENT_PATH = "/editDepartment";

        public static final String GET_ALL_EMPLOYEE_PATH = "/getEmployees";
        public static final String CREATE_EMPLOYEE_PATH = "/createEmployee";
        public static final String EDIT_EMPLOYEE_PATH = "/editEmployee";
        public static final String DELETE_EMPLOYEE_PATH = "/deleteEmployee";

        public static final String DEPARTMENTS = "departments";
        public static final String EMPLOYEES = "employees";
    }
}
