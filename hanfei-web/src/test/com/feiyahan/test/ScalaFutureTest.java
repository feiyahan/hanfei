package com.feiyahan.test;

import akka.actor.ActorSystem;
import akka.dispatch.Futures;
import akka.dispatch.OnComplete;
import scala.Function1;
import scala.concurrent.Future;
import scala.runtime.BoxedUnit;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by hanfei7 on 2017/7/25.
 * scala future 测试
 */
public class ScalaFutureTest {
    private static int count;
    private static Map map = new HashMap();

    public static void main(String args[]) {
        final ActorSystem system = ActorSystem.create("myTestSystem");

        try {
            for (int i = 0; i < 2; i++) {
                int sleepTime = 0;
                if (i == 0) sleepTime = 6000;
                else sleepTime = 4000;
                final int finalSleepTime = sleepTime;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Future<String> future = Futures.future(new Callable<String>() {
                            @Override
                            public String call() throws Exception {
                                Thread.sleep(finalSleepTime);
                                return "hello " + Thread.currentThread().getName() + " sleep " + finalSleepTime;
                            }
                        }, system.dispatcher());

                        future.onComplete(new OnComplete<String>() {
                            @Override
                            public void onComplete(Throwable failure, String success) throws Throwable {
                                count++;
                                if (null == failure) {
                                    map.put("success", success);
                                    System.out.println("count=" + count);
                                } else {
                                    throw new RuntimeException(failure);
                                }
                            }
                        }, system.dispatcher());// 非阻塞试
                        // 阻塞试
                        /*try {
                            String result = Await.result(future, FiniteDuration.create(7000, TimeUnit.MILLISECONDS));
                            set.add(result);
                            System.out.println(result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }*/
                        System.out.println(Thread.currentThread().getName() + " system hash code:" + system.dispatcher().hashCode());
                    }
                }).start();
            }

            ScalaFutureTest sft = new ScalaFutureTest();
            boolean stop = false;
            while (!stop) {
                Thread.sleep(100);
                int c = sft.getCount();
                System.out.println("c===="+c);
                if (c == 2) {
                    stop = true;
                    System.out.println(map.toString());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            system.shutdown();
            System.out.println("system.shutdown");
        }
    }

    public int getCount() {
        return count;
    }
}


