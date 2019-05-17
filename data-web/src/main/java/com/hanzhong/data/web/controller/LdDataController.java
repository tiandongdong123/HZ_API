package com.hanzhong.data.web.controller;

import com.hanzhong.data.web.constant.DataPackageStatusEnum;
import com.hanzhong.data.web.constant.ResultCodeEnum;
import com.hanzhong.data.web.model.ChangeDataPackageInfo;
import com.hanzhong.data.web.model.ChangeDataPackageInfoQryParam;
import com.hanzhong.data.web.model.JsonResult;
import com.hanzhong.data.web.model.vo.ChangeDataPkgInfoVO;
import com.hanzhong.data.web.service.LdDataService;
import com.hanzhong.data.web.util.*;
import com.hanzhong.data.web.util.longdun.base.LdApiUtils;
import com.hanzhong.data.web.util.longdun.datapackageinfo.LdDataPackageInfoApiUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 *  
 *  @Description 龙盾数据
 *  @Author   luqs   
 *  @Date 2019/2/21 13:26 
 *  @Version  V1.0   
 */
@Controller
@RequestMapping("/longdun")
public class LdDataController {
    private static final Logger logger = LoggerFactory.getLogger(LdDataController.class);

    /**
     * 权限码key
     */
    private static final String BRIER_PERMISSION_CODE_KEY = "briefPermissionCode";
    /**
     * 权限码有效时间
     */
    private static final int PERMISSION_CODE_VALID_TIME = 60;

    @Resource
    private LdDataService ldDataService;

    /**
     * 获取短暂有效的权限码
     * <br/><b>注：此方法自动生成一个有一定时效的权限码，存在session中，若多台服务器部署请及时更换此种检验方式</b>
     *
     * @param request 请求
     * @return JsonResult
     */
    @PostMapping("/brief_permission_code")
    @ResponseBody
    public JsonResult getBriefPermissionCode(HttpServletRequest request) {
        try {
            // 生成随机权限码
            String permissionCode = createRandomPermissionCode();
            // 设置session
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(BRIER_PERMISSION_CODE_KEY, permissionCode);
            httpSession.setMaxInactiveInterval(PERMISSION_CODE_VALID_TIME);
            // 设置返回值
            Map<String, String> dataMap = new HashMap<>(1);
            dataMap.put(BRIER_PERMISSION_CODE_KEY, permissionCode);
            return JsonResultUtils.build(ResultCodeEnum.SUCCESS, dataMap);
        } catch (Exception e) {
            logger.error("request：【{}】，获取短暂有效的权限码出现异常：", HttpUtils.getRequestParamJsonStr(request), e);
            return JsonResultUtils.build(ResultCodeEnum.SYSTEM_ERROR, null);
        }
    }

    /**
     * 获取变更数据包列表
     *
     * @param request 请求
     * @return JsonResult
     */
    @PostMapping("/change_data_package_list")
    @ResponseBody
    public JsonResult getChangeDataPackageList(HttpServletRequest request) {
        // 校验权限码
        if (!checkPermissionCode(request)) {
            logger.warn("request：【{}】，权限码校验未通过", HttpUtils.getRequestParamJsonStr(request));
            return JsonResultUtils.build(ResultCodeEnum.NO_PERMISSION, "权限码失效或不正确，请重新获取!");
        }

        try {
            // 创建更新数据包信息查询参数
            ChangeDataPackageInfoQryParam infoQryParam = createChangeDataPackageInfoQryParam(request);

            // 获取变更数据包
            logger.info("LdDataService.getChangeDataPackageList()的参数值：【{}】", infoQryParam);
            List<ChangeDataPackageInfo> packageInfoList = ldDataService.getChangeDataPackageList(infoQryParam);
            logger.info("LdDataService.getChangeDataPackageList()的返回值：【{}】", packageInfoList);

            // 转换成List<ChangeDataPkgInfoVO>
            List<ChangeDataPkgInfoVO> pkgInfoVOList = convertToDataPackageList(packageInfoList);
            logger.debug("转换成List<ChangeDataPkgInfoVO>的结果值：【{}】", packageInfoList);
            return JsonResultUtils.build(ResultCodeEnum.SUCCESS, pkgInfoVOList);
        } catch (Exception e) {
            logger.error("request：【{}】，获取变更数据包列表出现异常：", HttpUtils.getRequestParamJsonStr(request), e);
            return JsonResultUtils.build(ResultCodeEnum.SYSTEM_ERROR, null);
        }
    }

    /**
     * 变更数据包回馈状态
     *
     * @param request 请求
     * @return JsonResult
     */
    @PostMapping("/monitor_data_package_finish")
    @ResponseBody
    public JsonResult monitorDataPackageFinish(HttpServletRequest request) {
        // 数据包id
        String dataPackId = request.getParameter("dataPackId");

        // 判断必填参数是否为空
        if (StringUtils.isAnyBlank(dataPackId)) {
            logger.warn("request：【{}】，变更数据包回馈状态的必填项参数为空！", HttpUtils.getRequestParamJsonStr(request));
            return JsonResultUtils.build(ResultCodeEnum.PARAM_EMPTY, null);
        }

        // 校验权限码
        if (!checkPermissionCode(request)) {
            logger.warn("request：【{}】，变更数据包回馈状态权限码校验未通过", HttpUtils.getRequestParamJsonStr(request));
            return JsonResultUtils.build(ResultCodeEnum.NO_PERMISSION, "权限码失效或不正确，请重新获取!");
        }

        try {
            // 变更数据包回馈状态
            boolean monitorFlag = ldDataService.monitorDataPackageFinish(dataPackId);
            // 设置返回值
            Map<String, Object> dataMap = new HashMap<>(1);
            dataMap.put("monitorFlag", monitorFlag);
            return JsonResultUtils.build(ResultCodeEnum.SUCCESS, dataMap);
        } catch (Exception e) {
            logger.error("request：【{}】，变更数据包回馈状态出现异常：", HttpUtils.getRequestParamJsonStr(request), e);
            return JsonResultUtils.build(ResultCodeEnum.SYSTEM_ERROR, null);
        }
    }

