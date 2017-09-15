package com.feiyahan.hanfei.disruptor;

import com.lmax.disruptor.EventFactory;

public class QueueEvent<T> {

    private T object;

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public static EventFactory<QueueEvent> EVENT_FACTORY =new EventFactory<QueueEvent>() {
        public QueueEvent newInstance() {
            return new QueueEvent();
        }
    };
}
