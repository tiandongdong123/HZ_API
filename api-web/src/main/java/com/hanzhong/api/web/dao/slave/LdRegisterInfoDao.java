package com.hanzhong.api.web.dao.slave;

import com.hanzhong.api.web.model.entity.slave.LdRegisterInfoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/13 15:19 
 *  @Version  V1.0   
 */
public interface LdRegisterInfoDao {
    /**
     * 获取企业登记信息
     *
     * @param entName 企业名称
     * @return int
     */
    List<LdRegisterInfoEntity> getRegisterInfoListByQryParam(@Param("entName") String entName);

    /**
     * 添加企业登记信息
     *
     * @param registerInfoEntity 企业登记信息
     * @return int
     */
    int addRegisterInfo(LdRegisterInfoEntity registerInfoEntity);

    /**
     * 更新企业登记信息
     *
     * @param registerInfoEntity 企业登记信息
     * @return int
     */
    int updateRegisterInfo(LdRegisterInfoEntity registerInfoEntity);
}
