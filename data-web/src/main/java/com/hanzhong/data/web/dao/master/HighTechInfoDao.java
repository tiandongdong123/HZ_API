package com.hanzhong.data.web.dao.master;

import com.hanzhong.data.web.model.bo.HighTechEntQryBO;
import com.hanzhong.data.web.model.entity.master.HighTechEntInfoEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yifei
 * @date 2019/4/13
 */
@Repository
public interface HighTechInfoDao {

    /**
     * 获取高新企业信息
     *
     * @param qryBO 查询参数
     * @return List<HighTechEntInfoEntity>
     */
    List<HighTechEntInfoEntity> getHighTechInfoList(HighTechEntQryBO qryBO);

    /**
     * 更新高新企业信息
     *
     * @param highTechEntInfoEntity 高新企业信息
     * @return int
     */
    int updateHighTechInfo(HighTechEntInfoEntity highTechEntInfoEntity);

    /**
     * 插入高新企业信息
     *
     * @param highTechEntInfoEntity 高新企业信息
     * @return int
     */
    int insertHighTechInfo(HighTechEntInfoEntity highTechEntInfoEntity);
}
