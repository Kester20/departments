package com.aimprosoft.noormal.util;

import java.time.LocalDate;

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

    public static LocalDate getDateFromString(String param) {
        return param == null || param.equals("") ? null : LocalDate.parse(param);
    }
}
