package com.feiyahan.hanfei.queue;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by hanfei7 on 2017/8/28.
 */
public class BlockingQueueTest {
    //生产者
    public static class Producer implements Runnable {
        private final BlockingQueue<Integer> blockingQueue;
        private volatile boolean flag;
        private Random random;

        public Producer(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
            flag = false;
            random = new Random();

        }

        public void run() {
//            while (!flag) {
            for (int i = 0; i < 10000; i++) {
                int info = i;
                try {
                    blockingQueue.put(info);
                    System.out.println(Thread.currentThread().getName() + " produce " + info);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void shutDown() {
            flag = true;
        }
    }

    //消费者
    public static class Consumer implements Runnable {
        private final BlockingQueue<Integer> blockingQueue;
        private volatile boolean flag;

        public Consumer(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        public void run() {
            while (!flag) {
                int info;
                try {
                    info = blockingQueue.take();
                    System.out.println(Thread.currentThread().getName() + " consumer " + info);
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        public void shutDown() {
            flag = true;
        }
    }

    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(64);
        Producer producer = new Producer(blockingQueue);
        final Consumer consumer = new Consumer(blockingQueue);
        Thread threadP = new Thread(producer, "producer");
        threadP.start();
        //创建5个生产者，5个消费者
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    new Thread(consumer, "consumer" + (i)).start();
                }
            }
        });
        threadC.start();

        try {
            threadP.join();
            threadC.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producer.shutDown();
        consumer.shutDown();
        System.out.println("总耗时："+(System.currentTimeMillis()-s));

    }
}
