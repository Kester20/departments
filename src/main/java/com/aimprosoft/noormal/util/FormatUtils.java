package com.aimprosoft.noormal.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Arsalan
 */
public class FormatUtils {

    public static int getIntFromString(String param) {
        return param == null || param.equals("") ? 0 : Integer.parseInt(param);
    }

    public static long getLongFromString(String param) {
        return param == null || param.equals("") ? 0 : Long.parseLong(param);
    }

    public static Date getDateFromString(String param) throws ParseException {
        return param == null || param.equals("") ? null : new SimpleDateFormat("yyyy-MM-dd").parse(param);
    }
}
