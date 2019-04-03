package com.hanzhong.data.web.controller;

import com.hanzhong.data.web.constant.PatentQryFieldEnum;
import com.hanzhong.data.web.constant.ResultCodeEnum;
import com.hanzhong.data.web.constant.StandardQryFieldEnum;
import com.hanzhong.data.web.model.*;
import com.hanzhong.data.web.model.vo.EntPatentInfoResultVO;
import com.hanzhong.data.web.model.vo.EntPatentInfoVO;
import com.hanzhong.data.web.model.vo.EntStandardInfoResultVO;
import com.hanzhong.data.web.model.vo.EntStandardInfoVO;
import com.hanzhong.data.web.service.EntIntellectualPropertyService;
import com.hanzhong.data.web.util.BusinessHandlingUtils;
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
 * 企业知识产权信息
 *
 * @author yifei
 * @date 2019/3/30
 */
@Controller
@RequestMapping("/ent_int_property")
public class EntIntellectualPropertyController {

    private static final Logger logger = LoggerFactory.getLogger(EntIntellectualPropertyController.class);

    @Resource
    private EntIntellectualPropertyService entIntellectualPropertyService;

    /**
     * 获取企业专利信息
     *
     * @param request 请求
     * @return JsonResult
     */
    @PostMapping("/patent_info")
    @ResponseBody
    public JsonResult getEntPatenInfo(HttpServletRequest request) {
        // 查询字段参数
        String qryField = BusinessHandlingUtils.getQryFieldParamValue(request);
        try {
            // 创建查询参数
            EntPatentInfoQryParam qryParam = createEntPatentInfoQryParam(request, qryField);
            // 获取企业专利信息
            EntPatentInfoResultVO patentInfoResultVO = getEntPatentInfoResult(qryParam);
            if (patentInfoResultVO != null) {
                return JsonResultUtils.build(ResultCodeEnum.SUCCESS, patentInfoResultVO);
            }
            logger.info("request：【{}】，未查询到企业专利信息", HttpUtils.getRequestParamJsonStr(request));
            return JsonResultUtils.build(ResultCodeEnum.NO_DATA, null);
        } catch (Exception e) {
            logger.error("request：【{}】，获取企业专利信息出现异常：", HttpUtils.getRequestParamJsonStr(request), e);
            return JsonResultUtils.build(ResultCodeEnum.FAIL, null);
        }
    }

    /**
     * 获取企业标准信息
     *
     * @param request 请求
     * @return JsonResult
     */
    @PostMapping("/standard_info")
    @ResponseBody
    public JsonResult getEntStandardInfo(HttpServletRequest request) {
        // 查询字段参数
        String qryField = BusinessHandlingUtils.getQryFieldParamValue(request);
        try {
            // 创建查询参数
            EntStandardInfoQryParam qryParam = createEntStandardInfoQryParam(request, qryField);
            // 获取企业标准信息
            EntStandardInfoResultVO standardInfoResultVO = getEntStandardInfoResult(qryParam);
            if (standardInfoResultVO != null) {
                return JsonResultUtils.build(ResultCodeEnum.SUCCESS, standardInfoResultVO);
            }
            logger.info("request：【{}】，未查询到企业标准信息", HttpUtils.getRequestParamJsonStr(request));
            return JsonResultUtils.build(ResultCodeEnum.NO_DATA, null);
        } catch (Exception e) {
            logger.error("request：【{}】，获取企业标准信息出现异常：", HttpUtils.getRequestParamJsonStr(request), e);
            return JsonResultUtils.build(ResultCodeEnum.FAIL, null);
        }
    }

