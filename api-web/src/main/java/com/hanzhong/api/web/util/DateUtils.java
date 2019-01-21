package com.hanzhong.api.web.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *    
 *  @Description
 *  @Author   luqs   
 *  @Date 2018/8/8 18:04 
 *  @Version  V1.0   
 */
public class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    /**
     * 默认时间格式：yyyy-MM-dd HH:mm:ss
     */
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认日期格式：yyyy-MM-dd
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 默认时分秒格式：HH:mm:ss
     */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    private DateUtils() {
    }

    /**
     * 格式化时间(格式：yyyy-MM-dd HH:mm:ss)
     *
     * @param date 日期
     * @return String
     */
    public static String dateFormat(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
        return sdf.format(date);
    }

    /**
     * 格式化时间
     *
     * @param date       日期
     * @param dateFormat 时间格式
     * @return
     */
    public static String dateFormat(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }

    /**
     * 将String转换成Date
     *
     * @param dateStr 时间字符串，格式：yyyy-MM-dd HH:mm:ss
     * @return Date 若转换失败，则返回null
     */
    public static Date parse(String dateStr) {
        return parse(dateStr, DEFAULT_DATETIME_FORMAT);
    }

    /**
     * 将String转换成Date
     *
     * @param dateStr    时间字符串
     * @param dateFormat dateFormat 时间格式
     * @return Date 若转换失败，则返回null
     */
    public static Date parse(String dateStr, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            logger.error("dateStr：【{}】，将String转换成Date出现异常：", dateStr, e);
        }
        return date;
    }

    /**
     * 获取当前时间
     *
     * @return long
     */
    public static String getCurrentTime() {
        return dateFormat(new Date(), DEFAULT_DATETIME_FORMAT);
    }

    /**
     * 获取当前时间戳（秒）
     *
     * @return long
     */
    public static long getCurrentTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }
}
