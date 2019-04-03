package com.hanzhong.data.web.util.longdun.datapackageinfo;

import com.hanzhong.data.web.util.ObjectUtils;
import com.hanzhong.data.web.util.PropertiesUtils;
import com.hanzhong.data.web.util.longdun.base.LdApiUtils;
import com.hanzhong.data.web.util.longdun.base.constant.CmnConstant;
import com.hanzhong.data.web.util.longdun.base.model.ApiParam;
import com.hanzhong.data.web.util.longdun.base.model.ApiResult;
import com.hanzhong.data.web.util.longdun.datapackageinfo.constant.ServiceNameConstant;
import com.hanzhong.data.web.util.longdun.datapackageinfo.model.ChangeDataPkgInfo;
import com.hanzhong.data.web.util.longdun.datapackageinfo.model.ChangeDataPkgListQryParam;
import com.hanzhong.data.web.util.longdun.datapackageinfo.model.DataPackageFinishParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 *  
 *  @Description 数据包信息API工具类
 *  @Author   luqs   
 *  @Date 2019/4/2 20:38 
 *  @Version  V1.0   
 */
public class LdDataPackageInfoApiUtils {

    private static final Logger logger = LoggerFactory.getLogger(LdDataPackageInfoApiUtils.class);

    /**
     * 更新数据包下载url
     */
    public static final String CHANGE_DATA_PACKAGE_DOWNLOAD_URL = PropertiesUtils.getValueBySpecifiedFile(CmnConstant.LD_PROPERTIES_FILE, "changeDataPackage.download.url");

    private LdDataPackageInfoApiUtils() {
    }

    /**
     * 获取变更数据包列表
     *
     * @param listQryParam 变更数据包查询参数
     * @return String
     */
    public static String getChangeDataPackageList(ChangeDataPkgListQryParam listQryParam) {
        Map<String, Object> paramMap = new HashMap<>(5);
        paramMap.put("actionType", listQryParam.getActionTypeEnum().getValue());
        paramMap.put("startTime", listQryParam.getStartTime());
        paramMap.put("endTime", listQryParam.getEndTime());
        paramMap.put("pageNo", listQryParam.getPageNo());
        paramMap.put("pageSize", listQryParam.getPageSize());

        ApiParam apiParam = LdApiUtils.initApiParam(LdApiUtils.U_ID, ServiceNameConstant.CHANGE_DATA_PACKAGE_LIST_METHOD);
        apiParam.setParams(paramMap);

        logger.info("获取变更数据包列表LdApiUtils.getResultStrByApiParam()的参数值：【{}】", apiParam);
        String resultStr = LdApiUtils.getResultStrByApiParam(apiParam);
        logger.info("获取变更数据包列表LdApiUtils.getResultStrByApiParam()的返回值：【{}】", resultStr);
        return resultStr;
    }

    /**
     * 获取变更数据包列表
     *
     * @param listQryParam 变更数据包查询参数
     * @return ApiResult
     */
    public static ApiResult getChangeDataPackageListApiResult(ChangeDataPkgListQryParam listQryParam) {
        // 获取变更数据包列表
        String resultJsonStr = getChangeDataPackageList(listQryParam);
        // 转换成ApiResult
        ApiResult apiResult = LdApiUtils.convertToApiResult(resultJsonStr);
        // 是否有数据
        if (!LdApiUtils.matchData(apiResult)) {
            return apiResult;
        }

        List<Map<String, Object>> resultDataMapList = (List<Map<String, Object>>) apiResult.getResultData();
        List<ChangeDataPkgInfo> infoList = new ArrayList<>();
        for (Map<String, Object> map : resultDataMapList) {
            ChangeDataPkgInfo info = new ChangeDataPkgInfo();
            info.setDataPackId(ObjectUtils.defaultString(map.get("DATAPACKID")));
            info.setDataPackName(ObjectUtils.defaultString(map.get("DATAPACKNAME")));
            info.setDataPackTime(ObjectUtils.defaultLong(map.get("DATAPACKTIME")));
            info.setRecordCount(ObjectUtils.defaultInt(map.get("RECORDCOUNT")));
            info.setFileSize(ObjectUtils.defaultInt(map.get("FILESIZE")));
            info.setPassword(ObjectUtils.defaultString(map.get("PASSWORD")));
            infoList.add(info);
        }
        apiResult.setResultData(infoList);
        return apiResult;
    }

    /**
     * 获取变更数据包列表
     *
     * @param listQryParam 变更数据包查询参数
     * @return List<ChangeDataPkgInfo>
     */
    public static List<ChangeDataPkgInfo> getChangeDataPkgInfoList(ChangeDataPkgListQryParam listQryParam) {
        // 获取变更数据包列表
        ApiResult apiResult = getChangeDataPackageListApiResult(listQryParam);
        return LdApiUtils.matchData(apiResult) ? (List<ChangeDataPkgInfo>) apiResult.getResultData() : Collections.emptyList();
    }

    /**
     * 变更数据包回馈状态
     *
     * @param finishParam 回馈参数
     * @return String
     */
    public static String monitorDataPackageFinish(DataPackageFinishParam finishParam) {
        Map<String, Object> paramMap = new HashMap<>(1);
        paramMap.put("dataPackId", finishParam.getDataPackId());

        ApiParam apiParam = LdApiUtils.initApiParam(LdApiUtils.U_ID, ServiceNameConstant.MONITOR_DATA_PACKAGE_FINISH_METHOD);
        apiParam.setParams(paramMap);

        logger.info("变更数据包回馈状态LdApiUtils.getResultStrByApiParam()的参数值：【{}】", apiParam);
        String resultStr = LdApiUtils.getResultStrByApiParam(apiParam);
        logger.info("变更数据包回馈状态LdApiUtils.getResultStrByApiParam()的返回值：【{}】", resultStr);
        return resultStr;
    }

    /**
     * 变更数据包回馈状态
     *
     * @param finishParam 回馈参数
     * @return ApiResult
     */
    public static ApiResult monitorDataPackageFinishApiResult(DataPackageFinishParam finishParam) {
        // 变更数据包回馈状态
        String resultJsonStr = monitorDataPackageFinish(finishParam);
        // 转换成ApiResult
        ApiResult apiResult = LdApiUtils.convertToApiResult(resultJsonStr);
        // 是否有数据
        if (!LdApiUtils.matchData(apiResult)) {
            return apiResult;
        }

        List<Map<String, Object>> resultDataMapList = (List<Map<String, Object>>) apiResult.getResultData();
        boolean monitorFlag = false;
        for (Map<String, Object> map : resultDataMapList) {
            monitorFlag = Boolean.parseBoolean(ObjectUtils.defaultString(map.get("SUCCESS")));
        }
        apiResult.setResultData(monitorFlag);
        return apiResult;
    }

    /**
     * 变更数据包回馈状态
     *
     * @param finishParam 回馈参数
     * @return boolean
     */
    public static boolean monitorDataPackageFinishFlag(DataPackageFinishParam finishParam) {
        // 变更数据包回馈状态
        ApiResult apiResult = monitorDataPackageFinishApiResult(finishParam);
        return LdApiUtils.matchData(apiResult) ? (boolean) apiResult.getResultData() : Boolean.FALSE;
    }
}
