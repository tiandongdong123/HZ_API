package com.hanzhong.api.web.controller;

import com.hanzhong.api.web.constant.cmnenum.EntStatusEnum;
import com.hanzhong.api.web.constant.cmnenum.ProductParamEnum;
import com.hanzhong.api.web.constant.cmnenum.ResultCodeEnum;
import com.hanzhong.api.web.model.JsonResult;
import com.hanzhong.api.web.model.bo.CompanyInfoBO;
import com.hanzhong.api.web.model.bo.CompanyQryBO;
import com.hanzhong.api.web.model.vo.CompanyInfoVO;
import com.hanzhong.api.web.service.BusinessService;
import com.hanzhong.api.web.util.CheckUtils;
import com.hanzhong.api.web.util.LoggerUtils;
import com.hanzhong.api.web.util.business.*;
import com.hanzhong.api.web.util.business.area.AreaCodeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private BusinessService businessService;

    /**
     * 获取企业信息
     *
     * @param request
     * @return JsonResult
     */
    @PostMapping("/product_companyInfoOut")
    @ResponseBody
    public JsonResult getCompanyInfo(HttpServletRequest request) {
        LoggerUtils.appendDebugLog(logger, "getCompanyInfo()的参数值：【{}】", request);
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

            // 获取(在营)企业信息
            CompanyInfoVO companyInfoVO = getCompanyInfo(request, productParam);
            if (companyInfoVO != null) {
                return JsonResultUtils.build(ResultCodeEnum.SUCCESS, companyInfoVO);
            }

            return JsonResultUtils.build(ResultCodeEnum.NO_DATA, null);
        } catch (Exception e) {
            LoggerUtils.appendErrorLog(logger, "获取企业信息(getCompanyInfo())出现异常：", e);
            return JsonResultUtils.build(ResultCodeEnum.FAIL, null);
        }
    }

    /**
     * 是否符合参数非空
     *
     * @param request      请求
     * @param productParam 产品参数
     * @return boolean
     */
    private boolean meetNotBlankRequirement(HttpServletRequest request, String productParam) {
        return StringUtils.isNotBlank(productParam) && StringUtils.isNotBlank(request.getParameter(productParam));
    }

    /**
     * 获取(在营)企业信息
     *
     * @param request
     * @param productParam
     * @return 若查询不到企业，则返回null
     */
    private CompanyInfoVO getCompanyInfo(HttpServletRequest request, String productParam) {
        // 设置查询对象
        CompanyQryBO companyQryBO = setCompanyQryBO(request, productParam);

        List<CompanyInfoBO> companyInfoBOList = businessService.getCompanyInfoList(companyQryBO);
        if (companyInfoBOList == null || companyInfoBOList.isEmpty()) {
            return null;
        }

        // 若有多个查询结果，则优先返回“在营”企业信息
        for (CompanyInfoBO companyInfoBO : companyInfoBOList) {
            if (EntStatusEnum.DOING_BUSINESS.getKey().equals(companyInfoBO.getEntStatus())) {
                return convertCompanyInfo(companyInfoBO);
            }
        }

        return convertCompanyInfo(companyInfoBOList.get(0));
    }

    /**
     * 转换企业信息
     *
     * @param companyInfoBO
     * @return CompanyInfoBO
     */
    private CompanyInfoVO convertCompanyInfo(CompanyInfoBO companyInfoBO) {
        CompanyInfoVO companyInfoVO = new CompanyInfoVO();
        // 复制属性值
        BeanUtils.copyProperties(companyInfoBO, companyInfoVO);

        String code;
        // 企业(机构)类型
        code = companyInfoVO.getEntType();
        if (StringUtils.isNotBlank(code)) {
            companyInfoVO.setEntType(EntTypeCodeUtils.getNameByCode(code));
        }
        // 企业性质
        code = companyInfoVO.getsExtEntProperty();
        if (StringUtils.isNotBlank(code)) {
            companyInfoVO.setsExtEntProperty(EntNatureCodeUtils.getNameByCode(code));
        }
        // 企业状态
        code = companyInfoVO.getEntStatus();
        if (StringUtils.isNotBlank(code)) {
            companyInfoVO.setEntStatus(EntStatusEnum.getName(code));
        }
        // 经营(驻在)期限至
        if (companyInfoVO.getOpFrom() != null && companyInfoVO.getOpTo() == null) {
            companyInfoVO.setOpTo(getMaxDate());
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
     * @param request
     * @param productParam
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
        StringBuilder strBuilder = new StringBuilder();

        // 省
        String provinceName = provinceCode == null ? "" : AreaCodeUtils.getAreaNameByCode(provinceCode.toString());
        if (StringUtils.isNotEmpty(provinceName)) {
            strBuilder.append(provinceName);
        }
        // 市
        String cityName = cityCode == null ? "" : AreaCodeUtils.getAreaNameByCode(cityCode.toString());
        if (StringUtils.isNotEmpty(cityName)) {
            strBuilder.append(SLASH_SEPARATOR + cityName);
        }
        // 区
        String areaName = areaCode == null ? "" : AreaCodeUtils.getAreaNameByCode(areaCode.toString());
        if (StringUtils.isNotEmpty(areaName)) {
            strBuilder.append(SLASH_SEPARATOR + areaName);
        }

        return strBuilder.toString();
    }

    /**
     * 获取最大日期（4999-12-31）
     *
     * @return Date 若异常，则返回null
     */
    private Date getMaxDate() {
        try {
            return DateUtils.parseDate("4999-12-31", "yyyy-MM-dd");
        } catch (ParseException e) {
            LoggerUtils.appendErrorLog(logger, "获取最大日期(getMaxDate())出现异常：", e);
            return null;
        }
    }
}
