package com.hanzhong.data.web.controller;

import com.hanzhong.data.web.constant.DrugProtVarietyQryFieldEnum;
import com.hanzhong.data.web.constant.DrugQryFieldEnum;
import com.hanzhong.data.web.constant.MedicalDeviceQryFieldEnum;
import com.hanzhong.data.web.constant.ResultCodeEnum;
import com.hanzhong.data.web.model.*;
import com.hanzhong.data.web.model.vo.*;
import com.hanzhong.data.web.service.EntDrugService;
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
 * 企业医药信息
 *
 * @author yifei
 * @date 2019/3/30
 */
@Controller
@RequestMapping("/ent_drug")
public class EntDrugController {

    private static final Logger logger = LoggerFactory.getLogger(EntDrugController.class);

    @Resource
    private EntDrugService entDrugService;

    /**
     * 获取企业医药药品信息
     *
     * @param request 请求
     * @return JsonResult
     */
    @PostMapping("/drug_info")
    @ResponseBody
    public JsonResult getEntDrugInfo(HttpServletRequest request) {
        // 查询字段参数
        String qryField = BusinessHandlingUtils.getQryFieldParamValue(request);
        try {
            // 创建查询参数
            DrugInfoQryParam qryParam = createDrugInfoQryParam(request, qryField);
            // 获取企业专利信息
            DrugInfoResultVO drugInfoResultVO = getDrugInfoResult(qryParam);
            if (drugInfoResultVO != null) {
                return JsonResultUtils.build(ResultCodeEnum.SUCCESS, drugInfoResultVO);
            }
            logger.info("request：【{}】，未查询到企业医药药品信息", HttpUtils.getRequestParamJsonStr(request));
            return JsonResultUtils.build(ResultCodeEnum.NO_DATA, null);
        } catch (Exception e) {
            logger.error("request：【{}】，获取企业医药药品信息出现异常：", HttpUtils.getRequestParamJsonStr(request), e);
            return JsonResultUtils.build(ResultCodeEnum.FAIL, null);
        }
    }

    /**
     * 获取企业医药保护品种信息
     *
     * @param request 请求
     * @return JsonResult
     */
    @PostMapping("/drug_protect_variety_info")
    @ResponseBody
    public JsonResult getEntDrugProtVarietyInfo(HttpServletRequest request) {
        // 查询字段参数
        String qryField = BusinessHandlingUtils.getQryFieldParamValue(request);
        try {
            // 创建查询参数
            DrugProtVarietyInfoQryParam qryParam = createDrugProtVarietyInfoQryParamm(request, qryField);
            // 获取企业医药保护品种信息
            DrugProtVarietyInfoResultVO varietyInfoResultVO = getDrugProtVarietyInfoResult(qryParam);
            if (varietyInfoResultVO != null) {
                return JsonResultUtils.build(ResultCodeEnum.SUCCESS, varietyInfoResultVO);
            }
            logger.info("request：【{}】，未查询到企业医药保护品种信息", HttpUtils.getRequestParamJsonStr(request));
            return JsonResultUtils.build(ResultCodeEnum.NO_DATA, null);
        } catch (Exception e) {
            logger.error("request：【{}】，获取企业医药保护品种信息出现异常：", HttpUtils.getRequestParamJsonStr(request), e);
            return JsonResultUtils.build(ResultCodeEnum.FAIL, null);
        }
    }

    /**
     * 获取企业医疗器械信息
     *
     * @param request 请求
     * @return JsonResult
     */
    @PostMapping("/medical_device_info")
    @ResponseBody
    public JsonResult getEntMedicalDeviceInfo(HttpServletRequest request) {
        // 查询字段参数
        String qryField = BusinessHandlingUtils.getQryFieldParamValue(request);
        try {
            // 创建查询参数
            MedicalDeviceInfoQryParam qryParam = createMedicalDeviceInfoQryParam(request, qryField);
            // 获取企业医疗器械信息
            MedicalDeviceInfoResultVO deviceInfoResultVO = getMedicalDevicesInfoResult(qryParam);
            if (deviceInfoResultVO != null) {
                return JsonResultUtils.build(ResultCodeEnum.SUCCESS, deviceInfoResultVO);
            }
            logger.info("request：【{}】，未查询到企业医疗器械信息", HttpUtils.getRequestParamJsonStr(request));
            return JsonResultUtils.build(ResultCodeEnum.NO_DATA, null);
        } catch (Exception e) {
            logger.error("request：【{}】，获取企业医疗器械信息出现异常：", HttpUtils.getRequestParamJsonStr(request), e);
            return JsonResultUtils.build(ResultCodeEnum.FAIL, null);
        }
    }

