package com.hanzhong.api.web.util;

import org.apache.commons.lang3.StringUtils;

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
}
