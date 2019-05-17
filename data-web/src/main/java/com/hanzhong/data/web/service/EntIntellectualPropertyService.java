package com.hanzhong.data.web.service;

import com.hanzhong.data.web.model.EntPatentInfoQryParam;
import com.hanzhong.data.web.model.EntPatentInfoResult;
import com.hanzhong.data.web.model.EntStandardInfoQryParam;
import com.hanzhong.data.web.model.EntStandardInfoResult;

/**
 * 企业知识产权
 *
 * @author yifei
 * @date 2019/3/30
 */
public interface EntIntellectualPropertyService {

    /**
     * 分页获取企业专利信息
     *
     * @param qryParam 查询参数
     * @return EntPatentInfoResult
     */
    EntPatentInfoResult getEntPatentInfoByPage(EntPatentInfoQryParam qryParam);

    /**
     * 分页获取企业标准信息
     *
     * @param qryParam 查询参数
     * @return EntStandardInfoResult
     */
    EntStandardInfoResult getEntStandardInfoByPage(EntStandardInfoQryParam qryParam);
}
