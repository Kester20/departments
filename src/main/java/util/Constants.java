package util;

/**
 * @author Arsalan
 */
public class Constants {

    public static class DbConstants {
        public static final String DATA_SOURCE_LOOKUP = "java:comp/env/jdbc/departments";
    }

    public static class QueryConstants{
        public static final String CREATE_DEPARTMENT = "INSERT INTO `department` (`id`, `name`) VALUES (NULL, ?)";
        public static final String UPDATE_DEPARTMENT = "UPDATE `department` SET `name` = ? WHERE `department`.`id` = ? ";
        public static final String DELETE_DEPARTMENT = "DELETE FROM `department` WHERE `department`.`id` = ? ";
        public static final String SHOW_DEPARTMENT_EMPLOYEES = "SELECT employee.id, employee.name, employee.age, employee.date_of_birth from employee" +
                " INNER JOIN department_employee ON employee.id = department_employee.employee_id " +
                "WHERE department_employee.department_id = ?";
    }
}
