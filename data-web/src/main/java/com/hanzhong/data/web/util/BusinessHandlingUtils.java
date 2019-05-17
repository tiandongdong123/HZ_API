package com.hanzhong.data.web.util;

import com.hanzhong.data.web.model.BasePage;
import com.hanzhong.data.web.model.vo.BasePageVO;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.regex.Pattern;

/**
 * 业务处理工具类
 *
 * @author yifei
 * @date 2019/3/21
 */
public class BusinessHandlingUtils {
    /**
     * 默认查询字段参数
     */
    private static final String DEFAULT_QRY_FIELD_PARAM = "qryFieldParam";
    /**
     * 默认页码参数
     */
    private static final String DEFAULT_PAGE_NUM_PARAM = "pageNum";
    /**
     * 默认每页数量参数
     */
    private static final String DEFAULT_PAGE_SIZE_PARAM = "pageSize";
    /**
     * 默认页码
     */
    private static final int DEFAULT_PAGE_NUM = 1;
    /**
     * 默认每页数量
     */
    private static final int DEFAULT_PAGE_SIZE = 10;
    /**
     * 正则表达式：****.**.**，如：2019.03.30
     */
    private static final Pattern DOT_DATE_PATTERN = Pattern.compile("^\\d{4}\\.\\d{2}\\.\\d{2}$");
    /**
     * 正则表达式：东经：* 北纬：*，如：东经：116.317964 北纬：39.973508
     */
    private static final Pattern LON_LAT_PATTERN = Pattern.compile("^东经：\\d+.\\d+ 北纬：\\d+.\\d+$");
    /**
     * 分隔符：.
     */
    private static final String SEPARATOR_DOT = ".";
    /**
     * 分隔符：,
     */
    public static final String SEPARATOR_COMMA = ",";
    /**
     * 分隔符：-
     */
    private static final String SEPARATOR_MIDDLE_LINE = "-";

    private BusinessHandlingUtils() {
    }

    /**
     * 获取查询字段参数值
     *
     * @param request http请求
     * @return String
     */
    public static String getQryFieldParamValue(HttpServletRequest request) {
        return request.getParameter(DEFAULT_QRY_FIELD_PARAM);
    }

    /**
     * 获取页码
     *
     * @param request http请求
     * @return int
     */
    public static int getDefaultPageNum(HttpServletRequest request) {
        return getDefaultInt(request.getParameter(DEFAULT_PAGE_NUM_PARAM), DEFAULT_PAGE_NUM);
    }

    /**
     * 获取每页数量
     *
     * @param request http请求
     * @return int
     */
    public static int getDefaultPageSize(HttpServletRequest request) {
        return getDefaultInt(request.getParameter(DEFAULT_PAGE_SIZE_PARAM), DEFAULT_PAGE_SIZE);
    }

    /**
     * 获取页码
     *
     * @param pageNum 页码
     * @return int
     */
    public static int getDefaultPageNum(Integer pageNum) {
        return getDefaultInt(pageNum, DEFAULT_PAGE_NUM);
    }

    /**
     * 获取每页数量
     *
     * @param pageSize 每页数量
     * @return int
     */
    public static int getDefaultPageSize(Integer pageSize) {
        return getDefaultInt(pageSize, DEFAULT_PAGE_SIZE);
    }

    /**
     * 获取数据库分页的起始索引
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return int
     */
    public static int getStartIndex(int pageNum, int pageSize) {
        return (pageNum - 1) * pageSize;
    }

    /**
     * 获取总页数
     *
     * @param total    总数
     * @param pageSize 每页数量
     * @return int
     */
    public static int getPageTotal(int total, int pageSize) {
        // 整数部分
        int integerPart = total / pageSize;
        // 余数
        int compNum = total % pageSize;
        return compNum == 0 ? integerPart : (integerPart + 1);
    }

    /**
     * 是否有更多
     *
     * @param total      总数
     * @param startIndex 起始索引
     * @param pageSize   每页数量
     * @return boolean true：没更多
     */
    public static boolean notHaveMore(int total, int startIndex, int pageSize) {
        return (startIndex + pageSize) >= total;
    }

    /**
     * 复制BasePage
     *
     * @param basePageVO 分页信息
     * @param basePage   分页信息
     */
    public static void copyBasePage(BasePageVO basePageVO, BasePage basePage) {
        // 总数
        basePageVO.setTotal(basePage.getTotal());
        // 总页数
        basePageVO.setPageTotal(basePage.getPageTotal());
        // 是否有更多
        basePageVO.setNoMore(basePage.getNoMore());
        // 当前页码
        basePageVO.setCurrentPageNum(basePage.getCurrentPageNum());
        // 当前每页数量
        basePageVO.setCurrentPageSize(basePage.getCurrentPageSize());
    }

    /**
     * 格式化经纬度
     *
     * @param lonLat 经纬度，如：东经：116.317964 北纬：39.973508
     * @return String 若lonLat不符合正则，则返回null
     */
    public static String formatLonLat(String lonLat) {
        if (StringUtils.isNotBlank(lonLat) && LON_LAT_PATTERN.matcher(lonLat).matches()) {
            return lonLat.replace("东经：", "").replace(" 北纬：", SEPARATOR_COMMA);
        }
        return null;
    }

    /**
     * 格式化点分隔的日期
     *
     * @param dateStr      点分隔的日期，如：2019.03.30
     * @param defaultValue 默认值
     * @return String 若dateStr不符合点分隔，则返回默认值
     */
    public static String formatDateWithDot(String dateStr, String defaultValue) {
        if (StringUtils.isNotBlank(dateStr) && DOT_DATE_PATTERN.matcher(dateStr).matches()) {
            return dateStr.replace(SEPARATOR_DOT, SEPARATOR_MIDDLE_LINE);
        }
        return defaultValue;
    }

    /**
     * 是否空集合
     *
     * @param collection 集合
     * @return boolean true：空集合
     */
    public static boolean isBlankCollection(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 是否非空集合
     *
     * @param collection 集合
     * @return boolean true：非空集合
     */
    public static boolean isNotBlankCollection(Collection collection) {
        return !isBlankCollection(collection);
    }

    /**
     * 获取默认集合长度
     *
     * @param collection 集合
     * @return int
     */
    public static int getDefaultCollectionSize(Collection collection) {
        return collection == null ? 0 : collection.size();
    }

    /**
     * 获取int
     *
     * @param inputInt   Integer
     * @param defaultInt 默认值
     * @return int
     */
    private static int getDefaultInt(Integer inputInt, int defaultInt) {
        if (inputInt == null || inputInt.equals(0)) {
            return defaultInt;
        }
        return inputInt;
    }

    /**
     * 获取int
     *
     * @param inputStr   String
     * @param defaultInt 默认值
     * @return int
     */
    private static int getDefaultInt(String inputStr, int defaultInt) {
        if (StringUtils.isNumeric(inputStr)) {
            return Integer.parseInt(inputStr);
        }
        return defaultInt;
    }
}
