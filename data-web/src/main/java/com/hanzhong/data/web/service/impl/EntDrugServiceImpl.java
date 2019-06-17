package com.hanzhong.data.web.service.impl;

import com.hanzhong.data.web.constant.DrugProtVarietyQryFieldEnum;
import com.hanzhong.data.web.constant.DrugQryFieldEnum;
import com.hanzhong.data.web.constant.EntStatusEnum;
import com.hanzhong.data.web.constant.MedicalDeviceQryFieldEnum;
import com.hanzhong.data.web.dao.master.TblBusinessDao;
import com.hanzhong.data.web.dao.master.TblDrugDao;
import com.hanzhong.data.web.dao.master.TblDrugPrtcVarietyDao;
import com.hanzhong.data.web.dao.master.TblMedicalDevicesDao;
import com.hanzhong.data.web.dao.slave.LdRegisterInfoDao;
import com.hanzhong.data.web.model.*;
import com.hanzhong.data.web.model.bo.*;
import com.hanzhong.data.web.model.entity.master.TblDrugEntity;
import com.hanzhong.data.web.model.entity.master.TblDrugPrtcVarietyEntity;
import com.hanzhong.data.web.model.entity.master.TblMedicalDevicesEntity;
import com.hanzhong.data.web.service.EntDrugService;
import com.hanzhong.data.web.util.BusinessHandlingUtils;
import com.hanzhong.data.web.util.DateUtils;
import com.hanzhong.data.web.util.longdun.entbaseinfo.LdEntBaseInfoApiUtils;
import com.hanzhong.data.web.util.longdun.entbaseinfo.constant.KeyWordTypeEnum;
import com.hanzhong.data.web.util.longdun.entbaseinfo.model.EntInfo;
import com.hanzhong.data.web.util.longdun.entbaseinfo.model.EntKeyWordQryParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yifei
 * @date 2019/4/21
 */
@Service("entDrugService")
public class EntDrugServiceImpl implements EntDrugService {
    private static final Logger logger = LoggerFactory.getLogger(EntDrugServiceImpl.class);

    @Resource
    private TblDrugDao tblDrugDao;
    @Resource
    private TblDrugPrtcVarietyDao tblDrugPrtcVarietyDao;
    @Resource
    private TblMedicalDevicesDao tblMedicalDevicesDao;
    @Resource
    private TblBusinessDao tblBusinessDao;
    @Resource
    private LdRegisterInfoDao ldRegisterInfoDao;

    /**
     * 分页获取企业医药药品信息
     *
     * @param qryParam 查询参数
     * @return DrugInfoResult
     */
    @Override
    public DrugInfoResult getEntDrugInfoByPage(DrugInfoQryParam qryParam) {
        DrugQryFieldEnum qryFieldEnum = qryParam.getDrugQryFieldEnum();
        int pageNum = BusinessHandlingUtils.getDefaultPageNum(qryParam.getPageNum());
        int pageSize = BusinessHandlingUtils.getDefaultPageSize(qryParam.getPageSize());
        int startIndex = BusinessHandlingUtils.getStartIndex(pageNum, pageSize);

        switch (qryFieldEnum) {
            case ENT_NAME:
                EntDrugInfoQryBO drugInfoQryBO = createEntDrugInfoQryBOByEntName(qryParam.getEntName());
                drugInfoQryBO.setStartIndex(startIndex);
                drugInfoQryBO.setPageSize(pageSize);
                return getEntDrugInfoResult(pageNum, drugInfoQryBO);
            case UNIFIED_SOCIAL_CREDIT_CODE:
                EntDrugInfoQryBO drugInfoQryBO2 = createEntDrugInfoQryBOByUsCreditCode(qryParam.getUsCreditCode());
                if (drugInfoQryBO2 == null) {
                    logger.warn("通过统一社会信用代码【{}】未查询到企业医药药品信息", qryParam.getUsCreditCode());
                    return DrugInfoResult.init(pageNum, pageSize);
                }
                drugInfoQryBO2.setStartIndex(startIndex);
                drugInfoQryBO2.setPageSize(pageSize);
                return getEntDrugInfoResult(pageNum, drugInfoQryBO2);
            case APPR_NUM:
                DrugInfoQryBO drugInfoQryBO3 = new DrugInfoQryBO();
                drugInfoQryBO3.setApprNum(qryParam.getApprNUm());
                drugInfoQryBO3.setStartIndex(startIndex);
                drugInfoQryBO3.setPageSize(pageSize);
                return getDrugInfoResult(pageNum, drugInfoQryBO3);
            default:
                logger.warn("获取企业医药药品信息此枚举值【{}】未作相应处理", qryFieldEnum);
                return DrugInfoResult.init(pageNum, pageSize);
        }
    }

