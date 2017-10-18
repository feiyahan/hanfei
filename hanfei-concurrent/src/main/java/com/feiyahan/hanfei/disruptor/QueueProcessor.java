package com.feiyahan.hanfei.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class QueueProcessor implements EventHandler<QueueEvent>,WorkHandler<QueueEvent> {
    public void onEvent(QueueEvent queueEvent, long l, boolean b) throws Exception {
        this.onEvent(queueEvent);
    }

    public void onEvent(QueueEvent queueEvent) throws Exception {

    }
}
