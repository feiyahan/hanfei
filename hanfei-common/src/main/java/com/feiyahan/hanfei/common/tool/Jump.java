package com.feiyahan.hanfei.common.tool;

import java.util.Map;

/**
 * 跳转辅助类 祖先
 * Created by hanfei7 on 2017/1/13.
 */
public class Jump {

    /**
     * 跳转URL
     */
    private String jumpUrl;
    /**
     * 跳转类型
     */
    private String jumpType;

    /**
     * 跳转参数
     */
    private Map<String,Object> param;

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public String getJumpType() {
        return jumpType;
    }

    public void setJumpType(String jumpType) {
        this.jumpType = jumpType;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

}