    /**
     * 分页获取企业医药保护品种信息
     *
     * @param qryParam 查询参数
     * @return DrugProtVarietyInfoResult
     */
    @Override
    public DrugProtVarietyInfoResult getEntDrugProtVarietyInfoByPage(DrugProtVarietyInfoQryParam qryParam) {
        DrugProtVarietyQryFieldEnum qryFieldEnum = qryParam.getDrugProtVarietyQryFieldEnum();
        int pageNum = BusinessHandlingUtils.getDefaultPageNum(qryParam.getPageNum());
        int pageSize = BusinessHandlingUtils.getDefaultPageSize(qryParam.getPageSize());
        int startIndex = BusinessHandlingUtils.getStartIndex(pageNum, pageSize);

        switch (qryFieldEnum) {
            case ENT_NAME:
                EntDrugProtVarietyInfoQryBO varietyInfoQryBO = createEntDrugProtVarietyInfoQryBOByEntName(qryParam.getEntName());
                varietyInfoQryBO.setStartIndex(startIndex);
                varietyInfoQryBO.setPageSize(pageSize);
                return getEntDrugProtVarietyInfoResult(pageNum, varietyInfoQryBO);
            case UNIFIED_SOCIAL_CREDIT_CODE:
                EntDrugProtVarietyInfoQryBO varietyInfoQryBO2 = createEntDrugProtVarietyInfoQryBOByUsCreditCode(qryParam.getUsCreditCode());
                if (varietyInfoQryBO2 == null) {
                    logger.warn("通过统一社会信用代码【{}】未查询到企业医药保护品种信息", qryParam.getUsCreditCode());
                    return DrugProtVarietyInfoResult.init(pageNum, pageSize);
                }
                varietyInfoQryBO2.setStartIndex(startIndex);
                varietyInfoQryBO2.setPageSize(pageSize);
                return getEntDrugProtVarietyInfoResult(pageNum, varietyInfoQryBO2);
            case VARIETY_ID:
                DrugProtVarietyInfoQryBO varietyInfoQryBO3 = new DrugProtVarietyInfoQryBO();
                varietyInfoQryBO3.setVarietyId(qryParam.getVarietyId());
                varietyInfoQryBO3.setStartIndex(startIndex);
                varietyInfoQryBO3.setPageSize(pageSize);
                return getDrugProtVarietyInfoResult(pageNum, varietyInfoQryBO3);
            default:
                logger.warn("获取企业医药保护品种信息此枚举值【{}】未作相应处理", qryFieldEnum);
                return DrugProtVarietyInfoResult.init(pageNum, pageSize);
        }
    }

    /**
     * 分页获取企业医疗器械信息
     *
     * @param qryParam 查询参数
     * @return MedicalDevicesInfoResult
     */
    @Override
    public MedicalDeviceInfoResult getEntMedicalDeviceInfoByPage(MedicalDeviceInfoQryParam qryParam) {
        MedicalDeviceQryFieldEnum qryFieldEnum = qryParam.getMedicalDeviceQryFieldEnum();
        int pageNum = BusinessHandlingUtils.getDefaultPageNum(qryParam.getPageNum());
        int pageSize = BusinessHandlingUtils.getDefaultPageSize(qryParam.getPageSize());
        int startIndex = BusinessHandlingUtils.getStartIndex(pageNum, pageSize);

        switch (qryFieldEnum) {
            case ENT_NAME:
                EntMedicalDeviceInfoQryBO deviceInfoQryBO = createEntMedicalDeviceInfoQryBOByEntName(qryParam.getEntName());
                deviceInfoQryBO.setStartIndex(startIndex);
                deviceInfoQryBO.setPageSize(pageSize);
                return getEntMedicalDevicesInfoResult(pageNum, deviceInfoQryBO);
            case UNIFIED_SOCIAL_CREDIT_CODE:
                EntMedicalDeviceInfoQryBO deviceInfoQryBO2 = createEntMedicalDeviceInfoQryBOByUsCreditCode(qryParam.getUsCreditCode());
                if (deviceInfoQryBO2 == null) {
                    logger.warn("通过统一社会信用代码【{}】未查询到企业医疗器械信息", qryParam.getUsCreditCode());
                    return MedicalDeviceInfoResult.init(pageNum, pageSize);
                }
                deviceInfoQryBO2.setStartIndex(startIndex);
                deviceInfoQryBO2.setPageSize(pageSize);
                return getEntMedicalDevicesInfoResult(pageNum, deviceInfoQryBO2);
            case REG_CERT_NUM:
                MedicalDeviceInfoQryBO deviceInfoQryBO3 = new MedicalDeviceInfoQryBO();
                deviceInfoQryBO3.setRegCertNum(qryParam.getRegCertNum());
                deviceInfoQryBO3.setStartIndex(startIndex);
                deviceInfoQryBO3.setPageSize(pageSize);
                return getMedicalDevicesInfoResult(pageNum, deviceInfoQryBO3);
            default:
                logger.warn("获取企业医疗器械信息此枚举值【{}】未作相应处理", qryFieldEnum);
                return MedicalDeviceInfoResult.init(pageNum, pageSize);
        }
    }

    /**
     * 通过企业名称获取主体身份代码
     *
     * @param entName 企业名称
     * @return String 若无此企业，则返回null
     */
    private String getPripIdByEntName(String entName) {
//        EntUniqueInfoQryBO uniqueInfoQryBO = new EntUniqueInfoQryBO();
//        // 企业名称
//        uniqueInfoQryBO.setEntName(entName);
//        EntUniqueInfoBO entUniqueInfoBO = getEntUniqueInfoOfLocal(uniqueInfoQryBO);
//        if (entUniqueInfoBO == null) {
            return null;
//        }
//        return entUniqueInfoBO.getPripId();
    }

    /**
     * 通过统一社会信用代码获取主体身份代码
     *
     * @param usCreditCode 企业名称
     * @return String 若无此企业，则返回null
     */
    private String getPripIdByUsCreditCode(String usCreditCode) {
//        EntUniqueInfoQryBO uniqueInfoQryBO = new EntUniqueInfoQryBO();
//        // 统一社会信用代码
//        uniqueInfoQryBO.setUsCreditCode(usCreditCode);
//
//        // 获取本地数据库企业唯一标识信息
//        EntUniqueInfoBO entUniqueInfoBO = getEntUniqueInfoOfLocal(uniqueInfoQryBO);
//        if (entUniqueInfoBO == null) {
            return null;
//        }
//        return entUniqueInfoBO.getPripId();
    }

