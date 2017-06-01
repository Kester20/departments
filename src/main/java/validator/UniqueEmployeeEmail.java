package validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Arsalan
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@net.sf.oval.configuration.annotation.Constraint(checkWith = EmployeeValidator.class)
public @interface UniqueEmployeeEmail {
}