    /**
     * 创建企业专利信息查询参数
     *
     * @param request       请求
     * @param qryFieldParam 查询字段参数
     * @return EntPatentInfoQryParam
     */
    private EntPatentInfoQryParam createEntPatentInfoQryParam(HttpServletRequest request, String qryFieldParam) {
        EntPatentInfoQryParam qryParam = new EntPatentInfoQryParam();
        // 页码
        qryParam.setPageNum(BusinessHandlingUtils.getDefaultPageNum(request));
        // 每页数量
        qryParam.setPageSize(BusinessHandlingUtils.getDefaultPageSize(request));
        if (PatentQryFieldEnum.ENT_NAME.getValue().equals(qryFieldParam)) {
            // 企业名称
            qryParam.setEntName(request.getParameter(qryFieldParam));
            // 专利检索字段
            qryParam.setPatentQryFieldEnum(PatentQryFieldEnum.ENT_NAME);
        } else if (PatentQryFieldEnum.UNIFIED_SOCIAL_CREDIT_CODE.getValue().equals(qryFieldParam)) {
            // 统一社会信用代码
            qryParam.setUsCreditCode(request.getParameter(qryFieldParam));
            // 专利检索字段
            qryParam.setPatentQryFieldEnum(PatentQryFieldEnum.UNIFIED_SOCIAL_CREDIT_CODE);
        } else if (PatentQryFieldEnum.PATENT_ID.getValue().equals(qryFieldParam)) {
            // 申请号
            qryParam.setPatentId(request.getParameter(qryFieldParam));
            // 专利检索字段
            qryParam.setPatentQryFieldEnum(PatentQryFieldEnum.PATENT_ID);
        } else {
            logger.error("企业专利信息查询qryFieldParam的参数值【{}】不存在", qryFieldParam);
            throw new IllegalArgumentException("企业专利信息查询qryFieldParam的参数值【" + qryFieldParam + "】不存在");
        }
        return qryParam;
    }

    /**
     * 获取企业专利信息
     *
     * @param qryParam 企业专利信息查询参数
     * @return EntPatentInfoResultVO 若查询不到，则返回null
     */
    private EntPatentInfoResultVO getEntPatentInfoResult(EntPatentInfoQryParam qryParam) {
        logger.info("EntIntellectualPropertyService.getEntPatentInfoByPage()的参数值：【{}】", qryParam);
        EntPatentInfoResult patentInfoResult = entIntellectualPropertyService.getEntPatentInfoByPage(qryParam);
        logger.info("EntIntellectualPropertyService.getEntPatentInfoByPage()的返回值：【{}】", patentInfoResult);
        if (patentInfoResult == null) {
            return null;
        }

        // 转换成EntPatentInfoResultVO
        EntPatentInfoResultVO patentInfoResultVO = convertToEntPatentInfoResultVO(patentInfoResult);
        logger.debug("EntPatentInfoResult：【{}】，转换成EntPatentInfoResultVO后结果值：【{}】", patentInfoResult, patentInfoResultVO);
        return patentInfoResultVO;
    }

    /**
     * 转换成EntPatentInfoResultVO
     *
     * @param entPatentInfoResult 企业专利信息
     * @return EntPatentInfoResultVO
     */
    private EntPatentInfoResultVO convertToEntPatentInfoResultVO(EntPatentInfoResult entPatentInfoResult) {
        EntPatentInfoResultVO patentInfoResultVO = new EntPatentInfoResultVO();
        // 复制分页信息
        BusinessHandlingUtils.copyBasePage(patentInfoResultVO, entPatentInfoResult);

        List<EntPatentInfo> patentInfoList = entPatentInfoResult.getEntPatentInfoList();
        if (patentInfoList == null || patentInfoList.isEmpty()) {
            patentInfoResultVO.setEntPatentInfoVOList(Collections.emptyList());
            return patentInfoResultVO;
        }

        // 企业专利信息集
        List<EntPatentInfoVO> patentInfoVOList = new ArrayList<>();
        for (EntPatentInfo patentInfo : patentInfoList) {
            EntPatentInfoVO patentInfoVO = convertToEntPatentInfoVO(patentInfo);
            patentInfoVOList.add(patentInfoVO);
        }
        patentInfoResultVO.setEntPatentInfoVOList(patentInfoVOList);
        return patentInfoResultVO;
    }

