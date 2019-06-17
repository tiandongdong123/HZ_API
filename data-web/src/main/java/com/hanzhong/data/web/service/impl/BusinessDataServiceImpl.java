package com.hanzhong.data.web.service.impl;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.hanzhong.data.web.constant.CmnConstant;
import com.hanzhong.data.web.constant.EntStatusEnum;
import com.hanzhong.data.web.dao.master.TblBusinessDao;
import com.hanzhong.data.web.dao.slave.LdRegisterInfoDao;
import com.hanzhong.data.web.model.EnterpriseBaseInfo;
import com.hanzhong.data.web.model.EnterpriseBaseInfoQryParam;
import com.hanzhong.data.web.model.bo.EnterpriseInfoBO;
import com.hanzhong.data.web.model.bo.EnterpriseInfoQryBO;
import com.hanzhong.data.web.model.bo.LdRegInfoQryBO;
import com.hanzhong.data.web.model.entity.slave.LdRegisterInfoEntity;
import com.hanzhong.data.web.service.BusinessDataService;
import com.hanzhong.data.web.util.*;
import com.hanzhong.data.web.util.gaodemap.base.GdMapUtils;
import com.hanzhong.data.web.util.gaodemap.geocode.GeoCodeUtils;
import com.hanzhong.data.web.util.gaodemap.geocode.ReGeoCodeUtils;
import com.hanzhong.data.web.util.gaodemap.geocode.model.*;
import com.hanzhong.data.web.util.longdun.entbaseinfo.LdEntBaseInfoApiUtils;
import com.hanzhong.data.web.util.longdun.entbaseinfo.constant.KeyWordTypeEnum;
import com.hanzhong.data.web.util.longdun.entbaseinfo.model.EntInfo;
import com.hanzhong.data.web.util.longdun.entbaseinfo.model.EntKeyWordQryParam;
import com.hanzhong.data.web.util.longdun.entbaseinfo.model.RegisterInfo;
import com.hanzhong.data.web.util.longdun.entbaseinfo.model.RegisterInfoQryParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2018/11/8 9:14 
 *  @Version  V1.0   
 */
@Service("businessDataService")
public class BusinessDataServiceImpl implements BusinessDataService {

    private static final Logger logger = LoggerFactory.getLogger(BusinessDataServiceImpl.class);

