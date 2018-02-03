package com.admin.luntan.util;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * 时间工具类
 * Created by zhanghaichao on 2018/2/3.
 */
public class TimeUtil {

    /**
     * 获取当前的年月日
     *
     * @return
     */
    public static String[] getYearMonthDay() {
        String[] date = new String[3];
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));    //获取东八区时间
        //获取年
        int year = c.get(Calendar.YEAR);
        date[0] = year + "";
        //获取月份，0表示1月份
        int month = c.get(Calendar.MONTH) + 1;
        date[1] = month + "";
        //获取当前天数
        int day = c.get(Calendar.DAY_OF_MONTH);
        date[2] = day + "";

        return date;
    }
}
