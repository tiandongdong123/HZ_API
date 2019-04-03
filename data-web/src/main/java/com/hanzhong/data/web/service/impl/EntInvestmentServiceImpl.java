package com.hanzhong.data.web.service.impl;

import com.hanzhong.data.web.model.EntInvestAbroadInfo;
import com.hanzhong.data.web.model.EntInvestAbroadInfoQryParam;
import com.hanzhong.data.web.model.bo.EntInvAbInfoQryBO;
import com.hanzhong.data.web.service.EntInvestmentService;
import com.hanzhong.data.web.util.BusinessHandlingUtils;
import com.hanzhong.data.web.util.longdun.entinvestinfo.LdEntInvestInfoApiUtils;
import com.hanzhong.data.web.util.longdun.entinvestinfo.model.InvAbroadInfo;
import com.hanzhong.data.web.util.longdun.entinvestinfo.model.InvAbroadInfoQryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yifei
 * @date 2019/4/1
 */
@Service("entInvestmentService")
public class EntInvestmentServiceImpl implements EntInvestmentService {

    private static final Logger logger = LoggerFactory.getLogger(EntInvestmentServiceImpl.class);

    /**
     * 获取企业对外投资信息
     *
     * @param qryParam 查询参数
     * @return List<InvestAbroadInfo>
     */
    @Override
    public List<EntInvestAbroadInfo> getInvestmentAbroadInfoList(EntInvestAbroadInfoQryParam qryParam) {
        // 创建企业对外投资信息查询参数
        InvAbroadInfoQryParam invAbroadInfoQryParam = createInvAbroadInfoQryParam(qryParam);

        // 获取企业对外投资信息
        logger.info("LdEntInvestInfoApiUtils.getInvestmentAbroadInfoList()的参数值：【{}】", invAbroadInfoQryParam);
        List<InvAbroadInfo> invAbroadInfoList = LdEntInvestInfoApiUtils.getInvestmentAbroadInfoList(invAbroadInfoQryParam);
        logger.info("LdEntInvestInfoApiUtils.getInvestmentAbroadInfoList()的返回值：【{}】", invAbroadInfoList);

        // 转换成List<EntInvestAbroadInfo>
        List<EntInvestAbroadInfo> entInvestAbroadInfoList = convertToEntInvestAbroadInfoList(invAbroadInfoList);
        logger.debug("List<InvAbroadInfo>：【{}】，转换成List<EntInvestAbroadInfo>的结果值：【{}】", invAbroadInfoList, entInvestAbroadInfoList);
        return entInvestAbroadInfoList;
    }

    /**
     * 创建企业对外投资信息查询参数
     *
     * @param qryParam 企业对外投资信息查询参数
     * @return EntInvAbInfoQryBO
     */
    private EntInvAbInfoQryBO createEntInvAbInfoQryBO(EntInvestAbroadInfoQryParam qryParam) {
        int pageNum = BusinessHandlingUtils.getDefaultPageNum(qryParam.getPageNum());
        int pageSize = BusinessHandlingUtils.getDefaultPageSize(qryParam.getPageSize());

        EntInvAbInfoQryBO qryBO = new EntInvAbInfoQryBO();
        qryBO.setEntName(qryParam.getEntName());
        qryBO.setStartIndex(BusinessHandlingUtils.getStartIndex(pageNum, pageSize));
        qryBO.setPageSize(pageSize);
        return qryBO;
    }

    /**
     * 创建企业对外投资信息查询参数
     *
     * @param qryParam 企业对外投资信息查询参数
     * @return InvAbroadInfoQryParam
     */
    private InvAbroadInfoQryParam createInvAbroadInfoQryParam(EntInvestAbroadInfoQryParam qryParam) {
        InvAbroadInfoQryParam invAbroadInfoQryParam = new InvAbroadInfoQryParam();
        invAbroadInfoQryParam.setEntName(qryParam.getEntName());
        invAbroadInfoQryParam.setPageNo(BusinessHandlingUtils.getDefaultPageNum(qryParam.getPageNum()));
        invAbroadInfoQryParam.setPageSize(BusinessHandlingUtils.getDefaultPageSize(qryParam.getPageSize()));
        return invAbroadInfoQryParam;
    }

    /**
     * 转换成List<EntInvestAbroadInfo>
     *
     * @param invAbroadInfoList 企业对外投资信息集
     * @return List<EntInvestAbroadInfo>
     */
    private List<EntInvestAbroadInfo> convertToEntInvestAbroadInfoList(List<InvAbroadInfo> invAbroadInfoList) {
        if (invAbroadInfoList == null || invAbroadInfoList.isEmpty()) {
            return Collections.emptyList();
        }

        List<EntInvestAbroadInfo> entInvestAbroadInfoList = new ArrayList<>();
        for (InvAbroadInfo invAbroadInfo : invAbroadInfoList) {
            EntInvestAbroadInfo entInvestAbroadInfo = new EntInvestAbroadInfo();
            // 企业名称
            entInvestAbroadInfo.setEntName(invAbroadInfo.getEntName());
            // 统一社会信用代码
            entInvestAbroadInfo.setUsCreditCode(invAbroadInfo.getUsCreditCode());
            // 成立日期，格式：yyyy-MM-dd
            entInvestAbroadInfo.setEsDate(invAbroadInfo.getEsDate());
            // 企业状态
            entInvestAbroadInfo.setEntStatus(invAbroadInfo.getEntStatus());
            // 注册资金
            entInvestAbroadInfo.setRegCap(invAbroadInfo.getRegCap());
            // 认缴出资额
            entInvestAbroadInfo.setSubConAmount(invAbroadInfo.getSubConAmount());
            // 出资币种
            entInvestAbroadInfo.setConCur(invAbroadInfo.getConCur());
            // 出资比例
            entInvestAbroadInfo.setConRatIo(invAbroadInfo.getConRatIo());
            // 出资时间，格式：yyyy-MM-dd
            entInvestAbroadInfo.setConDate(invAbroadInfo.getConDate());
            entInvestAbroadInfoList.add(entInvestAbroadInfo);
        }
        return entInvestAbroadInfoList;
    }
}
