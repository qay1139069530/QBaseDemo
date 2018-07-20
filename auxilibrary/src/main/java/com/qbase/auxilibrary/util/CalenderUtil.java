package com.qbase.auxilibrary.util;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 此工具类主要处理 时间，日期格式化输出，还有关于时间判断、应用的一些方法。 使用详情可以参考 例子 main()
 */
public class CalenderUtil {

    /**
     * 获取年月日时间
     *
     * @param time
     * @return 2015/1/12
     */
    public static String getYearMonthDay(int time) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        Calendar calendar = Calendar.getInstance();
        long timeLong = time;
        calendar.setTimeInMillis(timeLong * 1000);
        int year = calendar.get(Calendar.YEAR); // 年
        int month = calendar.get(Calendar.MONTH) + 1; // 月
        int day = calendar.get(Calendar.DAY_OF_MONTH); // 日
        return year + "/" + month + "/" + day;
    }

    /**
     * 获取给定时间的秒数
     *
     * @param year，month，day
     * @return 2015/1/12
     */
    public static int getCurrentTime(int year, int month, int day) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        long time = calendar.getTimeInMillis() / 1000;
        return Integer.parseInt(String.valueOf(time)) - 1;
    }

    /**
     * 获取当前时间的时分秒
     *
     * @return
     */
    public static long getCurrentHms() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        return hour * 60 * 60 + minute * 60 + second;
    }


    /**
     * 获取结束时间，时分秒以当天23:59：59为准
     *
     * @param year，month，day
     * @return 2015/1/12
     */
    public static int getEndTime(int year, int month, int day) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day + 1, 0, 0, 0);
        long time = calendar.getTimeInMillis() / 1000;
        return Integer.parseInt(String.valueOf(time)) - 1;
    }

    /**
     * 获取开始时间，时分秒按照当天00:00:00
     *
     * @param year，month，day
     * @return 2015/1/12
     */
