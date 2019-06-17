package com.hanzhong.data.web.controller;

import com.hanzhong.data.web.constant.ResultCodeEnum;
import com.hanzhong.data.web.model.*;
import com.hanzhong.data.web.model.vo.EntAdminPenaltyInfoVO;
import com.hanzhong.data.web.model.vo.EntOptExceptionInfoVO;
import com.hanzhong.data.web.model.vo.EntSeriousDishonestyInfoVO;
import com.hanzhong.data.web.service.EntPenaltyService;
import com.hanzhong.data.web.util.DateUtils;
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
 *  
 *  @Description 企业工商处罚信息
 *  @Author   luqs   
 *  @Date 2019/6/15 14:13 
 *  @Version  V1.0   
 */
@Controller
@RequestMapping("/ent_penalty")
public class EntPenaltyController {
    private static final Logger logger = LoggerFactory.getLogger(EntPenaltyController.class);

    @Resource
    private EntPenaltyService entPenaltyService;

    /**
     * 获取企业行政处罚信息
     *
     * @param request 请求
     * @return JsonResult
     */
    @PostMapping("/admin_penalty_info")
    @ResponseBody
    public JsonResult getEntAdminPenaltyInfoList(HttpServletRequest request) {
        try {
            // 创建查询参数
            EntAdminPenaltyInfoQryParam qryParam = createEntAdminPenaltyInfoQryParam(request);
            // 获取企业行政处罚信息
            List<EntAdminPenaltyInfoVO> infoVOList = getEntAdminPenaltyInfoList(qryParam);
            if (!infoVOList.isEmpty()) {
                return JsonResultUtils.build(ResultCodeEnum.SUCCESS, infoVOList);
            }
            logger.info("request：【{}】，未查询到企业行政处罚信息", HttpUtils.getRequestParamJsonStr(request));
            return JsonResultUtils.build(ResultCodeEnum.NO_DATA, null);
        } catch (Exception e) {
            logger.error("request：【{}】，获取企业行政处罚信息出现异常：", HttpUtils.getRequestParamJsonStr(request), e);
            return JsonResultUtils.build(ResultCodeEnum.FAIL, null);
        }
    }

    /**
     * 获取企业经营异常信息
     *
     * @param request 请求
     * @return JsonResult
     */
    @PostMapping("/operation_exception_info")
    @ResponseBody
    public JsonResult getEntOptExceptionInfoList(HttpServletRequest request) {
        try {
            // 创建查询参数
            EntOptExceptionInfoQryParam qryParam = createEntOptExceptionInfoQryParam(request);
            // 获取企业经营异常信息
            List<EntOptExceptionInfoVO> infoVOList = getEntOptExceptionInfoList(qryParam);
            if (!infoVOList.isEmpty()) {
                return JsonResultUtils.build(ResultCodeEnum.SUCCESS, infoVOList);
            }
            logger.info("request：【{}】，未查询到企业经营异常信息", HttpUtils.getRequestParamJsonStr(request));
            return JsonResultUtils.build(ResultCodeEnum.NO_DATA, null);
        } catch (Exception e) {
            logger.error("request：【{}】，获取企业经营异常信息出现异常：", HttpUtils.getRequestParamJsonStr(request), e);
            return JsonResultUtils.build(ResultCodeEnum.FAIL, null);
        }
    }

    /**
     * 获取企业严重违法失信企业（黑名单）信息
     *
     * @param request 请求
     * @return JsonResult
     */
    @PostMapping("/serious_dishonesty_info")
    @ResponseBody
    public JsonResult getEntSeriousDishonestyInfoList(HttpServletRequest request) {
        try {
            // 创建查询参数
            EntSeriousDishonestyInfoQryParam qryParam = createEntSeriousDishonestyInfoQryParam(request);
            // 获取企业严重违法失信企业（黑名单）信息
            List<EntSeriousDishonestyInfoVO> infoVOList = getEntSeriousDishonestyInfoList(qryParam);
            if (!infoVOList.isEmpty()) {
                return JsonResultUtils.build(ResultCodeEnum.SUCCESS, infoVOList);
            }
            logger.info("request：【{}】，未查询到企业严重违法失信企业（黑名单）信息", HttpUtils.getRequestParamJsonStr(request));
            return JsonResultUtils.build(ResultCodeEnum.NO_DATA, null);
        } catch (Exception e) {
            logger.error("request：【{}】，获取企业严重违法失信企业（黑名单）信息出现异常：", HttpUtils.getRequestParamJsonStr(request), e);
            return JsonResultUtils.build(ResultCodeEnum.FAIL, null);
        }
    }

    /**
     * 创建企业行政处罚信息查询参数
     *
     * @param request 请求
     * @return EntAdminPenaltyInfoQryParam
     */
    private EntAdminPenaltyInfoQryParam createEntAdminPenaltyInfoQryParam(HttpServletRequest request) {
        EntAdminPenaltyInfoQryParam qryParam = new EntAdminPenaltyInfoQryParam();
        qryParam.setEntName(request.getParameter("entName"));
        return qryParam;
    }

