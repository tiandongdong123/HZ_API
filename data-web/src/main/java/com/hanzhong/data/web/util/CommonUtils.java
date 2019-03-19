package com.hanzhong.data.web.util;

import com.hanzhong.data.web.constant.LoggerEnum;
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
 * @author yifei
 * @date 2018/12/1
 */
public class CommonUtils {

    private static final Logger abnormalCodeDataLogger = LoggerFactory.getLogger(LoggerEnum.ABNORMAL_CODE_DATA.getValue());

    private CommonUtils() {
    }

    /**
     * 内部类-缓存代码参数
     */
    public static class CacheCodeParam {
        /**
         * 文件路径（classPath）
         */
        private String filePath;
        /**
         * sheet名称
         */
        private String sheetName;
        /**
         * 代码所在列号（从0开始）
         */
        private int codeColumnNum;
        /**
         * 名称所在列号（从0开始）
         */
        private int nameColumnNum;

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getSheetName() {
            return sheetName;
        }

        public void setSheetName(String sheetName) {
            this.sheetName = sheetName;
        }

        public int getCodeColumnNum() {
            return codeColumnNum;
        }

        public void setCodeColumnNum(int codeColumnNum) {
            this.codeColumnNum = codeColumnNum;
        }

        public int getNameColumnNum() {
            return nameColumnNum;
        }

        public void setNameColumnNum(int nameColumnNum) {
            this.nameColumnNum = nameColumnNum;
        }

        @Override
        public String toString() {
            return "CacheCodeParam{" +
                    "filePath='" + filePath + '\'' +
                    ", sheetName='" + sheetName + '\'' +
                    ", codeColumnNum=" + codeColumnNum +
                    ", nameColumnNum=" + nameColumnNum +
                    '}';
        }
    }

    /**
     * 缓存代码文件(过滤掉代码列含汉字的行)
     * <br>注：默认第1列为代码列，第2列为代码对应的值
     *
     * @param filePath  文件路径（classPath）
     * @param sheetName sheet名称
     * @return Map
     * @throws IOException
     */
    public static Map<String, String> cacheCodeMapExcludeChineseRow(String filePath, String sheetName) throws IOException {
        CacheCodeParam codeParam = new CacheCodeParam();
        codeParam.setFilePath(filePath);
        codeParam.setSheetName(sheetName);
        codeParam.setCodeColumnNum(0);
        codeParam.setNameColumnNum(1);
        return cacheCodeMapExcludeChineseRow(codeParam);
    }

    /**
     * 缓存代码文件(过滤掉代码列含汉字的行)
     *
     * @param codeParam 代码参数
     * @return Map
     * @throws IOException
     */
    public static Map<String, String> cacheCodeMapExcludeChineseRow(CacheCodeParam codeParam) throws IOException {
        Map<String, String> codeMap = new HashMap<>(16);
        String filePath = codeParam.getFilePath();
        String sheetName = codeParam.getSheetName();

        Workbook workbook = ExcelUtils.readExcel(filePath);

        if (workbook != null) {
            Sheet stdCodeSheet = workbook.getSheet(sheetName);
            int totalRow = stdCodeSheet.getLastRowNum();

            int codeColumnNum = codeParam.getCodeColumnNum();
            int nameColumnNum = codeParam.getNameColumnNum();
            String code;
            String name;
            Row row;
            for (int i = 0; i <= totalRow; i++) {
                row = stdCodeSheet.getRow(i);
                code = StringUtils.deleteWhitespace(ExcelUtils.getStringValue(row.getCell(codeColumnNum)));
                name = StringUtils.deleteWhitespace(ExcelUtils.getStringValue(row.getCell(nameColumnNum)));

                // 过滤掉非code行(即含汉字的行)
                if (CheckUtils.includeChinese(code)) {
                    continue;
                }

                // 判断code是否已存在
                if (codeMap.containsKey(code)) {
                    abnormalCodeDataLogger.warn("文件：【{}】，sheet：【{}】，行号：【{}】，代码【{}】已存在！", filePath, sheetName, i + 1, code);
                    continue;
                }

                // 判断name是否为空
                if (StringUtils.isEmpty(name)) {
                    abnormalCodeDataLogger.warn("文件：【{}】，sheet：【{}】，行号：【{}】，代码【{}】对应的值为空！", filePath, sheetName, i + 1, code);
                }

                codeMap.put(code, name);
            }
        }

        return codeMap;
    }

}