    /**
     * 转换成EntPatentInfoVO
     *
     * @param entPatentInfo 企业专利信息
     * @return EntPatentInfoVO
     */
    private EntPatentInfoVO convertToEntPatentInfoVO(EntPatentInfo entPatentInfo) {
        EntPatentInfoVO patentInfoVO = new EntPatentInfoVO();
        // 申请号
        patentInfoVO.setAppNum(entPatentInfo.getAppNum());
        // 申请日，格式：yyyy-MM-dd
        patentInfoVO.setAppDate(entPatentInfo.getAppDate());
        // 申请（专利权）人
        patentInfoVO.setAppName(entPatentInfo.getAppName());
        // 专利号
        patentInfoVO.setPatentNum(entPatentInfo.getPatentNum());
        // 专利名
        patentInfoVO.setPatentTitle(entPatentInfo.getPatentTitle());
        // 专利类型
        patentInfoVO.setPatentType(entPatentInfo.getPatentType());
        // 摘要
        patentInfoVO.setSummary(entPatentInfo.getSummary());
        // 公开（公告）号
        patentInfoVO.setPubNum(entPatentInfo.getPubNum());
        // 公开（公告）日，格式：yyyy-MM-dd
        patentInfoVO.setPubDate(entPatentInfo.getPubDate());
        // 颁证日，格式：yyyy-MM-dd
        patentInfoVO.setAwardDate(entPatentInfo.getAwardDate());
        // PCT公开
        patentInfoVO.setPctPub(entPatentInfo.getPctPub());
        // PCT申请
        patentInfoVO.setPctApp(entPatentInfo.getPctApp());
        // PCT信息
        patentInfoVO.setPctInfo(entPatentInfo.getPctInfo());
        return patentInfoVO;
    }

    /**
     * 创建企业标准信息查询参数
     *
     * @param request       请求
     * @param qryFieldParam 查询字段类型
     * @return EntStandardInfoQryParam
     */
    private EntStandardInfoQryParam createEntStandardInfoQryParam(HttpServletRequest request, String qryFieldParam) {
        EntStandardInfoQryParam qryParam = new EntStandardInfoQryParam();
        // 页码
        qryParam.setPageNum(BusinessHandlingUtils.getDefaultPageNum(request));
        // 每页数量
        qryParam.setPageSize(BusinessHandlingUtils.getDefaultPageSize(request));
        if (StandardQryFieldEnum.ENT_NAME.getValue().equals(qryFieldParam)) {
            // 企业名称
            qryParam.setEntName(request.getParameter(qryFieldParam));
            // 标准检索字段
            qryParam.setStandardQryFieldEnum(StandardQryFieldEnum.ENT_NAME);
        } else if (StandardQryFieldEnum.UNIFIED_SOCIAL_CREDIT_CODE.getValue().equals(qryFieldParam)) {
            // 统一社会信用代码
            qryParam.setUsCreditCode(request.getParameter(qryFieldParam));
            // 标准检索字段
            qryParam.setStandardQryFieldEnum(StandardQryFieldEnum.UNIFIED_SOCIAL_CREDIT_CODE);
        } else if (StandardQryFieldEnum.STANDARD_NUM.getValue().equals(qryFieldParam)) {
            // 标准号
            qryParam.setStandardNum(request.getParameter(qryFieldParam));
            // 标准检索字段
            qryParam.setStandardQryFieldEnum(StandardQryFieldEnum.STANDARD_NUM);
        } else {
            logger.error("企业标准信息查询qryFieldParam的参数值【{}】不存在", qryFieldParam);
            throw new IllegalArgumentException("企业标准信息查询qryFieldParam的参数值【" + qryFieldParam + "】不存在");
        }
        return qryParam;
    }

