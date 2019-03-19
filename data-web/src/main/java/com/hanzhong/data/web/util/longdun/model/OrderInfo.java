package com.hanzhong.data.web.util.longdun.model;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/10 19:50 
 *  @Version  V1.0   
 */
public class OrderInfo {
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 订单时间
     */
    private String orderDate;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderNo='" + orderNo + '\'' +
                ", orderDate='" + orderDate + '\'' +
                '}';
    }
}
