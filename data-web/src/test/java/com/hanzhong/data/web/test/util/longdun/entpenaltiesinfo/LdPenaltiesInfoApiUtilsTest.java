package com.hanzhong.data.web.test.util.longdun.entpenaltiesinfo;

import com.hanzhong.data.web.util.longdun.entpenaltiesinfo.LdPenaltiesInfoApiUtils;
import com.hanzhong.data.web.util.longdun.entpenaltiesinfo.model.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author yifei
 * @date 2019/6/11
 */
public class LdPenaltiesInfoApiUtilsTest {
    private static final Logger logger = LoggerFactory.getLogger(LdPenaltiesInfoApiUtilsTest.class);

    /**
     * 获取企业行政处罚信息
     */
    @Test
    public void getLawAdminInfoListTest() {
        LawAdminInfoQryParam lawAdminInfoQryParam = new LawAdminInfoQryParam();
        lawAdminInfoQryParam.setEntName("上海争鸣物业管理有限公司");
        logger.info("LdPenaltiesInfoApiUtils.getLawAdminInfoList()的参数值：【{}】", lawAdminInfoQryParam);
        List<LawAdminInfo> lawAdminInfoList = LdPenaltiesInfoApiUtils.getLawAdminInfoList(lawAdminInfoQryParam);
        logger.info("LdPenaltiesInfoApiUtils.getLawAdminInfoList()的返回值：【{}】", lawAdminInfoList);
    }

    /**
     * 获取企业经营异常信息
     */
    @Test
    public void getOperatingExceptionRotaListTest() {
        OperatExcepRotaQryParam qryParam = new OperatExcepRotaQryParam();
        qryParam.setEntName("福建省雨锋建材有限公司");
        logger.info("LdPenaltiesInfoApiUtils.getOperatingExceptionRotaList()的参数值：【{}】", qryParam);
        List<OperatExcepRotaInfo> infoList = LdPenaltiesInfoApiUtils.getOperatingExceptionRotaList(qryParam);
        logger.info("LdPenaltiesInfoApiUtils.getOperatingExceptionRotaList()的返回值：【{}】", infoList);
    }

    /**
     * 获取企业严重违法失信企业（黑名单）信息
     */
    @Test
    public void getSeriousDishonestyInfoListTest() {
        SeriousDishonestyInfoQryParam qryParam = new SeriousDishonestyInfoQryParam();
        qryParam.setEntName("昆明昆华钢板贸易有限公司");
        qryParam.setPageNo(1);
        qryParam.setPageSize(2);
        logger.info("LdPenaltiesInfoApiUtils.getSeriousDishonestyInfoList()的参数值：【{}】", qryParam);
        List<SeriousDishonestyInfo> infoList = LdPenaltiesInfoApiUtils.getSeriousDishonestyInfoList(qryParam);
        logger.info("LdPenaltiesInfoApiUtils.getSeriousDishonestyInfoList()的返回值：【{}】", infoList);
    }
}
