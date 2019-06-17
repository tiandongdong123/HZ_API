package com.hanzhong.data.web.service.impl;

import com.hanzhong.data.web.constant.EntStatusEnum;
import com.hanzhong.data.web.constant.PatentQryFieldEnum;
import com.hanzhong.data.web.constant.StandardQryFieldEnum;
import com.hanzhong.data.web.dao.master.TblBusinessDao;
import com.hanzhong.data.web.dao.master.TblPatentDao;
import com.hanzhong.data.web.dao.master.TblStandardDao;
import com.hanzhong.data.web.dao.slave.LdRegisterInfoDao;
import com.hanzhong.data.web.model.*;
import com.hanzhong.data.web.model.bo.*;
import com.hanzhong.data.web.model.entity.master.TblPatentEntity;
import com.hanzhong.data.web.model.entity.master.TblStandardEntity;
import com.hanzhong.data.web.service.EntIntellectualPropertyService;
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
 * 企业知识产权
 *
 * @author yifei
 * @date 2019/3/30
 */
@Service("intIntellectualPropertyService")
public class EntIntellectualPropertyServiceImpl implements EntIntellectualPropertyService {
    private static final Logger logger = LoggerFactory.getLogger(EntIntellectualPropertyServiceImpl.class);

    @Resource
    private TblPatentDao tblPatentDao;
    @Resource
    private TblStandardDao tblStandardDao;
    @Resource
    private TblBusinessDao tblBusinessDao;
    @Resource
    private LdRegisterInfoDao ldRegisterInfoDao;

    /**
     * 分页获取企业专利信息
     *
     * @param qryParam 查询参数
     * @return EntPatentInfoResult
     */
    @Override
    public EntPatentInfoResult getEntPatentInfoByPage(EntPatentInfoQryParam qryParam) {
        PatentQryFieldEnum patentQryFieldEnum = qryParam.getPatentQryFieldEnum();
        int pageNum = BusinessHandlingUtils.getDefaultPageNum(qryParam.getPageNum());
        int pageSize = BusinessHandlingUtils.getDefaultPageSize(qryParam.getPageSize());
        int startIndex = BusinessHandlingUtils.getStartIndex(pageNum, pageSize);

        switch (patentQryFieldEnum) {
            case ENT_NAME:
                EntPatentInfoQryBO patentInfoQryBO = createEntPatentInfoQryBOByEntName(qryParam.getEntName());
                patentInfoQryBO.setStartIndex(startIndex);
                patentInfoQryBO.setPageSize(pageSize);
                return getEntPatentInfoResult(pageNum, patentInfoQryBO);
            case UNIFIED_SOCIAL_CREDIT_CODE:
                EntPatentInfoQryBO patentInfoQryBO2 = createEntPatentInfoQryBOByUsCreditCode(qryParam.getUsCreditCode());
                if (patentInfoQryBO2 == null) {
                    logger.warn("通过统一社会信用代码【{}】未查询到企业专利信息", qryParam.getUsCreditCode());
                    return EntPatentInfoResult.init(pageNum, pageSize);
                }
                patentInfoQryBO2.setStartIndex(startIndex);
                patentInfoQryBO2.setPageSize(pageSize);
                return getEntPatentInfoResult(pageNum, patentInfoQryBO2);
            case PATENT_ID:
                PatentInfoQryBO patentInfoQryBO3 = new PatentInfoQryBO();
                patentInfoQryBO3.setPatentId(qryParam.getPatentId());
                patentInfoQryBO3.setStartIndex(startIndex);
                patentInfoQryBO3.setPageSize(pageSize);
                return getPatentInfoResult(pageNum, patentInfoQryBO3);
            default:
                logger.warn("获取企业专利信息此枚举值【{}】未作相应处理", patentQryFieldEnum);
                return EntPatentInfoResult.init(pageNum, pageSize);
        }
    }

