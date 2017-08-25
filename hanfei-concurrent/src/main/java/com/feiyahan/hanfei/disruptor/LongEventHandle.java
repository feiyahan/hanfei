package com.feiyahan.hanfei.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * Created by hanfei7 on 2017/8/14.
 */
public class LongEventHandle implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long l, boolean b) throws Exception {
        System.out.println("Long:"+l+" Event:"+event);
    }

}