    /**
     * 获取企业行政处罚信息
     *
     * @param qryParam 企业行政处罚信息查询参数
     * @return List<EntAdminPenaltyInfoVO>
     */
    private List<EntAdminPenaltyInfoVO> getEntAdminPenaltyInfoList(EntAdminPenaltyInfoQryParam qryParam) {
        // 获取企业行政处罚信息
        logger.info("EntPenaltyService.getEntAdminPenaltyInfoList()的参数值：【{}】", qryParam);
        List<EntAdminPenaltyInfo> penaltyInfoList = entPenaltyService.getEntAdminPenaltyInfoList(qryParam);
        logger.info("EntPenaltyService.getEntAdminPenaltyInfoList()的返回值：【{}】", penaltyInfoList);

        // 转换成List<EntAdminPenaltyInfoVO>
        List<EntAdminPenaltyInfoVO> infoVOList = convertToAdminPenaltyInfoVOList(penaltyInfoList);
        logger.debug("List<EntAdminPenaltyInfo>：【{}】，转换成List<EntAdminPenaltyInfoVO>后结果值：【{}】", penaltyInfoList, infoVOList);
        return infoVOList;
    }

    /**
     * 转换成List<AdminPenaltyInfoVO>
     *
     * @param adminPenaltyInfoList 企业行政处罚信息集
     * @return List<AdminPenaltyInfoVO>
     */
    private List<EntAdminPenaltyInfoVO> convertToAdminPenaltyInfoVOList(List<EntAdminPenaltyInfo> adminPenaltyInfoList) {
        if (adminPenaltyInfoList == null || adminPenaltyInfoList.isEmpty()) {
            return Collections.emptyList();
        }

        List<EntAdminPenaltyInfoVO> penaltyInfoVOList = new ArrayList<>();
        for (EntAdminPenaltyInfo penaltyInfo : adminPenaltyInfoList) {
            EntAdminPenaltyInfoVO penaltyInfoVO = new EntAdminPenaltyInfoVO();
            // 行政处罚决定书文号
            penaltyInfoVO.setPenDecNo(penaltyInfo.getPenDecNo());
            // 违法行为类型
            penaltyInfoVO.setIllegalActType(penaltyInfo.getIllegalActType());
            // 行政处罚内容
            penaltyInfoVO.setPenResult(penaltyInfo.getPenResult());
            // 决定机关名称
            penaltyInfoVO.setCaseDep(penaltyInfo.getCaseDep());
            // 处罚决定日期
            penaltyInfoVO.setPenDecIssDate(DateUtils.dateFormatIfBlank(penaltyInfo.getPenDecIssDate(), ""));
            // 公示时间
            penaltyInfoVO.setPubDate(DateUtils.dateFormatIfBlank(penaltyInfo.getPubDate(), ""));
            penaltyInfoVOList.add(penaltyInfoVO);
        }
        return penaltyInfoVOList;
    }

    /**
     * 创建企业经营异常信息查询参数
     *
     * @param request 请求
     * @return EntOptExceptionInfoQryParam
     */
    private EntOptExceptionInfoQryParam createEntOptExceptionInfoQryParam(HttpServletRequest request) {
        EntOptExceptionInfoQryParam qryParam = new EntOptExceptionInfoQryParam();
        qryParam.setEntName(request.getParameter("entName"));
        return qryParam;
    }

    /**
     * 获取企业经营异常信息
     *
     * @param qryParam 企业经营异常信息查询参数
     * @return List<EntOptExceptionInfoVO>
     */
    private List<EntOptExceptionInfoVO> getEntOptExceptionInfoList(EntOptExceptionInfoQryParam qryParam) {
        // 获取企业经营异常信息
        logger.info("EntPenaltyService.getEntOptExceptionInfoList()的参数值：【{}】", qryParam);
        List<EntOptExceptionInfo> exceptionInfoList = entPenaltyService.getEntOptExceptionInfoList(qryParam);
        logger.info("EntPenaltyService.getEntOptExceptionInfoList()的返回值：【{}】", exceptionInfoList);

        // 转换成List<EntOptExceptionInfoVO>
        List<EntOptExceptionInfoVO> infoVOList = convertToEntOptExceptionInfoVOList(exceptionInfoList);
        logger.debug("List<EntOptExceptionInfo>：【{}】，转换成List<EntOptExceptionInfoVO>后结果值：【{}】", exceptionInfoList, infoVOList);
        return infoVOList;
    }

