package com.feiyahan.hanfei.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by hanfei7 on 2017/8/22.
 */
public class RobotMsgMain {
    public static void main(String[] args) {
        long sTime = System.currentTimeMillis();
        // Executor that will be used to construct new threads for consumers
        ExecutorService executor = Executors.newCachedThreadPool();

        // The factory for the event
        EventFactory<RobotMsgText> factory = RobotMsgText.EVENT_FACTORY;

        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;

        /**
         //BlockingWaitStrategy 是最低效的策略，但其对CPU的消耗最小并且在各种不同部署环境中能提供更加一致的性能表现
         WaitStrategy BLOCKING_WAIT = new BlockingWaitStrategy();
         //SleepingWaitStrategy 的性能表现跟BlockingWaitStrategy差不多，对CPU的消耗也类似，但其对生产者线程的影响最小，适合用于异步日志类似的场景
         WaitStrategy SLEEPING_WAIT = new SleepingWaitStrategy();
         //YieldingWaitStrategy 的性能是最好的，适合用于低延迟的系统。在要求极高性能且事件处理线数小于CPU逻辑核心数的场景中，推荐使用此策略；例如，CPU开启超线程的特性
         WaitStrategy YIELDING_WAIT = new YieldingWaitStrategy();
         */
        // Construct the Disruptor
        Disruptor<RobotMsgText> disruptor = new Disruptor<RobotMsgText>(factory, bufferSize, executor, ProducerType.SINGLE, new YieldingWaitStrategy());

        // Connect the handler
        disruptor.handleEventsWith(new RobotMsgHandle());

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<RobotMsgText> ringBuffer = disruptor.getRingBuffer();

        final RobotMsgProducer producer = new RobotMsgProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);
        final RobotMsgText text = new RobotMsgText();
        text.setPin("dddddd");
        text.setMsgType("aaaaaaa");
        List<Future<Void>> futureList = new ArrayList<Future<Void>>();
        for (long l = 0; l < 100; l++) {
            final long finalL = l;
            Future<Void> future = executor.submit(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    text.setMsgId("" + finalL);
                    text.setMsg("我是生产者" + finalL);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    producer.onData(text);
                    return null;
                }
            });
            futureList.add(future);
        }
        for (int i = 0; i < futureList.size(); i++) {
            try {
                futureList.get(i).get();
                System.out.println("future get " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        disruptor.shutdown();//关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；
        executor.shutdown();//关闭 disruptor 使用的线程池；如果需要的话，必须手动关闭， disruptor 在 shutdown 时不会自动关闭；
        System.out.println("共耗时："+(System.currentTimeMillis()-sTime));
    }

    public void test() throws ExecutionException, InterruptedException {
        int BUFFER_SIZE = 1024;
        int THREAD_NUMBERS = 4;
        /*
         * createSingleProducer创建一个单生产者的RingBuffer，
         * 第一个参数叫EventFactory，从名字上理解就是"事件工厂"，其实它的职责就是产生数据填充RingBuffer的区块。
         * 第二个参数是RingBuffer的大小，它必须是2的指数倍 目的是为了将求模运算转为&运算提高效率
         * 第三个参数是RingBuffer的生产都在没有可用区块的时候(可能是消费者（或者说是事件处理器） 太慢了)的等待策略
         */
        final RingBuffer<RobotMsgText> ringBuffer = RingBuffer.createSingleProducer(RobotMsgText.EVENT_FACTORY, BUFFER_SIZE, new YieldingWaitStrategy());

        //创建线程池
        ExecutorService executors = Executors.newFixedThreadPool(THREAD_NUMBERS);

        //创建SequenceBarrier
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

        /****************** @beg 消费者消费数据 2017-1-11******************/
        //创建消息处理器
        BatchEventProcessor<RobotMsgText> transProcessor = new BatchEventProcessor<RobotMsgText>(
                ringBuffer, sequenceBarrier, new RobotMsgHandle());

        //这一步的目的就是把消费者的位置信息引用注入到生产者    如果只有一个消费者的情况可以省略
        ringBuffer.addGatingSequences(transProcessor.getSequence());

        //把消息处理器提交到线程池
        executors.submit(transProcessor);
        /****************** @end 消费者消费数据 2017-1-11******************/

        //如果存在多个消费者 那重复执行上面3行代码 把TradeHandler换成其它消费者类

        /****************** @beg 生产者生产数据  2017-1-11******************/

        Future<?> future = executors.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                long seq;
                for (int i = 0; i < 100; i++) {
                    seq = ringBuffer.next();//占个坑 --ringBuffer一个可用区块
                    ringBuffer.get(seq).setPin((Math.random() * 9999) + "");//给这个区块放入 数据
                    ringBuffer.publish(seq);//发布这个区块的数据使handler(consumer)可见
                    System.out.println("生产了" + i + "个" + ringBuffer.get(seq));
                }
                return null;
            }
        });

        /****************** @end 生产者生产数据 2017-1-11******************/

        future.get();//等待生产者结束
        Thread.sleep(1000);//等上1秒，等消费都处理完成
        transProcessor.halt();//通知事件(或者说消息)处理器 可以结束了（并不是马上结束!!!）
        executors.shutdown();//终止线程
    }
}
