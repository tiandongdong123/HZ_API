package com.hanzhong.data.web.service;

import com.hanzhong.data.web.model.EntGoodsInfo;
import com.hanzhong.data.web.model.EntGoodsInfoQryParam;

import java.util.List;

/**
 * 企业经营信息
 *
 * @author yifei
 * @date 2019/4/1
 */
public interface EntManagementService {
    /**
     * 获取企业商品信息
     *
     * @param qryParam 查询参数
     * @return List<GoodsInfo>
     */
    List<EntGoodsInfo> getGoodsInfoList(EntGoodsInfoQryParam qryParam);
}
