package com.hanzhong.data.web.service;

import com.hanzhong.data.web.model.*;

import java.util.List;

/**
 *  
 *  @Description 企业工商处罚信息
 *  @Author   luqs   
 *  @Date 2019/6/15 14:36 
 *  @Version  V1.0   
 */
public interface EntPenaltyService {

    /**
     * 获取企业行政处罚信息
     *
     * @param qryParam 查询参数
     * @return List<EntAdminPenaltyInfo>
     */
    List<EntAdminPenaltyInfo> getEntAdminPenaltyInfoList(EntAdminPenaltyInfoQryParam qryParam);

    /**
     * 获取企业经营异常信息
     *
     * @param qryParam 查询参数
     * @return List<EntOptExceptionInfo>
     */
    List<EntOptExceptionInfo> getEntOptExceptionInfoList(EntOptExceptionInfoQryParam qryParam);

    /**
     * 获取企业严重违法失信企业（黑名单）信息
     *
     * @param qryParam 查询参数
     * @return List<EntSeriousDishonestyInfo>
     */
    List<EntSeriousDishonestyInfo> getEntSeriousDishonestyInfoList(EntSeriousDishonestyInfoQryParam qryParam);
}
