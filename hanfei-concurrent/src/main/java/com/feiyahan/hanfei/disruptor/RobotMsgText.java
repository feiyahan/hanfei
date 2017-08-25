package com.feiyahan.hanfei.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by hanfei7 on 2017/8/21.
 * 机器人消息体
 */
public final class RobotMsgText {
    private String msgId;// 消息唯一ID
    private String pin;// 用户PIN
    private String msgType;// 业务类型 需要申请才能接入 否则视为非法的业务类型
    private String msg;// 消息内容

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static EventFactory<RobotMsgText> EVENT_FACTORY =new EventFactory<RobotMsgText>() {
        @Override
        public RobotMsgText newInstance() {
            return new RobotMsgText();
        }
    };

    @Override
    public String toString() {
        return "RobotMsgText{" +
                "msgId='" + msgId + '\'' +
                ", pin='" + pin + '\'' +
                ", msgType='" + msgType + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
