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
 *  @Description 企业分类码工具类
 *  @Author   luqs   
 *  @Date 2018/11/21 18:33 
 *  @Version  V1.0   
 */
public class EntClassCodeUtils {

    private static final Logger logger = LoggerFactory.getLogger(LoggerEnum.ABNORMAL_CODE_DATA.getValue());

    private static Map<String, String> codeMap = new HashMap<>(16);

    /**
     * 企业分类码文件路径
     */
    private static final String ENT_CLASS_CODE_FILE_PATH = PropertiesUtils.getValue("dataCodeSetReferenceDoc.file.path");
    /**
     * 企业分类码文件sheet名称
     */
    private static final String ENT_CLASS_CODE_FILE_SHEET_NAME = PropertiesUtils.getValue("entClassCode.sheet.name");

    static {
        // 缓存代码
        cacheCodeMap();
    }

    private EntClassCodeUtils() {
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
     * 缓存企业分类码
     */
    private static void cacheCodeMap() {
        try {
            CommonUtils.CacheCodeParam codeParam = new CommonUtils.CacheCodeParam();
            codeParam.setFilePath(ENT_CLASS_CODE_FILE_PATH);
            codeParam.setSheetName(ENT_CLASS_CODE_FILE_SHEET_NAME);
            codeParam.setCodeColumnNum(0);
            codeParam.setNameColumnNum(1);
            codeMap = CommonUtils.cacheCodeMapExcludeChineseRow(codeParam);
        } catch (IOException e) {
            LoggerUtils.appendErrorLog(logger, "文件：【{}】，sheet：【{}】,缓存代码(cacheCodeMap())出现异常：", ENT_CLASS_CODE_FILE_PATH, ENT_CLASS_CODE_FILE_SHEET_NAME, e);
        }
    }

}