    /**
     * 分页获取企业标准信息
     *
     * @param qryParam 查询参数
     * @return EntStandardInfoResult
     */
    @Override
    public EntStandardInfoResult getEntStandardInfoByPage(EntStandardInfoQryParam qryParam) {
        StandardQryFieldEnum standardQryFieldEnum = qryParam.getStandardQryFieldEnum();
        int pageNum = BusinessHandlingUtils.getDefaultPageNum(qryParam.getPageNum());
        int pageSize = BusinessHandlingUtils.getDefaultPageSize(qryParam.getPageSize());
        int startIndex = BusinessHandlingUtils.getStartIndex(pageNum, pageSize);

        switch (standardQryFieldEnum) {
            case ENT_NAME:
                EntStandardInfoQryBO standardInfoQryBO = createEntStandardInfoQryBOByEntName(qryParam.getEntName());
                standardInfoQryBO.setStartIndex(startIndex);
                standardInfoQryBO.setPageSize(pageSize);
                return getEnStandardInfoResult(pageNum, standardInfoQryBO);
            case UNIFIED_SOCIAL_CREDIT_CODE:
                EntStandardInfoQryBO standardInfoQryBO2 = createEntStandardInfoQryBOByUsCreditCode(qryParam.getUsCreditCode());
                if (standardInfoQryBO2 == null) {
                    logger.warn("通过统一社会信用代码【{}】未查询到企业标准信息", qryParam.getUsCreditCode());
                    return EntStandardInfoResult.init(pageNum, pageSize);
                }
                standardInfoQryBO2.setStartIndex(startIndex);
                standardInfoQryBO2.setPageSize(pageSize);
                return getEnStandardInfoResult(pageNum, standardInfoQryBO2);
            case STANDARD_NUM:
                StandardInfoQryBO standardInfoQryBO3 = new StandardInfoQryBO();
                standardInfoQryBO3.setStandardNum(qryParam.getStandardNum());
                standardInfoQryBO3.setStartIndex(startIndex);
                standardInfoQryBO3.setPageSize(pageSize);
                return getStandardInfoResult(pageNum, standardInfoQryBO3);
            default:
                logger.warn("获取企业标准信息此枚举值【{}】未作相应处理", standardQryFieldEnum);
                return EntStandardInfoResult.init(pageNum, pageSize);
        }
    }

    /**
     * 通过企业名称创建企业专利信息查询参数
     *
     * @param entName 企业名称
     * @return EntPatentInfoQryBO
     */
    private EntPatentInfoQryBO createEntPatentInfoQryBOByEntName(String entName) {
        // 通过企业名称获取主体身份代码
        String pripId = getPripIdByEntName(entName);

        EntPatentInfoQryBO patentInfoQryBO = new EntPatentInfoQryBO();
        if (StringUtils.isNotBlank(pripId)) {
            // 主体身份代码
            patentInfoQryBO.setPripId(pripId);
            return patentInfoQryBO;
        }
        // 企业名称
        patentInfoQryBO.setEntName(entName);
        return patentInfoQryBO;
    }

    /**
     * 通过统一社会信用代码创建企业专利信息查询参数
     *
     * @param usCreditCode 统一社会信用代码
     * @return EntPatentInfoQryBO 若无此统一社会信用代码，则返回null
     */
    private EntPatentInfoQryBO createEntPatentInfoQryBOByUsCreditCode(String usCreditCode) {
        // 通过统一社会信用代码获取主体身份代码
        String pripId = getPripIdByUsCreditCode(usCreditCode);

        EntPatentInfoQryBO patentInfoQryBO = new EntPatentInfoQryBO();
        if (StringUtils.isNotBlank(pripId)) {
            // 主体身份代码
            patentInfoQryBO.setPripId(pripId);
            return patentInfoQryBO;
        }

        /* 获取龙盾数据库企业唯一标识信息 */
        EntUniqueInfoQryBO entUniqueInfoQryBO = new EntUniqueInfoQryBO();
        entUniqueInfoQryBO.setUsCreditCode(usCreditCode);
        EntUniqueInfoBO entUniqueInfoBO = getEntUniqueInfoOfLd(entUniqueInfoQryBO);
        if (entUniqueInfoBO != null) {
            // 企业名称
            patentInfoQryBO.setEntName(entUniqueInfoBO.getEntName());
            return patentInfoQryBO;
        }

        /* 获取龙盾API企业信息 */
        EntKeyWordQryParam keyWordQryParam = new EntKeyWordQryParam();
        keyWordQryParam.setKeyword(usCreditCode);
        keyWordQryParam.setKeyWordTypeEnum(KeyWordTypeEnum.REG_NUM_OR_USCC_NUM);
        EntInfo entInfo = LdEntBaseInfoApiUtils.getEntInfoByKeyword(keyWordQryParam);
        if (entInfo != null) {
            // 企业名称
            patentInfoQryBO.setEntName(entInfo.getEntName());
            return patentInfoQryBO;
        }

        return null;
    }