    /**
     * 转换成List<EntOptExceptionInfoVO>
     *
     * @param optExceptionInfoList 企业经营异常信息集
     * @return List<EntOptExceptionInfoVO>
     */
    private List<EntOptExceptionInfoVO> convertToEntOptExceptionInfoVOList(List<EntOptExceptionInfo> optExceptionInfoList) {
        if (optExceptionInfoList == null || optExceptionInfoList.isEmpty()) {
            return Collections.emptyList();
        }

        List<EntOptExceptionInfoVO> exceptionInfoVOList = new ArrayList<>();
        for (EntOptExceptionInfo exceptionInfo : optExceptionInfoList) {
            EntOptExceptionInfoVO exceptionInfoVO = new EntOptExceptionInfoVO();
            // 列入经营异常名录原因
            exceptionInfoVO.setInReason(exceptionInfo.getInReason());
            // 列入日期
            exceptionInfoVO.setInDate(DateUtils.dateFormatIfBlank(exceptionInfo.getInDate(),""));
            // 作出决定机关（列入）
            exceptionInfoVO.setInRegOrg(exceptionInfo.getInRegOrg());
            // 移出经营异常名录原因
            exceptionInfoVO.setOutReason(exceptionInfo.getOutReason());
            // 移出日期，格式：yyyy-MM-dd
            exceptionInfoVO.setOutDate(DateUtils.dateFormatIfBlank(exceptionInfo.getOutDate(), ""));
            // 作出决定机关（移出）
            exceptionInfoVO.setOutRegOrg(exceptionInfo.getOutRegOrg());
            // 是否移除
            exceptionInfoVO.setMoveFlag(exceptionInfo.getMoveFlag());
            exceptionInfoVOList.add(exceptionInfoVO);
        }
        return exceptionInfoVOList;
    }

    /**
     * 创建企业严重违法失信企业（黑名单）信息查询参数
     *
     * @param request 请求
     * @return EntSeriousDishonestyInfoQryParam
     */
    private EntSeriousDishonestyInfoQryParam createEntSeriousDishonestyInfoQryParam(HttpServletRequest request) {
        EntSeriousDishonestyInfoQryParam qryParam = new EntSeriousDishonestyInfoQryParam();
        qryParam.setEntName(request.getParameter("entName"));
        qryParam.setPageNum(Integer.parseInt(request.getParameter("pageNum")));
        qryParam.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
        return qryParam;
    }

    /**
     * 获取企业严重违法失信企业（黑名单）信息
     *
     * @param qryParam 企业严重违法失信企业（黑名单）信息查询参数
     * @return List<EntSeriousDishonestyInfo>
     */
    private List<EntSeriousDishonestyInfoVO> getEntSeriousDishonestyInfoList(EntSeriousDishonestyInfoQryParam qryParam) {
        // 获取企业严重违法失信企业（黑名单）信息
        logger.info("EntPenaltyService.getEntSeriousDishonestyInfoList()的参数值：【{}】", qryParam);
        List<EntSeriousDishonestyInfo> dishonestyInfoList = entPenaltyService.getEntSeriousDishonestyInfoList(qryParam);
        logger.info("EntPenaltyService.getEntSeriousDishonestyInfoList()的返回值：【{}】", dishonestyInfoList);

        // 转换成List<EntSeriousDishonestyInfoVO>
        List<EntSeriousDishonestyInfoVO> infoVOList = convertToEntSeriousDishonestyInfoVOList(dishonestyInfoList);
        logger.debug("List<EntSeriousDishonestyInfo>：【{}】，转换成List<EntSeriousDishonestyInfoVO>后结果值：【{}】", dishonestyInfoList, infoVOList);
        return infoVOList;
    }

    /**
     * 转换成List<EntSeriousDishonestyInfoVO>
     *
     * @param seriousDishonestyInfoList 企业严重违法失信企业（黑名单）信息集
     * @return List<EntSeriousDishonestyInfoVO>
     */
    private List<EntSeriousDishonestyInfoVO> convertToEntSeriousDishonestyInfoVOList(List<EntSeriousDishonestyInfo> seriousDishonestyInfoList) {
        if (seriousDishonestyInfoList == null || seriousDishonestyInfoList.isEmpty()) {
            return Collections.emptyList();
        }

        List<EntSeriousDishonestyInfoVO> dishonestyInfoVOList = new ArrayList<>();
        for (EntSeriousDishonestyInfo dishonestyInfo : seriousDishonestyInfoList) {
            EntSeriousDishonestyInfoVO dishonestyInfoVO = new EntSeriousDishonestyInfoVO();
            // 类别
            dishonestyInfoVO.setType(dishonestyInfo.getType());
            // 列入严重违法失信企业名单（黑名单）原因
            dishonestyInfoVO.setInReason(dishonestyInfo.getInReason());
            // 列入日期
            dishonestyInfoVO.setInDate(DateUtils.dateFormatIfBlank(dishonestyInfo.getInDate(),""));
            // 作出决定机关（列入）
            dishonestyInfoVO.setInRegOrg(dishonestyInfo.getInRegOrg());
            // 移出严重违法失信企业名单（黑名单）原因
            dishonestyInfoVO.setOutReason(dishonestyInfo.getOutReason());
            // 移出日期，格式：yyyy-MM-dd
            dishonestyInfoVO.setOutDate(DateUtils.dateFormatIfBlank(dishonestyInfo.getOutDate(), ""));
            // 作出决定机关（移出）
            dishonestyInfoVO.setOutRegOrg(dishonestyInfo.getOutRegOrg());
            dishonestyInfoVOList.add(dishonestyInfoVO);
        }
        return dishonestyInfoVOList;
    }
}
