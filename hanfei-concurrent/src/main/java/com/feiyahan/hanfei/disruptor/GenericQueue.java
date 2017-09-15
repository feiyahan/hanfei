package com.feiyahan.hanfei.disruptor;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.YieldingWaitStrategy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GenericQueue {
    public static void main(String[] args) {
        int BUFFER_SIZE = 1024;
        int THREAD_NUMBERS = 4;
        /*
         * createSingleProducer创建一个单生产者的RingBuffer，
         * 第一个参数叫EventFactory，从名字上理解就是"事件工厂"，其实它的职责就是产生数据填充RingBuffer的区块。
         * 第二个参数是RingBuffer的大小，它必须是2的指数倍 目的是为了将求模运算转为&运算提高效率
         * 第三个参数是RingBuffer的生产都在没有可用区块的时候(可能是消费者（或者说是事件处理器） 太慢了)的等待策略
         */
        RingBuffer<QueueEvent> ringBuffer = RingBuffer.createSingleProducer(QueueEvent.EVENT_FACTORY, BUFFER_SIZE, new YieldingWaitStrategy());

        //创建线程池
        ExecutorService executors = Executors.newFixedThreadPool(THREAD_NUMBERS);

        //创建SequenceBarrier
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

        /****************** @beg 消费者消费数据 2017-1-11******************/
        //创建消息处理器
        BatchEventProcessor<QueueEvent> transProcessor = new BatchEventProcessor<QueueEvent>(ringBuffer, sequenceBarrier, new QueueProcessor());

        //这一步的目的就是把消费者的位置信息引用注入到生产者    如果只有一个消费者的情况可以省略
        ringBuffer.addGatingSequences(transProcessor.getSequence());

        //把消息处理器提交到线程池
        executors.submit(transProcessor);
        /****************** @end 消费者消费数据 2017-1-11******************/

        //如果存在多个消费者 那重复执行上面3行代码 把TradeHandler换成其它消费者类

        /****************** @beg 生产者生产数据  2017-1-11******************/

        QueueProducer producer = new QueueProducer(ringBuffer);

        producer.onData(new QueueEvent());

        /****************** @end 生产者生产数据 2017-1-11******************/

        transProcessor.halt();//通知事件(或者说消息)处理器 可以结束了（并不是马上结束!!!）
        executors.shutdown();//终止线程
    }
}