    /**
     * 获取本地数据库企业唯一标识信息
     *
     * @param uniqueInfoQryBO 查询参数
     * @return EntUniqueInfoBO
     */
    private EntUniqueInfoBO getEntUniqueInfoOfLocal(EntUniqueInfoQryBO uniqueInfoQryBO) {
        logger.info("TblBusinessDao.getEntUniqueInfoList()的参数值：【{}】", uniqueInfoQryBO);
        List<EntUniqueInfoBO> entUniqueInfoBOList = tblBusinessDao.getEntUniqueInfoList(uniqueInfoQryBO);
        logger.info("TblBusinessDao.getEntUniqueInfoList()的返回值：【{}】", entUniqueInfoBOList);
        if (entUniqueInfoBOList == null || entUniqueInfoBOList.isEmpty()) {
            return null;
        }

        // 若有多个查询结果，则优先返回“在营”企业信息
        for (EntUniqueInfoBO infoBO : entUniqueInfoBOList) {
            if (EntStatusEnum.DOING_BUSINESS.getKey().equals(infoBO.getEntStatus())) {
                return infoBO;
            }
        }
        return entUniqueInfoBOList.get(0);
    }

    /**
     * 获取龙盾数据库企业唯一标识信息
     *
     * @param uniqueInfoQryBO 查询参数
     * @return EntUniqueInfoBO
     */
    private EntUniqueInfoBO getEntUniqueInfoOfLd(EntUniqueInfoQryBO uniqueInfoQryBO) {
        logger.info("LdRegisterInfoDao.getEntUniqueInfoList()的参数值：【{}】", uniqueInfoQryBO);
        List<EntUniqueInfoBO> entUniqueInfoBOList = ldRegisterInfoDao.getEntUniqueInfoList(uniqueInfoQryBO);
        logger.info("LdRegisterInfoDao.getEntUniqueInfoList()的返回值：【{}】", entUniqueInfoBOList);
        if (entUniqueInfoBOList == null || entUniqueInfoBOList.isEmpty()) {
            return null;
        }
        return entUniqueInfoBOList.get(0);
    }

    /**
     * 通过企业名称创建企业医药药品信息查询参数
     *
     * @param entName 企业名称
     * @return EntDrugInfoQryBO
     */
    private EntDrugInfoQryBO createEntDrugInfoQryBOByEntName(String entName) {
        // 通过企业名称获取主体身份代码
        String pripId = getPripIdByEntName(entName);

        EntDrugInfoQryBO drugInfoQryBO = new EntDrugInfoQryBO();
        if (StringUtils.isNotBlank(pripId)) {
            // 主体身份代码
            drugInfoQryBO.setPripId(pripId);
            return drugInfoQryBO;
        }
        // 企业名称
        drugInfoQryBO.setEntName(entName);
        return drugInfoQryBO;
    }

    /**
     * 通过统一社会信用代码创建企业医药药品信息查询参数
     *
     * @param usCreditCode 统一社会信用代码
     * @return EntDrugInfoQryBO 若无此统一社会信用代码，则返回null
     */
    private EntDrugInfoQryBO createEntDrugInfoQryBOByUsCreditCode(String usCreditCode) {
        // 通过统一社会信用代码获取主体身份代码
        String pripId = getPripIdByUsCreditCode(usCreditCode);

        EntDrugInfoQryBO drugInfoQryBO = new EntDrugInfoQryBO();
        if (StringUtils.isNotBlank(pripId)) {
            // 主体身份代码
            drugInfoQryBO.setPripId(pripId);
            return drugInfoQryBO;
        }

        /* 获取龙盾数据库企业唯一标识信息 */
        EntUniqueInfoQryBO entUniqueInfoQryBO = new EntUniqueInfoQryBO();
        entUniqueInfoQryBO.setUsCreditCode(usCreditCode);
        EntUniqueInfoBO entUniqueInfoBO = getEntUniqueInfoOfLd(entUniqueInfoQryBO);
        if (entUniqueInfoBO != null) {
            // 企业名称
            drugInfoQryBO.setEntName(entUniqueInfoBO.getEntName());
            return drugInfoQryBO;
        }

        /* 获取龙盾API企业信息 */
        EntKeyWordQryParam keyWordQryParam = new EntKeyWordQryParam();
        keyWordQryParam.setKeyword(usCreditCode);
        keyWordQryParam.setKeyWordTypeEnum(KeyWordTypeEnum.REG_NUM_OR_USCC_NUM);
        EntInfo entInfo = LdEntBaseInfoApiUtils.getEntInfoByKeyword(keyWordQryParam);
        if (entInfo != null) {
            // 企业名称
            drugInfoQryBO.setEntName(entInfo.getEntName());
            return drugInfoQryBO;
        }

        return null;
    }

    /**
     * 获取企业医药药品信息
     *
     * @param pageNum          页码
     * @param entDrugInfoQryBO 查询参数
     * @return DrugInfoResult
     */
    private DrugInfoResult getEntDrugInfoResult(int pageNum, EntDrugInfoQryBO entDrugInfoQryBO) {
        // 查询企业医药药品信息总数
        logger.info("TblDrugDao.getEntDrugInfoCount()的参数值：【{}】", entDrugInfoQryBO);
        int total = tblDrugDao.getEntDrugInfoCount(entDrugInfoQryBO);
        logger.info("TblDrugDao.getEntDrugInfoCount()的返回值：【{}】", total);

        // 查询企业医药药品信息
        logger.info("TblDrugDao.getEntDrugInfoList()的参数值：【{}】", entDrugInfoQryBO);
        List<TblDrugEntity> drugEntityList = tblDrugDao.getEntDrugInfoList(entDrugInfoQryBO);
        logger.info("TblDrugDao.getEntDrugInfoList()的返回值的结果数：【{}】", BusinessHandlingUtils.getDefaultCollectionSize(drugEntityList));

        int pageSize = entDrugInfoQryBO.getPageSize();
        DrugInfoResult drugInfoResult = new DrugInfoResult();
        // 总数
        drugInfoResult.setTotal(total);
        // 总页数
        drugInfoResult.setPageTotal(BusinessHandlingUtils.getPageTotal(total, pageSize));
        // 当前页码
        drugInfoResult.setCurrentPageNum(pageNum);
        // 当前每页数量
        drugInfoResult.setCurrentPageSize(pageSize);
        // 是否有更多
        drugInfoResult.setNoMore(BusinessHandlingUtils.notHaveMore(total, entDrugInfoQryBO.getStartIndex(), pageSize));
        // 企业医药药品信息
        List<DrugInfo> drugInfoList = new ArrayList<>();
        for (TblDrugEntity drugEntity : drugEntityList) {
            // 转换成EDrugInfo
            DrugInfo patentInfo = convertToDrugInfo(drugEntity);
            drugInfoList.add(patentInfo);
        }
        drugInfoResult.setDrugInfoList(drugInfoList);
        logger.debug("获取企业医药药品信息的返回值：【{}】", drugInfoResult);
        return drugInfoResult;
    }

