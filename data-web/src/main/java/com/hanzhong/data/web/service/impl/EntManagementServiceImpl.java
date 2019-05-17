package com.hanzhong.data.web.service.impl;

import com.hanzhong.data.web.model.EntGoodsInfo;
import com.hanzhong.data.web.model.EntGoodsInfoQryParam;
import com.hanzhong.data.web.model.bo.EntGoodsInfoQryBO;
import com.hanzhong.data.web.service.EntManagementService;
import com.hanzhong.data.web.util.BusinessHandlingUtils;
import com.hanzhong.data.web.util.longdun.entmanageinfo.LdEntManageInfoApiUtils;
import com.hanzhong.data.web.util.longdun.entmanageinfo.model.GoodsInfo;
import com.hanzhong.data.web.util.longdun.entmanageinfo.model.GoodsInfoQryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yifei
 * @date 2019/4/1
 */
@Service("entManagementService")
public class EntManagementServiceImpl implements EntManagementService {

    private static final Logger logger = LoggerFactory.getLogger(EntManagementServiceImpl.class);

    /**
     * 获取企业商品信息
     *
     * @param qryParam 查询参数
     * @return List<GoodsInfo>
     */
    @Override
    public List<EntGoodsInfo> getGoodsInfoList(EntGoodsInfoQryParam qryParam) {
        // 创建企业商品信息查询参数
        GoodsInfoQryParam goodsInfoQryParam = createGoodsInfoQryParam(qryParam);

        // 获取企业商品信息
        logger.info("LdEntManageInfoApiUtils.getGoodsInfoList()的参数值：【{}】", goodsInfoQryParam);
        List<GoodsInfo> goodsInfoList = LdEntManageInfoApiUtils.getGoodsInfoList(goodsInfoQryParam);
        logger.info("LdEntManageInfoApiUtils.getGoodsInfoList()的返回值：【{}】", goodsInfoList);

        // 转换成List<EntGoodsInfo>
        List<EntGoodsInfo> entGoodsInfoList = convertToEntGoodsInfoList(goodsInfoList);
        logger.debug("List<GoodsInfo>：【{}】，转换成List<EntGoodsInfo>的结果值：【{}】", goodsInfoList, entGoodsInfoList);
        return entGoodsInfoList;
    }

    /**
     * 创建企业商品信息查询参数
     *
     * @param qryParam 企业商品信息查询参数
     * @return EntGoodsInfoQryBO
     */
    private EntGoodsInfoQryBO createEntGoodsInfoQryBO(EntGoodsInfoQryParam qryParam) {
        int pageNum = BusinessHandlingUtils.getDefaultPageNum(qryParam.getPageNum());
        int pageSize = BusinessHandlingUtils.getDefaultPageSize(qryParam.getPageSize());

        EntGoodsInfoQryBO qryBO = new EntGoodsInfoQryBO();
        qryBO.setEntName(qryParam.getEntName());
        qryBO.setStartIndex(BusinessHandlingUtils.getStartIndex(pageNum, pageSize));
        qryBO.setPageSize(pageSize);
        return qryBO;
    }

    /**
     * 创建企业商品信息查询参数
     *
     * @param qryParam 企业商品信息查询参数
     * @return GoodsInfoQryParam
     */
    private GoodsInfoQryParam createGoodsInfoQryParam(EntGoodsInfoQryParam qryParam) {
        GoodsInfoQryParam goodsInfoQryParam = new GoodsInfoQryParam();
        goodsInfoQryParam.setEntName(qryParam.getEntName());
        goodsInfoQryParam.setPageNo(BusinessHandlingUtils.getDefaultPageNum(qryParam.getPageNum()));
        goodsInfoQryParam.setPageSize(BusinessHandlingUtils.getDefaultPageSize(qryParam.getPageSize()));
        return goodsInfoQryParam;
    }

    /**
     * 转换成List<EntGoodsInfo>
     *
     * @param goodsInfoList 企业商品信息集
     * @return List<EntGoodsInfo>
     */
    private List<EntGoodsInfo> convertToEntGoodsInfoList(List<GoodsInfo> goodsInfoList) {
        if (goodsInfoList == null || goodsInfoList.isEmpty()) {
            return Collections.emptyList();
        }

        List<EntGoodsInfo> entGoodsInfoList = new ArrayList<>();
        for (GoodsInfo goodsInfo : goodsInfoList) {
            EntGoodsInfo entGoodsInfo = new EntGoodsInfo();
            // 商品条码
            entGoodsInfo.setCode(goodsInfo.getCode());
            // 名称
            entGoodsInfo.setName(goodsInfo.getName());
            // 规格型号
            entGoodsInfo.setModel(goodsInfo.getModel());
            // 描述
            entGoodsInfo.setDesc(goodsInfo.getDesc());
            // 商标
            entGoodsInfo.setBrand(goodsInfo.getBrand());
            // 发布厂家
            entGoodsInfo.setEntName(goodsInfo.getEntName());
            // 条码状态
            entGoodsInfo.setStatus(goodsInfo.getStatus());
            entGoodsInfoList.add(entGoodsInfo);
        }
        return entGoodsInfoList;
    }
}