    /**
     * 获取企业专利信息
     *
     * @param pageNum            页码
     * @param entPatentInfoQryBO 查询参数
     * @return EntPatentInfoResult
     */
    private EntPatentInfoResult getEntPatentInfoResult(int pageNum, EntPatentInfoQryBO entPatentInfoQryBO) {
        // 查询企业专利信息总数
        logger.info("TblPatentDao.getEntPatentInfoCount()的参数值：【{}】", entPatentInfoQryBO);
        int total = tblPatentDao.getEntPatentInfoCount(entPatentInfoQryBO);
        logger.info("TblPatentDao.getEntPatentInfoCount()的返回值：【{}】", total);

        // 查询企业专利信息
        logger.info("TblPatentDao.getEntPatentInfoList()的参数值：【{}】", entPatentInfoQryBO);
        List<TblPatentEntity> patentEntityList = tblPatentDao.getEntPatentInfoList(entPatentInfoQryBO);
        logger.info("TblPatentDao.getEntPatentInfoList()的返回值：【{}】", entPatentInfoQryBO);

        int pageSize = entPatentInfoQryBO.getPageSize();
        EntPatentInfoResult entPatentInfoResult = new EntPatentInfoResult();
        // 总数
        entPatentInfoResult.setTotal(total);
        // 总页数
        entPatentInfoResult.setPageTotal(BusinessHandlingUtils.getPageTotal(total, pageSize));
        // 当前页码
        entPatentInfoResult.setCurrentPageNum(pageNum);
        // 当前每页数量
        entPatentInfoResult.setCurrentPageSize(pageSize);
        // 是否有更多
        entPatentInfoResult.setNoMore(BusinessHandlingUtils.notHaveMore(total, entPatentInfoQryBO.getStartIndex(), pageSize));
        // 企业专利信息
        List<EntPatentInfo> patentInfoList = new ArrayList<>();
        for (TblPatentEntity patentEntity : patentEntityList) {
            // 转换成EntPatentInfo
            EntPatentInfo patentInfo = convertToEntPatentInfo(patentEntity);
            patentInfoList.add(patentInfo);
        }
        entPatentInfoResult.setEntPatentInfoList(patentInfoList);
        logger.debug("获取企业专利信息的返回值：【{}】", entPatentInfoResult);
        return entPatentInfoResult;
    }

    /**
     * 获取专利信息
     *
     * @param pageNum         页码
     * @param patentInfoQryBO 查询参数
     * @return EntPatentInfoResult
     */
    private EntPatentInfoResult getPatentInfoResult(int pageNum, PatentInfoQryBO patentInfoQryBO) {
        // 查询专利信息总数
        logger.info("TblPatentDao.getPatentInfoCount()的参数值：【{}】", patentInfoQryBO);
        int total = tblPatentDao.getPatentInfoCount(patentInfoQryBO);
        logger.info("TblPatentDao.getPatentInfoCount()的返回值：【{}】", total);

        // 查询专利信息
        logger.info("TblPatentDao.getPatentInfoList()的参数值：【{}】", patentInfoQryBO);
        List<TblPatentEntity> patentEntityList = tblPatentDao.getPatentInfoList(patentInfoQryBO);
        logger.info("TblPatentDao.getPatentInfoList()的返回值：【{}】", patentInfoQryBO);

        int pageSize = patentInfoQryBO.getPageSize();
        EntPatentInfoResult entPatentInfoResult = new EntPatentInfoResult();
        // 总数
        entPatentInfoResult.setTotal(total);
        // 总页数
        entPatentInfoResult.setPageTotal(BusinessHandlingUtils.getPageTotal(total, pageSize));
        // 当前页码
        entPatentInfoResult.setCurrentPageNum(pageNum);
        // 当前每页数量
        entPatentInfoResult.setCurrentPageSize(pageSize);
        // 是否有更多
        entPatentInfoResult.setNoMore(BusinessHandlingUtils.notHaveMore(total, patentInfoQryBO.getStartIndex(), pageSize));
        // 企业专利信息
        List<EntPatentInfo> patentInfoList = new ArrayList<>();
        for (TblPatentEntity patentEntity : patentEntityList) {
            // 转换成EntPatentInfo
            EntPatentInfo patentInfo = convertToEntPatentInfo(patentEntity);
            patentInfoList.add(patentInfo);
        }
        entPatentInfoResult.setEntPatentInfoList(patentInfoList);
        logger.debug("获取专利信息的返回值：【{}】", entPatentInfoResult);
        return entPatentInfoResult;
    }

