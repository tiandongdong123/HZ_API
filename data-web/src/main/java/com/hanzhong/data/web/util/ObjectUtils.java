package com.hanzhong.data.web.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/8 17:56 
 *  @Version  V1.0   
 */
public class ObjectUtils {

    private ObjectUtils() {
    }

    /**
     * 正则表达式：含小数位
     */
    private static final String REGEX_DOUBLE = "^[0-9]+[.]*[0-9]*$";

    /**
     * Object转换成String
     *
     * @param object Object
     * @return String 若为空，则返回""
     */
    public static String defaultString(Object object) {
        if (object == null) {
            return "";
        }
        return object.toString();
    }

    /**
     * Object转换成int
     *
     * @param object Object
     * @return int 若为空，则返回0
     */
    public static int defaultInt(Object object) {
        if (object instanceof Integer) {
            return (Integer) object;
        } else if (object instanceof String && StringUtils.isNumeric((String) object)) {
            return Integer.parseInt((String) object);
        }
        return 0;
    }

    /**
     * Object转换成long
     *
     * @param object Object
     * @return long 若为空，则返回0
     */
    public static long defaultLong(Object object) {
        if (object instanceof Long) {
            return (Long) object;
        } else if (object instanceof String && StringUtils.isNumeric((String) object)) {
            return Long.parseLong((String) object);
        }
        return 0;
    }

    /**
     * Object转换成double
     *
     * @param object Object
     * @return int 若为空，则返回0
     */
    public static double defaultDouble(Object object) {
        if (object instanceof BigDecimal) {
            return ((BigDecimal) object).doubleValue();
        } else if (object instanceof Double) {
            return (Double) object;
        } else if (object instanceof Integer) {
            return (Integer) object;
        } else if (object instanceof String && Pattern.matches(REGEX_DOUBLE, (String) object)) {
            return Double.parseDouble((String) object);
        }
        return 0.00;
    }
}