//    public static int getStartTime(int year, int month, int day) {
//        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(year, month, day, 0, 0, 0);
//        long time = calendar.getTimeInMillis() / 1000;
//        return Integer.parseInt(String.valueOf(time));
//    }


    /**
     * 获取开始时间，时分秒按照当天00:00:00
     *
     * @param year，month，day
     * @return 2015/1/12
     */
    public static long getStartTime(int year, int month, int day) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, 0, 0, 0);
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 获取开始时间，时分秒按照当天00:00:00
     *
     * @param year，month，day
     * @return 2015/1/12
     */
    public static int getStartTime(int year, int month, int day, int hour, int minute) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute, 0);
        long time = calendar.getTimeInMillis() / 1000;
        return Integer.parseInt(String.valueOf(time));
    }

    /**
     * 获取开始时间，时分秒以当前时间定
     *
     * @param year，month，day
     * @return 2015/1/12
     */
    public static int getStartCurrentTime(int year, int month, int day) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        long time = calendar.getTimeInMillis() / 1000;
        return Integer.parseInt(String.valueOf(time)) - 1;
    }

    /**
     * 获取前一天 最后时间 前一天 23：59：59
     *
     * @return 2015/1/12
     */
    public static long getLastDayEnd() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        Calendar calendar = Calendar.getInstance();
        //将小时至0  
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0  
        calendar.set(Calendar.MINUTE, 0);
        //将秒至0  
        calendar.set(Calendar.SECOND, 0);
        //将毫秒至0  
        calendar.set(Calendar.MILLISECOND, 0);
        //在当前月的下一月基础上减去1毫秒  
        calendar.add(Calendar.MILLISECOND, -1);
        //获得当前月最后一天  
        Date edate = calendar.getTime();
        return edate.getTime() / 1000;
    }

    /**
     * 获取前一天 最后时间 前一天 23：59：59
     *
     * @return 2015/1/12
     */
    public static long getTodayDayEnd() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime().getTime() / 1000;

    }

    //*****************************************************月有关*******************************************************//

    /**
     * 获取当前月第一天 且 00：00：00
     *
     * @return 秒数
     */
    public static long getCurrentMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0  
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0  
        calendar.set(Calendar.MINUTE, 0);
        //将秒至0  
        calendar.set(Calendar.SECOND, 0);
        //将毫秒至0  
        calendar.set(Calendar.MILLISECOND, 0);
        //获得当前月第一天  
        Date sdate = calendar.getTime();
        return sdate.getTime() / 1000;
    }

    /**
     * 获取相对于当前月，上几次的月的第一天 且 00：00：00
     *
     * @return 秒数
     */
    public static long getAppointFirstDay(int month) {
        Calendar calendar = Calendar.getInstance();
        //当前是地第几月
        int currentmonth = getCurrentMonth() - 1;
        //到当前第几月之前
        calendar.set(Calendar.MONTH, currentmonth - month);
        //到此月的第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0  
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0  
        calendar.set(Calendar.MINUTE, 0);
        //将秒至0  
        calendar.set(Calendar.SECOND, 0);
        //将毫秒至0  
        calendar.set(Calendar.MILLISECOND, 0);
        //获得当前月第一天  
        Date sdate = calendar.getTime();
        return sdate.getTime() / 1000;
    }

    /**
     * 获取相对于当前月，上几次的月的第一天 且 00：00：00
     *
     * @return 秒数
     */
    public static long getAppointLastDay(int month) {
        Calendar calendar = Calendar.getInstance();
        //当前是地第几月
        int currentmonth = getCurrentMonth() - 1;
        //到当前第几月之前
        calendar.set(Calendar.MONTH, currentmonth - month);
        //到此月的第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0  
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0  
        calendar.set(Calendar.MINUTE, 0);
        //将秒至0  
        calendar.set(Calendar.SECOND, 0);
        //将毫秒至0  
        calendar.set(Calendar.MILLISECOND, 0);
        //将当前月加1；  
        calendar.add(Calendar.MONTH, 1);
        //将毫秒至0  
        calendar.add(Calendar.MILLISECOND, -1);
        //获得当前月第一天  
        Date sdate = calendar.getTime();
        return sdate.getTime() / 1000;
    }

    /**
     * 获取当前月最后一天 且 23：59：59
     *
     * @return 秒数
     */
    public static long getCurrentMonthLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0  
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0  
        calendar.set(Calendar.MINUTE, 0);
        //将秒至0  
        calendar.set(Calendar.SECOND, 0);
        //将毫秒至0  
        calendar.set(Calendar.MILLISECOND, 0);
        //将当前月加1；  
        calendar.add(Calendar.MONTH, 1);
        //在当前月的下一月基础上减去1毫秒  
        calendar.add(Calendar.MILLISECOND, -1);
        //获得当前月最后一天  
        Date edate = calendar.getTime();
        return edate.getTime() / 1000;
    }

    /**
     * 获取当前月月是第几月
     *
     * @return
     */
    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    //*****************************************************月有关*******************************************************//
    //*****************************************************周有关*******************************************************//

    /**
     * 获得当前日期与本周一相差的天数
     */
    public static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期一是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }

    /**
     * 获得相对于当前时间的，上几次的第几周星期一的日期
     *
     * @param weeks 当前时间为 0
     * @return xxxx-xx-xx
     */
    public static String getPreviousMonday(int weeks) {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    /**
     * 获得本周星期一的日期
     *
     * @return xxxx-xx-xx
     */
    public static String getCurrentWeekMonday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    /**
     * 获得下周星期一的日期
     *
     * @return xxxx-xx-xx
     */
    public static String getAppointMonday(int week) {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * week);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = TimeUtil.timeFormat(df.format(monday), TimeUtil.YY_MM_DD_UNIT, TimeUtil.YYYY_MM_DD_BARS);
        return preMonday;
    }

    /**
     * 相应周的星期天日期
     *
     * @param week
     * @return xxxx-xx-xx
     */
    public static String getAppointSunday(int week) {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * week + 6);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = TimeUtil.timeFormat(df.format(monday), TimeUtil.YY_MM_DD_UNIT, TimeUtil.YYYY_MM_DD_BARS);
        return preMonday;
    }

    /**
     * 判断是否为今天
     *
     * @param time
     * @return true今天 false不是
     */
    public static boolean isToday(long time) {
        try {
            Calendar _today = Calendar.getInstance(Locale.CHINA);
            _today.setTimeInMillis(System.currentTimeMillis());
            Calendar _time = Calendar.getInstance(Locale.CHINA);
            _time.setTimeInMillis(time);

            if (_today.get(Calendar.YEAR) == _time.get(Calendar.YEAR)
                    && _today.get(Calendar.MONTH) == _time.get(Calendar.MONTH)
                    && _today.get(Calendar.DAY_OF_MONTH) == _time.get(Calendar.DAY_OF_MONTH)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断是否为昨天
     *
     * @param time
     * @return true昨天 false不是
     */
    public static boolean isYesterday(long time) {
        Calendar _today = Calendar.getInstance(Locale.CHINA);
        _today.setTimeInMillis(System.currentTimeMillis());
        Calendar _time = Calendar.getInstance(Locale.CHINA);
        _time.setTimeInMillis(time);

        if (_today.get(Calendar.YEAR) == _time.get(Calendar.YEAR)
                && _today.get(Calendar.MONTH) == _time.get(Calendar.MONTH)
                && _today.get(Calendar.DAY_OF_MONTH) == _time.get(Calendar.DAY_OF_MONTH) - 1) {
            return true;
        }
        return false;
    }

    /**
     * 判断七天内
     *
     * @param time
     * @return true 七天内 false不是
     */
    public static boolean withinSevenDay(long time) {
        Calendar _today = Calendar.getInstance(Locale.CHINA);
        _today.setTimeInMillis(System.currentTimeMillis());
        Calendar _time = Calendar.getInstance(Locale.CHINA);
        _time.setTimeInMillis(time);

        if (_today.get(Calendar.YEAR) == _time.get(Calendar.YEAR)
                && _today.get(Calendar.MONTH) == _time.get(Calendar.MONTH)
                && (_today.get(Calendar.DAY_OF_MONTH) - _time.get(Calendar.DAY_OF_MONTH) < 7)) {
            return true;
        }

        System.out.println(_today.get(Calendar.DAY_OF_MONTH));
        System.out.println(_time.get(Calendar.DAY_OF_MONTH));
        System.out.println(_today.get(Calendar.DAY_OF_MONTH) - _time.get(Calendar.DAY_OF_MONTH));
        return false;
    }

    /**
     * 给定时间是星期几
     * 1=星期日 7=星期六
     *
     * @param time
     */
    public static String timeWeekDay(long time) {
        Calendar _time = Calendar.getInstance(Locale.CHINA);
        _time.setTimeInMillis(time);
        String day = "";
        switch (_time.get(Calendar.DAY_OF_WEEK)) {
            case 7:
                day = "星期六";
                break;
            case 6:
                day = "星期五";
                break;
            case 5:
                day = "星期四";
                break;
            case 4:
                day = "星期三";
                break;
            case 3:
                day = "星期二";
                break;
            case 2:
                day = "星期一";
                break;
            case 1:
                day = "星期日";
                break;
        }
        return day;
    }


    /**
     * 获取固定格式的时间
     *
     * @param time 以秒数来定
     * @return
     */
    public static long intFromatTime(String time, String format) {
        long longTime = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date date = sdf.parse(time);
            longTime = date.getTime();
        } catch (Exception e) {
            return 0;
        }
        return longTime;
    }


    /**
     * 根据固定事件格式，获取时间区间
     * 2019-06-06 00:00:00
     * 当天：时分表示 00:00
     * 昨天：显示昨天
     * 星期X:以当天为基准，往前推第二天起以星期表示，直到第七天（含）
     * 短日期：以当天为基准，从第七天起（不含），均以短日期表示
     */
    public static String timeDayWeek(String time_format) {
        if (TextUtils.isEmpty(time_format)) {
            return "";
        }
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        String time = TimeUtil.initStandardTimeS(time_format);
        String time_str;
        long time_m = intFromatTime(time, TimeUtil.YYYY_MM_DD_HH_MM_SS);
        if (isToday(time_m)) {
            //当天
            time_str = TimeUtil.intFromatTime(time, TimeUtil.HH_MM);
        } else if (isYesterday(time_m)) {
            time_str = "昨天";
        } else if (withinSevenDay(time_m)) {
            //七天内 则显示星期几
            time_str = timeWeekDay(time_m);
        } else {
            time_str = TimeUtil.intFromatTime(time, TimeUtil.YYYY_MM_DD_BARS);
        }
        return time_str;
    }


}