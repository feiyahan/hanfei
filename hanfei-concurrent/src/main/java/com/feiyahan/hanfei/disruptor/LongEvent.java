package com.feiyahan.hanfei.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by hanfei7 on 2017/8/14.
 */
public final class LongEvent {
    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public final static EventFactory<LongEvent> EVENT_FACTORY = new EventFactory<LongEvent>() {
        public LongEvent newInstance() {
            return new LongEvent();
        }
    };
}
