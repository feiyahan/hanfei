package com.feiyahan.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 多线程测试类
 * Created by hanfei7 on 2016/12/8.
 */
public class MultiThreadTest {

    private ApplicationContext applicationContext;
    public void initApplicationContext(){
        applicationContext=new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
    }
    /**
     * 单线程串行计算
     */
    @Test
    public void singleThreadSerial() throws InterruptedException {
        long start = System.currentTimeMillis();
        Count count = new Count();
        List<Integer> res = new ArrayList();
        res.add(count.random());
        res.add(count.random());
        res.add(count.random());
        res.add(count.random());
        int totle = 0;
        for (int i = 0; i < res.size(); i++) {
            totle += res.get(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("运算结束 耗时：" + (end - start) + "ms  totle：" + totle);
    }

    /**
     * 多线程异步并行计算
     */
    @Test
    public void MultiThreadParallel() throws ExecutionException, InterruptedException, TimeoutException {
        // 异步任务列表
        ArrayList<FutureTask<Integer>> futureTasks = new ArrayList<FutureTask<Integer>>();
        long aStart = System.currentTimeMillis();
        // 初始化十个线程的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        System.out.println("获取线程池所用时间："+(System.currentTimeMillis() - aStart)+"毫秒");
        long start = System.currentTimeMillis();
        final Count count = new Count();
        // Callable 接口实现
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println(Thread.currentThread().getName()+" 执行任务");
                int random = count.random();
                System.out.println(Thread.currentThread().getName()+" 获取结果:" + random);
                return random;
            }
        };
        for (int i = 0; i < 10; i++) {
            //创建一个异步任务
            FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
            futureTasks.add(futureTask);
            //提交异步任务到线程池，让线程池管理任务。
            //由于是异步并行任务，所以这里并不会阻塞
            executorService.submit(futureTask);
        }

        int sum = 0;
        for (FutureTask<Integer> futureTask : futureTasks) {
            //futureTask.get() 得到我们想要的结果
            //该方法有一个重载get(long timeout, TimeUnit unit) 第一个参数为最大等待时间，第二个为时间的单位
            sum += futureTask.get();
        }
        long end = System.currentTimeMillis();
        System.out.println("线程池的任务全部完成:结果为:" + sum + "，main线程关闭，进行线程的清理");
        System.out.println("使用时间：" + (end - start) + "ms");
        //清理线程池
        executorService.shutdown();
    }
    /**
     * Spring 管理 多线程异步并行计算
     */
    @Test
    public void SpringMultiThreadParallel() throws ExecutionException, InterruptedException, TimeoutException {

        long aStart = System.currentTimeMillis();
        // 获取异步线程池对象
        ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) applicationContext.getBean("taskExecutor");
        System.out.println("获取线程池"+taskExecutor+"所用时间："+(System.currentTimeMillis() - aStart)+" 毫秒");
        // 异步任务列表
        ArrayList<FutureTask<Integer>> futureTasks = new ArrayList<FutureTask<Integer>>();

        long start = System.currentTimeMillis();
        final Count count = new Count();
        // Callable 接口实现
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println(Thread.currentThread().getName()+" 执行任务");
                int random = count.random();
                System.out.println(Thread.currentThread().getName()+" 返回结果:" + random);
                return random;
            }
        };
        for (int i = 0; i < 10; i++) {
            //创建一个异步任务
            FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
            futureTasks.add(futureTask);
            //提交异步任务到线程池，让线程池管理任务。
            //由于是异步并行任务，所以这里并不会阻塞
            taskExecutor.submit(futureTask);
        }

        int sum = 0;
        for (FutureTask<Integer> futureTask : futureTasks) {
            //futureTask.get() 得到我们想要的结果
            //该方法有一个重载get(long timeout, TimeUnit unit) 第一个参数为最大等待时间，第二个为时间的单位
            sum += futureTask.get();
        }
        long end = System.currentTimeMillis();
        System.out.println("线程池的任务全部完成:结果为:" + sum);
        System.out.println("使用时间：" + (end - start) + "ms");
        //关闭线程池
//        taskExecutor.shutdown();
    }

    @Test
    public void MultiClientRequest() throws Exception {
        // 初始化Spring容器
        initApplicationContext();

        for (int i=0;i<10;i++){
            System.out.println("================="+i);
            SpringMultiThreadParallel();
//            MultiThreadParallel();
        }
    }

    class Count {
        int random() throws InterruptedException {
            Thread.sleep(1000); //
            return new Random().nextInt(100);
        }

    }
}
