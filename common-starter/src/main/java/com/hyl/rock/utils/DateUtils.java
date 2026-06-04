package com.hyl.rock.utils;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间工具类
 *
 */
@SuppressWarnings("deprecation")
public class DateUtils extends org.apache.commons.lang3.time.DateUtils
{
    public static final String YYYY = "yyyy";

    public static final String YYYY_MM = "yyyy-MM";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";


    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static final String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};


    /**
     * 按照yyyy-MM-dd HH:mm:ss格式获取当前时间
     * @return 时间字符串
     */
    public static String getTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 按照指定格式获取当前时间
     * @param format 格式
     * @return 时间字符串
     */
    public static String dateTimeNow(String format) {
        return parseDateToStr(format, LocalDateTime.now());
    }

    /**
     * 将日期转换为字符串
     * @param format 格式
     * @param date 日期
     * @return 时间字符串
     */
    public static String parseDateToStr(String format, LocalDateTime date) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return df.format(date);
    }


    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }


    /**
     * 计算相差天数
     */
    public static int differentDaysByMillisecond(LocalDateTime start, LocalDateTime end) {
        return Math.toIntExact(Duration.between(start, end).toDays());
    }


    /**
     * 获取当天开始时间
     * @return 时间
     */
    public static LocalDateTime getDayStart() {
        return LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    /**
     * 获取当天结束时间
     * @return 时间
     */
    public static LocalDateTime getDayEnd() {
        return LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(999999999);
    }

    /**
     * 将日期转换为当天开始时间字符串
     * @param date
     * @return
     */
    public static String getDayStartStr(String date) {
        if(StringUtils.isBlank(date)) {
            return null;
        }
        return date+" 00:00:00";
    }

    /**
     * 将日期转换为当天结束时间字符串
     * @param date
     * @return
     */
    public static String getDayEndStr(String date) {
        if(StringUtils.isBlank(date)) {
            return null;
        }
        return date+" 23:59:59";
    }
}