    /**
     * 创建企业医药药品信息查询参数
     *
     * @param request       请求
     * @param qryFieldParam 查询字段参数
     * @return DrugInfoQryParam
     */
    private DrugInfoQryParam createDrugInfoQryParam(HttpServletRequest request, String qryFieldParam) {
        DrugInfoQryParam qryParam = new DrugInfoQryParam();
        // 页码
        qryParam.setPageNum(BusinessHandlingUtils.getDefaultPageNum(request));
        // 每页数量
        qryParam.setPageSize(BusinessHandlingUtils.getDefaultPageSize(request));
        if (DrugQryFieldEnum.ENT_NAME.getValue().equals(qryFieldParam)) {
            // 企业名称
            qryParam.setEntName(request.getParameter(qryFieldParam));
            // 医药药品检索字段
            qryParam.setDrugQryFieldEnum(DrugQryFieldEnum.ENT_NAME);
        } else if (DrugQryFieldEnum.UNIFIED_SOCIAL_CREDIT_CODE.getValue().equals(qryFieldParam)) {
            // 统一社会信用代码
            qryParam.setUsCreditCode(request.getParameter(qryFieldParam));
            // 医药药品检索字段
            qryParam.setDrugQryFieldEnum(DrugQryFieldEnum.UNIFIED_SOCIAL_CREDIT_CODE);
        } else if (DrugQryFieldEnum.APPR_NUM.getValue().equals(qryFieldParam)) {
            // 申请号
            qryParam.setApprNUm(request.getParameter(qryFieldParam));
            // 医药药品检索字段
            qryParam.setDrugQryFieldEnum(DrugQryFieldEnum.APPR_NUM);
        } else {
            logger.error("企业医药药品信息查询qryFieldParam的参数值【{}】不存在", qryFieldParam);
            throw new IllegalArgumentException("企业医药药品信息查询qryFieldParam的参数值【" + qryFieldParam + "】不存在");
        }
        return qryParam;
    }

    /**
     * 获取企业医药药品信息
     *
     * @param qryParam 企业医药药品信息查询参数
     * @return EntPatentInfoResultVO 若查询不到，则返回null
     */
    private DrugInfoResultVO getDrugInfoResult(DrugInfoQryParam qryParam) {
        logger.info("EntDrugService.getEntDrugInfoByPage()的参数值：【{}】", qryParam);
        DrugInfoResult drugInfoResult = entDrugService.getEntDrugInfoByPage(qryParam);
        logger.info("EntDrugService.getEntDrugInfoByPage()的返回值：【{}】", drugInfoResult);
        if (drugInfoResult == null) {
            return null;
        }

        // 转换成DrugInfoResultVO
        DrugInfoResultVO drugInfoResultVO = convertToDrugInfoResultVO(drugInfoResult);
        logger.debug("DrugInfoResult：【{}】，转换成DrugInfoResultVO后结果值：【{}】", drugInfoResult, drugInfoResultVO);
        return drugInfoResultVO;
    }

    /**
     * 转换成DrugInfoResultVO
     *
     * @param drugInfoResult 企业医药药品信息
     * @return DrugInfoResultVO
     */
    private DrugInfoResultVO convertToDrugInfoResultVO(DrugInfoResult drugInfoResult) {
        DrugInfoResultVO drugInfoResultVO = new DrugInfoResultVO();
        // 复制分页信息
        BusinessHandlingUtils.copyBasePage(drugInfoResultVO, drugInfoResult);

        List<DrugInfo> drugInfoList = drugInfoResult.getDrugInfoList();
        if (drugInfoList == null || drugInfoList.isEmpty()) {
            drugInfoResultVO.setDrugInfoVOList(Collections.emptyList());
            return drugInfoResultVO;
        }

        // 企业医药药品信息集
        List<DrugInfoVO> drugInfoVOList = new ArrayList<>();
        for (DrugInfo drugInfo : drugInfoList) {
            DrugInfoVO drugInfoVO = convertToDrugInfoVO(drugInfo);
            drugInfoVOList.add(drugInfoVO);
        }
        drugInfoResultVO.setDrugInfoVOList(drugInfoVOList);
        return drugInfoResultVO;
    }

