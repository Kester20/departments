package ua.aimprosoft.noormal.validator;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.FieldContext;
import net.sf.oval.context.OValContext;
import ua.aimprosoft.noormal.exception.ValidationException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Arsalan
 */
public class CustomValidator {

    private static Validator validator = new Validator();

    private CustomValidator() {
    }

    public static void validate(Object object) throws ValidationException {
        List<ConstraintViolation> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            Map<String, String> errorMap = new HashMap<>();
            for (ConstraintViolation violation : violations) {
                String errorMessage = violation.getMessage();
                OValContext context = violation.getContext();
                if (context instanceof FieldContext) {
                    FieldContext fieldContext = (FieldContext) context;
                    Field field = fieldContext.getField();
                    String fieldName = field.getName();
                    errorMap.put(fieldName, errorMessage);
                }
            }
            if (!errorMap.isEmpty()) {
                throw new ValidationException(errorMap);
            }
        }
    }


}
