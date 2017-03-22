package com.feiyahan.hanfei.api;

/**
 * Created by hanfei7 on 2017/2/10.
 * Api返回基础实体类
 */
public class ApiResult {
    private int code;// 编码
    private String message; // 消息
    private Object data;// 业务数据

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