    /**
     * 获取医药药品信息
     *
     * @param pageNum       页码
     * @param drugInfoQryBO 查询参数
     * @return DrugInfoResult
     */
    private DrugInfoResult getDrugInfoResult(int pageNum, DrugInfoQryBO drugInfoQryBO) {
        // 查询医药药品信息总数
        logger.info("TblDrugDao.getDrugInfoCount()的参数值：【{}】", drugInfoQryBO);
        int total = tblDrugDao.getDrugInfoCount(drugInfoQryBO);
        logger.info("TblDrugDao.getDrugInfoCount()的返回值：【{}】", total);

        // 查询医药药品信息
        logger.info("TblDrugDao.getDrugInfoList()的参数值：【{}】", drugInfoQryBO);
        List<TblDrugEntity> drugEntityList = tblDrugDao.getDrugInfoList(drugInfoQryBO);
        logger.info("TblDrugDao.getDrugInfoList()的返回值的结果数：【{}】", BusinessHandlingUtils.getDefaultCollectionSize(drugEntityList));

        int pageSize = drugInfoQryBO.getPageSize();
        DrugInfoResult drugInfoResult = new DrugInfoResult();
        // 总数
        drugInfoResult.setTotal(total);
        // 总页数
        drugInfoResult.setPageTotal(BusinessHandlingUtils.getPageTotal(total, pageSize));
        // 当前页码
        drugInfoResult.setCurrentPageNum(pageNum);
        // 当前每页数量
        drugInfoResult.setCurrentPageSize(pageSize);
        // 是否有更多
        drugInfoResult.setNoMore(BusinessHandlingUtils.notHaveMore(total, drugInfoQryBO.getStartIndex(), pageSize));
        // 企业医药药品信息
        List<DrugInfo> drugInfoList = new ArrayList<>();
        for (TblDrugEntity drugEntity : drugEntityList) {
            // 转换成EntPatentInfo
            DrugInfo drugInfo = convertToDrugInfo(drugEntity);
            drugInfoList.add(drugInfo);
        }
        drugInfoResult.setDrugInfoList(drugInfoList);
        logger.debug("获取医药药品信息的返回值：【{}】", drugInfoResult);
        return drugInfoResult;
    }

    /**
     * 转换成DrugInfo
     *
     * @param tblDrugEntity 医药药品信息
     * @return DrugInfo
     */
    private DrugInfo convertToDrugInfo(TblDrugEntity tblDrugEntity) {
        DrugInfo drugInfo = new DrugInfo();
        // 批准文号
        drugInfo.setApprNum(tblDrugEntity.getAprvNum());
        // 产品名称
        drugInfo.setProdName(tblDrugEntity.getProdName());
        // 英文名称
        drugInfo.setEngName(tblDrugEntity.getEngName());
        // 剂型
        drugInfo.setDosageForm(tblDrugEntity.getDosageForm());
        // 规格
        drugInfo.setSpec(tblDrugEntity.getSpec());
        // 生产单位
        drugInfo.setProdUnit(tblDrugEntity.getEntName());
        // 生产地址
        drugInfo.setProdAddress(tblDrugEntity.getOpLoc());
        // 产品类别
        drugInfo.setProdCategory(tblDrugEntity.getProdCatg());
        // 批准日期
        String aprvDate = tblDrugEntity.getAprvDate();
        drugInfo.setApprDate(BusinessHandlingUtils.formatDateWithDot(aprvDate, aprvDate));
        // 原批准文号
        drugInfo.setOrigApprNum(tblDrugEntity.getOrigAprvNum());
        // 药品本位码
        drugInfo.setStdCode(tblDrugEntity.getStdCode());
        // 药品本位码备注
        drugInfo.setStdDesc(tblDrugEntity.getStdDesc());
        // 详情页链接
        drugInfo.setDetailLink(tblDrugEntity.getDetlLink());
        logger.debug("转换成DrugInfo的值：【{}】", drugInfo);
        return drugInfo;
    }

    /**
     * 通过企业名称创建企业医药保护品种信息查询参数
     *
     * @param entName 企业名称
     * @return EntDrugProtVarietyInfoQryBO
     */
    private EntDrugProtVarietyInfoQryBO createEntDrugProtVarietyInfoQryBOByEntName(String entName) {
        // 通过企业名称获取主体身份代码
        String pripId = getPripIdByEntName(entName);

        EntDrugProtVarietyInfoQryBO drugProtVarietyInfoQryBO = new EntDrugProtVarietyInfoQryBO();
        if (StringUtils.isNotBlank(pripId)) {
            // 主体身份代码
            drugProtVarietyInfoQryBO.setPripId(pripId);
            return drugProtVarietyInfoQryBO;
        }
        // 企业名称
        drugProtVarietyInfoQryBO.setEntName(entName);
        return drugProtVarietyInfoQryBO;
    }

