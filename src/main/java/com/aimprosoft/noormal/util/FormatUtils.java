package com.aimprosoft.noormal.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static Date getDateFromString(String param) throws ParseException {
        return param == null || param.equals("") ? null : new SimpleDateFormat("yyyy-MM-dd").parse(param);
    }
}
