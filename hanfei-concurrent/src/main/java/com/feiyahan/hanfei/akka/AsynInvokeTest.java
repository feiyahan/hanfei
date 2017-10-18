package com.feiyahan.hanfei.akka;

import scala.concurrent.Future;

import java.util.HashMap;

public class AsynInvokeTest {

    public String helloWorld(String name) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello " + name;
    }

    public static void main(String[] args) {
        AsynInvokeTest invokeTest = new AsynInvokeTest();
        InvokeParam invokeParam = new InvokeParam();
        invokeParam.setObject(invokeTest);
        invokeParam.setMethod("helloWorld");
        invokeParam.setArgs(new Object[]{"hanfei"});
        AsynInvoke asynTest = new AsynInvoke("asynTest");
        try {
            Future<Object> future = asynTest.invoke(invokeParam);
            Thread.sleep(1100);
            Object result = asynTest.getResult(future, 10);
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            asynTest.shoutDown();
        }
    }
}
