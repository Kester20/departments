package util;

/**
 * @author Arsalan
 */
public class FormatUtils {

    public static Integer getIntFromString(String param) {
        return param == null || param.equals("") ? null : Integer.parseInt(param);
    }
}
