package com.hanzhong.data.web.test.util;

import com.hanzhong.data.web.util.CheckUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/4/3 13:15 
 *  @Version  V1.0   
 */
public class CheckUtilsTest {

    private static final Logger logger = LoggerFactory.getLogger(CheckUtilsTest.class);

    @Test
    public void isAllDateTimeFormatTest() {
        String timeStr1 = "2019-04-03 12:00:00";
        String timeStr2 = "2019-04-03 12:59:00";
        logger.info("timeStr1：【{}】，timeStr2：【{}】，CheckUtils.isAllDateTimeFormat()的返回值：【{}】", timeStr1, timeStr2, CheckUtils.isAllDateTimeFormat(timeStr1, timeStr2));
    }
}
