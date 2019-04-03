package com.hanzhong.data.web.controller;

import com.hanzhong.data.web.constant.ResultCodeEnum;
import com.hanzhong.data.web.model.EntInvestAbroadInfo;
import com.hanzhong.data.web.model.EntInvestAbroadInfoQryParam;
import com.hanzhong.data.web.model.JsonResult;
import com.hanzhong.data.web.model.vo.EntInvestAbroadInfoVO;
import com.hanzhong.data.web.service.EntInvestmentService;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 企业投资信息
 *
 * @author yifei
 * @date 2019/4/1
 */
@Controller
@RequestMapping("/ent_invest")
public class EntInvestmentController {

    private static final Logger logger = LoggerFactory.getLogger(EntInvestmentController.class);

    @Resource
    private EntInvestmentService entInvestmentService;

    /**
     * 获取企业对外投资信息
     *
     * @param request 请求
     * @return JsonResult
     */
    @PostMapping("/ent_investment_abroad_info")
    @ResponseBody
    public JsonResult getEntInvestmentAbroadInfo(HttpServletRequest request) {
        try {
            // 创建查询参数
            EntInvestAbroadInfoQryParam qryParam = createEntInvestAbroadInfoQryParam(request);
            // 获取企业对外投资信息
            List<EntInvestAbroadInfoVO> investAbroadInfoVOList = getEntInvestAbroadInfoList(qryParam);
            if (!investAbroadInfoVOList.isEmpty()) {
                return JsonResultUtils.build(ResultCodeEnum.SUCCESS, investAbroadInfoVOList);
            }
            logger.info("request：【{}】，未查询到企业对外投资信息", HttpUtils.getRequestParamJsonStr(request));
            return JsonResultUtils.build(ResultCodeEnum.NO_DATA, null);
        } catch (Exception e) {
            logger.error("request：【{}】，获取企业对外投资信息出现异常：", HttpUtils.getRequestParamJsonStr(request), e);
            return JsonResultUtils.build(ResultCodeEnum.FAIL, null);
        }
    }

    /**
     * 创建企业对外投资信息查询参数
     *
     * @param request 请求
     * @return EntInvestAbroadInfoQryParam
     */
    private EntInvestAbroadInfoQryParam createEntInvestAbroadInfoQryParam(HttpServletRequest request) {
        EntInvestAbroadInfoQryParam qryParam = new EntInvestAbroadInfoQryParam();
        qryParam.setEntName(request.getParameter("entName"));
        qryParam.setPageNum(Integer.parseInt(request.getParameter("pageNum")));
        qryParam.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
        return qryParam;
    }

    /**
     * 获取企业对外投资信息
     *
     * @param qryParam 企业对外投资信息查询参数
     * @return 若查询不到，则返回null
     */
    private List<EntInvestAbroadInfoVO> getEntInvestAbroadInfoList(EntInvestAbroadInfoQryParam qryParam) {
        // 获取企业对外投资信息
        logger.info("BusinessDataService.getInvestmentAbroadInfoList()的参数值：【{}】", qryParam);
        List<EntInvestAbroadInfo> entInvestAbroadInfoList = entInvestmentService.getInvestmentAbroadInfoList(qryParam);
        logger.info("BusinessDataService.getInvestmentAbroadInfoList()的返回值：【{}】", entInvestAbroadInfoList);

        // 转换成EntInvestAbroadInfoVO
        List<EntInvestAbroadInfoVO> infoVOList = convertToEntInvestAbroadInfoVOList(entInvestAbroadInfoList);
        logger.debug("List<EntInvestAbroadInfo>：【{}】，转换成List<EntInvestAbroadInfoVO>后结果值：【{}】", entInvestAbroadInfoList, infoVOList);
        return infoVOList;
    }

    /**
     * 转换成List<EntInvestAbroadInfoVO>
     *
     * @param entInvestAbroadInfoList 企业对外投资信息集
     * @return List<EntInvestAbroadInfoVO>
     */
    private List<EntInvestAbroadInfoVO> convertToEntInvestAbroadInfoVOList(List<EntInvestAbroadInfo> entInvestAbroadInfoList) {
        if (entInvestAbroadInfoList == null || entInvestAbroadInfoList.isEmpty()) {
            return Collections.emptyList();
        }

        List<EntInvestAbroadInfoVO> entInvestAbroadInfoVOList = new ArrayList<>();
        for (EntInvestAbroadInfo investAbroadInfo : entInvestAbroadInfoList) {
            EntInvestAbroadInfoVO investAbroadInfoVO = new EntInvestAbroadInfoVO();
            // 企业名称
            investAbroadInfoVO.setEntName(investAbroadInfo.getEntName());
            // 统一社会信用代码
            investAbroadInfoVO.setUsCreditCode(investAbroadInfo.getUsCreditCode());
            // 成立日期，格式：yyyy-MM-dd
            investAbroadInfoVO.setEsDate(investAbroadInfo.getEsDate());
            // 企业状态
            investAbroadInfoVO.setEntStatus(investAbroadInfo.getEntStatus());
            // 注册资金
            investAbroadInfoVO.setRegCap(investAbroadInfo.getRegCap());
            // 认缴出资额
            investAbroadInfoVO.setSubConAmount(investAbroadInfo.getSubConAmount());
            // 出资币种
            investAbroadInfoVO.setConCur(investAbroadInfo.getConCur());
            // 出资比例
            investAbroadInfoVO.setConRatIo(investAbroadInfo.getConRatIo());
            // 出资时间，格式：yyyy-MM-dd
            investAbroadInfoVO.setConDate(investAbroadInfo.getConDate());
            entInvestAbroadInfoVOList.add(investAbroadInfoVO);
        }
        return entInvestAbroadInfoVOList;
    }

}
