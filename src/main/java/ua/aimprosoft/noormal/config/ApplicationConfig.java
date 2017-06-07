package ua.aimprosoft.noormal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.aimprosoft.noormal.dao.impl.DepartmentDao;
import ua.aimprosoft.noormal.dao.impl.EmployeeDao;
import ua.aimprosoft.noormal.model.Department;
import ua.aimprosoft.noormal.model.Employee;

/**
 * @author Arsalan
 */
@Configuration
@ComponentScan("ua.aimprosoft.noormal")
public class ApplicationConfig {

    @Bean
    public DepartmentDao departmentDao() {
        return new DepartmentDao(Department.class);
    }

    @Bean
    public EmployeeDao employeeDao() {
        return new EmployeeDao(Employee.class);
    }

}
