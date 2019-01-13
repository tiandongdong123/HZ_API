package com.hanzhong.api.web.test.util;

import com.hanzhong.api.web.util.DateUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/13 19:10 
 *  @Version  V1.0   
 */
public class DateUtilsTest {

    private static final Logger logger = LoggerFactory.getLogger(DateUtilsTest.class);

    @Test
    public void parseTest() {
        String dateStr = "2019-01-12";
        logger.info("dateStr：【{}】，转换后：【{}】", dateStr, DateUtils.parse(dateStr, DateUtils.DEFAULT_DATE_FORMAT));
    }
}