    /**
     * 通过统一社会信用代码创建企业医药保护品种信息查询参数
     *
     * @param usCreditCode 统一社会信用代码
     * @return EntDrugProtVarietyInfoQryBO 若无此统一社会信用代码，则返回null
     */
    private EntDrugProtVarietyInfoQryBO createEntDrugProtVarietyInfoQryBOByUsCreditCode(String usCreditCode) {
        // 通过统一社会信用代码获取主体身份代码
        String pripId = getPripIdByUsCreditCode(usCreditCode);

        EntDrugProtVarietyInfoQryBO drugProtVarietyInfoQryBO = new EntDrugProtVarietyInfoQryBO();
        if (StringUtils.isNotBlank(pripId)) {
            // 主体身份代码
            drugProtVarietyInfoQryBO.setPripId(pripId);
            return drugProtVarietyInfoQryBO;
        }

        /* 获取龙盾数据库企业唯一标识信息 */
        EntUniqueInfoQryBO entUniqueInfoQryBO = new EntUniqueInfoQryBO();
        entUniqueInfoQryBO.setUsCreditCode(usCreditCode);
        EntUniqueInfoBO entUniqueInfoBO = getEntUniqueInfoOfLd(entUniqueInfoQryBO);
        if (entUniqueInfoBO != null) {
            // 企业名称
            drugProtVarietyInfoQryBO.setEntName(entUniqueInfoBO.getEntName());
            return drugProtVarietyInfoQryBO;
        }

        /* 获取龙盾API企业信息 */
        EntKeyWordQryParam keyWordQryParam = new EntKeyWordQryParam();
        keyWordQryParam.setKeyword(usCreditCode);
        keyWordQryParam.setKeyWordTypeEnum(KeyWordTypeEnum.REG_NUM_OR_USCC_NUM);
        EntInfo entInfo = LdEntBaseInfoApiUtils.getEntInfoByKeyword(keyWordQryParam);
        if (entInfo != null) {
            // 企业名称
            drugProtVarietyInfoQryBO.setEntName(entInfo.getEntName());
            return drugProtVarietyInfoQryBO;
        }

        return null;
    }

    /**
     * 获取企业医药保护品种信息
     *
     * @param pageNum                     页码
     * @param entDrugProtVarietyInfoQryBO 查询参数
     * @return DrugProtVarietyInfoResult
     */
    private DrugProtVarietyInfoResult getEntDrugProtVarietyInfoResult(int pageNum, EntDrugProtVarietyInfoQryBO entDrugProtVarietyInfoQryBO) {
        // 查询企业医药保护品种信息总数
        logger.info("TblDrugPrtcVarietyDao.getEntDrugPrtcVarietyInfoCount()的参数值：【{}】", entDrugProtVarietyInfoQryBO);
        int total = tblDrugPrtcVarietyDao.getEntDrugPrtcVarietyInfoCount(entDrugProtVarietyInfoQryBO);
        logger.info("TblDrugPrtcVarietyDao.getEntDrugPrtcVarietyInfoCount()的返回值：【{}】", total);

        // 查询企业医药保护品种信息
        logger.info("TblDrugPrtcVarietyDao.getEntDrugPrtcVarietyInfoList()的参数值：【{}】", entDrugProtVarietyInfoQryBO);
        List<TblDrugPrtcVarietyEntity> drugPrtcVarietyEntityList = tblDrugPrtcVarietyDao.getEntDrugPrtcVarietyInfoList(entDrugProtVarietyInfoQryBO);
        logger.info("TblDrugPrtcVarietyDao.getEntDrugPrtcVarietyInfoList()的返回值的结果数：【{}】", BusinessHandlingUtils.getDefaultCollectionSize(drugPrtcVarietyEntityList));

        int pageSize = entDrugProtVarietyInfoQryBO.getPageSize();
        DrugProtVarietyInfoResult drugProtVarietyInfoResult = new DrugProtVarietyInfoResult();
        // 总数
        drugProtVarietyInfoResult.setTotal(total);
        // 总页数
        drugProtVarietyInfoResult.setPageTotal(BusinessHandlingUtils.getPageTotal(total, pageSize));
        // 当前页码
        drugProtVarietyInfoResult.setCurrentPageNum(pageNum);
        // 当前每页数量
        drugProtVarietyInfoResult.setCurrentPageSize(pageSize);
        // 是否有更多
        drugProtVarietyInfoResult.setNoMore(BusinessHandlingUtils.notHaveMore(total, entDrugProtVarietyInfoQryBO.getStartIndex(), pageSize));
        // 企业医药保护品种信息
        List<DrugProtVarietyInfo> drugProtVarietyInfoList = new ArrayList<>();
        for (TblDrugPrtcVarietyEntity drugPrtcVarietyEntity : drugPrtcVarietyEntityList) {
            // 转换成DrugProtVarietyInfo
            DrugProtVarietyInfo drugProtVarietyInfo = convertToDrugProtVarietyInfo(drugPrtcVarietyEntity);
            drugProtVarietyInfoList.add(drugProtVarietyInfo);
        }
        drugProtVarietyInfoResult.setDrugProtVarietyInfoList(drugProtVarietyInfoList);
        logger.debug("获取企业医药保护品种信息的返回值：【{}】", drugProtVarietyInfoResult);
        return drugProtVarietyInfoResult;
    }

