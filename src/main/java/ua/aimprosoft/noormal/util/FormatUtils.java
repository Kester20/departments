package ua.aimprosoft.noormal.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static ua.aimprosoft.noormal.util.Constants.ServiceConstants.DATE_FORMAT;

/**
 * @author Arsalan
 */
public class FormatUtils {

    public static Integer getIntFromString(String param) {
        return param == null || param.equals("") ? null : Integer.parseInt(param);
    }

    public static Long getLongFromString(String param) {
        return param == null || param.equals("") ? null : Long.parseLong(param);
    }

    public static Date getDateFromString(String param) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        try {
            return param == null || param.equals("") ? null : formatter.parse(param);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
