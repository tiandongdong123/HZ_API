package com.hanzhong.api.web.util.business;

import com.hanzhong.api.web.constant.cmnenum.LoggerEnum;
import com.hanzhong.api.web.util.LoggerUtils;
import com.hanzhong.api.web.util.PropertiesUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *  
 *  @Description 住所产权代码工具类
 *  @Author   luqs   
 *  @Date 2018/11/21 18:33 
 *  @Version  V1.0   
 */
public class DomPropertyCodeUtils {

    private static final Logger logger = LoggerFactory.getLogger(LoggerEnum.ABNORMAL_CODE_DATA.getValue());

    private static Map<String, String> codeMap = new HashMap<>(16);
    /**
     * 正则表达式：1-9
     */
    private static final String REGEX_NUM = "^[1-9]{1}$";
    private static final String ZERO_PREFIX = "0";

    /**
     * 住所产权代码文件路径
     */
    private static final String DOM_PROPERTY_CODE_FILE_PATH = PropertiesUtils.getValue("dataCodeSetReferenceDoc.file.path");
    /**
     * 住所产权代码文件sheet名称
     */
    private static final String DOM_PROPERTY_CODE_FILE_SHEET_NAME = PropertiesUtils.getValue("domPropertyCode.sheet.name");

    static {
        // 缓存代码
        cacheCodeMap();
    }

    private DomPropertyCodeUtils() {
    }

    /**
     * 通过代码获取名称
     *
     * @param code
     * @return String
     */
    public static String getNameByCode(String code) {
        if (codeMap == null || codeMap.isEmpty()) {
            // 缓存代码
            cacheCodeMap();
        }
        return codeMap.get(convertCode(code));
    }

    /**
     * 缓存住所产权代码
     */
    private static void cacheCodeMap() {
        try {
            CommonUtils.CacheCodeParam codeParam = new CommonUtils.CacheCodeParam();
            codeParam.setFilePath(DOM_PROPERTY_CODE_FILE_PATH);
            codeParam.setSheetName(DOM_PROPERTY_CODE_FILE_SHEET_NAME);
            codeParam.setCodeColumnNum(0);
            codeParam.setNameColumnNum(1);
            codeMap = CommonUtils.cacheCodeMapExcludeChineseRow(codeParam);
        } catch (IOException e) {
            LoggerUtils.appendErrorLog(logger, "文件：【{}】，sheet：【{}】,缓存代码(cacheCodeMap())出现异常：", DOM_PROPERTY_CODE_FILE_PATH, DOM_PROPERTY_CODE_FILE_SHEET_NAME, e);
        }
    }

    /**
     * 转换code
     *
     * @param code
     * @return
     */
    private static String convertCode(String code) {
        if (Pattern.matches(REGEX_NUM, code)) {
            return ZERO_PREFIX + code;
        }
        return code;
    }
}
