package com.feiyahan.hanfei.pojo;

import java.util.Date;

/**
 *
 * Created with IntelliJ IDEA <br/>
 * User: hanfei <br/>
 * Date:2015/7/30 15:14 <br/>
 * Version:V1.0 <br/>
 */
public class ApiSource {

    private int id;
    private String act;//服务编号
    private String name;//服务名称
    private String className;// 类名
    private String methodName;// 方法名
//    private String classMethod;//类和方法
    private String ver;//版本号
    private int actType;//Action 使用对象
    private Date createTime;//入口时间
    private int login;//是否登录访问0_不需要，1_需要
    private int isOpen;//打开0_关闭 1_打开

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public int getActType() {
        return actType;
    }

    public void setActType(int actType) {
        this.actType = actType;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public int getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(int isOpen) {
        this.isOpen = isOpen;
    }
}