    /**
     * 获取医药保护品种信息
     *
     * @param pageNum                  页码
     * @param drugProtVarietyInfoQryBO 查询参数
     * @return DrugProtVarietyInfoResult
     */
    private DrugProtVarietyInfoResult getDrugProtVarietyInfoResult(int pageNum, DrugProtVarietyInfoQryBO drugProtVarietyInfoQryBO) {
        // 查询医药药品信息总数
        logger.info("TblDrugPrtcVarietyDao.getDrugPrtcVarietyInfoCount()的参数值：【{}】", drugProtVarietyInfoQryBO);
        int total = tblDrugPrtcVarietyDao.getDrugPrtcVarietyInfoCount(drugProtVarietyInfoQryBO);
        logger.info("TblDrugPrtcVarietyDao.getDrugPrtcVarietyInfoCount()的返回值：【{}】", total);

        // 查询医药药品信息
        logger.info("TblDrugPrtcVarietyDao.getDrugPrtcVarietyInfoList()的参数值：【{}】", drugProtVarietyInfoQryBO);
        List<TblDrugPrtcVarietyEntity> drugPrtcVarietyEntityList = tblDrugPrtcVarietyDao.getDrugPrtcVarietyInfoList(drugProtVarietyInfoQryBO);
        logger.info("TblDrugPrtcVarietyDao.getDrugPrtcVarietyInfoList()的返回值：【{}】", BusinessHandlingUtils.getDefaultCollectionSize(drugPrtcVarietyEntityList));

        int pageSize = drugProtVarietyInfoQryBO.getPageSize();
        DrugProtVarietyInfoResult drugProtVarietyInfoResult = new DrugProtVarietyInfoResult();
        // 总数
        drugProtVarietyInfoResult.setTotal(total);
        // 总页数
        drugProtVarietyInfoResult.setPageTotal(BusinessHandlingUtils.getPageTotal(total, pageSize));
        // 当前页码
        drugProtVarietyInfoResult.setCurrentPageNum(pageNum);
        // 当前每页数量
        drugProtVarietyInfoResult.setCurrentPageSize(pageSize);
        // 是否有更多
        drugProtVarietyInfoResult.setNoMore(BusinessHandlingUtils.notHaveMore(total, drugProtVarietyInfoQryBO.getStartIndex(), pageSize));
        // 企业医药保护品种信息
        List<DrugProtVarietyInfo> drugProtVarietyInfoList = new ArrayList<>();
        for (TblDrugPrtcVarietyEntity drugPrtcVarietyEntity : drugPrtcVarietyEntityList) {
            // 转换成DrugProtVarietyInfo
            DrugProtVarietyInfo drugProtVarietyInfo = convertToDrugProtVarietyInfo(drugPrtcVarietyEntity);
            drugProtVarietyInfoList.add(drugProtVarietyInfo);
        }
        drugProtVarietyInfoResult.setDrugProtVarietyInfoList(drugProtVarietyInfoList);
        logger.debug("获取医药保护品种信息的返回值：【{}】", drugProtVarietyInfoResult);
        return drugProtVarietyInfoResult;
    }

    /**
     * 转换成DrugProtVarietyInfo
     *
     * @param tblDrugPrtcVarietyEntity 医药保护品种信息
     * @return DrugProtVarietyInfo
     */
    private DrugProtVarietyInfo convertToDrugProtVarietyInfo(TblDrugPrtcVarietyEntity tblDrugPrtcVarietyEntity) {
        DrugProtVarietyInfo drugProtVarietyInfo = new DrugProtVarietyInfo();
        // 保护品种编号
        drugProtVarietyInfo.setVarietyId(tblDrugPrtcVarietyEntity.getVarietyId());
        // 药品名称
        drugProtVarietyInfo.setDrugName(tblDrugPrtcVarietyEntity.getDrugName());
        // 公告号
        drugProtVarietyInfo.setPubNum(tblDrugPrtcVarietyEntity.getPubNum());
        // 药品批准文号
        drugProtVarietyInfo.setApprNum(tblDrugPrtcVarietyEntity.getAprvNum());
        // 保护级别
        drugProtVarietyInfo.setLevel(tblDrugPrtcVarietyEntity.getLevel());
        // 规格
        drugProtVarietyInfo.setSpec(tblDrugPrtcVarietyEntity.getSpec());
        // 保护起始日，格式：yyyy-MM-dd
        drugProtVarietyInfo.setStartDate(DateUtils.dateFormat(tblDrugPrtcVarietyEntity.getStartDate()));
        // 保护终止日，格式：yyyy-MM-dd
        drugProtVarietyInfo.setEndDate(DateUtils.dateFormat(tblDrugPrtcVarietyEntity.getEndDate()));
        // 生产企业
        drugProtVarietyInfo.setManuEnt(tblDrugPrtcVarietyEntity.getMfrsEnt());
        // 剂型
        drugProtVarietyInfo.setDosageForm(tblDrugPrtcVarietyEntity.getDosageForm());
        // 保护期限
        drugProtVarietyInfo.setProtDuration(tblDrugPrtcVarietyEntity.getLimitTime());
        logger.debug("转换成DrugProtVarietyInfo的值：【{}】", drugProtVarietyInfo);
        return drugProtVarietyInfo;
    }

    /**
     * 通过企业名称创建企业医疗器械信息查询参数
     *
     * @param entName 企业名称
     * @return EntMedicalDeviceInfoQryBO
     */
    private EntMedicalDeviceInfoQryBO createEntMedicalDeviceInfoQryBOByEntName(String entName) {
        // 通过企业名称获取主体身份代码
        String pripId = getPripIdByEntName(entName);

        EntMedicalDeviceInfoQryBO deviceInfoQryBO = new EntMedicalDeviceInfoQryBO();
        if (StringUtils.isNotBlank(pripId)) {
            // 主体身份代码
            deviceInfoQryBO.setPripId(pripId);
            return deviceInfoQryBO;
        }
        // 企业名称
        deviceInfoQryBO.setEntName(entName);
        return deviceInfoQryBO;
    }