    /**
     * 转换成EntPatentInfo
     *
     * @param tblPatentEntity 专利信息
     * @return EntPatentInfo
     */
    private EntPatentInfo convertToEntPatentInfo(TblPatentEntity tblPatentEntity) {
        EntPatentInfo patentInfo = new EntPatentInfo();
        // 申请号
        patentInfo.setAppNum(tblPatentEntity.getPatentId());
        // 申请日，格式：yyyy-MM-dd
        String appDate = tblPatentEntity.getAppDate();
        patentInfo.setAppDate(BusinessHandlingUtils.formatDateWithDot(appDate, appDate));
        // 申请（专利权）人
        patentInfo.setAppName(tblPatentEntity.getProposerName());
        // 专利号
        patentInfo.setPatentNum(tblPatentEntity.getPatentNum());
        // 专利名
        patentInfo.setPatentTitle(tblPatentEntity.getPatentTitle());
        // 专利类型
        patentInfo.setPatentType(tblPatentEntity.getPatentType());
        // 摘要
        patentInfo.setSummary(tblPatentEntity.getSummary());
        // 公开（公告）号
        patentInfo.setPubNum(tblPatentEntity.getPubNum());
        // 公开（公告）日，格式：yyyy-MM-dd
        String pubDate = tblPatentEntity.getPubDate();
        patentInfo.setPubDate(BusinessHandlingUtils.formatDateWithDot(pubDate, pubDate));
        // 颁证日，格式：yyyy-MM-dd
        String awardDate = tblPatentEntity.getAwardDate();
        patentInfo.setAwardDate(BusinessHandlingUtils.formatDateWithDot(awardDate, awardDate));
        // PCT公开
        patentInfo.setPctPub(tblPatentEntity.getPctPub());
        // PCT申请
        patentInfo.setPctApp(tblPatentEntity.getPctApp());
        // PCT信息
        patentInfo.setPctInfo(tblPatentEntity.getPctInfo());
        logger.debug("转换成EntPatentInfo的值：【{}】", patentInfo);
        return patentInfo;
    }

    /**
     * 通过企业名称创建企业标准信息查询参数
     *
     * @param entName 企业名称
     * @return EntPatentInfoQryBO
     */
    private EntStandardInfoQryBO createEntStandardInfoQryBOByEntName(String entName) {
        // 通过企业名称获取主体身份代码
        String pripId = getPripIdByEntName(entName);

        EntStandardInfoQryBO standardInfoQryBO = new EntStandardInfoQryBO();
        if (StringUtils.isNotBlank(pripId)) {
            // 主体身份代码
            standardInfoQryBO.setPripId(pripId);
            return standardInfoQryBO;
        }
        // 企业名称
        standardInfoQryBO.setEntName(entName);
        return standardInfoQryBO;
    }

