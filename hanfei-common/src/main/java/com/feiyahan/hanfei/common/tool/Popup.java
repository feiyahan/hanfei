package com.feiyahan.hanfei.common.tool;

import java.util.List;

/**
 * Created by hanfei7 on 2017/1/13.
 */
public class Popup {

    /**
     * 弹窗标题
     */
    private String title;
    /**
     * 弹窗文案
     */
    private String content;
    /**
     * 扩展字段
     */
    private String extend;
    /**
     * 按钮
     */
    private List<Button> buttons;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }
}