    /**
     * 创建更新数据包信息查询参数
     *
     * @param request 请求
     * @return ChangeDataPackageInfoQryParam
     */
    private ChangeDataPackageInfoQryParam createChangeDataPackageInfoQryParam(HttpServletRequest request) {
        // 数据包状态
        String statusCode = request.getParameter("dataPackageStatus");
        // 起始时间,格式：yyyy-MM-dd hh:mm:ss
        String startTimeStr = request.getParameter("startTime");
        // 结束时间,格式：yyyy-MM-dd hh:mm:ss
        String endTimeStr = request.getParameter("endTime");
        // 页码
        String pageNum = request.getParameter("pageNum");
        // 每页数量
        String pageSizeStr = request.getParameter("pageSize");

        ChangeDataPackageInfoQryParam qryParam = new ChangeDataPackageInfoQryParam();
        qryParam.setStatusEnum(getDataPackageStatusEnumByCode(statusCode));
        qryParam.setStartTime(startTimeStr);
        qryParam.setEndTime(endTimeStr);
        qryParam.setPageNum(Integer.parseInt(pageNum));
        qryParam.setPageSize(Integer.parseInt(pageSizeStr));
        return qryParam;
    }

    /**
     * 通过code获取数据包状态枚举
     *
     * @param code 状态码
     * @return DataPackageStatusEnum 若状态码不存在，则默认DataPackageStatusEnum.NO_READ
     */
    private DataPackageStatusEnum getDataPackageStatusEnumByCode(String code) {
        DataPackageStatusEnum[] statusEnums = DataPackageStatusEnum.values();
        for (DataPackageStatusEnum statusEnum : statusEnums) {
            if (statusEnum.getValue().equals(code)) {
                return statusEnum;
            }
        }
        return DataPackageStatusEnum.NO_READ;
    }

    /**
     * 转换成List<ChangeDataPkgInfoVO>
     *
     * @param packageInfoList 更新数据包集
     * @return List<ChangeDataPkgInfoVO>
     */
    private List<ChangeDataPkgInfoVO> convertToDataPackageList(List<ChangeDataPackageInfo> packageInfoList) {
        if (packageInfoList == null || packageInfoList.isEmpty()) {
            return Collections.emptyList();
        }

        List<ChangeDataPkgInfoVO> dataPkgInfoVOList = new ArrayList<>();
        for (ChangeDataPackageInfo packageInfo : packageInfoList) {
            ChangeDataPkgInfoVO pkgInfoVO = new ChangeDataPkgInfoVO();
            // 数据包id
            pkgInfoVO.setDataPackId(packageInfo.getDataPackId());
            // 数据包名称
            pkgInfoVO.setDataPackName(packageInfo.getDataPackName());
            // 密码
            pkgInfoVO.setPassword(packageInfo.getPassword());
            // 数据时间
            String dataTimeStr = packageInfo.getDataPackTime() == null ? "" : DateUtils.dateFormat(new Date(packageInfo.getDataPackTime()));
            pkgInfoVO.setDataTime(dataTimeStr);
            // 数量
            pkgInfoVO.setRecordCount(packageInfo.getRecordCount());
            // 文件大小（单位：M）
            BigDecimal fileSize = new BigDecimal(packageInfo.getFileSize() == null ? 0 : packageInfo.getFileSize());
            pkgInfoVO.setFileSize(fileSize.divide(new BigDecimal(1024 * 1024), 2, RoundingMode.HALF_UP).toString() + "M");
            // 下载url
            pkgInfoVO.setDownloadUrl(LdDataPackageInfoApiUtils.CHANGE_DATA_PACKAGE_DOWNLOAD_URL + "/" + LdApiUtils.U_ID + "/" + packageInfo.getDataPackName());
            dataPkgInfoVOList.add(pkgInfoVO);
        }
        // 排序
        sortChangeDataPkgInfoVO(dataPkgInfoVOList);
        return dataPkgInfoVOList;
    }

    /**
     * 对更新数据包集进行排序
     *
     * @param pkgInfoVOList 更新数据包集
     * @return List<ChangeDataPkgInfoVO>
     */
    private List<ChangeDataPkgInfoVO> sortChangeDataPkgInfoVO(List<ChangeDataPkgInfoVO> pkgInfoVOList) {
        // 排序
        pkgInfoVOList.sort((ChangeDataPkgInfoVO o1, ChangeDataPkgInfoVO o2) ->
                // 降序
                o2.getDataTime().compareTo(o1.getDataTime())
        );
        return pkgInfoVOList;
    }

    /**
     * 生成随机权限码
     *
     * @return String
     */
    private String createRandomPermissionCode() {
        return "pId_" + RandomStringUtils.randomAlphanumeric(12);
    }

    /**
     * 校验权限码
     *
     * @param request 请求
     * @return boolean true：校验通过
     */
    private boolean checkPermissionCode(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        String sessionCode = ObjectUtils.defaultString(httpSession.getAttribute(BRIER_PERMISSION_CODE_KEY));
        String requestCode = request.getParameter(BRIER_PERMISSION_CODE_KEY);
        boolean checkFlag = StringUtils.isNotBlank(requestCode) && sessionCode.equals(requestCode);
        logger.debug("session中的权限码：【{}】，请求中的权限码：【{}】,校验结果：【{}】", sessionCode, requestCode, checkFlag);
        return checkFlag;
    }
}
