package com.hanzhong.data.web.dao.master;


import com.hanzhong.data.web.model.bo.IpWhiteListQryBO;
import com.hanzhong.data.web.model.entity.master.TblIpWhiteListEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/4 19:44 
 *  @Version  V1.0   
 */
@Repository("tblIpWhiteListDao")
public interface TblIpWhiteListDao {
    /**
     * 获取企业信息
     *
     * @param ipWhiteListQryBO ip白名单查询参数
     * @return List<TblIpWhiteListEntity>
     */
    List<TblIpWhiteListEntity> getIpWhiteList(IpWhiteListQryBO ipWhiteListQryBO);
}
