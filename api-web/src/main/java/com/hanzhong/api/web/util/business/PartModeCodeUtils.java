package com.hanzhong.api.web.util.business;

import com.hanzhong.api.web.constant.cmnenum.LoggerEnum;
import com.hanzhong.api.web.util.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *  
 *  @Description 合伙方式代码工具类
 *  @Author   luqs   
 *  @Date 2018/11/21 18:33 
 *  @Version  V1.0   
 */
public class PartModeCodeUtils {

    private static final Logger logger = LoggerFactory.getLogger(LoggerEnum.ABNORMAL_CODE_DATA.getValue());

    private static Map<String, String> codeMap = new HashMap<>(16);

    /**
     * 合伙方式代码文件路径
     */
    private static final String PART_MODE_CODE_FILE_PATH = PropertiesUtils.getValue("dataCodeSetReferenceDoc.file.path");
    /**
     * 合伙方式代码文件sheet名称
     */
    private static final String PART_MODE_CODE_FILE_SHEET_NAME = PropertiesUtils.getValue("partModeCode.sheet.name");

    static {
        // 缓存代码
        cacheCodeMap();
    }

    private PartModeCodeUtils() {
    }

    /**
     * 通过代码获取名称
     *
     * @param code
     * @return String
     */
    public static String getNameByCode(String code) {
        return codeMap.get(code);
    }

    /**
     * 缓存合伙方式代码
     */
    private static void cacheCodeMap() {
        try {
            codeMap = CommonUtils.cacheCodeMapExcludeChineseRow(PART_MODE_CODE_FILE_PATH, PART_MODE_CODE_FILE_SHEET_NAME);
        } catch (IOException e) {
            logger.error("文件：【{}】，sheet：【{}】,缓存代码(cacheCodeMap())出现异常：", PART_MODE_CODE_FILE_PATH, PART_MODE_CODE_FILE_SHEET_NAME, e);
        }
    }

}
