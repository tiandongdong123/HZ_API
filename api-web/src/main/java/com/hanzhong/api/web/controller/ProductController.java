package com.hanzhong.api.web.controller;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.hanzhong.api.web.constant.CmnConstant;
import com.hanzhong.api.web.constant.cmnenum.EntStatusEnum;
import com.hanzhong.api.web.constant.cmnenum.ProductParamEnum;
import com.hanzhong.api.web.constant.cmnenum.ResultCodeEnum;
import com.hanzhong.api.web.model.JsonResult;
import com.hanzhong.api.web.model.bo.CompanyInfoBO;
import com.hanzhong.api.web.model.bo.CompanyQryBO;
import com.hanzhong.api.web.model.vo.CompanyInfoVO;
import com.hanzhong.api.web.service.BusinessService;
import com.hanzhong.api.web.service.LdDataService;
import com.hanzhong.api.web.util.CheckUtils;
import com.hanzhong.api.web.util.business.AdminCodeUtils;
import com.hanzhong.api.web.util.business.DevZoneCodeUtils;
import com.hanzhong.api.web.util.business.EntTypeCodeUtils;
import com.hanzhong.api.web.util.business.JsonResultUtils;
import com.hanzhong.api.web.util.business.area.AreaCodeUtils;
import com.hanzhong.api.web.util.business.longdun.LdApiUtils;
import com.hanzhong.api.web.util.business.longdun.constant.KeyWordTypeEnum;
import com.hanzhong.api.web.util.business.longdun.model.*;
import com.hanzhong.api.web.util.gaodemap.geocode.GeoCodeUtils;
import com.hanzhong.api.web.util.gaodemap.geocode.model.GcGeoCode;
import com.hanzhong.api.web.util.gaodemap.geocode.model.GcQryResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2018/11/5 16:29 
 *  @Version  V1.0   
 */