    /**
     * 转换成DrugInfoVO
     *
     * @param drugInfo 企业医药药品信息
     * @return DrugInfoVO
     */
    private DrugInfoVO convertToDrugInfoVO(DrugInfo drugInfo) {
        DrugInfoVO drugInfoVO = new DrugInfoVO();
        // 批准文号
        drugInfoVO.setApprNum(drugInfo.getApprNum());
        // 产品名称
        drugInfoVO.setProdName(drugInfo.getProdName());
        // 英文名称
        drugInfoVO.setEngName(drugInfo.getEngName());
        // 剂型
        drugInfoVO.setDosageForm(drugInfo.getDosageForm());
        // 规格
        drugInfoVO.setSpec(drugInfo.getSpec());
        // 生产单位
        drugInfoVO.setProdUnit(drugInfo.getProdUnit());
        // 生产地址
        drugInfoVO.setProdAddress(drugInfo.getProdAddress());
        // 产品类别
        drugInfoVO.setProdCategory(drugInfo.getProdCategory());
        // 批准日期
        drugInfoVO.setApprDate(drugInfo.getApprDate());
        // 原批准文号
        drugInfoVO.setOrigApprNum(drugInfo.getOrigApprNum());
        // 药品本位码
        drugInfoVO.setStdCode(drugInfo.getStdCode());
        // 药品本位码备注
        drugInfoVO.setStdDesc(drugInfo.getStdDesc());
        // 详情页链接
        drugInfoVO.setDetailLink(drugInfo.getDetailLink());
        return drugInfoVO;
    }

    /**
     * 创建企业医药保护品种信息查询参数
     *
     * @param request       请求
     * @param qryFieldParam 查询字段类型
     * @return DrugProtVarietyInfoQryParam
     */
    private DrugProtVarietyInfoQryParam createDrugProtVarietyInfoQryParamm(HttpServletRequest request, String qryFieldParam) {
        DrugProtVarietyInfoQryParam qryParam = new DrugProtVarietyInfoQryParam();
        // 页码
        qryParam.setPageNum(BusinessHandlingUtils.getDefaultPageNum(request));
        // 每页数量
        qryParam.setPageSize(BusinessHandlingUtils.getDefaultPageSize(request));
        if (DrugProtVarietyQryFieldEnum.ENT_NAME.getValue().equals(qryFieldParam)) {
            // 企业名称
            qryParam.setEntName(request.getParameter(qryFieldParam));
            // 医药保护品种检索字段
            qryParam.setDrugProtVarietyQryFieldEnum(DrugProtVarietyQryFieldEnum.ENT_NAME);
        } else if (DrugProtVarietyQryFieldEnum.UNIFIED_SOCIAL_CREDIT_CODE.getValue().equals(qryFieldParam)) {
            // 统一社会信用代码
            qryParam.setUsCreditCode(request.getParameter(qryFieldParam));
            // 医药保护品种检索字段
            qryParam.setDrugProtVarietyQryFieldEnum(DrugProtVarietyQryFieldEnum.UNIFIED_SOCIAL_CREDIT_CODE);
        } else if (DrugProtVarietyQryFieldEnum.VARIETY_ID.getValue().equals(qryFieldParam)) {
            // 保护品种编号
            qryParam.setVarietyId(request.getParameter(qryFieldParam));
            // 医药保护品种检索字段
            qryParam.setDrugProtVarietyQryFieldEnum(DrugProtVarietyQryFieldEnum.VARIETY_ID);
        } else {
            logger.error("企业医药保护品种信息查询qryFieldParam的参数值【{}】不存在", qryFieldParam);
            throw new IllegalArgumentException("企业医药保护品种信息查询qryFieldParam的参数值【" + qryFieldParam + "】不存在");
        }
        return qryParam;
    }

