package com.hanzhong.api.web.util.business;

import com.hanzhong.api.web.constant.cmnenum.LoggerEnum;
import com.hanzhong.api.web.util.ExcelUtils;
import com.hanzhong.api.web.util.LoggerUtils;
import com.hanzhong.api.web.util.PropertiesUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *  
 *  @Description 企业(机构)类型代码工具类
 *  @Author   luqs   
 *  @Date 2018/11/21 18:33 
 *  @Version  V1.0   
 */
public class EntTypeCodeUtils {

    private static final Logger logger = LoggerFactory.getLogger(LoggerEnum.ABNORMAL_CODE_DATA.getValue());

    private static Map<String, String> codeMap = new HashMap<>(16);

    /**
     * 企业(机构)类型代码文件路径
     */
    private static final String ENT_TYPE_CODE_FILE_PATH = PropertiesUtils.getValue("dataCodeSetReferenceDoc.file.path");
    /**
     * 企业(机构)类型代码文件sheet名称
     */
    private static final String ENT_TYPE_CODE_FILE_SHEET_NAME = PropertiesUtils.getValue("entTypeCode.sheet.name");

    static {
        // 缓存代码
        cacheCodeMap();
    }

    private EntTypeCodeUtils() {
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
     * 缓存企业(机构)类型代码
     */
    private static void cacheCodeMap() {
        try {
            CommonUtils.CacheCodeParam codeParam = new CommonUtils.CacheCodeParam();
            codeParam.setFilePath(ENT_TYPE_CODE_FILE_PATH);
            codeParam.setSheetName(ENT_TYPE_CODE_FILE_SHEET_NAME);
            codeParam.setCodeColumnNum(0);
            codeParam.setNameColumnNum(1);
            codeMap = CommonUtils.cacheCodeMapExcludeChineseRow(codeParam);
        } catch (IOException e) {
            LoggerUtils.appendErrorLog(logger, "文件：【{}】，sheet：【{}】,缓存代码(cacheCodeMap())出现异常：", ENT_TYPE_CODE_FILE_PATH, ENT_TYPE_CODE_FILE_SHEET_NAME, e);
        }
    }

}
