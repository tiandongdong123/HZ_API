package com.hanzhong.data.web.model.bo;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/4 19:30 
 *  @Version  V1.0   
 */
public class IpWhiteListQryBO {
    /**
     * 名称
     */
    private String name;
    /**
     * 状态
     */
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "IpWhiteListQryBO{" +
                "name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