    /**
     * 获取企业医药保护品种信息
     *
     * @param qryParam 企业医药保护品种信息查询参数
     * @return DrugProtVarietyInfoResultVO 若查询不到，则返回null
     */
    private DrugProtVarietyInfoResultVO getDrugProtVarietyInfoResult(DrugProtVarietyInfoQryParam qryParam) {
        logger.info("EntDrugService.getEntDrugProtVarietyInfoByPage()的参数值：【{}】", qryParam);
        DrugProtVarietyInfoResult drugProtVarietyInfoResult = entDrugService.getEntDrugProtVarietyInfoByPage(qryParam);
        logger.info("EntDrugService.getEntDrugProtVarietyInfoByPage()的返回值：【{}】", drugProtVarietyInfoResult);
        if (drugProtVarietyInfoResult == null) {
            return null;
        }

        // 转换成DrugProtVarietyInfoResultVO
        DrugProtVarietyInfoResultVO drugProtVarietyInfoResultVO = convertToDrugProtVarietyInfoResultVO(drugProtVarietyInfoResult);
        logger.debug("DrugProtVarietyInfoResult：【{}】，转换成DrugProtVarietyInfoResultVO后结果值：【{}】", drugProtVarietyInfoResult, drugProtVarietyInfoResultVO);
        return drugProtVarietyInfoResultVO;
    }

    /**
     * 转换成DrugProtVarietyInfoResultVO
     *
     * @param drugProtVarietyInfoResult 企业医药保护品种信息
     * @return DrugProtVarietyInfoResultVO
     */
    private DrugProtVarietyInfoResultVO convertToDrugProtVarietyInfoResultVO(DrugProtVarietyInfoResult drugProtVarietyInfoResult) {
        DrugProtVarietyInfoResultVO drugProtVarietyInfoResultVO = new DrugProtVarietyInfoResultVO();
        // 复制分页信息
        BusinessHandlingUtils.copyBasePage(drugProtVarietyInfoResultVO, drugProtVarietyInfoResult);

        List<DrugProtVarietyInfo> varietyInfoList = drugProtVarietyInfoResult.getDrugProtVarietyInfoList();
        if (varietyInfoList == null || varietyInfoList.isEmpty()) {
            drugProtVarietyInfoResultVO.setDrugProtVarietyInfoVOList(Collections.emptyList());
            return drugProtVarietyInfoResultVO;
        }

        // 企业医药保护品种信息集
        List<DrugProtVarietyInfoVO> varietyInfoVOList = new ArrayList<>();
        for (DrugProtVarietyInfo varietyInfo : varietyInfoList) {
            DrugProtVarietyInfoVO varietyInfoVO = convertToDrugProtVarietyInfoVO(varietyInfo);
            varietyInfoVOList.add(varietyInfoVO);
        }
        drugProtVarietyInfoResultVO.setDrugProtVarietyInfoVOList(varietyInfoVOList);
        return drugProtVarietyInfoResultVO;
    }

    /**
     * 转换成DrugProtVarietyInfoVO
     *
     * @param drugProtVarietyInfo 企业医药保护品种信息
     * @return DrugProtVarietyInfoVO
     */
    private DrugProtVarietyInfoVO convertToDrugProtVarietyInfoVO(DrugProtVarietyInfo drugProtVarietyInfo) {
        DrugProtVarietyInfoVO drugProtVarietyInfoVO = new DrugProtVarietyInfoVO();
        // 保护品种编号
        drugProtVarietyInfoVO.setVarietyId(drugProtVarietyInfo.getVarietyId());
        // 药品名称
        drugProtVarietyInfoVO.setDrugName(drugProtVarietyInfo.getDrugName());
        // 公告号
        drugProtVarietyInfoVO.setPubNum(drugProtVarietyInfo.getPubNum());
        // 药品批准文号
        drugProtVarietyInfoVO.setApprNum(drugProtVarietyInfo.getApprNum());
        // 保护级别
        drugProtVarietyInfoVO.setLevel(drugProtVarietyInfo.getLevel());
        // 规格
        drugProtVarietyInfoVO.setSpec(drugProtVarietyInfo.getSpec());
        // 保护起始日，格式：yyyy-MM-dd
        drugProtVarietyInfoVO.setStartDate(drugProtVarietyInfo.getStartDate());
        // 保护终止日，格式：yyyy-MM-dd
        drugProtVarietyInfoVO.setEndDate(drugProtVarietyInfo.getEndDate());
        // 生产企业
        drugProtVarietyInfoVO.setManuEnt(drugProtVarietyInfo.getManuEnt());
        // 剂型
        drugProtVarietyInfoVO.setDosageForm(drugProtVarietyInfo.getDosageForm());
        // 保护期限
        drugProtVarietyInfoVO.setProtDuration(drugProtVarietyInfo.getProtDuration());
        return drugProtVarietyInfoVO;
    }

