package com.hanzhong.data.web.util.longdun.model;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/10 19:43 
 *  @Version  V1.0   
 */
public class ApiResult {
    /**
     * 用户id
     */
    private String uId;
    /**
     * 订单是否正常 1正常 0异常
     */
    private String isUsual;
    /**
     * 是否匹配到企业 1匹配 0未匹配
     */
    private String isMatch;
    /**
     * 版本号
     */
    private String version;
    /**
     * 订单信息
     */
    private OrderInfo orderInfo;
    /**
     * 分页信息
     */
    private PageInfo pageInfo;
    /**
     * 结果数据
     */
    private Object resultData;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getIsUsual() {
        return isUsual;
    }

    public void setIsUsual(String isUsual) {
        this.isUsual = isUsual;
    }

    public String getIsMatch() {
        return isMatch;
    }

    public void setIsMatch(String isMatch) {
        this.isMatch = isMatch;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public Object getResultData() {
        return resultData;
    }

    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }

    @Override
    public String toString() {
        return "BaseReults{" +
                "uId='" + uId + '\'' +
                ", isUsual='" + isUsual + '\'' +
                ", isMatch='" + isMatch + '\'' +
                ", version='" + version + '\'' +
                ", orderInfo=" + orderInfo +
                ", pageInfo=" + pageInfo +
                ", resultData=" + resultData +
                '}';
    }
}
