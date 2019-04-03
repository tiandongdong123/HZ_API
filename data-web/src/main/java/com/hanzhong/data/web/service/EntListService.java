package com.hanzhong.data.web.service;

import com.hanzhong.data.web.model.ListedEntBaseInfo;
import com.hanzhong.data.web.model.ListedEntBaseInfoQryParam;

/**
 * 企业上市信息
 *
 * @author yifei
 * @date 2019/4/1
 */
public interface EntListService {

    /**
     * 获取上市企业基本信息
     *
     * @param qryParam 查询参数
     * @return ListedEntBaseInfo
     */
    ListedEntBaseInfo getListedEntBaseInfo(ListedEntBaseInfoQryParam qryParam);

}
