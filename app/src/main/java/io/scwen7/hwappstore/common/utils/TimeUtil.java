package io.scwen7.hwappstore.common.utils;

import java.util.Calendar;

/**
 * Created by 解晓辉 on 2017/6/14.
 * 作用：
 */

public class TimeUtil {

    public static boolean isBeyondTime() {
        Calendar calender = Calendar.getInstance();
        calender.set(2017, 5, 22, 0, 0);
        long calenderTimeInMillis = calender.getTimeInMillis();
        long systemTime = System.currentTimeMillis();
        return systemTime >= calenderTimeInMillis;
    }
}
