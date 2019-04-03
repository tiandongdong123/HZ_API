package com.hanzhong.data.web.service.impl;

import com.hanzhong.data.web.model.ListedEntBaseInfo;
import com.hanzhong.data.web.model.ListedEntBaseInfoQryParam;
import com.hanzhong.data.web.model.bo.ListedEntBaseInfoQryBO;
import com.hanzhong.data.web.service.EntListService;
import com.hanzhong.data.web.util.longdun.entlistedinfo.LdEntListInfoApiUtils;
import com.hanzhong.data.web.util.longdun.entlistedinfo.model.ListedCompanyBaseInfo;
import com.hanzhong.data.web.util.longdun.entlistedinfo.model.ListedCompanyBaseInfoQryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author yifei
 * @date 2019/4/1
 */
@Service("entListService")
public class EntListServiceImpl implements EntListService {

    private static final Logger logger = LoggerFactory.getLogger(EntListServiceImpl.class);

    /**
     * 获取上市企业基本信息
     *
     * @param qryParam 查询参数
     * @return ListedEntBaseInfo
     */
    @Override
    public ListedEntBaseInfo getListedEntBaseInfo(ListedEntBaseInfoQryParam qryParam) {
        // 创建上市企业基本信息查询参数
        ListedCompanyBaseInfoQryParam companyBaseInfoQryParam = createListedCompanyBaseInfoQryParam(qryParam);

        // 获取企业对外投资信息
        logger.info("LdEntListInfoApiUtils.getListedCompanyBaseInfo()的参数值：【{}】", companyBaseInfoQryParam);
        ListedCompanyBaseInfo listedCompanyBaseInfo = LdEntListInfoApiUtils.getListedCompanyBaseInfo(companyBaseInfoQryParam);
        logger.info("LdEntListInfoApiUtils.getListedCompanyBaseInfo()的返回值：【{}】", listedCompanyBaseInfo);

        // 转换成ListedEntBaseInfo
        ListedEntBaseInfo listedEntBaseInfo = convertToListedEntBaseInfo(listedCompanyBaseInfo);
        logger.debug("ListedCompanyBaseInfo：【{}】，转换成ListedEntBaseInfo的结果值：【{}】", listedCompanyBaseInfo, listedEntBaseInfo);
        return listedEntBaseInfo;
    }

    /**
     * 创建上市企业基本信息查询参数
     *
     * @param qryParam 上市企业基本信息查询参数
     * @return ListedEntBaseInfoQryBO
     */
    private ListedEntBaseInfoQryBO createListedEntBaseInfoQryBO(ListedEntBaseInfoQryParam qryParam) {
        ListedEntBaseInfoQryBO qryBO = new ListedEntBaseInfoQryBO();
        qryBO.setEntName(qryParam.getEntName());
        return qryBO;
    }

    /**
     * 创建上市企业基本信息查询参数
     *
     * @param qryParam 上市企业基本信息查询参数
     * @return ListedCompanyBaseInfoQryParam
     */
    private ListedCompanyBaseInfoQryParam createListedCompanyBaseInfoQryParam(ListedEntBaseInfoQryParam qryParam) {
        ListedCompanyBaseInfoQryParam baseInfoQryParam = new ListedCompanyBaseInfoQryParam();
        baseInfoQryParam.setEntName(qryParam.getEntName());
        return baseInfoQryParam;
    }

    /**
     * 转换成ListedEntBaseInfo
     *
     * @param listedCompanyBaseInfo 上市企业基本信息
     * @return ListedEntBaseInfo
     */
    private ListedEntBaseInfo convertToListedEntBaseInfo(ListedCompanyBaseInfo listedCompanyBaseInfo) {
        if (listedCompanyBaseInfo == null) {
            return null;
        }

        ListedEntBaseInfo listedEntBaseInfo = new ListedEntBaseInfo();
        // 证券代码
        listedEntBaseInfo.setStockCode(listedCompanyBaseInfo.getStockCode());
        // 股票简称
        listedEntBaseInfo.setStockShortName(listedCompanyBaseInfo.getStockShortName());
        // 企业中文全称
        listedEntBaseInfo.setCnName(listedCompanyBaseInfo.getCnName());
        // 企业中文简称
        listedEntBaseInfo.setCnShortName(listedCompanyBaseInfo.getCnShortName());
        // 企业英文全称
        listedEntBaseInfo.setEnName(listedCompanyBaseInfo.getEnName());
        // 企业英文简称
        listedEntBaseInfo.setEnShortName(listedCompanyBaseInfo.getEnShortName());
        // 成立日期，格式：yyyy-MM-dd
        listedEntBaseInfo.setEsDate(listedCompanyBaseInfo.getEsDate());
        // 注册资金
        listedEntBaseInfo.setRegCap(listedCompanyBaseInfo.getRegCap());
        // 币种
        listedEntBaseInfo.setCurrency(listedCompanyBaseInfo.getCurrency());
        // 注册地址
        listedEntBaseInfo.setRegAddress(listedCompanyBaseInfo.getRegAddress());
        // 办公地址
        listedEntBaseInfo.setOfficeAddress(listedCompanyBaseInfo.getOfficeAddress());
        // 公司网址
        listedEntBaseInfo.setWebSite(listedCompanyBaseInfo.getWebSite());
        // 公司简介
        listedEntBaseInfo.setComBrief(listedCompanyBaseInfo.getComBrief());
        // 主营业务
        listedEntBaseInfo.setPriBusiness(listedCompanyBaseInfo.getPriBusiness());
        // 员工人数
        listedEntBaseInfo.setStaffNum(listedCompanyBaseInfo.getStaffNum());
        // 信息发布日期，格式：yyyy-MM-dd
        listedEntBaseInfo.setDeclareDate(listedCompanyBaseInfo.getDeclareDate());
        // 上市日期，格式：yyyy-MM-dd
        listedEntBaseInfo.setListDate(listedCompanyBaseInfo.getListDate());
        // 上市状态
        listedEntBaseInfo.setListStatus(listedCompanyBaseInfo.getListStatus());
        // 证券交易所
        listedEntBaseInfo.setStockExchange(listedCompanyBaseInfo.getStockExchange());
        // 证券类别
        listedEntBaseInfo.setStockType(listedCompanyBaseInfo.getStockType());
        // 退市日期
        listedEntBaseInfo.setDelistingDate(listedCompanyBaseInfo.getDelistingDate());
        // ISIN代码
        listedEntBaseInfo.setIsin(listedCompanyBaseInfo.getIsin());
        return listedEntBaseInfo;
    }
}
