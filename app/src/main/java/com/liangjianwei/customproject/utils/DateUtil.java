package com.liangjianwei.customproject.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtil {


    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 字符串的日期格式的计算
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 根据时间戳获取年月日
     *
     * @param time 2015-12-15
     * @return
     */
    public static String getYMD(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(time);
        String dateStr = formatter.format(curDate);
        return dateStr;
    }

    /**
     * 根据时间戳获取年月日时分秒
     *
     * @param time 2015-12-15 22:22:22
     * @return
     */
    public static String getYMDHMS(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(time);
        String dateStr = formatter.format(curDate);
        return dateStr;
    }

    /**
     * 根据时间戳获取时分秒
     *
     * @param time 22:22:22
     * @return
     */
    public static String getHMS(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date curDate = new Date(time);
        String dateStr = formatter.format(curDate);
        return dateStr;
    }

    /**
     * 根据时间戳获取月份日期
     *
     * @param time 12-15
     * @return
     */
    public static String getMD(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
        Date curDate = new Date(time);
        String dateStr = formatter.format(curDate);
        return dateStr;
    }

    /**
     * 根据年月日时分秒转换成时间戳
     *
     * @param time 1465213650
     * @return
     */
    public static long getTime(String time) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(time);
            long dateTime = date.getTime();
            return dateTime;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 将年月日转换成时间戳
     * @param dateStr  2015-12-15
     */
    public static Date getDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
