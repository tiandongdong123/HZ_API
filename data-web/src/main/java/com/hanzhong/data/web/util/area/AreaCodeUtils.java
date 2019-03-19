package com.hanzhong.data.web.util.area;

import com.hanzhong.data.web.constant.LoggerEnum;
import com.hanzhong.data.web.util.CommonUtils;
import com.hanzhong.data.web.util.PropertiesUtils;
import com.hanzhong.data.web.util.area.model.AreaCode;
import com.hanzhong.data.web.util.area.model.AreaInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *    
 *  @Description 行政区划代码工具类
 *  @Author   luqs   
 *  @Date 2018/7/18 14:59 
 *  @Version  V1.0   
 */
public class AreaCodeUtils {

    private static final Logger logger = LoggerFactory.getLogger(LoggerEnum.ABNORMAL_CODE_DATA.getValue());
    private static Map<String, String> codeMap = new HashMap<>(16);
    /**
     * 行政区划代码文件路径
     */
    private static final String AREA_CODE_FILE_PATH = PropertiesUtils.getValue("dataCodeSetReferenceDoc.file.path");
    /**
     * 行政区划代码文件sheet名称
     */
    private static final String AREA_CODE_FILE_SHEET_NAME = PropertiesUtils.getValue("regionalismCode.sheet.name");
    /**
     * 行政区划区代码的等级起始码
     */
    private static final String AREA_LEVEL_START_CODE = "00";

    static {
        // 缓存代码
        cacheCodeMap();
    }

    private AreaCodeUtils() {
    }

    /**
     * 通过区代码获取区域信息
     *
     * @param areaCode 行政区划代码,如：110000
     * @return Area 若areaCode为空或不存在，则返回null
     */
    public static AreaInfo getAreaInfoByCode(String areaCode) {
        if (!isMeetAreaCodeFormat(areaCode)) {
            return null;
        }

        AreaCode areaCodeObj = getAreaCodeWithoutCheck(areaCode);
        String provinceCode = areaCodeObj.getProvinceCode();
        String cityCode = areaCodeObj.getCityCode();
        String districtCode = areaCodeObj.getDistrictCode();
        AreaInfo areaInfo = new AreaInfo();
        // 省（/直辖市）名
        areaInfo.setProvinceCode(provinceCode);
        areaInfo.setProvinceName(codeMap.get(provinceCode));
        // 市（/县）名
        areaInfo.setCityCode(cityCode);
        areaInfo.setCityName(codeMap.get(cityCode));
        // 区（/县）名
        areaInfo.setDistrictCode(districtCode);
        areaInfo.setDistrictName(codeMap.get(districtCode));

        return areaInfo;
    }

    /**
     * 通过区代码获取区域名称
     *
     * @param areaCode 行政区划代码,如：110000
     * @return Area 若areaCode为空或不存在，则返回null
     */
    public static String getAreaNameByCode(String areaCode) {
        if (!isMeetAreaCodeFormat(areaCode)) {
            return null;
        }

        return codeMap.get(areaCode);
    }

    /**
     * 获取行政区划区代码的等级码
     *
     * @param areaCode 行政区划代码,如：110000
     * @return AreaCode 若不符合行政区划代码格式，则返回null
     */
    public static AreaCode getAreaCode(String areaCode) {
        return getAreaCode(areaCode, true);
    }

    /**
     * 获取区代码的等级码
     *
     * @param areaCode 行政区划代码,如：110000
     * @return AreaCode
     */
    public static AreaCode getAreaCodeWithoutCheck(String areaCode) {
        return getAreaCode(areaCode, false);
    }

    /**
     * 获取区代码的等级码
     *
     * @param areaCode 行政区划代码,如：110000
     * @param checkFlg 是否校验行政区划代码格式
     * @return AreaCode 若不符合行政区划代码格式，则返回null
     */
    private static AreaCode getAreaCode(String areaCode, boolean checkFlg) {
        AreaCode areaCodeObj = new AreaCode();

        if (checkFlg && !isMeetAreaCodeFormat(areaCode)) {
            return null;
        }

        // 省（/直辖市）码
        String provinceCode;
        // 市（/县）码
        String cityCode = null;
        // 区（/县）码
        String districtCode = null;
        // 省（/直辖市）级码
        String provinceLevelCode = areaCode.substring(0, 2);
        // 市（/县）级码
        String cityLevelCode = areaCode.substring(2, 4);
        // 区（/县）级码
        String districtLevelCode = areaCode.substring(4, 6);

        if (!AREA_LEVEL_START_CODE.equals(districtLevelCode)) {
            provinceCode = provinceLevelCode + AREA_LEVEL_START_CODE + AREA_LEVEL_START_CODE;
            cityCode = provinceLevelCode + cityLevelCode + AREA_LEVEL_START_CODE;
            districtCode = areaCode;
        } else if (!AREA_LEVEL_START_CODE.equals(cityLevelCode)) {
            provinceCode = provinceLevelCode + AREA_LEVEL_START_CODE + AREA_LEVEL_START_CODE;
            cityCode = areaCode;
        } else {
            provinceCode = areaCode;
        }

        areaCodeObj.setProvinceCode(provinceCode);
        areaCodeObj.setCityCode(cityCode);
        areaCodeObj.setDistrictCode(districtCode);
        return areaCodeObj;
    }

    /**
     * 是否符合行政区划代码格式
     *
     * @param areaCode 行政区划代码,如：110000
     * @return boolean 若符合行政区划代码格式，则返回true
     */
    public static boolean isMeetAreaCodeFormat(String areaCode) {
        return StringUtils.isNotBlank(areaCode) && StringUtils.isNumeric(areaCode) && areaCode.length() % 3 == 0;
    }

    /**
     * 缓存行政区划代码
     */
    private static void cacheCodeMap() {
        try {
            codeMap = CommonUtils.cacheCodeMapExcludeChineseRow(AREA_CODE_FILE_PATH, AREA_CODE_FILE_SHEET_NAME);
        } catch (IOException e) {
            logger.error("文件：【{}】，sheet：【{}】,缓存代码(cacheCodeMap())出现异常：", AREA_CODE_FILE_PATH, AREA_CODE_FILE_SHEET_NAME, e);
        }
    }

}
