package com.hanzhong.data.web.controller;

import com.hanzhong.data.web.constant.ResultCodeEnum;
import com.hanzhong.data.web.model.HighTechEntInputParam;
import com.hanzhong.data.web.model.JsonResult;
import com.hanzhong.data.web.service.HighTechEntService;
import com.hanzhong.data.web.util.HttpUtils;
import com.hanzhong.data.web.util.JsonResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据录入
 *
 * @author yifei
 * @date 2019/4/13
 */
@Controller
@RequestMapping("/data_input")
public class DataInputController {

    private static final Logger logger = LoggerFactory.getLogger(DataInputController.class);

    @Resource
    private HighTechEntService highTechEntService;

    /**
     * 录入高新企业信息
     *
     * @param request 请求
     * @return JsonResult
     */
    @RequestMapping("/high_tech_ent_info")
    @ResponseBody
    public JsonResult inputHighTechEntInfo(HttpServletRequest request) {
        try {
            // 创建高新企业录入参数
            HighTechEntInputParam inputParam = createHighTechEntInputParam(request);
            logger.info("HighTechEntService.inputHighTechEntInfo()的参数值：【{}】", inputParam);
            boolean inputFlag = highTechEntService.inputHighTechEntInfo(inputParam);
            logger.info("HighTechEntService.inputHighTechEntInfo()的返回值：【{}】", inputFlag);
            // 设置返回值
            Map<String, Object> dataMap = new HashMap<>(1);
            dataMap.put("inputFlag", inputFlag);
            return JsonResultUtils.build(ResultCodeEnum.SUCCESS, dataMap);
        } catch (Exception e) {
            logger.error("request：【{}】，录入高新企业信息出现异常：", HttpUtils.getRequestParamJsonStr(request), e);
            return JsonResultUtils.build(ResultCodeEnum.SYSTEM_ERROR, null);
        }
    }

    /**
     * 创建高新企业录入参数
     *
     * @param request 请求
     * @return HighTechEntInputParam
     */
    private HighTechEntInputParam createHighTechEntInputParam(HttpServletRequest request) {
        HighTechEntInputParam inputParam = new HighTechEntInputParam();
        inputParam.setEntName(request.getParameter("entName"));
        inputParam.setUsCreditCode(request.getParameter("usCreditCode"));
        return inputParam;
    }
}
