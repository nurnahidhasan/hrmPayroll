package com.bracu.hrm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by HP on 1/17/2018.
 */
public class DateUtil {
    public static Date dynamicFormatter(String format, String date) throws ParseException {

        System.out.print(format + " " + date);
        Date d1 = new SimpleDateFormat(format).parse(date);
        SimpleDateFormat dateformat = new SimpleDateFormat(format);
        String formatter = dateformat.format(d1);
        Date d2 = new SimpleDateFormat(format).parse(formatter);
        return d2;
    }

}