    /**
     * 通过统一社会信用代码创建企业标准信息查询参数
     *
     * @param usCreditCode 统一社会信用代码
     * @return EntPatentInfoQryBO 若无此统一社会信用代码，则返回null
     */
    private EntStandardInfoQryBO createEntStandardInfoQryBOByUsCreditCode(String usCreditCode) {
        // 通过统一社会信用代码获取主体身份代码
        String pripId = getPripIdByUsCreditCode(usCreditCode);

        EntStandardInfoQryBO standardInfoQryBO = new EntStandardInfoQryBO();
        if (StringUtils.isNotBlank(pripId)) {
            // 主体身份代码
            standardInfoQryBO.setPripId(pripId);
            return standardInfoQryBO;
        }

        /* 获取龙盾数据库企业唯一标识信息 */
        EntUniqueInfoQryBO entUniqueInfoQryBO = new EntUniqueInfoQryBO();
        entUniqueInfoQryBO.setUsCreditCode(usCreditCode);
        EntUniqueInfoBO entUniqueInfoBO = getEntUniqueInfoOfLd(entUniqueInfoQryBO);
        if (entUniqueInfoBO != null) {
            // 企业名称
            standardInfoQryBO.setEntName(entUniqueInfoBO.getEntName());
            return standardInfoQryBO;
        }

        /* 获取龙盾API企业信息 */
        EntKeyWordQryParam keyWordQryParam = new EntKeyWordQryParam();
        keyWordQryParam.setKeyword(usCreditCode);
        keyWordQryParam.setKeyWordTypeEnum(KeyWordTypeEnum.REG_NUM_OR_USCC_NUM);
        EntInfo entInfo = LdEntBaseInfoApiUtils.getEntInfoByKeyword(keyWordQryParam);
        if (entInfo != null) {
            // 企业名称
            standardInfoQryBO.setEntName(entInfo.getEntName());
            return standardInfoQryBO;
        }

        return null;
    }

    /**
     * 通过企业名称获取主体身份代码
     *
     * @param entName 企业名称
     * @return String 若无此企业，则返回null
     */
    private String getPripIdByEntName(String entName) {
        EntUniqueInfoQryBO uniqueInfoQryBO = new EntUniqueInfoQryBO();
        // 企业名称
        uniqueInfoQryBO.setEntName(entName);
        EntUniqueInfoBO entUniqueInfoBO = getEntUniqueInfoOfLocal(uniqueInfoQryBO);
        if (entUniqueInfoBO == null) {
            return null;
        }
        return entUniqueInfoBO.getPripId();
    }

