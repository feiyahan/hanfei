package com.feiyahan.hanfei.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by hanfei7 on 2017/8/14.
 */
public class LongEventMain {
    public static void main(String[] args) throws Exception {
        // Executor that will be used to construct new threads for consumers
        Executor executor = Executors.newCachedThreadPool();

        // The factory for the event
        EventFactory<LongEvent> factory = LongEvent.EVENT_FACTORY;

        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 8;

        // Construct the Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, executor, ProducerType.SINGLE, new BlockingWaitStrategy());

        // Connect the handler
        disruptor.handleEventsWith(new LongEventHandle());

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);
        long millis = System.currentTimeMillis();
        for (long l = 0; l<10000; l++) {
            bb.putLong(0, l);
            producer.onData(bb);
//            Thread.sleep(1000);
        }
    }
}
