package com.hanzhong.data.web.controller;

import com.hanzhong.data.web.constant.ResultCodeEnum;
import com.hanzhong.data.web.model.EntGoodsInfo;
import com.hanzhong.data.web.model.EntGoodsInfoQryParam;
import com.hanzhong.data.web.model.JsonResult;
import com.hanzhong.data.web.model.vo.EntGoodsInfoVO;
import com.hanzhong.data.web.service.EntManagementService;
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
 * 企业经营
 *
 * @author yifei
 * @date 2019/4/1
 */
@Controller
@RequestMapping("/ent_manage")
public class EntManagementController {
    private static final Logger logger = LoggerFactory.getLogger(EntManagementController.class);

    @Resource
    private EntManagementService entManagementService;

    /**
     * 获取企业商品信息
     *
     * @param request 请求
     * @return JsonResult
     */
    @PostMapping("/ent_goods_info")
    @ResponseBody
    public JsonResult getEntGoodsInfoList(HttpServletRequest request) {
        try {
            // 创建查询参数
            EntGoodsInfoQryParam qryParam = createEntGoodsInfoQryParam(request);
            // 获取企业商品信息
            List<EntGoodsInfoVO> infoVOList = getEntGoodsInfoList(qryParam);
            if (!infoVOList.isEmpty()) {
                return JsonResultUtils.build(ResultCodeEnum.SUCCESS, infoVOList);
            }
            logger.info("request：【{}】，未查询到企业商品信息", HttpUtils.getRequestParamJsonStr(request));
            return JsonResultUtils.build(ResultCodeEnum.NO_DATA, null);
        } catch (Exception e) {
            logger.error("request：【{}】，获取企业商品信息出现异常：", HttpUtils.getRequestParamJsonStr(request), e);
            return JsonResultUtils.build(ResultCodeEnum.FAIL, null);
        }
    }

    /**
     * 创建企业商品信息查询参数
     *
     * @param request 请求
     * @return EntGoodsInfoQryParam
     */
    private EntGoodsInfoQryParam createEntGoodsInfoQryParam(HttpServletRequest request) {
        EntGoodsInfoQryParam qryParam = new EntGoodsInfoQryParam();
        qryParam.setEntName(request.getParameter("entName"));
        qryParam.setPageNum(Integer.parseInt(request.getParameter("pageNum")));
        qryParam.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
        return qryParam;
    }

    /**
     * 获取企业商品信息
     *
     * @param qryParam 企业商品信息查询参数
     * @return List<EntGoodsInfoVO>
     */
    private List<EntGoodsInfoVO> getEntGoodsInfoList(EntGoodsInfoQryParam qryParam) {
        // 获取企业商品信息
        logger.info("BusinessDataService.getGoodsInfoList()的参数值：【{}】", qryParam);
        List<EntGoodsInfo> goodsInfoList = entManagementService.getGoodsInfoList(qryParam);
        logger.info("BusinessDataService.getGoodsInfoList()的返回值：【{}】", goodsInfoList);

        // 转换成List<EntGoodsInfoVO>
        List<EntGoodsInfoVO> infoVOList = convertToEntGoodsInfoVOList(goodsInfoList);
        logger.debug("List<EntGoodsInfo>：【{}】，转换成List<EntGoodsInfoVO>后结果值：【{}】", goodsInfoList, infoVOList);
        return infoVOList;
    }

    /**
     * 转换成List<EntGoodsInfoVO>
     *
     * @param goodsInfoList 企业商品信息集
     * @return List<EntGoodsInfoVO>
     */
    private List<EntGoodsInfoVO> convertToEntGoodsInfoVOList(List<EntGoodsInfo> goodsInfoList) {
        if (goodsInfoList == null || goodsInfoList.isEmpty()) {
            return Collections.emptyList();
        }

        List<EntGoodsInfoVO> goodsInfoVOList = new ArrayList<>();
        for (EntGoodsInfo goodsInfo : goodsInfoList) {
            EntGoodsInfoVO entGoodsInfoVO = new EntGoodsInfoVO();
            // 商品条码
            entGoodsInfoVO.setCode(goodsInfo.getCode());
            // 名称
            entGoodsInfoVO.setName(goodsInfo.getName());
            // 规格型号
            entGoodsInfoVO.setModel(goodsInfo.getModel());
            // 描述
            entGoodsInfoVO.setDesc(goodsInfo.getDesc());
            // 商标
            entGoodsInfoVO.setBrand(goodsInfo.getBrand());
            // 发布厂家
            entGoodsInfoVO.setEntName(goodsInfo.getEntName());
            // 条码状态
            entGoodsInfoVO.setStatus(goodsInfo.getStatus());
            goodsInfoVOList.add(entGoodsInfoVO);
        }
        return goodsInfoVOList;
    }
}
