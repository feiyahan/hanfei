package com.feiyahan.hanfei.common.tool;

/**
 * Created by hanfei7 on 2017/1/13.
 */
public class Button {

    /**
     * 按钮名称
     */
    private String name;
    /**
     * 按钮类型 button reset submit
     */
    private String type;

    /**
     * 点击按钮跳转
     */
    private Jump jump;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Jump getJump() {
        return jump;
    }

    public void setJump(Jump jump) {
        this.jump = jump;
    }
}
