package exception;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Arsalan
 */
public class ValidationException extends Exception {

    private Map<String, String> errorMap = new HashMap<>();

    public ValidationException(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }
}
