package me.lyinlong.taskline.utils;

import me.lyinlong.taskline.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 关于时间的工具类
 * Created by eightpigs on 16/10/26.
 */

public class TimeUtils {


    /**
     * 获取当前系统时间
     *
     * @param formatStr 格式化的字符串
     * @return
     */
    public static String getNowTime(String formatStr) {

        return new SimpleDateFormat(formatStr).format(new Date());
    }

    /**
     * 获取指定间隔的时间
     *
     * @param value      格式时间（往前获取填负数）
     * @param type       间隔类型 ： 1：小时   2：日   3：月   4：年
     * @param time       指定时间（为空==当前时间）
     * @param returnDate 是否返回日期格式
     * @param formatStr  格式化
     * @return
     */
    public static Object getTimeByCons(Integer value, Integer type, String time, Boolean returnDate, String formatStr) {

        try {
            Date date = null;


            if (time == null) {
                date = new Date();
            } else {
                date = new SimpleDateFormat(Constants.DATE_FORMAT_yyyy年MM月dd日HH时mm分).parse(time);
            }

            // 通过类型、值计算时间
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            switch (type) {
                case 1:
                    cal.add(Calendar.HOUR, value);
                    break;    // 时
                case 2:
                    cal.add(Calendar.DATE, value);
                    break;    // 日
                case 3:
                    cal.add(Calendar.MONTH, value);
                    break;    // 月
                case 4:
                    cal.add(Calendar.YEAR, value);
                    break;    // 年
            }

            if (returnDate)
                return cal.getTime();

            return new SimpleDateFormat(formatStr).format(cal.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前日期
     * @return
     */
    public static int getNowDay(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }

}
