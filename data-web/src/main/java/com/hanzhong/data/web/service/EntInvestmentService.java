package com.hanzhong.data.web.service;

import com.hanzhong.data.web.model.EntInvestAbroadInfo;
import com.hanzhong.data.web.model.EntInvestAbroadInfoQryParam;

import java.util.List;

/**
 * 企业投资信息
 *
 * @author yifei
 * @date 2019/4/1
 */
public interface EntInvestmentService {

    /**
     * 获取企业对外投资信息
     *
     * @param qryParam 查询参数
     * @return List<InvestAbroadInfo>
     */
    List<EntInvestAbroadInfo> getInvestmentAbroadInfoList(EntInvestAbroadInfoQryParam qryParam);

}
