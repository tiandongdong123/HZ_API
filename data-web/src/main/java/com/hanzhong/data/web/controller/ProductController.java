package com.hanzhong.data.web.controller;

import com.hanzhong.data.web.constant.ProductParamEnum;
import com.hanzhong.data.web.constant.ResultCodeEnum;
import com.hanzhong.data.web.model.EnterpriseBaseInfo;
import com.hanzhong.data.web.model.EnterpriseBaseInfoQryParam;
import com.hanzhong.data.web.model.JsonResult;
import com.hanzhong.data.web.model.vo.EnterpriseInfoVO;
import com.hanzhong.data.web.service.BusinessDataService;
import com.hanzhong.data.web.util.CheckUtils;
import com.hanzhong.data.web.util.HttpUtils;
import com.hanzhong.data.web.util.JsonResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

    @Resource
    private BusinessDataService businessDataService;

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

            // 创建查询参数
            EnterpriseBaseInfoQryParam qryParam = createEntBaseInfoQryParam(request, productParam);
            // 获取企业信息
            EnterpriseInfoVO enterpriseInfoVO = getEnterpriseInfo(qryParam);
            if (enterpriseInfoVO != null) {
                return JsonResultUtils.build(ResultCodeEnum.SUCCESS, enterpriseInfoVO);
            }
            logger.info("request：【{}】，未获取企业信息", HttpUtils.getRequestParamJsonStr(request));
            return JsonResultUtils.build(ResultCodeEnum.NO_DATA, null);
        } catch (Exception e) {
            logger.error("request：【{}】，获取企业信息出现异常：", HttpUtils.getRequestParamJsonStr(request), e);
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
     * 创建查询参数
     *
     * @param request      请求
     * @param productParam 查询参数类型
     * @return CompanyQryBO
     */
    private EnterpriseBaseInfoQryParam createEntBaseInfoQryParam(HttpServletRequest request, String productParam) {
        EnterpriseBaseInfoQryParam qryParam = new EnterpriseBaseInfoQryParam();
        if (ProductParamEnum.COMPANY_NAME.getValue().equals(productParam)) {
            qryParam.setEntName(request.getParameter(productParam));
        } else if (ProductParamEnum.COR_CODE_TY.getValue().equals(productParam)) {
            qryParam.setUsCreditCode(request.getParameter(productParam));
        } else if (ProductParamEnum.COR_CODE.getValue().equals(productParam)) {
            qryParam.setOrgCode(request.getParameter(productParam));
        } else {
            logger.error("productParam的参数值【{}】不存在", productParam);
            throw new IllegalArgumentException("productParam的参数值【" + productParam + "】不存在");
        }
        return qryParam;
    }

    /**
     * 获取企业信息
     *
     * @param qryParam 企业查询参数
     * @return 若查询不到企业，则返回null
     */
    private EnterpriseInfoVO getEnterpriseInfo(EnterpriseBaseInfoQryParam qryParam) {
        logger.info("BusinessDataService.getEnterpriseBaseInfo()的参数值：【{}】", qryParam);
        EnterpriseBaseInfo enterpriseBaseInfo = businessDataService.getEnterpriseBaseInfo(qryParam);
        logger.info("BusinessDataService.getEnterpriseBaseInfo()的返回值：【{}】", enterpriseBaseInfo);

        if (enterpriseBaseInfo != null) {
            // 转换成EnterpriseInfoVO
            EnterpriseInfoVO infoVO = convertToEnterpriseInfoVO(enterpriseBaseInfo);
            logger.debug("request：【{}】，转换成EnterpriseInfoVO后结果值：【{}】", enterpriseBaseInfo, infoVO);
            return infoVO;
        }
        return null;
    }

    /**
     * 转换成EnterpriseInfoVO
     *
     * @param enterpriseBaseInfo 企业基本信息
     * @return EnterpriseInfoVO
     */
    private EnterpriseInfoVO convertToEnterpriseInfoVO(EnterpriseBaseInfo enterpriseBaseInfo) {
        EnterpriseInfoVO enterpriseInfoVO = new EnterpriseInfoVO();
        // 统一社会信息代码
        enterpriseInfoVO.setUsCreditCode(enterpriseBaseInfo.getUsCreditCode());
        // 组织机构代码
        enterpriseInfoVO.setOrgCode(enterpriseBaseInfo.getOrgCode());
        // 企业名称
        enterpriseInfoVO.setEntName(enterpriseBaseInfo.getEntName());
        // 曾用名
        enterpriseInfoVO.setUsedName(enterpriseBaseInfo.getUsedName());
        // 行业领域
        enterpriseInfoVO.setIndustry(enterpriseBaseInfo.getIndustry());
        // 行业领域代码
        enterpriseInfoVO.setIndustryCode(enterpriseBaseInfo.getIndustryCode());
        // 注册资金（默认单位：万）
        enterpriseInfoVO.setRegCap(enterpriseBaseInfo.getRegCap());
        // 注册资本(金)币种
        enterpriseInfoVO.setRegCapCur(enterpriseBaseInfo.getRegCapCur());
        // 企业类型
        enterpriseInfoVO.setEntType(enterpriseBaseInfo.getEntType());
        // 企业状态
        enterpriseInfoVO.setEntStatus(enterpriseBaseInfo.getEntStatus());
        //  经营(业务)范围
        enterpriseInfoVO.setOpScope(enterpriseBaseInfo.getOpScope());
        // 成立日期(格式：yyyy-MM-dd)
        enterpriseInfoVO.setEsDate(enterpriseBaseInfo.getEsDate());
        // 核准日期(格式：yyyy-MM-dd)
        enterpriseInfoVO.setApprDate(enterpriseBaseInfo.getApprDate());
        // 死亡日期(格式：yyyy-MM-dd)
        enterpriseInfoVO.setEndDate(enterpriseBaseInfo.getEndDate());
        // 吊销日期(格式：yyyy-MM-dd)
        enterpriseInfoVO.setRevDate(enterpriseBaseInfo.getRevDate());
        // 注销日期(格式：yyyy-MM-dd)
        enterpriseInfoVO.setCanDate(enterpriseBaseInfo.getCanDate());
        // 经营(驻在)期限自(格式：yyyy-MM-dd)
        enterpriseInfoVO.setOpFrom(enterpriseBaseInfo.getOpFrom());
        // 经营(驻在)期限至(格式：yyyy-MM-dd)
        enterpriseInfoVO.setOpTo(enterpriseBaseInfo.getOpTo());
        // 法定代表人
        enterpriseInfoVO.setName(enterpriseBaseInfo.getName());
        // 登记机关
        enterpriseInfoVO.setRegOrg(enterpriseBaseInfo.getRegOrg());
        // 邮政编码
        enterpriseInfoVO.setPostalCode(enterpriseBaseInfo.getPostalCode());
        // 住所
        enterpriseInfoVO.setDom(enterpriseBaseInfo.getDom());
        // 住所行政区划
        enterpriseInfoVO.setDomDistrict(enterpriseBaseInfo.getDomDistrict());
        // 住所所在经济开发区
        enterpriseInfoVO.setEcoTecDevZone(enterpriseBaseInfo.getEcoTecDevZone());
        return enterpriseInfoVO;
    }

}
