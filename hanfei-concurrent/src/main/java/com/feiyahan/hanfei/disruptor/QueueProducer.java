package com.feiyahan.hanfei.disruptor;

import com.lmax.disruptor.RingBuffer;

public class QueueProducer {

    private final RingBuffer<QueueEvent> ringBuffer;

    public QueueProducer(RingBuffer<QueueEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(QueueEvent queueEvent){
        long sequence = ringBuffer.next();
        try {
            QueueEvent event = ringBuffer.get(sequence);
            event.setObject("ceshi");
        } finally {
            ringBuffer.publish(sequence);
        }

    }

}
