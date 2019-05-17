package com.hanzhong.data.web.util.longdun.entmanageinfo;

import com.hanzhong.data.web.util.ObjectUtils;
import com.hanzhong.data.web.util.longdun.base.LdApiUtils;
import com.hanzhong.data.web.util.longdun.base.model.ApiParam;
import com.hanzhong.data.web.util.longdun.base.model.ApiResult;
import com.hanzhong.data.web.util.longdun.entmanageinfo.constant.ServiceNameConstant;
import com.hanzhong.data.web.util.longdun.entmanageinfo.model.GoodsInfo;
import com.hanzhong.data.web.util.longdun.entmanageinfo.model.GoodsInfoQryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 *  
 *  @Description 企业经营信息API工具类
 *  @Author   luqs   
 *  @Date 2019/4/2 19:54 
 *  @Version  V1.0   
 */
public class LdEntManageInfoApiUtils {

    private static final Logger logger = LoggerFactory.getLogger(LdEntManageInfoApiUtils.class);

    private LdEntManageInfoApiUtils() {
    }

    /**
     * 获取企业商品信息
     *
     * @param goodsInfoQryParam 商品信息查询参数
     * @return String
     */
    public static String getGoodsInfo(GoodsInfoQryParam goodsInfoQryParam) {
        Map<String, Object> paramMap = new HashMap<>(3);
        paramMap.put("entName", goodsInfoQryParam.getEntName());
        paramMap.put("pageNo", goodsInfoQryParam.getPageNo());
        paramMap.put("pageSize", goodsInfoQryParam.getPageSize());

        ApiParam apiParam = LdApiUtils.initApiParam(LdApiUtils.U_ID, ServiceNameConstant.GET_GOODS_INFO_METHOD);
        apiParam.setParams(paramMap);

        logger.info("获取企业商品信息LdApiUtils.getResultStrByApiParam()的参数值：【{}】", apiParam);
        String resultStr = LdApiUtils.getResultStrByApiParam(apiParam);
        logger.info("获取企业商品信息LdApiUtils.getResultStrByApiParam()的返回值：【{}】", resultStr);
        return resultStr;
    }

    /**
     * 获取企业商品信息
     *
     * @param goodsInfoQryParam 商品信息查询参数
     * @return ApiResult
     */
    public static ApiResult getGoodsInfoApiResult(GoodsInfoQryParam goodsInfoQryParam) {
        // 获取企业商品信息
        String resultJsonStr = getGoodsInfo(goodsInfoQryParam);
        // 转换成ApiResult
        ApiResult apiResult = LdApiUtils.convertToApiResult(resultJsonStr);
        // 是否有数据
        if (!LdApiUtils.matchData(apiResult)) {
            return apiResult;
        }

        List<Map<String, Object>> resultDataMapList = (List<Map<String, Object>>) apiResult.getResultData();
        List<GoodsInfo> infoList = new ArrayList<>();
        for (Map<String, Object> map : resultDataMapList) {
            GoodsInfo info = new GoodsInfo();
            info.setCode(ObjectUtils.defaultString(map.get("CODE")));
            info.setName(ObjectUtils.defaultString(map.get("NAME")));
            info.setModel(ObjectUtils.defaultString(map.get("MODEL")));
            info.setDesc(ObjectUtils.defaultString(map.get("DESC")));
            info.setBrand(ObjectUtils.defaultString(map.get("BRAND")));
            info.setEntName(ObjectUtils.defaultString(map.get("ENTNAME")));
            info.setStatus(ObjectUtils.defaultString(map.get("STATUS")));
            infoList.add(info);
        }
        apiResult.setResultData(infoList);
        return apiResult;
    }

    /**
     * 获取企业商品信息
     *
     * @param goodsInfoQryParam 商品信息查询参数
     * @return List<GoodsInfo>
     */
    public static List<GoodsInfo> getGoodsInfoList(GoodsInfoQryParam goodsInfoQryParam) {
        // 获取企业商品信息
        ApiResult apiResult = getGoodsInfoApiResult(goodsInfoQryParam);
        return LdApiUtils.matchData(apiResult) ? (List<GoodsInfo>) apiResult.getResultData() : Collections.emptyList();
    }
}