    /**
     * 创建企业医疗器械信息查询参数
     *
     * @param request       请求
     * @param qryFieldParam 查询字段类型
     * @return MedicalDeviceInfoQryParam
     */
    private MedicalDeviceInfoQryParam createMedicalDeviceInfoQryParam(HttpServletRequest request, String qryFieldParam) {
        MedicalDeviceInfoQryParam qryParam = new MedicalDeviceInfoQryParam();
        // 页码
        qryParam.setPageNum(BusinessHandlingUtils.getDefaultPageNum(request));
        // 每页数量
        qryParam.setPageSize(BusinessHandlingUtils.getDefaultPageSize(request));
        if (MedicalDeviceQryFieldEnum.ENT_NAME.getValue().equals(qryFieldParam)) {
            // 企业名称
            qryParam.setEntName(request.getParameter(qryFieldParam));
            // 医疗器械检索字段
            qryParam.setMedicalDeviceQryFieldEnum(MedicalDeviceQryFieldEnum.ENT_NAME);
        } else if (MedicalDeviceQryFieldEnum.UNIFIED_SOCIAL_CREDIT_CODE.getValue().equals(qryFieldParam)) {
            // 统一社会信用代码
            qryParam.setUsCreditCode(request.getParameter(qryFieldParam));
            // 医疗器械检索字段
            qryParam.setMedicalDeviceQryFieldEnum(MedicalDeviceQryFieldEnum.UNIFIED_SOCIAL_CREDIT_CODE);
        } else if (MedicalDeviceQryFieldEnum.REG_CERT_NUM.getValue().equals(qryFieldParam)) {
            // 注册证编号
            qryParam.setRegCertNum(request.getParameter(qryFieldParam));
            // 医疗器械检索字段
            qryParam.setMedicalDeviceQryFieldEnum(MedicalDeviceQryFieldEnum.REG_CERT_NUM);
        } else {
            logger.error("企业医疗器械信息查询qryFieldParam的参数值【{}】不存在", qryFieldParam);
            throw new IllegalArgumentException("企业医疗器械信息查询qryFieldParam的参数值【" + qryFieldParam + "】不存在");
        }
        return qryParam;
    }

    /**
     * 获取企业医疗器械信息
     *
     * @param qryParam 企业医疗器械信息查询参数
     * @return MedicalDevicesInfoResultVO 若查询不到，则返回null
     */
    private MedicalDeviceInfoResultVO getMedicalDevicesInfoResult(MedicalDeviceInfoQryParam qryParam) {
        logger.info("EntDrugService.getEntMedicalDeviceInfoByPage()的参数值：【{}】", qryParam);
        MedicalDeviceInfoResult deviceInfoResult = entDrugService.getEntMedicalDeviceInfoByPage(qryParam);
        logger.info("EntDrugService.getEntMedicalDeviceInfoByPage()的返回值：【{}】", deviceInfoResult);
        if (deviceInfoResult == null) {
            return null;
        }

        // 转换成MedicalDevicesInfoResultVO
        MedicalDeviceInfoResultVO devicesInfoResultVO = convertToMedicalDevicesInfoResultVO(deviceInfoResult);
        logger.debug("MedicalDeviceInfoResult：【{}】，转换成MedicalDevicesInfoResultVO后结果值：【{}】", deviceInfoResult, devicesInfoResultVO);
        return devicesInfoResultVO;
    }

