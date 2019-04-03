package com.hanzhong.data.web.dao.master;

import com.hanzhong.data.web.model.bo.EntStandardInfoQryBO;
import com.hanzhong.data.web.model.bo.StandardInfoQryBO;
import com.hanzhong.data.web.model.entity.master.TblStandardEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 标准信息
 *
 * @author yifei
 * @date 2019/3/27
 */
@Repository("tblStandardDao")
public interface TblStandardDao {
    /**
     * 获取标准信息
     *
     * @param qryBO 查询参数
     * @return int
     */
    int getStandardInfoCount(StandardInfoQryBO qryBO);

    /**
     * 获取标准信息
     *
     * @param qryBO 查询参数
     * @return List<TblStandardEntity>
     */
    List<TblStandardEntity> getStandardInfoList(StandardInfoQryBO qryBO);

    /**
     * 获取企业标准信息
     *
     * @param qryBO 查询参数
     * @return int
     */
    int getEntStandardInfoCount(EntStandardInfoQryBO qryBO);

    /**
     * 获取企业标准信息
     *
     * @param qryBO 查询参数
     * @return List<TblStandardEntity>
     */
    List<TblStandardEntity> getEntStandardInfoList(EntStandardInfoQryBO qryBO);
}
