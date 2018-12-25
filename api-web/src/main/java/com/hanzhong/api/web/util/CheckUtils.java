package com.hanzhong.api.web.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 *  
 *  @Description 校验工具类
 *  @Author   luqs   
 *  @Date 2018/11/5 18:06 
 *  @Version  V1.0   
 */
public class CheckUtils {

    /**
     * 正则表达式：统一社会信用代码
     */
    private static final String REGEX_UNIFIED_SOCIAL_CREDIT_CODE = "^[A-Z0-9]{18}$";
    /**
     * 正则表达式：组织机构代码
     */
    private static final String REGEX_ORGANIZATION_CODE = "^[A-Z0-9]{9}$";
    /**
     * 正则表达式：含汉字
     */
    private static final String REGEX_CHINESE = "^.*[\u4e00-\u9fa5]+.*$";

    private CheckUtils() {
    }

    /**
     * 判断是否存在空
     *
     * @param params
     * @return boolean
     */
    public static boolean isBlank(String... params) {
        for (String param : params) {
            if (StringUtils.isBlank(param)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断是否全为空
     *
     * @param params
     * @return boolean
     */
    public static boolean isAllBlank(String... params) {
        for (String param : params) {
            if (StringUtils.isNotBlank(param)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断是否存在空
     *
     * @param params
     * @return boolean
     */
    public static boolean isEmpty(String... params) {
        for (String param : params) {
            if (StringUtils.isEmpty(param)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断是否为统一社会信用代码
     *
     * @param uniSocialCreditCode 统一社会信用代码
     * @return boolean
     */
    public static boolean isUnifiedSocialCreditCode(String uniSocialCreditCode) {
        return Pattern.matches(REGEX_UNIFIED_SOCIAL_CREDIT_CODE, uniSocialCreditCode);
    }

    /**
     * 判断是否为组织结构代码
     *
     * @param orgCode 组织结构代码
     * @return boolean
     */
    public static boolean isOrganizationCode(String orgCode) {
        return Pattern.matches(REGEX_ORGANIZATION_CODE, orgCode);
    }

    /**
     * 判断是否含汉字
     *
     * @param str 组织结构代码
     * @return boolean
     */
    public static boolean includeChinese(String str) {
        return Pattern.matches(REGEX_CHINESE, str);
    }
}