    /**
     * 转换成MedicalDevicesInfoResultVO
     *
     * @param medicalDeviceInfoResult 企业医疗器械信息
     * @return MedicalDevicesInfoResultVO
     */
    private MedicalDeviceInfoResultVO convertToMedicalDevicesInfoResultVO(MedicalDeviceInfoResult medicalDeviceInfoResult) {
        MedicalDeviceInfoResultVO devicesInfoResultVO = new MedicalDeviceInfoResultVO();
        // 复制分页信息
        BusinessHandlingUtils.copyBasePage(devicesInfoResultVO, medicalDeviceInfoResult);

        List<MedicalDeviceInfo> deviceInfoList = medicalDeviceInfoResult.getMedicalDeviceInfoList();
        if (deviceInfoList == null || deviceInfoList.isEmpty()) {
            devicesInfoResultVO.setMedicalDeviceInfoVOList(Collections.emptyList());
            return devicesInfoResultVO;
        }

        // 企业医疗器械信息集
        List<MedicalDeviceInfoVO> devicesInfoVOList = new ArrayList<>();
        for (MedicalDeviceInfo deviceInfo : deviceInfoList) {
            MedicalDeviceInfoVO devicesInfoVO = convertToMedicalDevicesInfoVO(deviceInfo);
            devicesInfoVOList.add(devicesInfoVO);
        }
        devicesInfoResultVO.setMedicalDeviceInfoVOList(devicesInfoVOList);
        return devicesInfoResultVO;
    }

    /**
     * 转换成MedicalDevicesInfoVO
     *
     * @param medicalDeviceInfo 企业医疗器械信息
     * @return MedicalDevicesInfoVO
     */
    private MedicalDeviceInfoVO convertToMedicalDevicesInfoVO(MedicalDeviceInfo medicalDeviceInfo) {
        MedicalDeviceInfoVO medicalDeviceInfoVO = new MedicalDeviceInfoVO();
        // 注册证编号
        medicalDeviceInfoVO.setRegCertNum(medicalDeviceInfo.getRegCertNum());
        // 注册人名称
        medicalDeviceInfoVO.setRegName(medicalDeviceInfo.getRegName());
        // 注册人住所
        medicalDeviceInfoVO.setDom(medicalDeviceInfo.getDom());
        // 生产地址
        medicalDeviceInfoVO.setProdAddress(medicalDeviceInfo.getProdAddress());
        // 代理人名称
        medicalDeviceInfoVO.setAgyName(medicalDeviceInfo.getAgyName());
        // 代理人住所
        medicalDeviceInfoVO.setAgyDom(medicalDeviceInfo.getAgyDom());
        // 产品名称
        medicalDeviceInfoVO.setProdName(medicalDeviceInfo.getProdName());
        // 规格
        medicalDeviceInfoVO.setSpec(medicalDeviceInfo.getSpec());
        // 结构及组成
        medicalDeviceInfoVO.setStructure(medicalDeviceInfo.getStructure());
        // 适用范围
        medicalDeviceInfoVO.setAppScope(medicalDeviceInfo.getAppScope());
        // 其他内容
        medicalDeviceInfoVO.setOthDesc(medicalDeviceInfo.getOthDesc());
        // 备注
        medicalDeviceInfoVO.setRemark(medicalDeviceInfo.getRemark());
        // 批准日期
        medicalDeviceInfoVO.setApprDate(medicalDeviceInfo.getApprDate());
        // 有效期至
        medicalDeviceInfoVO.setExpDate(medicalDeviceInfo.getExpDate());
        // 产品标准
        medicalDeviceInfoVO.setProdStd(medicalDeviceInfo.getProdStd());
        // 变更日期
        medicalDeviceInfoVO.setRevDate(medicalDeviceInfo.getRevDate());
        // 邮编
        medicalDeviceInfoVO.setPostalCode(medicalDeviceInfo.getPostalCode());
        // 主要组成成分
        medicalDeviceInfoVO.setMajorConst(medicalDeviceInfo.getMajorConst());
        // 预期用途
        medicalDeviceInfoVO.setIntendUse(medicalDeviceInfo.getIntendUse());
        // 产品储存条件及有效期
        medicalDeviceInfoVO.setStgCond(medicalDeviceInfo.getStgCond());
        // 审批部门
        medicalDeviceInfoVO.setApprDepart(medicalDeviceInfo.getApprDepart());
        // 变更情况
        medicalDeviceInfoVO.setAlteration(medicalDeviceInfo.getAlteration());
        // 详情页链接
        medicalDeviceInfoVO.setDetailLink(medicalDeviceInfo.getDetailLink());
        return medicalDeviceInfoVO;
    }
}
