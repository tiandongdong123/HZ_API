package com.hanzhong.data.web.controller;

import com.hanzhong.data.web.constant.ResultCodeEnum;
import com.hanzhong.data.web.model.JsonResult;
import com.hanzhong.data.web.model.ListedEntBaseInfo;
import com.hanzhong.data.web.model.ListedEntBaseInfoQryParam;
import com.hanzhong.data.web.model.vo.ListedEntBaseInfoVO;
import com.hanzhong.data.web.service.EntListService;
import com.hanzhong.data.web.util.HttpUtils;
import com.hanzhong.data.web.util.JsonResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 企业上市信息
 *
 * @author yifei
 * @date 2019/4/1
 */
@Controller
@RequestMapping("/ent_list")
public class EntListController {
    private static final Logger logger = LoggerFactory.getLogger(EntListController.class);

    @Resource
    private EntListService entListService;

    /**
     * 获取上市企业基本信息
     *
     * @param request 请求
     * @return JsonResult
     */
    @PostMapping("/listed_ent_base_info")
    @ResponseBody
    public JsonResult getListedEntBaseInfo(HttpServletRequest request) {
        try {
            // 创建查询参数
            ListedEntBaseInfoQryParam qryParam = createListedEntBaseInfoQryParam(request);
            // 获取上市企业基本信息
            ListedEntBaseInfoVO baseInfoVO = getListedEntBaseInfo(qryParam);
            if (baseInfoVO != null) {
                return JsonResultUtils.build(ResultCodeEnum.SUCCESS, baseInfoVO);
            }
            logger.info("request：【{}】，未查询到上市企业基本信息", HttpUtils.getRequestParamJsonStr(request));
            return JsonResultUtils.build(ResultCodeEnum.NO_DATA, null);
        } catch (Exception e) {
            logger.error("request：【{}】，获取上市企业基本信息出现异常：", HttpUtils.getRequestParamJsonStr(request), e);
            return JsonResultUtils.build(ResultCodeEnum.FAIL, null);
        }
    }

    /**
     * 创建企业上市信息查询参数
     *
     * @param request 请求
     * @return ListedEntBaseInfoQryParam
     */
    private ListedEntBaseInfoQryParam createListedEntBaseInfoQryParam(HttpServletRequest request) {
        ListedEntBaseInfoQryParam qryParam = new ListedEntBaseInfoQryParam();
        qryParam.setEntName(request.getParameter("entName"));
        return qryParam;
    }

    /**
     * 获取上市企业基本信息
     *
     * @param qryParam 企业上市信息查询参数
     * @return ListedEntBaseInfoVO
     */
    private ListedEntBaseInfoVO getListedEntBaseInfo(ListedEntBaseInfoQryParam qryParam) {
        // 获取上市企业基本信息
        logger.info("BusinessDataService.getListedEntBaseInfo()的参数值：【{}】", qryParam);
        ListedEntBaseInfo listedEntBaseInfo = entListService.getListedEntBaseInfo(qryParam);
        logger.info("BusinessDataService.getListedEntBaseInfo()的返回值：【{}】", listedEntBaseInfo);

        // 转换成listedEntBaseInfoVO
        ListedEntBaseInfoVO listedEntBaseInfoVO = convertToListedEntBaseInfoVO(listedEntBaseInfo);
        logger.debug("ListedEntBaseInfo：【{}】，转换成ListedEntBaseInfoVO后结果值：【{}】", listedEntBaseInfo, listedEntBaseInfoVO);
        return listedEntBaseInfoVO;
    }

    /**
     * 转换成ListedEntBaseInfoVO
     *
     * @param listedEntBaseInfo 上市企业基本信息
     * @return ListedEntBaseInfoVO
     */
    private ListedEntBaseInfoVO convertToListedEntBaseInfoVO(ListedEntBaseInfo listedEntBaseInfo) {
        if (listedEntBaseInfo == null) {
            return null;
        }

        ListedEntBaseInfoVO listedEntBaseInfoVO = new ListedEntBaseInfoVO();
        // 证券代码
        listedEntBaseInfoVO.setStockCode(listedEntBaseInfo.getStockCode());
        // 股票简称
        listedEntBaseInfoVO.setStockShortName(listedEntBaseInfo.getStockShortName());
        // 企业中文全称
        listedEntBaseInfoVO.setCnName(listedEntBaseInfo.getCnName());
        // 企业中文简称
        listedEntBaseInfoVO.setCnShortName(listedEntBaseInfo.getCnShortName());
        // 企业英文全称
        listedEntBaseInfoVO.setEnName(listedEntBaseInfo.getEnName());
        // 企业英文简称
        listedEntBaseInfoVO.setEnShortName(listedEntBaseInfo.getEnShortName());
        // 成立日期，格式：yyyy-MM-dd
        listedEntBaseInfoVO.setEsDate(listedEntBaseInfo.getEsDate());
        // 注册资金
        listedEntBaseInfoVO.setRegCap(listedEntBaseInfo.getRegCap());
        // 币种
        listedEntBaseInfoVO.setCurrency(listedEntBaseInfo.getCurrency());
        // 注册地址
        listedEntBaseInfoVO.setRegAddress(listedEntBaseInfo.getRegAddress());
        // 办公地址
        listedEntBaseInfoVO.setOfficeAddress(listedEntBaseInfo.getOfficeAddress());
        // 公司网址
        listedEntBaseInfoVO.setWebSite(listedEntBaseInfo.getWebSite());
        // 公司简介
        listedEntBaseInfoVO.setComBrief(listedEntBaseInfo.getComBrief());
        // 主营业务
        listedEntBaseInfoVO.setPriBusiness(listedEntBaseInfo.getPriBusiness());
        // 员工人数
        listedEntBaseInfoVO.setStaffNum(listedEntBaseInfo.getStaffNum());
        // 信息发布日期，格式：yyyy-MM-dd
        listedEntBaseInfoVO.setDeclareDate(listedEntBaseInfo.getDeclareDate());
        // 上市日期，格式：yyyy-MM-dd
        listedEntBaseInfoVO.setListDate(listedEntBaseInfo.getListDate());
        // 上市状态
        listedEntBaseInfoVO.setListStatus(listedEntBaseInfo.getListStatus());
        // 证券交易所
        listedEntBaseInfoVO.setStockExchange(listedEntBaseInfo.getStockExchange());
        // 证券类别
        listedEntBaseInfoVO.setStockType(listedEntBaseInfo.getStockType());
        // 退市日期
        listedEntBaseInfoVO.setDelistingDate(listedEntBaseInfo.getDelistingDate());
        // ISIN代码
        listedEntBaseInfoVO.setIsin(listedEntBaseInfo.getIsin());
        return listedEntBaseInfoVO;
    }

}
