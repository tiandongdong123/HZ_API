package com.hanzhong.api.web.service;

import com.hanzhong.api.web.util.business.longdun.model.RegisterInfo;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/13 15:22 
 *  @Version  V1.0   
 */
public interface LdDataService {
    /**
     * 记录企业登记信息
     *
     * @param registerInfo 企业登记信息
     * @return int
     */
    int recordRegisterInfo(RegisterInfo registerInfo);
}