    /**
     * 通过统一社会信用代码创建企业医疗器械信息查询参数
     *
     * @param usCreditCode 统一社会信用代码
     * @return EntMedicalDeviceInfoQryBO 若无此统一社会信用代码，则返回null
     */
    private EntMedicalDeviceInfoQryBO createEntMedicalDeviceInfoQryBOByUsCreditCode(String usCreditCode) {
        // 通过统一社会信用代码获取主体身份代码
        String pripId = getPripIdByUsCreditCode(usCreditCode);

        EntMedicalDeviceInfoQryBO deviceInfoQryBO = new EntMedicalDeviceInfoQryBO();
        if (StringUtils.isNotBlank(pripId)) {
            // 主体身份代码
            deviceInfoQryBO.setPripId(pripId);
            return deviceInfoQryBO;
        }

        /* 获取龙盾数据库企业唯一标识信息 */
        EntUniqueInfoQryBO entUniqueInfoQryBO = new EntUniqueInfoQryBO();
        entUniqueInfoQryBO.setUsCreditCode(usCreditCode);
        EntUniqueInfoBO entUniqueInfoBO = getEntUniqueInfoOfLd(entUniqueInfoQryBO);
        if (entUniqueInfoBO != null) {
            // 企业名称
            deviceInfoQryBO.setEntName(entUniqueInfoBO.getEntName());
            return deviceInfoQryBO;
        }

        /* 获取龙盾API企业信息 */
        EntKeyWordQryParam keyWordQryParam = new EntKeyWordQryParam();
        keyWordQryParam.setKeyword(usCreditCode);
        keyWordQryParam.setKeyWordTypeEnum(KeyWordTypeEnum.REG_NUM_OR_USCC_NUM);
        EntInfo entInfo = LdEntBaseInfoApiUtils.getEntInfoByKeyword(keyWordQryParam);
        if (entInfo != null) {
            // 企业名称
            deviceInfoQryBO.setEntName(entInfo.getEntName());
            return deviceInfoQryBO;
        }

        return null;
    }

    /**
     * 获取企业医疗器械信息
     *
     * @param pageNum                   页码
     * @param entMedicalDeviceInfoQryBO 查询参数
     * @return MedicalDevicesInfoResult
     */
    private MedicalDeviceInfoResult getEntMedicalDevicesInfoResult(int pageNum, EntMedicalDeviceInfoQryBO entMedicalDeviceInfoQryBO) {
        // 查询企业医疗器械信息总数
        logger.info("TblMedicalDevicesDao.getEntMedicalDeviceInfoCount()的参数值：【{}】", entMedicalDeviceInfoQryBO);
        int total = tblMedicalDevicesDao.getEntMedicalDeviceInfoCount(entMedicalDeviceInfoQryBO);
        logger.info("TblMedicalDevicesDao.getEntMedicalDeviceInfoCount()的返回值：【{}】", total);

        // 查询企业医药药品信息
        logger.info("TblMedicalDevicesDao.getEntMedicalDeviceInfoList()的参数值：【{}】", entMedicalDeviceInfoQryBO);
        List<TblMedicalDevicesEntity> medicalDevicesEntityList = tblMedicalDevicesDao.getEntMedicalDeviceInfoList(entMedicalDeviceInfoQryBO);
        logger.info("TblMedicalDevicesDao.getEntMedicalDeviceInfoList()的返回值的结果数：【{}】", BusinessHandlingUtils.getDefaultCollectionSize(medicalDevicesEntityList));

        int pageSize = entMedicalDeviceInfoQryBO.getPageSize();
        MedicalDeviceInfoResult medicalDeviceInfoResult = new MedicalDeviceInfoResult();
        // 总数
        medicalDeviceInfoResult.setTotal(total);
        // 总页数
        medicalDeviceInfoResult.setPageTotal(BusinessHandlingUtils.getPageTotal(total, pageSize));
        // 当前页码
        medicalDeviceInfoResult.setCurrentPageNum(pageNum);
        // 当前每页数量
        medicalDeviceInfoResult.setCurrentPageSize(pageSize);
        // 是否有更多
        medicalDeviceInfoResult.setNoMore(BusinessHandlingUtils.notHaveMore(total, entMedicalDeviceInfoQryBO.getStartIndex(), pageSize));
        // 企业医疗器械信息
        List<MedicalDeviceInfo> medicalDeviceInfoList = new ArrayList<>();
        for (TblMedicalDevicesEntity medicalDevicesEntity : medicalDevicesEntityList) {
            // 转换成MedicalDeviceInfo
            MedicalDeviceInfo medicalDeviceInfo = convertToMedicalDeviceInfo(medicalDevicesEntity);
            medicalDeviceInfoList.add(medicalDeviceInfo);
        }
        medicalDeviceInfoResult.setMedicalDeviceInfoList(medicalDeviceInfoList);
        logger.debug("获取企业医疗器械信息的返回值：【{}】", medicalDeviceInfoResult);
        return medicalDeviceInfoResult;
    }

