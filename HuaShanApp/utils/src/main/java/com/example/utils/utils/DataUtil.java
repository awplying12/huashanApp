package com.example.utils.utils;

import android.text.format.Time;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2016/6/30.
 */
public class DataUtil {

    /**
     * 时间格式转换
     * @param date
     * @param mode
     * @return
     */
    public static String getDate(Date date, String mode) {
        SimpleDateFormat format = new SimpleDateFormat(mode);  //"MM-dd HH:mm"
        return format.format(date);
    }

    public static String getDate(String time,String oldMode, String newMode) {
        SimpleDateFormat sdf = new SimpleDateFormat(oldMode);

        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format = new SimpleDateFormat(newMode);  //"MM-dd HH:mm"
        return format.format(date);
    }

    /**
     * 判断是否是在时间范围内
     * @return
     */
    public static boolean onTime(String timeStr,String mode,Long confines) {
        Date dt= new Date();
        Long time_now= dt.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat(mode);
        Long time = null;
        try {
            Date date = sdf.parse(timeStr);
            time = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            Log.i("msg",e.toString());
        }
        Log.i("time123","timeStr : "+timeStr+"time : "+time+"  time_now : "+time_now);
        if((time - time_now) >= confines){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 判断是否为今天(效率比较高)
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */
    public static boolean IsToday(long day) throws ParseException {

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        Date date = new Date(day);
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为昨天(效率比较高)
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */
    public static boolean IsYesterday(long day) throws ParseException {

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        Date date = new Date(day);
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == -1) {
                return true;
            }
        }
        return false;
    }

    public static String getWeekDay(long day) throws ParseException{

        Calendar cal = Calendar.getInstance();
        Date date = new Date(day);
        cal.setTime(date);

        Time time = new Time("GMT+8");
        time.set(day);

        String weekday;
        switch (time.weekDay){
            case 0:
                weekday =  "周一";
                break;
            case 1:
                weekday =  "周二";
                break;
            case 2:
                weekday =  "周三";
                break;
            case 3:
                weekday =  "周四";
                break;
            case 4:
                weekday =  "周五";
                break;
            case 5:
                weekday =  "周六";
                break;
            case 6:
                weekday =  "周日";
                break;
            default:
                weekday =  "未知";
                break;

        }
        Log.i("msg",weekday+"     "+String.valueOf(time.weekDay)+"   "+ day);
        return weekday;
//        return String.valueOf(Calendar.DAY_OF_WEEK);
    }


}
