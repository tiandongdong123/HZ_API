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
 *  @Description 开发区代码工具类
 *  @Author   luqs   
 *  @Date 2018/11/21 18:33 
 *  @Version  V1.0   
 */
public class DevZoneCodeUtils {

    private static final Logger logger = LoggerFactory.getLogger(LoggerEnum.ABNORMAL_CODE_DATA.getValue());

    private static Map<String, String> codeMap = new HashMap<>(16);

    /**
     * 开发区代码文件路径
     */
    private static final String DEV_ZONE_CODE_FILE_PATH = PropertiesUtils.getValue("dataCodeSetReferenceDoc.file.path");
    /**
     * 开发区代码文件sheet名称
     */
    private static final String DEV_ZONE_CODE_FILE_SHEET_NAME = PropertiesUtils.getValue("devZoneCode.sheet.name");

    static {
        // 缓存代码
        cacheCodeMap();
    }

    private DevZoneCodeUtils() {
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
     * 缓存开发区代码
     */
    private static void cacheCodeMap() {
        try {
            CommonUtils.CacheCodeParam codeParam = new CommonUtils.CacheCodeParam();
            codeParam.setFilePath(DEV_ZONE_CODE_FILE_PATH);
            codeParam.setSheetName(DEV_ZONE_CODE_FILE_SHEET_NAME);
            codeParam.setCodeColumnNum(0);
            codeParam.setNameColumnNum(1);
            codeMap = CommonUtils.cacheCodeMapExcludeChineseRow(codeParam);
        } catch (IOException e) {
            LoggerUtils.appendErrorLog(logger, "文件：【{}】，sheet：【{}】,缓存代码(cacheCodeMap())出现异常：", DEV_ZONE_CODE_FILE_PATH, DEV_ZONE_CODE_FILE_SHEET_NAME, e);
        }
    }

}
