package com.superarilo.utils;

import java.util.Calendar;

public class DateUtil {

    /**
     * 年份（后两位）
     */
    public static final String YY_PATTERN = "yy";

    /**
     * 年份（四位）
     */
    public static final String YYYY_PATTERN = "yyyy";

    /**
     * 年月(以 短横线 ‘-’ 分割)
     */
    public static final String YYYY_MM_PATTERN = "yyyy-MM";

    /**
     * 年月(以 斜线 ‘/’ 分割)
     */
    public static final String YYYY_MM_BIAS_PATTERN = "yyyy/MM";
    /**
     * 年月日(以 短横线 ‘-’ 分割)
     */
    public static final String YYYY_MM_DD_PATTERN = "yyyy-MM-dd";

    /**
     * 年月日(以 斜线 ‘/’ 分割)
     */
    public static final String YYYY_MM_DD_BIAS_PATTERN = "yyyy/MM/dd";

    /**
     * 年月日时分秒(以 短横线 ‘-’ 分割)
     */
    public static final String YYYY_MM_DD_HH_MM_SS_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 年月日时分秒(以 斜线 ‘/’ 分割)
     */
    public static final String YYYY_MM_DD_HH_MM_SS_BIAS_PATTERN = "yyyy/MM/dd HH:mm:ss";


    /**
     * 一天二十四小时的秒数
     * @return 返回秒数
     */
    public static long theNumberOfSecondsInADay() {
        return 24 * 60 * 60L;
    }


    /**
     * 计算当天剩余秒数 如果当天时间小于两小时，则在追加一天时间
     * @return 返回秒数
     */
    public static long theRestOfTheDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        long endTime = calendar.getTime().getTime();
        long currentTime = System.currentTimeMillis();
        long time = endTime - currentTime;
        return time / 1000 > 2 * 60 * 60 ? time : time + theNumberOfSecondsInADay();
    }
}
