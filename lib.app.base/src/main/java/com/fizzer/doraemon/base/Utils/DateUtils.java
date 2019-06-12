package com.fizzer.doraemon.base.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Fizzer on 2019/5/24.
 * Email: fizzer503@gmail.com
 * Function: 日期格式化工具类
 */

public class DateUtils {
    public interface dateFormateType {
        String sdf1 = "yyyy:MM:dd HH:mm:ss";
        String sdf2 = "yyyy:MM:dd";
        String sdf3 = "HH:mm:ss";
    }

    /**
     * 格式化时间戳
     */
    public static String getFormatData(long time, String type) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        SimpleDateFormat format = new SimpleDateFormat(type);
        return format.format(cal.getTime());
    }


}
