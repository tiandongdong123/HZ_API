package com.hanzhong.api.web.util.business;

import com.hanzhong.api.web.constant.cmnenum.LoggerEnum;
import com.hanzhong.api.web.util.LoggerUtils;
import com.hanzhong.api.web.util.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *  
 *  @Description 工商行政管理局代码工具类
 *  @Author   luqs   
 *  @Date 2018/11/21 18:33 
 *  @Version  V1.0   
 */
public class AdminCodeUtils {

    private static final Logger logger = LoggerFactory.getLogger(LoggerEnum.ABNORMAL_CODE_DATA.getValue());

    private static Map<String, String> codeMap = new HashMap<>(16);

    /**
     * 工商行政管理局代码文件路径
     */
    private static final String ADMIN_CODE_FILE_PATH = PropertiesUtils.getValue("dataCodeSetReferenceDoc.file.path");
    /**
     * 工商行政管理局代码文件sheet名称
     */
    private static final String ADMIN_CODE_FILE_SHEET_NAME = PropertiesUtils.getValue("adminCode.sheet.name");

    static {
        // 缓存代码
        cacheCodeMap();
    }

    private AdminCodeUtils() {
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
        return codeMap.get(code);
    }

    /**
     * 缓存工商行政管理局代码
     */
    private static void cacheCodeMap() {
        try {
            CommonUtils.CacheCodeParam codeParam = new CommonUtils.CacheCodeParam();
            codeParam.setFilePath(ADMIN_CODE_FILE_PATH);
            codeParam.setSheetName(ADMIN_CODE_FILE_SHEET_NAME);
            codeParam.setCodeColumnNum(0);
            codeParam.setNameColumnNum(1);
            codeMap = CommonUtils.cacheCodeMapExcludeChineseRow(codeParam);
        } catch (IOException e) {
            LoggerUtils.appendErrorLog(logger, "文件：【{}】，sheet：【{}】,缓存代码(cacheCodeMap())出现异常：", ADMIN_CODE_FILE_PATH, ADMIN_CODE_FILE_SHEET_NAME, e);
        }
    }

}