    /**
     * 通过统一社会信用代码获取主体身份代码
     *
     * @param usCreditCode 企业名称
     * @return String 若无此企业，则返回null
     */
    private String getPripIdByUsCreditCode(String usCreditCode) {
        EntUniqueInfoQryBO uniqueInfoQryBO = new EntUniqueInfoQryBO();
        // 统一社会信用代码
        uniqueInfoQryBO.setUsCreditCode(usCreditCode);

        // 获取本地数据库企业唯一标识信息
        EntUniqueInfoBO entUniqueInfoBO = getEntUniqueInfoOfLocal(uniqueInfoQryBO);
        if (entUniqueInfoBO == null) {
            return null;
        }
        return entUniqueInfoBO.getPripId();
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
     * 获取企业标准信息
     *
     * @param pageNum              页码
     * @param entStandardInfoQryBO 查询参数
     * @return EntStandardInfoResult
     */
    private EntStandardInfoResult getEnStandardInfoResult(int pageNum, EntStandardInfoQryBO entStandardInfoQryBO) {
        // 查询企业标准信息总数
        logger.info("TblStandardDao.getEntStandardInfoCount()的参数值：【{}】", entStandardInfoQryBO);
        int total = tblStandardDao.getEntStandardInfoCount(entStandardInfoQryBO);
        logger.info("TblStandardDao.getEntStandardInfoCount()的返回值：【{}】", total);

        // 查询企业标准信息
        logger.info("TblStandardDao.getEntStandardInfoList()的参数值：【{}】", entStandardInfoQryBO);
        List<TblStandardEntity> standardEntityList = tblStandardDao.getEntStandardInfoList(entStandardInfoQryBO);
        logger.info("TblStandardDao.getEntStandardInfoList()的返回值：【{}】", standardEntityList);

        int pageSize = entStandardInfoQryBO.getPageSize();
        EntStandardInfoResult entStandardInfoResult = new EntStandardInfoResult();
        // 总数
        entStandardInfoResult.setTotal(total);
        // 总页数
        entStandardInfoResult.setPageTotal(BusinessHandlingUtils.getPageTotal(total, pageSize));
        // 当前页码
        entStandardInfoResult.setCurrentPageNum(pageNum);
        // 当前每页数量
        entStandardInfoResult.setCurrentPageSize(pageSize);
        // 是否有更多
        entStandardInfoResult.setNoMore(BusinessHandlingUtils.notHaveMore(total, entStandardInfoQryBO.getStartIndex(), pageSize));
        // 企业标准信息
        List<EntStandardInfo> standardInfoList = new ArrayList<>();
        for (TblStandardEntity standardEntity : standardEntityList) {
            // 转换成EntStandardInfo
            EntStandardInfo standardInfo = convertToEntStandardInfo(standardEntity);
            standardInfoList.add(standardInfo);
        }
        entStandardInfoResult.setEntStandardInfoList(standardInfoList);
        return entStandardInfoResult;
    }

    /**
     * 获取标准信息
     *
     * @param pageNum           页码
     * @param standardInfoQryBO 查询参数
     * @return EntStandardInfoResult
     */
    private EntStandardInfoResult getStandardInfoResult(int pageNum, StandardInfoQryBO standardInfoQryBO) {
        // 查询标准信息总数
        logger.info("TblStandardDao.getStandardInfoCount()的参数值：【{}】", standardInfoQryBO);
        int total = tblStandardDao.getStandardInfoCount(standardInfoQryBO);
        logger.info("TblStandardDao.getStandardInfoCount()的返回值：【{}】", total);

        // 查询标准信息
        logger.info("TblStandardDao.getStandardInfoList()的参数值：【{}】", standardInfoQryBO);
        List<TblStandardEntity> standardEntityList = tblStandardDao.getStandardInfoList(standardInfoQryBO);
        logger.info("TblStandardDao.getStandardInfoList()的返回值：【{}】", standardEntityList);

        int pageSize = standardInfoQryBO.getPageSize();
        EntStandardInfoResult entStandardInfoResult = new EntStandardInfoResult();
        // 总数
        entStandardInfoResult.setTotal(total);
        // 总页数
        entStandardInfoResult.setPageTotal(BusinessHandlingUtils.getPageTotal(total, pageSize));
        // 当前页码
        entStandardInfoResult.setCurrentPageNum(pageNum);
        // 当前每页数量
        entStandardInfoResult.setCurrentPageSize(pageSize);
        // 是否有更多
        entStandardInfoResult.setNoMore(BusinessHandlingUtils.notHaveMore(total, standardInfoQryBO.getStartIndex(), pageSize));
        // 企业标准信息
        List<EntStandardInfo> standardInfoList = new ArrayList<>();
        for (TblStandardEntity standardEntity : standardEntityList) {
            // 转换成EntStandardInfo
            EntStandardInfo standardInfo = convertToEntStandardInfo(standardEntity);
            standardInfoList.add(standardInfo);
        }
        entStandardInfoResult.setEntStandardInfoList(standardInfoList);
        return entStandardInfoResult;
    }

    /**
     * 转换成EntStandardInfo
     *
     * @param tblStandardEntity 标准信息
     * @return EntStandardInfo
     */
    private EntStandardInfo convertToEntStandardInfo(TblStandardEntity tblStandardEntity) {
        EntStandardInfo standardInfo = new EntStandardInfo();
        // 标准编号
        standardInfo.setStandardNum(tblStandardEntity.getStandardNum());
        // 新标准号
        standardInfo.setNewStandardNum(tblStandardEntity.getNewStandardNum());
        // 标准名称
        standardInfo.setStandardName(tblStandardEntity.getStandardName());
        // 标准类型
        standardInfo.setStandardType(tblStandardEntity.getStandardType());
        // 标准水平
        standardInfo.setStandardLevel(tblStandardEntity.getStandardLevel());
        // 标准状态
        standardInfo.setStandardStatus(tblStandardEntity.getStandardStatus());
        // 中文关键词
        standardInfo.setKeywords(tblStandardEntity.getKeywords());
        // 起草单位
        standardInfo.setDraftUnit(tblStandardEntity.getDraftUnit());
        // 发布日期，格式：yyyy-MM-dd
        standardInfo.setIssueDate(DateUtils.dateFormat(tblStandardEntity.getIssueDate()));
        // 实施日期，格式：yyyy-MM-dd
        standardInfo.setForceDate(DateUtils.dateFormat(tblStandardEntity.getForceDate()));
        // 替代标准
        standardInfo.setOldStandard(tblStandardEntity.getOldStandard());
        // 废止日期，格式：yyyy-MM-dd
        standardInfo.setAvoidDate(tblStandardEntity.getAvoidDate());
        return standardInfo;
    }
}
