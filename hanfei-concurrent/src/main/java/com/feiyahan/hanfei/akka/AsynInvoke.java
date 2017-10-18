package com.feiyahan.hanfei.akka;

import akka.actor.ActorSystem;
import akka.dispatch.Futures;
import org.apache.commons.lang.StringUtils;
import scala.concurrent.Await;
import scala.concurrent.ExecutionContextExecutor;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 异步调用封装类 基于AKKA
 */
public class AsynInvoke {

    private ActorSystem actorSystem;
    private ExecutionContextExecutor ece;

    public AsynInvoke(String asName) {
        this.actorSystem = ActorSystem.create(asName);
        this.ece = actorSystem.dispatcher();
    }

    public Future<Object> invoke(InvokeParam invokeParam) throws Exception {
        final Object object = invokeParam.getObject();
        String method = invokeParam.getMethod();
        final Object[] args = invokeParam.getArgs();
        if (null == object || StringUtils.isBlank(method)) {
            throw new RuntimeException("NullPointerException");
        }
        Class[] classes = null;
        if (null != args) {
            classes = new Class[args.length];
            for (int i=0;i<classes.length;i++) {
                Object arg = args[i];
                classes[i] = arg.getClass();
            }
        }
//        final Object obj = aClass.newInstance();
        final Method declaredMethod = object.getClass().getDeclaredMethod(method, classes);
        return Futures.future(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return declaredMethod.invoke(object,args);
            }
        }, ece);
    }

    public Object getResult(Future<Object> future, long timeOut) throws Exception {
        return Await.result(future, Duration.create(timeOut, TimeUnit.MILLISECONDS));// 业务接口等5秒，这里最多等6秒
    }
    public void shoutDown(){
        if (null != actorSystem) {
            actorSystem.shutdown();
            actorSystem.awaitTermination();// 等待子线程都释放了才能完全关闭actorSystem
        }
    }
}
