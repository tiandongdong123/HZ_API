package com.hanzhong.data.web.util.longdun.entinvestinfo.model;

/**
 *  
 *  @Description
 *  @Author   luqs   
 *  @Date 2019/1/10 15:08 
 *  @Version  V1.0   
 */
public class EntActualControllerQryParam {
    /**
     * 企业名称
     */
    private String entName;
    /**
     * 过滤关系
     */
    private String attIds;
    /**
     * 层级
     */
    private int level;

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getAttIds() {
        return attIds;
    }

    public void setAttIds(String attIds) {
        this.attIds = attIds;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "EntActualControllerQryParam{" +
                "entName='" + entName + '\'' +
                ", attIds='" + attIds + '\'' +
                ", level=" + level +
                '}';
    }
}