    /**
     * 获取医疗器械信息
     *
     * @param pageNum                页码
     * @param medicalDeviceInfoQryBO 查询参数
     * @return MedicalDevicesInfoResult
     */
    private MedicalDeviceInfoResult getMedicalDevicesInfoResult(int pageNum, MedicalDeviceInfoQryBO medicalDeviceInfoQryBO) {
        // 查询医药药品信息总数
        logger.info("TblMedicalDevicesDao.getMedicalDeviceInfoCount()的参数值：【{}】", medicalDeviceInfoQryBO);
        int total = tblMedicalDevicesDao.getMedicalDeviceInfoCount(medicalDeviceInfoQryBO);
        logger.info("TblMedicalDevicesDao.getMedicalDeviceInfoCount()的返回值：【{}】", total);

        // 查询医药药品信息
        logger.info("TblMedicalDevicesDao.getMedicalDeviceInfoList()的参数值：【{}】", medicalDeviceInfoQryBO);
        List<TblMedicalDevicesEntity> medicalDevicesEntityList = tblMedicalDevicesDao.getMedicalDeviceInfoList(medicalDeviceInfoQryBO);
        logger.info("TblMedicalDevicesDao.getMedicalDeviceInfoList()的返回值的结果数：【{}】", BusinessHandlingUtils.getDefaultCollectionSize(medicalDevicesEntityList));

        int pageSize = medicalDeviceInfoQryBO.getPageSize();
        MedicalDeviceInfoResult medicalDeviceInfoResult = new MedicalDeviceInfoResult();
        // 总数
        medicalDeviceInfoResult.setTotal(total);
        // 总页数
        medicalDeviceInfoResult.setPageTotal(BusinessHandlingUtils.getPageTotal(total, pageSize));
        // 当前页码
        medicalDeviceInfoResult.setCurrentPageNum(pageNum);
        // 当前每页数量
        medicalDeviceInfoResult.setCurrentPageSize(pageSize);
        // 是否有更多
        medicalDeviceInfoResult.setNoMore(BusinessHandlingUtils.notHaveMore(total, medicalDeviceInfoQryBO.getStartIndex(), pageSize));
        // 企业医疗器械信息
        List<MedicalDeviceInfo> medicalDeviceInfoList = new ArrayList<>();
        for (TblMedicalDevicesEntity medicalDevicesEntity : medicalDevicesEntityList) {
            // 转换成MedicalDeviceInfo
            MedicalDeviceInfo medicalDeviceInfo = convertToMedicalDeviceInfo(medicalDevicesEntity);
            medicalDeviceInfoList.add(medicalDeviceInfo);
        }
        medicalDeviceInfoResult.setMedicalDeviceInfoList(medicalDeviceInfoList);
        logger.debug("获取医疗器械信息的返回值：【{}】", medicalDeviceInfoResult);
        return medicalDeviceInfoResult;
    }

    /**
     * 转换成MedicalDeviceInfo
     *
     * @param tblMedicalDevicesEntity 医疗器械信息
     * @return MedicalDeviceInfo
     */
    private MedicalDeviceInfo convertToMedicalDeviceInfo(TblMedicalDevicesEntity tblMedicalDevicesEntity) {
        MedicalDeviceInfo medicalDeviceInfo = new MedicalDeviceInfo();
        // 注册证编号
        medicalDeviceInfo.setRegCertNum(tblMedicalDevicesEntity.getRegCertNum());
        // 注册人名称
        medicalDeviceInfo.setRegName(tblMedicalDevicesEntity.getEntName());
        // 注册人住所
        medicalDeviceInfo.setDom(tblMedicalDevicesEntity.getDom());
        // 生产地址
        medicalDeviceInfo.setProdAddress(tblMedicalDevicesEntity.getOpLoc());
        // 代理人名称
        medicalDeviceInfo.setAgyName(tblMedicalDevicesEntity.getAgyName());
        // 代理人住所
        medicalDeviceInfo.setAgyDom(tblMedicalDevicesEntity.getAgyDom());
        // 产品名称
        medicalDeviceInfo.setProdName(tblMedicalDevicesEntity.getProdName());
        // 规格
        medicalDeviceInfo.setSpec(tblMedicalDevicesEntity.getSpec());
        // 结构及组成
        medicalDeviceInfo.setStructure(tblMedicalDevicesEntity.getStruc());
        // 适用范围
        medicalDeviceInfo.setAppScope(tblMedicalDevicesEntity.getAppScope());
        // 其他内容
        medicalDeviceInfo.setOthDesc(tblMedicalDevicesEntity.getOthDesc());
        // 备注
        medicalDeviceInfo.setRemark(tblMedicalDevicesEntity.getRemark());
        // 批准日期
        String apprDateStr = tblMedicalDevicesEntity.getAprvDate();
        medicalDeviceInfo.setApprDate(BusinessHandlingUtils.formatDateWithDot(apprDateStr, apprDateStr));
        // 有效期至
        String expDateStr = tblMedicalDevicesEntity.getExpDate();
        medicalDeviceInfo.setExpDate(BusinessHandlingUtils.formatDateWithDot(expDateStr, expDateStr));
        // 产品标准
        medicalDeviceInfo.setProdStd(tblMedicalDevicesEntity.getProdStd());
        // 变更日期
        String revDateStr = tblMedicalDevicesEntity.getRevDate();
        medicalDeviceInfo.setRevDate(BusinessHandlingUtils.formatDateWithDot(revDateStr, revDateStr));
        // 邮编
        medicalDeviceInfo.setPostalCode(tblMedicalDevicesEntity.getPostalCode());
        // 主要组成成分
        medicalDeviceInfo.setMajorConst(tblMedicalDevicesEntity.getMajorConst());
        // 预期用途
        medicalDeviceInfo.setIntendUse(tblMedicalDevicesEntity.getIntendUse());
        // 产品储存条件及有效期
        medicalDeviceInfo.setStgCond(tblMedicalDevicesEntity.getStgCond());
        // 审批部门
        medicalDeviceInfo.setApprDepart(tblMedicalDevicesEntity.getAprvDept());
        // 变更情况
        medicalDeviceInfo.setAlteration(tblMedicalDevicesEntity.getAlteration());
        // 详情页链接
        medicalDeviceInfo.setDetailLink(tblMedicalDevicesEntity.getDetlLink());
        logger.debug("转换成MedicalDeviceInfo的值：【{}】", medicalDeviceInfo);
        return medicalDeviceInfo;
    }
}
