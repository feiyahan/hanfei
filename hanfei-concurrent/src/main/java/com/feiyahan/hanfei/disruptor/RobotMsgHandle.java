package com.feiyahan.hanfei.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * Created by hanfei7 on 2017/8/22.
 */
public class RobotMsgHandle implements EventHandler<RobotMsgText>,WorkHandler<RobotMsgText> {

    @Override
    public void onEvent(RobotMsgText robotMsgText, long l, boolean b) throws Exception {
        this.onEvent(robotMsgText);
    }

    @Override
    public void onEvent(RobotMsgText robotMsgText) throws Exception {
        //这里做具体的消费逻辑
//        robotMsgText.setMsgId(UUID.randomUUID().toString());//简单生成下ID
        System.out.println("消费者："+robotMsgText.toString()+Thread.currentThread().getName());
        Thread.sleep(50);
    }
}