    /**
     * 分隔符-“/”
     */
    private static final String SLASH_SEPARATOR = "/";
    /**
     * 市辖区
     */
    private static final String MUNICIPAL_DISTRICT = "市辖区";
    /**
     * 空数组字符串
     */
    private static final String EMPTY_ARRAY_STR = "[]";
    /**
     * 线程池（注：设置线程池参数时，请详细了解ThreadPoolExecutor的相关知识，以免出现OOM）
     */
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1024), new ThreadFactoryBuilder().setNameFormat("threadPool_ldData_%d").build(),
            new ThreadPoolExecutor.DiscardPolicy());

    @Resource
    private TblBusinessDao tblBusinessDao;
    @Resource
    private LdRegisterInfoDao ldRegisterInfoDao;

    /**
     * 获取企业基本信息
     *
     * @param qryParam 查询参数
     * @return EnterpriseBaseInfo
     */
    @Override
    public EnterpriseBaseInfo getEnterpriseBaseInfo(EnterpriseBaseInfoQryParam qryParam) {
        EnterpriseBaseInfo baseInfo;
        // 获取本地库企业信息
        EnterpriseInfoBO localEntInfoBO = getEnterpriseInfoOfLocal(qryParam);
        // 获取龙盾库企业信息
        LdRegisterInfoEntity registerInfoEntity = getEnterpriseInfoOfLd(qryParam);

        /* 若龙盾库中存在此企业信息且当前时间与企业信息数据更新时间差小于数据更新间隔，则取龙盾库已有数据，与本地库企业信息进行合并返回 */
        long currentTimeStamp = DateUtils.getCurrentTimeStamp();
        // 企业信息合并标记
        boolean mergeFlag = registerInfoEntity != null && registerInfoEntity.getUpdateTime() != null && (currentTimeStamp - registerInfoEntity.getUpdateTime()) <= CmnConstant.THIRD_PARTY_DATA_UPDATE_INTERVAL;
        if (mergeFlag) {
            logger.debug("合并企业信息的参数值：EnterpriseInfoBO：【{}】，LdRegisterInfoEntity：【{}】", localEntInfoBO, registerInfoEntity);
            // 合并企业信息
            baseInfo = mergeEnterpriseInfo(localEntInfoBO, registerInfoEntity);
            logger.debug("合并企业信息的返回值：【{}】", baseInfo);
            return baseInfo;
        }

        /* 通过企业名称从龙盾获取企业信息 */
        String entName = qryParam.getEntName();
        if (StringUtils.isNotBlank(entName)) {
            // 通过企业名称获取企业信息
            baseInfo = getEntInfoByEntName(localEntInfoBO, entName);
            return baseInfo;
        }

        /* 通过统一社会信用代码从龙盾获取企业信息 */
        String usCreditCode = qryParam.getUsCreditCode();
        if (StringUtils.isNotBlank(usCreditCode)) {
            // 通过统一社会信用代码获取企业信息
            baseInfo = getEntInfoByUsCreditCode(localEntInfoBO, usCreditCode);
            return baseInfo;
        }

        /* 通过组织结构代码查询企业信息 */
        String orgCode = qryParam.getOrgCode();
        // 通过组织结构代码查询企业信息
        baseInfo = getEntInfoByOrgCode(localEntInfoBO, orgCode);
        return baseInfo;
    }

    /**
     * 获取本地库企业信息
     *
     * @param qryParam 查询参数
     * @return EnterpriseInfoBO 若未查询到数据，则返回null
     */
    private EnterpriseInfoBO getEnterpriseInfoOfLocal(EnterpriseBaseInfoQryParam qryParam) {
        // 创建企业信息查询参数
        EnterpriseInfoQryBO qryParamBO = createEnterpriseInfoQryParam(qryParam);
        logger.debug("获取本地库企业信息参数：转换前：【{}】，转换后：【{}】", qryParam, qryParamBO);
        // 获取企业信息
        logger.debug("TblBusinessDao.getEnterpriseInfoListByQryParam()的参数值：【{}】", qryParamBO);
        List<EnterpriseInfoBO> enterpriseInfoBOList = tblBusinessDao.getEnterpriseInfoListByQryParam(qryParamBO);
        logger.debug("TblBusinessDao.getEnterpriseInfoListByQryParam()的返回值：【{}】", enterpriseInfoBOList);
        if (enterpriseInfoBOList == null || enterpriseInfoBOList.isEmpty()) {
            return null;
        }

        // 若有多个查询结果，则优先返回“在营”企业信息
        for (EnterpriseInfoBO infoBO : enterpriseInfoBOList) {
            if (EntStatusEnum.DOING_BUSINESS.getKey().equals(infoBO.getEntStatus())) {
                return infoBO;
            }
        }
        return enterpriseInfoBOList.get(0);
    }

    /**
     * 获取龙盾库企业信息
     *
     * @param qryParam 查询参数
     * @return EnterpriseInfoBO 若未查询到数据，则返回null
     */
    private LdRegisterInfoEntity getEnterpriseInfoOfLd(EnterpriseBaseInfoQryParam qryParam) {
        // 创建企业信息查询参数
        LdRegInfoQryBO qryParamBO = createLdRegInfoQryParam(qryParam);
        logger.debug("获取龙盾库企业信息参数：转换前：【{}】，转换后：【{}】", qryParam, qryParamBO);
        // 查询企业信息
        logger.debug("LdRegisterInfoDao.getRegisterInfoListByQryParam()的参数值：【{}】", qryParamBO);
        List<LdRegisterInfoEntity> registerInfoEntityList = ldRegisterInfoDao.getRegisterInfoListByQryParam(qryParamBO);
        logger.debug("LdRegisterInfoDao.getRegisterInfoListByQryParam()的返回值：【{}】", registerInfoEntityList);

        if (registerInfoEntityList == null || registerInfoEntityList.isEmpty()) {
            logger.info("参数：【{}】，龙盾库未查询到企业信息", qryParamBO);
            return null;
        }
        return registerInfoEntityList.get(0);
    }

    /**
     * 创建企业信息查询参数
     *
     * @param qryParam 查询参数
     * @return EnterpriseInfoQryParamBO
     */
    private EnterpriseInfoQryBO createEnterpriseInfoQryParam(EnterpriseBaseInfoQryParam qryParam) {
        EnterpriseInfoQryBO qryBO = new EnterpriseInfoQryBO();
        // 企业名称
        qryBO.setEntName(qryParam.getEntName());
        // 统一社会信用代码
        qryBO.setUsCreditCode(qryParam.getUsCreditCode());
        // 组织机构代码
        qryBO.setOrgCode(qryParam.getOrgCode());
        return qryBO;
    }

    /**
     * 创建龙盾企业信息查询参数
     *
     * @param qryParam 查询参数
     * @return EnterpriseInfoQryParamBO
     */
    private LdRegInfoQryBO createLdRegInfoQryParam(EnterpriseBaseInfoQryParam qryParam) {
        LdRegInfoQryBO qryBO = new LdRegInfoQryBO();
        // 企业名称
        qryBO.setEntName(qryParam.getEntName());
        // 统一社会信用代码
        qryBO.setUsCreditCode(qryParam.getUsCreditCode());
        // 组织机构代码
        qryBO.setOrgCode(qryParam.getOrgCode());
        return qryBO;
    }

    /**
     * 通过企业名称获取企业信息
     *
     * @param enterpriseInfoBO 本地库企业信息
     * @param entName          企业名称
     * @return EnterpriseBaseInfo 若通过企业名称未查询到企业，则返回null
     */
    private EnterpriseBaseInfo getEntInfoByEntName(EnterpriseInfoBO enterpriseInfoBO, String entName) {
        // 通过企业名称获取龙盾企业信息
        RegisterInfo registerInfo = getRegInfoFromLdByEntName(entName);

        if (registerInfo != null) {
            logger.debug("合并企业信息的参数值：EnterpriseInfoBO：【{}】，RegisterInfo：【{}】", enterpriseInfoBO, registerInfo);
            EnterpriseBaseInfo baseInfo = mergeEnterpriseInfo(enterpriseInfoBO, registerInfo);
            logger.debug("合并企业信息的返回值：【{}】", baseInfo);

            // 异步记录龙盾企业信息数据
            asyncRecordLdRegisterInfoData(registerInfo);
            return baseInfo;
        }
        return null;
    }

    /**
     * 通过统一社会信用代码获取企业信息
     *
     * @param enterpriseInfoBO 本地库企业信息
     * @param usCreditCode     统一社会信用代码
     * @return EnterpriseBaseInfo 若通过统一社会信用代码未查询到企业，则返回null
     */
    private EnterpriseBaseInfo getEntInfoByUsCreditCode(EnterpriseInfoBO enterpriseInfoBO, String usCreditCode) {
        // 通过统一社会信用代码从龙盾获取企业信息
        EntInfo entInfo = getEntInfoFromLdByUsCreditCode(usCreditCode);
        if (entInfo == null) {
            logger.info("通过统一社会信用代码【{}】查询在龙盾未查询到该企业信息", usCreditCode);
            return null;
        }

        // 通过企业名称获取龙盾企业信息
        EnterpriseBaseInfo baseInfo = getEntInfoByEntName(enterpriseInfoBO, entInfo.getEntName());
        logger.debug("通过统一社会信用代码【{}】查询到的企业信息：【{}】", usCreditCode, baseInfo);
        return baseInfo;
    }

    /**
     * 通过组织机构代码获取企业信息
     *
     * @param enterpriseInfoBO 本地库企业信息
     * @param orgCode          组织机构代码
     * @return EnterpriseBaseInfo 若通过组织机构代码未查询到企业，则返回null
     */
    private EnterpriseBaseInfo getEntInfoByOrgCode(EnterpriseInfoBO enterpriseInfoBO, String orgCode) {
        if (enterpriseInfoBO == null) {
            logger.info("通过组织结构代码【{}】在本地未查询到企业信息", orgCode);
            return null;
        }
        // 通过企业名称获取龙盾企业信息
        EnterpriseBaseInfo baseInfo = getEntInfoByEntName(enterpriseInfoBO, enterpriseInfoBO.getEntName());
        logger.debug("通过组织结构代码【{}】查询到的企业信息：【{}】", orgCode, baseInfo);
        return baseInfo;
    }

    /**
     * 合并企业信息
     *
     * @param enterpriseInfoBO     本地库企业信息
     * @param ldRegisterInfoEntity 龙盾库企业信息
     * @return EnterpriseBaseInfo
     */
    private EnterpriseBaseInfo mergeEnterpriseInfo(EnterpriseInfoBO enterpriseInfoBO, LdRegisterInfoEntity ldRegisterInfoEntity) {
        EnterpriseBaseInfo baseInfo = new EnterpriseBaseInfo();
        // 统一社会信息代码
        baseInfo.setUsCreditCode(CheckUtils.isUnifiedSocialCreditCode(ldRegisterInfoEntity.getShxydm()) ? ldRegisterInfoEntity.getShxydm() : "");
        // 组织机构代码
        baseInfo.setOrgCode(CheckUtils.isOrganizationCode(ldRegisterInfoEntity.getOrgId()) ? ldRegisterInfoEntity.getOrgId() : "");
        // 企业名称
        baseInfo.setEntName(ldRegisterInfoEntity.getEntName());
        // 曾用名
        baseInfo.setUsedName(ldRegisterInfoEntity.getOldName());
        // 行业领域
        baseInfo.setIndustry(ldRegisterInfoEntity.getIndustry());
        // 行业领域代码
        baseInfo.setIndustryCode(ldRegisterInfoEntity.getIndustryCode());
        // 注册资金（默认单位：万）
        baseInfo.setRegCap(ldRegisterInfoEntity.getRegCap());
        // 注册资本(金)币种
        baseInfo.setRegCapCur(ldRegisterInfoEntity.getRegCapCur());
        // 企业类型
        baseInfo.setEntType(ldRegisterInfoEntity.getEntType());
        // 企业状态
        baseInfo.setEntStatus(ldRegisterInfoEntity.getEntStatus());
        //  经营(业务)范围
        baseInfo.setOpScope(ldRegisterInfoEntity.getOpScope());
        // 成立日期(格式：yyyy-MM-dd)
        baseInfo.setEsDate(DateUtils.dateFormat(ldRegisterInfoEntity.getEsDate()));
        // 核准日期(格式：yyyy-MM-dd)
        baseInfo.setApprDate(DateUtils.dateFormat(ldRegisterInfoEntity.getApprDate()));
        // 死亡日期(格式：yyyy-MM-dd)
        baseInfo.setEndDate(DateUtils.dateFormat(ldRegisterInfoEntity.getEndDate()));
        // 吊销日期(格式：yyyy-MM-dd)
        baseInfo.setRevDate(DateUtils.dateFormat(ldRegisterInfoEntity.getRevDate()));
        // 注销日期(格式：yyyy-MM-dd)
        baseInfo.setCanDate(DateUtils.dateFormat(ldRegisterInfoEntity.getCanDate()));
        // 经营(驻在)期限自(格式：yyyy-MM-dd)
        baseInfo.setOpFrom(DateUtils.dateFormat(ldRegisterInfoEntity.getOpFrom()));
        // 经营(驻在)期限至(格式：yyyy-MM-dd)
        baseInfo.setOpTo((ldRegisterInfoEntity.getOpFrom() != null && ldRegisterInfoEntity.getOpTo() == null) ? CmnConstant.LONG_TERM_DATE : DateUtils.dateFormat(ldRegisterInfoEntity.getOpTo()));
        // 法定代表人
        baseInfo.setName(ldRegisterInfoEntity.getFrdb());
        // 登记机关
        baseInfo.setRegOrg(ldRegisterInfoEntity.getRegOrg());
        // 邮政编码
        baseInfo.setPostalCode(enterpriseInfoBO == null ? "" : ObjectUtils.defaultString(enterpriseInfoBO.getPostalCode()));
        // 住所
        String dom = ldRegisterInfoEntity.getDom();
        baseInfo.setDom(dom);
        // 住所行政区划
        String domDistrict = getAdministrativeDivision(ldRegisterInfoEntity.getDom(), ldRegisterInfoEntity.getJwd());
        baseInfo.setDomDistrict(domDistrict);
        // 住所所在经济开发区
        baseInfo.setEcoTecDevZone(enterpriseInfoBO != null ? DevZoneCodeUtils.getNameByCode(enterpriseInfoBO.getEcoTecDevZone()) : "");
        return baseInfo;
    }

    /**
     * 合并企业信息
     *
     * @param enterpriseInfoBO 本地库企业信息
     * @param registerInfo     龙盾库企业信息
     * @return EnterpriseBaseInfo
     */
    private EnterpriseBaseInfo mergeEnterpriseInfo(EnterpriseInfoBO enterpriseInfoBO, RegisterInfo registerInfo) {
        EnterpriseBaseInfo baseInfo = new EnterpriseBaseInfo();
        // 统一社会信息代码
        baseInfo.setUsCreditCode(CheckUtils.isUnifiedSocialCreditCode(registerInfo.getShxydm()) ? registerInfo.getShxydm() : "");
        // 组织机构代码
        baseInfo.setOrgCode(CheckUtils.isOrganizationCode(registerInfo.getOrgId()) ? registerInfo.getOrgId() : "");
        // 企业名称
        baseInfo.setEntName(registerInfo.getEntName());
        // 曾用名
        baseInfo.setUsedName(registerInfo.getOldName());
        // 行业领域
        baseInfo.setIndustry(registerInfo.getIndustry());
        // 行业领域代码
        baseInfo.setIndustryCode(registerInfo.getIndustryCode());
        // 注册资金（默认单位：万）
        baseInfo.setRegCap(registerInfo.getRegCap());
        // 注册资本(金)币种
        baseInfo.setRegCapCur(registerInfo.getRegCapCur());
        // 企业类型
        baseInfo.setEntType(registerInfo.getEntType());
        // 企业状态
        baseInfo.setEntStatus(registerInfo.getEntStatus());
        //  经营(业务)范围
        baseInfo.setOpScope(registerInfo.getOpScope());
        // 成立日期(格式：yyyy-MM-dd)
        baseInfo.setEsDate(registerInfo.getEsDate());
        // 核准日期(格式：yyyy-MM-dd)
        baseInfo.setApprDate(registerInfo.getApprDate());
        // 死亡日期(格式：yyyy-MM-dd)
        baseInfo.setEndDate(registerInfo.getEndDate());
        // 吊销日期(格式：yyyy-MM-dd)
        baseInfo.setRevDate(registerInfo.getRevDate());
        // 注销日期(格式：yyyy-MM-dd)
        baseInfo.setCanDate(registerInfo.getCanDate());
        // 经营(驻在)期限自(格式：yyyy-MM-dd)
        baseInfo.setOpFrom(registerInfo.getOpFrom());
        // 经营(驻在)期限至(格式：yyyy-MM-dd)
        baseInfo.setOpTo((registerInfo.getOpFrom() != null && registerInfo.getOpTo() == null) ? CmnConstant.LONG_TERM_DATE : registerInfo.getOpTo());
        // 法定代表人
        baseInfo.setName(registerInfo.getFrdb());
        // 登记机关
        baseInfo.setRegOrg(registerInfo.getRegOrg());
        // 邮政编码
        baseInfo.setPostalCode(enterpriseInfoBO == null ? "" : ObjectUtils.defaultString(enterpriseInfoBO.getPostalCode()));
        // 住所
        String dom = registerInfo.getDom();
        baseInfo.setDom(dom);
        // 住所行政区划
        String domDistrict = getAdministrativeDivision(registerInfo.getDom(), registerInfo.getJwd());
        baseInfo.setDomDistrict(domDistrict);
        // 住所所在经济开发区
        baseInfo.setEcoTecDevZone(enterpriseInfoBO != null ? DevZoneCodeUtils.getNameByCode(enterpriseInfoBO.getEcoTecDevZone()) : "");
        return baseInfo;
    }

    /**
     * 获取行政区划信息
     *
     * @param address 地址
     * @param lonLat  经纬度
     * @return String
     */
    private String getAdministrativeDivision(String address, String lonLat) {
        String formatLonLat = BusinessHandlingUtils.formatLonLat(lonLat);
        if (StringUtils.isNotBlank(formatLonLat)) {
            String[] array = formatLonLat.split(BusinessHandlingUtils.SEPARATOR_COMMA);
            return getAdministrativeDivisionByLonLat(array[0], array[1]);
        }
        if (StringUtils.isNotBlank(address)) {
            return getAdministrativeDivisionByAddress(address);
        }
        return StringUtils.EMPTY;
    }

    /**
     * 获取行政区划信息
     *
     * @param address 地址
     * @return String
     */
    private String getAdministrativeDivisionByAddress(String address) {
        logger.debug("GeoCodeUtils.getGeoCodeInfo()的参数值：address：【{}】", address);
        GeoCodeQryResult geoCodeQryResult = GeoCodeUtils.getGeoCodeInfo(address, "");
        logger.debug("GeoCodeUtils.getGeoCodeInfo()的返回值：【{}】", geoCodeQryResult);

        // 判断是否有查询结果
        boolean haveResultFlag = GeoCodeUtils.isSuccess(geoCodeQryResult) && Integer.parseInt(StringUtils.defaultIfEmpty(geoCodeQryResult.getCount(), "0")) > 0;
        if (!haveResultFlag) {
            logger.warn("address：【{}】，通过地址获取的地理编码信息为空！", address);
            return StringUtils.EMPTY;
        }

        List<GeoCodeInfo> geoCodeInfoList = geoCodeQryResult.getGeoCodeInfoList();
        // 若只有一条查询结果，则取之
        if (geoCodeInfoList != null && !geoCodeInfoList.isEmpty()) {
            GeoCodeInfo geoCodeInfo = geoCodeInfoList.get(0);
            return connectAdministrativeDivision(geoCodeInfo.getProvince(), geoCodeInfo.getCity(), geoCodeInfo.getDistrict());
        }
        return StringUtils.EMPTY;
    }

    /**
     * 获取行政区划信息
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @return String
     */
    private String getAdministrativeDivisionByLonLat(String longitude, String latitude) {
        logger.debug("ReGeoCodeUtils.getReGeoCodeInfo()的参数值：经纬度：【{},{}】", longitude, latitude);
        ReGeoCodeQryResult qryResult = ReGeoCodeUtils.getReGeoCodeInfo(longitude, latitude);
        logger.debug("ReGeoCodeUtils.getReGeoCodeInfo()的返回值：【{}】", qryResult);

        // 判断是否有查询结果
        boolean haveResultFlag = GdMapUtils.isSuccess(qryResult);
        if (!haveResultFlag) {
            logger.warn("经纬度：【{},{}】，通过经纬度获取的逆地理编码信息为空！", longitude, latitude);
            return StringUtils.EMPTY;
        }

        ReGeoCodeInfo reGeoCodeInfo = qryResult.getReGeoCodeInfo();
        AddressComponent addressComponent = reGeoCodeInfo == null ? new AddressComponent() : reGeoCodeInfo.getAddressComponent();
        return connectAdministrativeDivision(addressComponent.getProvince(), addressComponent.getCity(), addressComponent.getDistrict());
    }

    /**
     * 拼接省市区名称（以“/”分隔）
     *
     * @param province 省
     * @param city     市
     * @param district 区
     * @return String 如：湖北省/黄石市/西塞山区
     */
    private String connectAdministrativeDivision(String province, String city, String district) {
        StringBuilder strBuilder = new StringBuilder();
        // 省
        if (StringUtils.isNotBlank(province) && !EMPTY_ARRAY_STR.equals(province)) {
            strBuilder.append(province);
        }
        // 市，若为“市辖区”则不拼
        if (StringUtils.isNotBlank(city) && !MUNICIPAL_DISTRICT.equals(city) && !EMPTY_ARRAY_STR.equals(city) && !city.equals(province)) {
            strBuilder.append(SLASH_SEPARATOR + city);
        }
        // 区
        if (StringUtils.isNotBlank(district) && !EMPTY_ARRAY_STR.equals(district)) {
            strBuilder.append(SLASH_SEPARATOR + district);
        }
        return strBuilder.toString();
    }

    /**
     * 通过企业名称获取龙盾企业信息
     *
     * @param entName 企业名称
     * @return RegisterInfo 若未查询到企业，则返回null
     */
    private RegisterInfo getRegInfoFromLdByEntName(String entName) {
        RegisterInfoQryParam infoQryParam = new RegisterInfoQryParam();
        infoQryParam.setEntName(entName);
        logger.debug("LdEntBaseInfoApiUtils.getRegInfo()的参数值：【{}】", entName);
        // 获取企业登记信息
        RegisterInfo registerInfo = LdEntBaseInfoApiUtils.getRegInfo(infoQryParam);
        logger.debug("LdEntBaseInfoApiUtils.getRegInfo()的返回值：【{}】", registerInfo);
        return registerInfo;
    }

    /**
     * 通过统一社会信用代码获取龙盾企业信息
     *
     * @param usCreditCode 统一社会信用代码
     * @return RegisterInfo 若未查询到企业，则返回null
     */
    private EntInfo getEntInfoFromLdByUsCreditCode(String usCreditCode) {
        EntKeyWordQryParam keyWordQryParam = new EntKeyWordQryParam();
        keyWordQryParam.setKeyword(usCreditCode);
        keyWordQryParam.setKeyWordTypeEnum(KeyWordTypeEnum.REG_NUM_OR_USCC_NUM);
        logger.debug("LdEntBaseInfoApiUtils.getEntInfoByKeyword()的参数值：【{}】", usCreditCode);
        // 根据关键字获取企业列表信息
        EntInfo entInfo = LdEntBaseInfoApiUtils.getEntInfoByKeyword(keyWordQryParam);
        logger.debug("LdEntBaseInfoApiUtils.getEntInfoByKeyword()的返回值：【{}】", entInfo);
        return entInfo;
    }

    /**
     * 异步记录龙盾企业信息数据
     *
     * @param registerInfo 企业登记信息
     */
    private void asyncRecordLdRegisterInfoData(RegisterInfo registerInfo) {
        threadPoolExecutor.execute(() ->
                // 记录龙盾企业信息
                recordRegisterInfoOfLd(registerInfo)
        );
    }

    /**
     * 记录龙盾企业信息
     *
     * @param registerInfo 企业信息
     * @return int
     */
    private int recordRegisterInfoOfLd(RegisterInfo registerInfo) {
        LdRegInfoQryBO qryParamBO = new LdRegInfoQryBO();
        qryParamBO.setEntName(registerInfo.getEntName());
        // 查询企业信息
        List<LdRegisterInfoEntity> registerInfoEntityList = ldRegisterInfoDao.getRegisterInfoListByQryParam(qryParamBO);

        // 若不存在此企业，则添加，否则更新
        if (registerInfoEntityList.isEmpty()) {
            LdRegisterInfoEntity registerInfoEntity = convertToRegisterInfoEntity("", registerInfo);
            registerInfoEntity.setPripId(createRandomPripId());
            return ldRegisterInfoDao.addRegisterInfo(registerInfoEntity);
        }

        LdRegisterInfoEntity dbRegisterInfoEntity = registerInfoEntityList.get(0);
        LdRegisterInfoEntity registerInfoEntity = convertToRegisterInfoEntity(dbRegisterInfoEntity.getPripId(), registerInfo);
        registerInfoEntity.setId(dbRegisterInfoEntity.getId());
        return ldRegisterInfoDao.updateRegisterInfo(registerInfoEntity);
    }

    /**
     * 转换成LdRegisterInfoEntity
     *
     * @param pripId       主体身份代码
     * @param registerInfo 企业登记信息
     * @return LdRegisterInfoEntity
     */
    private LdRegisterInfoEntity convertToRegisterInfoEntity(String pripId, RegisterInfo registerInfo) {
        LdRegisterInfoEntity registerInfoEntity = new LdRegisterInfoEntity();
        // 主体身份代码
        registerInfoEntity.setPripId(pripId);
        // 企业名称
        registerInfoEntity.setEntName(registerInfo.getEntName());
        // 曾用名
        registerInfoEntity.setOldName(registerInfo.getOldName());
        // 英文名称
        registerInfoEntity.setEngName(registerInfo.getEngName());
        // 统一社会信用代码
        registerInfoEntity.setShxydm(registerInfo.getShxydm());
        // 组织机构代码
        registerInfoEntity.setOrgId(registerInfo.getOrgId());
        // 法定代表人
        registerInfoEntity.setFrdb(registerInfo.getFrdb());
        // 企业类型
        registerInfoEntity.setEntType(registerInfo.getEntType());
        // 企业状态
        registerInfoEntity.setEntStatus(registerInfo.getEntStatus());
        // 注册资金
        registerInfoEntity.setRegCap(registerInfo.getRegCap());
        // 注册资金币种
        registerInfoEntity.setRegCapCur(registerInfo.getRegCapCur());
        // 行业领域
        registerInfoEntity.setIndustry(registerInfo.getIndustry());
        // 行业领域代码
        registerInfoEntity.setIndustryCode(registerInfo.getIndustryCode());
        // 经营业务范围
        registerInfoEntity.setOpScope(registerInfo.getOpScope());
        // 登记机关
        registerInfoEntity.setRegOrg(registerInfo.getRegOrg());
        // 住所
        registerInfoEntity.setDom(registerInfo.getDom());
        // 经纬度
        registerInfoEntity.setJwd(registerInfo.getJwd());
        // 登记省
        registerInfoEntity.setProvince(registerInfo.getProvince());
        // 企业官网
        registerInfoEntity.setWebSite(registerInfo.getWebSite());
        // 成立日期
        registerInfoEntity.setEsDate(parseByDateFormat(registerInfo.getEsDate()));
        // 经营期限自
        registerInfoEntity.setOpFrom(parseByDateFormat(registerInfo.getOpFrom()));
        // 经营期限至
        registerInfoEntity.setOpTo(CmnConstant.LONG_TERM_WORD.equals(registerInfo.getOpTo()) ? null : parseByDateFormat(registerInfo.getOpTo()));
        // 核准日期
        registerInfoEntity.setApprDate(parseByDateFormat(registerInfo.getApprDate()));
        // 死亡日期
        registerInfoEntity.setEndDate(parseByDateFormat(registerInfo.getEndDate()));
        // 吊销日期
        registerInfoEntity.setRevDate(parseByDateFormat(registerInfo.getRevDate()));
        // 注销日期
        registerInfoEntity.setCanDate(parseByDateFormat(registerInfo.getCanDate()));
        // 更新时间戳
        registerInfoEntity.setUpdateTime(DateUtils.getCurrentTimeStamp());
        return registerInfoEntity;
    }

    /**
     * 解析成Date
     *
     * @param dateStr 时间字符串，格式：yyyy-MM-dd
     * @return Date
     */
    private Date parseByDateFormat(String dateStr) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        return DateUtils.parse(dateStr, DateUtils.DEFAULT_DATE_FORMAT);
    }

    /**
     * 生成随机主体身份代码
     *
     * @return String
     */
    private String createRandomPripId() {
        return "LD_" + UUID.randomUUID();
    }

}
