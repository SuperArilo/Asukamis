package com.superarilo.utils;

public class TimeFormat {
    public static String timeCheck(long startTime, long endTime) {
        long t = endTime - startTime;
        //获取天数时间差
        int days = (int) (t / (1000 * 60 * 60 * 24));
        //获取小时时间差
        int HH = (int) (t / (1000 * 60 * 60));
        //获取分钟时间差
        int mm = (int) (t / (1000 * 60));
        //获取秒时间差
        int ss = (int) (t / (1000));
        String beApart;
        if (days <= 1) {
            if (ss <= 60) {
                beApart = "刚刚";
            } else if (ss <= 60 * 60) {
                beApart = mm + "分钟前";
            } else if (ss <= 60 * 60 * 24) {
                beApart = HH + "小时前";
            } else {
                beApart = days + "天前";
            }
        } else if (days < 30) {
            beApart = days + "天前";
        } else if (days < 365) {
            int days1 = days / 30;
            beApart = days1 + "月前";
        } else {
            int days2 = days / 365;
            beApart = days2 + "年前";
        }
        return beApart;
    }
}
