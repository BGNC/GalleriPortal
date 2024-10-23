package com.bgnc.galleriportal.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String getCurrentDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(date);
    }
}
