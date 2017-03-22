package com.feiyahan.hanfei.api;

import java.util.Map;

/**
 * Api参数实体类
 * Created with IntelliJ IDEA <br/>
 * User: hanfei <br/>
 * Date:2015/7/30 10:51 <br/>
 * Version:V1.0 <br/>
 */
public class ApiParams {
    private String dataJson;
    private long timestamp;//时间戳
    private String sign;//密文
    private String uid;//用户id
    private String act;//请求action
    private String ver;//app版本
    private String os;//客户端类型
    private Map<String, Object> data;//参数集合

    public String getDataJson() {
        return dataJson;
    }
    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Map<String, Object> getData() {
        return data;
    }
    public void setData(Map<String, Object> data) {
        this.data = data;
    }
    public Object getParam(String key) {
        return data.get(key);
    }
    public void setParam(String key, Object value) {
        this.data.put(key, value);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }
}