@Controller
@RequestMapping("/productOutInterface")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    /**
     * 分隔符-“-”
     */
    private static final String SMALL_MIDDLE_LINE_SEPARATOR = "-";
    /**
     * 分隔符-“/”
     */
    private static final String SLASH_SEPARATOR = "/";
    /**
     * 市辖区
     */
    private static final String MUNICIPAL_DISTRICT = "市辖区";
    /**
     * 线程池（注：设置线程池参数时，请详细了解ThreadPoolExecutor的相关知识，以免出现OOM）
     */
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1024), new ThreadFactoryBuilder().setNameFormat("threadPool_ldData_%d").build(),
            new ThreadPoolExecutor.DiscardPolicy());

    @Resource
    private BusinessService businessService;

    @Resource
    private LdDataService ldDataService;

    /**
     * 获取企业信息
     *
     * @param request 请求
     * @return JsonResult
     */
    @PostMapping("/product_companyInfoOut")
    @ResponseBody
    public JsonResult getCompanyInfo(HttpServletRequest request) {
        // 统一社会信用代码
        String corCodeTy = request.getParameter(ProductParamEnum.COR_CODE_TY.getValue());
        // 组织机构代码
        String corCode = request.getParameter(ProductParamEnum.COR_CODE.getValue());
        // 参数
        String productParam = request.getParameter("productpara");

        try {
            // 判断必填参数是否为空
            if (!meetNotBlankRequirement(request, productParam)) {
                return JsonResultUtils.build(ResultCodeEnum.PARAM_EMPTY, null);
            }

            // 判断统一社会信用代码格式是否正确
            if (ProductParamEnum.COR_CODE_TY.getValue().equals(productParam) && !CheckUtils.isUnifiedSocialCreditCode(corCodeTy)) {
                return JsonResultUtils.build(ResultCodeEnum.PARAM_FORMAT_ERROR, null);
            }

            // 判断组织机构代码格式是否正确
            if (ProductParamEnum.COR_CODE.getValue().equals(productParam) && !CheckUtils.isOrganizationCode(corCode.replace(SMALL_MIDDLE_LINE_SEPARATOR, ""))) {
                return JsonResultUtils.build(ResultCodeEnum.PARAM_FORMAT_ERROR, null);
            }

            // 设置查询对象
            CompanyQryBO companyQryBO = setCompanyQryBO(request, productParam);
            // 获取(在营)企业信息
            CompanyInfoVO companyInfoVO = getCompanyInfo(companyQryBO);
            if (companyInfoVO != null) {
                return JsonResultUtils.build(ResultCodeEnum.SUCCESS, companyInfoVO);
            }
            return JsonResultUtils.build(ResultCodeEnum.NO_DATA, null);
        } catch (Exception e) {
            logger.error("获取企业信息出现异常：", e);
            return JsonResultUtils.build(ResultCodeEnum.FAIL, null);
        }
    }

    /**
     * 是否符合参数非空
     *
     * @param request      请求
     * @param productParam 产品参数
     * @return boolean true：符合
     */
    private boolean meetNotBlankRequirement(HttpServletRequest request, String productParam) {
        return StringUtils.isNotBlank(productParam) && StringUtils.isNotBlank(request.getParameter(productParam));
    }

    /**
     * 获取(在营)企业信息
     *
     * @param companyQryBO 企业查询参数
     * @return 若查询不到企业，则返回null
     */
    private CompanyInfoVO getCompanyInfo(CompanyQryBO companyQryBO) {
        // 获取企业信息
        List<CompanyInfoBO> companyInfoBOList = businessService.getCompanyInfoList(companyQryBO);

        CompanyInfoBO companyInfoBO = new CompanyInfoBO();
        boolean doingBusinessFlag = false;
        // 若有多个查询结果，则优先返回“在营”企业信息
        for (CompanyInfoBO infoBO : companyInfoBOList) {
            if (EntStatusEnum.DOING_BUSINESS.getKey().equals(companyInfoBO.getEntStatus())) {
                doingBusinessFlag = true;
                companyInfoBO = infoBO;
            }
        }
        if (!companyInfoBOList.isEmpty() && !doingBusinessFlag) {
            companyInfoBO = companyInfoBOList.get(0);
        }

        // 通过企业名称查询
        String companyName = companyQryBO.getCompanyName();
        if (StringUtils.isNotBlank(companyName)) {
            RegisterInfoQryParam infoQryParam = new RegisterInfoQryParam();
            infoQryParam.setEntName(companyName);
            RegisterInfo registerInfo = getRegisterInfo(infoQryParam);
            if (registerInfo == null) {
                logger.warn("第三方通过企业名称【{}】查询未查询到企业信息", companyName);
                return null;
            }
            // 异步记录龙盾企业信息数据
            asyncRecordLdRegisterInfoData(registerInfo);
            return mergeCompanyInfo(companyInfoBO, registerInfo);
        }

        // 通过统一社会信用代码
        String usCreditCode = companyQryBO.getUsCreditCode();
        if (StringUtils.isNotBlank(usCreditCode)) {
            EntKeyWordQryParam keyWordQryParam = new EntKeyWordQryParam();
            keyWordQryParam.setKeyword(companyQryBO.getUsCreditCode());
            keyWordQryParam.setKeyWordTypeEnum(KeyWordTypeEnum.REG_NUM_OR_USCC_NUM);
            EntInfo entInfo = getUniqueEntInfo(keyWordQryParam);
            if (entInfo == null) {
                logger.warn("第三方通过统一社会信用代码【{}】查询未查询到企业信息", usCreditCode);
                return null;
            }
            RegisterInfoQryParam infoQryParam = new RegisterInfoQryParam();
            infoQryParam.setEntName(entInfo.getEntName());
            RegisterInfo registerInfo = getRegisterInfo(infoQryParam);
            if (registerInfo == null) {
                return null;
            }
            // 异步记录龙盾企业信息数据
            asyncRecordLdRegisterInfoData(registerInfo);
            return mergeCompanyInfo(companyInfoBO, registerInfo);
        }

        // 通过组织结构代码查询
        String orgCode = companyQryBO.getOrgCode();
        if (companyInfoBOList.isEmpty()) {
            logger.info("通过组织结构代码【{}】查询的企业信息为空！", orgCode);
            return null;
        }

        RegisterInfoQryParam infoQryParam = new RegisterInfoQryParam();
        infoQryParam.setEntName(companyInfoBO.getEntName());
        RegisterInfo registerInfo = getRegisterInfo(infoQryParam);
        if (registerInfo == null) {
            logger.info("通过组织结构代码【{}】查询企业名称为【{}】的企业信息为空！", orgCode, companyInfoBO.getEntName());
            return null;
        }
        // 异步记录龙盾企业信息数据
        asyncRecordLdRegisterInfoData(registerInfo);
        return mergeCompanyInfo(companyInfoBO, registerInfo);
    }

    /**
     * 转换企业信息
     *
     * @param companyInfoBO 企业信息
     * @return CompanyInfoVO
     */
    private CompanyInfoVO convertToCompanyInfoVO(CompanyInfoBO companyInfoBO) {
        CompanyInfoVO companyInfoVO = new CompanyInfoVO();
        // 复制属性值
        BeanUtils.copyProperties(companyInfoBO, companyInfoVO);

        // 注册资金（数据库中的金额默认单位为万元）
        BigDecimal regCap = companyInfoVO.getRegCap();
        if (regCap != null) {
            companyInfoVO.setRegCap(regCap.setScale(6, BigDecimal.ROUND_HALF_UP));
        }
        String code;
        // 企业(机构)类型
        code = companyInfoVO.getEntType();
        if (StringUtils.isNotBlank(code)) {
            companyInfoVO.setEntType(EntTypeCodeUtils.getNameByCode(code));
        }
        // 企业状态
        code = companyInfoVO.getEntStatus();
        if (StringUtils.isNotBlank(code)) {
            companyInfoVO.setEntStatus(EntStatusEnum.getName(code));
        }
        // 经营(驻在)期限至
        if (companyInfoVO.getOpFrom() != null && companyInfoVO.getOpTo() == null) {
            companyInfoVO.setOpTo(CmnConstant.LONG_TERM_DATE);
        }
        // 登记机关
        code = companyInfoVO.getRegOrg();
        if (StringUtils.isNotBlank(code)) {
            companyInfoVO.setRegOrg(AdminCodeUtils.getNameByCode(code));
        }
        // 住所所在行政区划
        companyInfoVO.setDomDistrict(getDomDistrictNameByCode(companyInfoBO.getProvince(), companyInfoBO.getCity(), companyInfoBO.getArea()));
        // 住所所在开发区
        code = companyInfoVO.getEcoTecDevZone();
        if (StringUtils.isNotBlank(code)) {
            companyInfoVO.setEcoTecDevZone(DevZoneCodeUtils.getNameByCode(code));
        }
        return companyInfoVO;
    }

    /**
     * 设置查询对象
     *
     * @param request      请求
     * @param productParam 查询参数类型
     * @return CompanyQryBO
     */
    private CompanyQryBO setCompanyQryBO(HttpServletRequest request, String productParam) {
        CompanyQryBO companyQryBO = new CompanyQryBO();
        if (ProductParamEnum.COMPANY_NAME.getValue().equals(productParam)) {
            companyQryBO.setCompanyName(request.getParameter(productParam));
        } else if (ProductParamEnum.COR_CODE_TY.getValue().equals(productParam)) {
            companyQryBO.setUsCreditCode(request.getParameter(productParam));
        } else if (ProductParamEnum.COR_CODE.getValue().equals(productParam)) {
            companyQryBO.setOrgCode(request.getParameter(productParam));
        } else {
            logger.error("productParam的参数值【{}】不存在", productParam);
            throw new IllegalArgumentException("productParam的参数值【" + productParam + "】不存在");
        }
        return companyQryBO;
    }

    /**
     * 根据行政区划码获取对应省市区名称（以“/”分隔）
     *
     * @param areaCode 行政区划码，如：420203
     * @return String 如：湖北省/黄石市/西塞山区
     */
    private String getDomDistrictNameByCode(Integer provinceCode, Integer cityCode, Integer areaCode) {
        // 省
        String provinceName = provinceCode == null ? "" : AreaCodeUtils.getAreaNameByCode(provinceCode.toString());
        // 市
        String cityName = cityCode == null ? "" : AreaCodeUtils.getAreaNameByCode(cityCode.toString());
        // 区
        String districtName = areaCode == null ? "" : AreaCodeUtils.getAreaNameByCode(areaCode.toString());
        return connectAdministrativeDivision(provinceName, cityName, districtName);
    }

    /**
     * 获取唯一的企业
     *
     * @param keyWordQryParam 企业列表信息查询参数
     * @return EntInfo
     */
    private EntInfo getUniqueEntInfo(EntKeyWordQryParam keyWordQryParam) {
        // 根据关键字获取企业列表信息
        ApiResult apiResult = LdApiUtils.getEntApiResultByKeyword(keyWordQryParam);
        if (apiResult == null) {
            return null;
        }

        // 判断结果数据是否为空
        List<EntInfo> entInfoList = (List<EntInfo>) apiResult.getResultData();
        if (entInfoList == null || entInfoList.isEmpty()) {
            return null;
        }

        if (KeyWordTypeEnum.ENT_NAME.equals(keyWordQryParam.getKeyWordTypeEnum())) {
            for (EntInfo entInfo : entInfoList) {
                if (entInfo.getEntName().equals(keyWordQryParam.getKeyword())) {
                    return entInfo;
                }
            }
        } else if (KeyWordTypeEnum.REG_NUM_OR_USCC_NUM.equals(keyWordQryParam.getKeyWordTypeEnum())) {
            return entInfoList.get(0);
        }
        return null;
    }

    /**
     * 获取企业基本信息
     *
     * @param infoQryParam 企业基本信息查询参数
     * @return RegisterInfo
     */
    private RegisterInfo getRegisterInfo(RegisterInfoQryParam infoQryParam) {
        // 获取企业登记信息
        ApiResult apiResult = LdApiUtils.getRegisterInfoApiResult(infoQryParam);
        if (apiResult == null) {
            return null;
        }

        // 判断结果数据是否为空
        List<RegisterInfo> registerInfoList = (List<RegisterInfo>) apiResult.getResultData();
        if (registerInfoList == null || registerInfoList.isEmpty()) {
            return null;
        }

        return registerInfoList.get(0);
    }

    /**
     * 合并企业结果信息
     *
     * @param companyInfoBO 本地库企业信息
     * @param registerInfo  第三方提供企业信息
     * @return CompanyInfoVO
     */
    private CompanyInfoVO mergeCompanyInfo(CompanyInfoBO companyInfoBO, RegisterInfo registerInfo) {
        CompanyInfoVO companyInfoVO = new CompanyInfoVO();
        companyInfoVO.setUsCreditCode(CheckUtils.isUnifiedSocialCreditCode(registerInfo.getShxydm()) ? registerInfo.getShxydm() : "");
        companyInfoVO.setOrgCode(CheckUtils.isOrganizationCode(registerInfo.getOrgId()) ? registerInfo.getOrgId() : "");
        companyInfoVO.setEntName(registerInfo.getEntName());
        companyInfoVO.setIndustry(registerInfo.getIndustry());
        companyInfoVO.setRegCap(registerInfo.getRegCap());
        companyInfoVO.setRegCapCur(registerInfo.getRegCapCur());
        companyInfoVO.setEntType(registerInfo.getEntType());
        companyInfoVO.setEntStatus(registerInfo.getEntStatus());
        companyInfoVO.setOpScope(registerInfo.getOpScope());
        companyInfoVO.setEsDate(registerInfo.getEsDate());
        companyInfoVO.setApprDate(registerInfo.getApprDate());
        companyInfoVO.setOpFrom(registerInfo.getOpFrom());
        companyInfoVO.setOpTo(StringUtils.isBlank(registerInfo.getOpTo()) || CmnConstant.LONG_TERM_WROD.equals(registerInfo.getOpTo()) ? CmnConstant.LONG_TERM_DATE : registerInfo.getOpTo());
        companyInfoVO.setName(registerInfo.getFrdb());
        companyInfoVO.setRegOrg(registerInfo.getRegOrg());
        companyInfoVO.setDom(registerInfo.getDom());
        companyInfoVO.setPostalCode(companyInfoBO.getPostalCode());
        // 住所行政区划
        String domDistrict = getDomDistrictNameByCode(companyInfoBO.getProvince(), companyInfoBO.getCity(), companyInfoBO.getArea());
        if (StringUtils.isBlank(domDistrict)) {
            domDistrict = getAdministrativeDivision(registerInfo.getDom());
        }
        companyInfoVO.setDomDistrict(domDistrict);
        companyInfoVO.setEcoTecDevZone(DevZoneCodeUtils.getNameByCode(companyInfoBO.getEcoTecDevZone()));
        return companyInfoVO;
    }

    /**
     * 获取行政区划信息
     *
     * @param address 地址
     * @return String
     */
    private String getAdministrativeDivision(String address) {
        GcQryResult gcQryResult = GeoCodeUtils.getGeoCodeInfo(address, "");
        // 判断是否有查询结果
        boolean haveResultFlag = GeoCodeUtils.isSuccess(gcQryResult) && Integer.parseInt(StringUtils.defaultIfEmpty(gcQryResult.getCount(), "0")) > 0;
        if (!haveResultFlag) {
            logger.warn("address：【{}】，通过地址获取的地理编码信息为空！", address);
            return StringUtils.EMPTY;
        }

        List<GcGeoCode> gcGeoCodeList = gcQryResult.getGcGeoCodes();
        // 若只有一条查询结果，则取之
        if (gcGeoCodeList != null && gcGeoCodeList.size() == 1) {
            GcGeoCode gcGeoCode = gcGeoCodeList.get(0);
            return connectAdministrativeDivision(gcGeoCode.getProvince(), gcGeoCode.getCity(), gcGeoCode.getDistrict());
        }
        return StringUtils.EMPTY;
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
        if (StringUtils.isNotBlank(province)) {
            strBuilder.append(province);
        }
        // 市，若为“市辖区”则不拼
        if (StringUtils.isNotBlank(city) && !MUNICIPAL_DISTRICT.equals(city) && !city.equals(province)) {
            strBuilder.append(SLASH_SEPARATOR + city);
        }
        // 区
        if (StringUtils.isNotBlank(district)) {
            strBuilder.append(SLASH_SEPARATOR + district);
        }
        return strBuilder.toString();
    }

    /**
     * 异步记录龙盾企业信息数据
     *
     * @param registerInfo 企业登记信息
     */
    private void asyncRecordLdRegisterInfoData(RegisterInfo registerInfo) {
        threadPoolExecutor.execute(() ->
                ldDataService.recordRegisterInfo(registerInfo)
        );
    }
}
