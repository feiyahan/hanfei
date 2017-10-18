package com.feiyahan.hanfei.akka;

public class InvokeParam {
    private Object object;// 调用实体对象
    private String method;// 调用方法名称
    private String[] parameterTypes;// 参数类型
    private Object[] args;// 参数列表

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(String[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
