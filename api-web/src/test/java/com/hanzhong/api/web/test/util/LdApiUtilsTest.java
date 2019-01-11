package com.hanzhong.api.web.test.util;

import com.hanzhong.api.web.util.business.longdun.LdApiUtils;
import com.hanzhong.api.web.util.business.longdun.constant.CmnConstant;
import com.hanzhong.api.web.util.business.longdun.model.ApiParam;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yifei
 * @date 2019/1/9
 */
public class LdApiUtilsTest {

    private static final Logger logger = LoggerFactory.getLogger(LdApiUtilsTest.class);

    @Test
    public void getResultStrByApiParamTest() {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("entName", "北京东方黑森林贸易有限公司");

        ApiParam apiParam = new ApiParam();
        apiParam.setUid(LdApiUtils.U_ID);
        apiParam.setService(CmnConstant.GET_REGISTER_INFO_METHOD);
        apiParam.setParams(paramMap);

        logger.info("getResultStrByApiParam()返回值：【{}】", LdApiUtils.getResultStrByApiParam(apiParam));
    }
}