    /**
     * 获取企业标准信息
     *
     * @param qryParam 企业标准信息查询参数
     * @return EntStandardInfoResultVO 若查询不到，则返回null
     */
    private EntStandardInfoResultVO getEntStandardInfoResult(EntStandardInfoQryParam qryParam) {
        logger.info("EntIntellectualPropertyService.getEntStandardInfoByPage()的参数值：【{}】", qryParam);
        EntStandardInfoResult standardInfoResult = entIntellectualPropertyService.getEntStandardInfoByPage(qryParam);
        logger.info("EntIntellectualPropertyService.getEntStandardInfoByPage()的返回值：【{}】", standardInfoResult);
        if (standardInfoResult == null) {
            return null;
        }

        // 转换成EntStandardInfoResultVO
        EntStandardInfoResultVO standardInfoResultVO = convertToEntStandardInfoResultVO(standardInfoResult);
        logger.debug("EntStandardInfoResult：【{}】，转换成EntPatentInfoResultVO后结果值：【{}】", standardInfoResult, standardInfoResultVO);
        return standardInfoResultVO;
    }

    /**
     * 转换成EntStandardInfoVO
     *
     * @param entStandardInfoResult 企业标准信息
     * @return EntStandardInfoResultVO
     */
    private EntStandardInfoResultVO convertToEntStandardInfoResultVO(EntStandardInfoResult entStandardInfoResult) {
        EntStandardInfoResultVO standardInfoResultVO = new EntStandardInfoResultVO();
        // 复制分页信息
        BusinessHandlingUtils.copyBasePage(standardInfoResultVO, entStandardInfoResult);

        List<EntStandardInfo> standardInfoList = entStandardInfoResult.getEntStandardInfoList();
        if (standardInfoList == null || standardInfoList.isEmpty()) {
            standardInfoResultVO.setEntStandardInfoVOList(Collections.emptyList());
            return standardInfoResultVO;
        }

        // 企业专利信息集
        List<EntStandardInfoVO> standardInfoVOList = new ArrayList<>();
        for (EntStandardInfo standardInfo : standardInfoList) {
            EntStandardInfoVO standardInfoVO = convertToEntStandardInfoVO(standardInfo);
            standardInfoVOList.add(standardInfoVO);
        }
        standardInfoResultVO.setEntStandardInfoVOList(standardInfoVOList);
        return standardInfoResultVO;
    }

    /**
     * 转换成EntStandardInfoVO
     *
     * @param entStandardInfo 企业标准信息
     * @return EntStandardInfoVO
     */
    private EntStandardInfoVO convertToEntStandardInfoVO(EntStandardInfo entStandardInfo) {
        EntStandardInfoVO standardInfoVO = new EntStandardInfoVO();
        // 标准编号
        standardInfoVO.setStandardNum(entStandardInfo.getStandardNum());
        // 新标准号
        standardInfoVO.setNewStandardNum(entStandardInfo.getNewStandardNum());
        // 标准名称
        standardInfoVO.setStandardName(entStandardInfo.getStandardName());
        // 标准类型
        standardInfoVO.setStandardType(entStandardInfo.getStandardType());
        // 标准水平
        standardInfoVO.setStandardLevel(entStandardInfo.getStandardLevel());
        // 标准状态
        standardInfoVO.setStandardStatus(entStandardInfo.getStandardStatus());
        // 中文关键词
        standardInfoVO.setKeywords(entStandardInfo.getKeywords());
        // 起草单位
        standardInfoVO.setDraftUnit(entStandardInfo.getDraftUnit());
        // 发布日期，格式：yyyy-MM-dd
        standardInfoVO.setIssueDate(entStandardInfo.getIssueDate());
        // 实施日期，格式：yyyy-MM-dd
        standardInfoVO.setForceDate(entStandardInfo.getForceDate());
        // 废止日期，格式：yyyy-MM-dd
        standardInfoVO.setAvoidDate(entStandardInfo.getAvoidDate());
        return standardInfoVO;
    }
}
