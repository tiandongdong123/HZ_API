package com.hanzhong.data.web.controller;

import com.hanzhong.data.web.constant.ProductParamEnum;
import com.hanzhong.data.web.constant.ResultCodeEnum;
import com.hanzhong.data.web.model.EnterpriseBaseInfo;
import com.hanzhong.data.web.model.EnterpriseBaseInfoQryParam;
import com.hanzhong.data.web.model.JsonResult;
import com.hanzhong.data.web.model.vo.EnterpriseBaseInfoVO;
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
 *  @Description 企业基本信息
 *  @Author   luqs   
 *  @Date 2018/11/5 16:29 
 *  @Version  V1.0   
 */
@Controller
@RequestMapping("/productOutInterface")
public class EntBaseInfoController {

    private static final Logger logger = LoggerFactory.getLogger(EntBaseInfoController.class);

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
    public JsonResult getEntBaseInfo(HttpServletRequest request) {
        // 统一社会信用代码
        String corCodeTy = request.getParameter(ProductParamEnum.COR_CODE_TY.getValue());
        // 组织机构代码
        String corCode = request.getParameter(ProductParamEnum.COR_CODE.getValue());
        // 参数
        String productParam = request.getParameter("productpara");

        try {
            // 判断必填参数是否为空
            if (!meetNotBlankRequirement(request, productParam)) {
                logger.warn("request：【{}】，获取企业信息的必填项参数为空！", HttpUtils.getRequestParamJsonStr(request));
                return JsonResultUtils.build(ResultCodeEnum.PARAM_EMPTY, null);
            }

            // 判断统一社会信用代码格式是否正确
            if (ProductParamEnum.COR_CODE_TY.getValue().equals(productParam) && !CheckUtils.isUnifiedSocialCreditCode(corCodeTy)) {
                logger.warn("request：【{}】，获取企业信息的统一社会信用代码格式不正确！", HttpUtils.getRequestParamJsonStr(request));
                return JsonResultUtils.build(ResultCodeEnum.PARAM_FORMAT_ERROR, null);
            }

            // 判断组织机构代码格式是否正确
            if (ProductParamEnum.COR_CODE.getValue().equals(productParam) && !CheckUtils.isOrganizationCode(corCode.replace(SMALL_MIDDLE_LINE_SEPARATOR, ""))) {
                logger.warn("request：【{}】，获取企业信息的组织机构代码格式不正确！", HttpUtils.getRequestParamJsonStr(request));
                return JsonResultUtils.build(ResultCodeEnum.PARAM_FORMAT_ERROR, null);
            }

            // 创建查询参数
            EnterpriseBaseInfoQryParam qryParam = createEntBaseInfoQryParam(request, productParam);
            // 获取企业信息
            EnterpriseBaseInfoVO enterpriseBaseInfoVO = getEnterpriseBaseInfo(qryParam);
            if (enterpriseBaseInfoVO != null) {
                return JsonResultUtils.build(ResultCodeEnum.SUCCESS, enterpriseBaseInfoVO);
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
     * 创建企业基本信息查询参数
     *
     * @param request      请求
     * @param productParam 查询参数类型
     * @return EnterpriseBaseInfoQryParam
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
    private EnterpriseBaseInfoVO getEnterpriseBaseInfo(EnterpriseBaseInfoQryParam qryParam) {
        logger.info("BusinessDataService.getEnterpriseBaseInfo()的参数值：【{}】", qryParam);
        EnterpriseBaseInfo enterpriseBaseInfo = businessDataService.getEnterpriseBaseInfo(qryParam);
        logger.info("BusinessDataService.getEnterpriseBaseInfo()的返回值：【{}】", enterpriseBaseInfo);
        if (enterpriseBaseInfo == null) {
            return null;
        }

        // 转换成EnterpriseInfoVO
        EnterpriseBaseInfoVO infoVO = convertToEnterpriseBaseInfoVO(enterpriseBaseInfo);
        logger.debug("EnterpriseBaseInfo：【{}】，转换成EnterpriseInfoVO后结果值：【{}】", enterpriseBaseInfo, infoVO);
        return infoVO;
    }

    /**
     * 转换成EnterpriseInfoVO
     *
     * @param enterpriseBaseInfo 企业基本信息
     * @return EnterpriseBaseInfoVO
     */
    private EnterpriseBaseInfoVO convertToEnterpriseBaseInfoVO(EnterpriseBaseInfo enterpriseBaseInfo) {
        EnterpriseBaseInfoVO enterpriseBaseInfoVO = new EnterpriseBaseInfoVO();
        // 统一社会信息代码
        enterpriseBaseInfoVO.setUsCreditCode(enterpriseBaseInfo.getUsCreditCode());
        // 组织机构代码
        enterpriseBaseInfoVO.setOrgCode(enterpriseBaseInfo.getOrgCode());
        // 企业名称
        enterpriseBaseInfoVO.setEntName(enterpriseBaseInfo.getEntName());
        // 曾用名
        enterpriseBaseInfoVO.setUsedName(enterpriseBaseInfo.getUsedName());
        // 行业领域
        enterpriseBaseInfoVO.setIndustry(enterpriseBaseInfo.getIndustry());
        // 行业领域代码
        enterpriseBaseInfoVO.setIndustryCode(enterpriseBaseInfo.getIndustryCode());
        // 注册资金（默认单位：万）
        enterpriseBaseInfoVO.setRegCap(enterpriseBaseInfo.getRegCap());
        // 注册资本(金)币种
        enterpriseBaseInfoVO.setRegCapCur(enterpriseBaseInfo.getRegCapCur());
        // 企业类型
        enterpriseBaseInfoVO.setEntType(enterpriseBaseInfo.getEntType());
        // 企业状态
        enterpriseBaseInfoVO.setEntStatus(enterpriseBaseInfo.getEntStatus());
        //  经营(业务)范围
        enterpriseBaseInfoVO.setOpScope(enterpriseBaseInfo.getOpScope());
        // 成立日期(格式：yyyy-MM-dd)
        enterpriseBaseInfoVO.setEsDate(enterpriseBaseInfo.getEsDate());
        // 核准日期(格式：yyyy-MM-dd)
        enterpriseBaseInfoVO.setApprDate(enterpriseBaseInfo.getApprDate());
        // 死亡日期(格式：yyyy-MM-dd)
        enterpriseBaseInfoVO.setEndDate(enterpriseBaseInfo.getEndDate());
        // 吊销日期(格式：yyyy-MM-dd)
        enterpriseBaseInfoVO.setRevDate(enterpriseBaseInfo.getRevDate());
        // 注销日期(格式：yyyy-MM-dd)
        enterpriseBaseInfoVO.setCanDate(enterpriseBaseInfo.getCanDate());
        // 经营(驻在)期限自(格式：yyyy-MM-dd)
        enterpriseBaseInfoVO.setOpFrom(enterpriseBaseInfo.getOpFrom());
        // 经营(驻在)期限至(格式：yyyy-MM-dd)
        enterpriseBaseInfoVO.setOpTo(enterpriseBaseInfo.getOpTo());
        // 法定代表人
        enterpriseBaseInfoVO.setName(enterpriseBaseInfo.getName());
        // 登记机关
        enterpriseBaseInfoVO.setRegOrg(enterpriseBaseInfo.getRegOrg());
        // 邮政编码
        enterpriseBaseInfoVO.setPostalCode(enterpriseBaseInfo.getPostalCode());
        // 住所
        enterpriseBaseInfoVO.setDom(enterpriseBaseInfo.getDom());
        // 住所行政区划
        enterpriseBaseInfoVO.setDomDistrict(enterpriseBaseInfo.getDomDistrict());
        // 住所所在经济开发区
        enterpriseBaseInfoVO.setEcoTecDevZone(enterpriseBaseInfo.getEcoTecDevZone());
        return enterpriseBaseInfoVO;
    }

}
