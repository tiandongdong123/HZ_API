package com.hanzhong.data.web.util;

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
    private static final Pattern UNIFIED_SOCIAL_CREDIT_CODE_PATTERN = Pattern.compile("^[A-Z0-9]{18}$");
    /**
     * 正则表达式：组织机构代码
     */
    private static final Pattern ORGANIZATION_CODE_PATTERN = Pattern.compile("^[A-Z0-9]{9}$");
    /**
     * 正则表达式：含汉字
     */
    private static final Pattern CHINESE_PATTERN = Pattern.compile("^.*[\u4e00-\u9fa5]+.*$");
    /**
     * 正则表达式：ip
     */
    private static final Pattern IP_PATTERN = Pattern.compile("^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$");


    private CheckUtils() {
    }

    /**
     * 判断是否存在空
     *
     * @param params 参数集
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
     * @param params 参数集
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
     * @param params 参数集
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
        return UNIFIED_SOCIAL_CREDIT_CODE_PATTERN.matcher(uniSocialCreditCode).matches();
    }

    /**
     * 判断是否为组织结构代码
     *
     * @param orgCode 组织结构代码
     * @return boolean
     */
    public static boolean isOrganizationCode(String orgCode) {
        return ORGANIZATION_CODE_PATTERN.matcher(orgCode).matches();
    }

    /**
     * 判断是否含汉字
     *
     * @param str 组织结构代码
     * @return boolean
     */
    public static boolean includeChinese(String str) {
        return CHINESE_PATTERN.matcher(str).matches();
    }

    /**
     * 检测ip格式
     *
     * @param ip ip
     * @return true: 符合ip格式
     */
    public static boolean checkIpFormat(String ip) {
        return IP_PATTERN.matcher(ip).matches();
    }
}
