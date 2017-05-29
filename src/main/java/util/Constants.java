package util;

import java.time.LocalDate;

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
        public static final String SHOW_DEPARTMENT_EMPLOYEES = "SELECT employee.id, employee.name, employee.age, employee.date_of_birth from employee" +
                " INNER JOIN department_employee ON employee.id = department_employee.employee_id " +
                "WHERE department_employee.department_id = ?";

        public static final String UPDATE_EMPLOYEE = "UPDATE `employee` SET `name` = ? , `age` = ?, " +
                "`date_of_birth` = ? WHERE `employee`.`id` = ? ";
        public static final String CREATE_EMPLOYEE = "INSERT INTO `employee` (`id`, `name`, `age`, `date_of_birth`) " +
                "VALUES (NULL, ?, ?, ?)";
        public static final String DELETE_EMPLOYEE = "DELETE FROM `employee` WHERE `employee`.`id` = ? ";
    }

    public static class ContextConstants {
        public static final String DEPARTMENT_SERVICE = "departmentService";
        public static final String EMPLOYEE_SERVICE = "employeeService";
    }
}
