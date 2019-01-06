package com.hanzhong.api.web.dao.master;

import com.hanzhong.api.web.model.bo.IpWhiteListQryBO;
import com.hanzhong.api.web.model.entity.master.TblIpWhiteListEntity;

import java.util.List;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/4 19:44 
 *  @Version  V1.0   
 */
public interface TblIpWhiteListDao {
    /**
     * 获取企业信息
     *
     * @param ipWhiteListQryBO ip白名单查询参数
     * @return List<TblIpWhiteListEntity>
     */
    List<TblIpWhiteListEntity> getIpWhiteList(IpWhiteListQryBO ipWhiteListQryBO);
}
