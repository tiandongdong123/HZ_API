package com.hanzhong.data.web.service;

import com.hanzhong.data.web.model.ChangeDataPackageInfo;
import com.hanzhong.data.web.model.ChangeDataPackageInfoQryParam;

import java.util.List;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/3/11 18:24 
 *  @Version  V1.0   
 */
public interface LdDataService {

    /**
     * 获取变更数据包
     *
     * @param qryParam 查询参数
     * @return List<ChangeDataPackageInfo>
     */
    List<ChangeDataPackageInfo> getChangeDataPackageList(ChangeDataPackageInfoQryParam qryParam);

    /**
     * 变更数据包回馈状态
     *
     * @param dataPkgId 数据包id
     * @return boolean true: 变更成功
     */
    boolean monitorDataPackageFinish(String dataPkgId);
}
