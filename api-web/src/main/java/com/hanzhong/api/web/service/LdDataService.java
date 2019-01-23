package com.hanzhong.api.web.service;

import com.hanzhong.api.web.model.bo.LdRegisterInfoQryBO;
import com.hanzhong.api.web.model.entity.slave.LdRegisterInfoEntity;
import com.hanzhong.api.web.util.business.longdun.model.RegisterInfo;

import java.util.List;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/13 15:22 
 *  @Version  V1.0   
 */
public interface LdDataService {
    /**
     * 获取企业登记信息
     *
     * @param qryBO 查询参数
     * @return List<LdRegisterInfoEntity>
     */
    List<LdRegisterInfoEntity> getRegisterInfoListByQryParam(LdRegisterInfoQryBO qryBO);

    /**
     * 记录企业登记信息
     *
     * @param registerInfo 企业登记信息
     * @return int
     */
    int recordRegisterInfo(RegisterInfo registerInfo);
}
