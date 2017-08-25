import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.ScalaActorRef;
import akka.dispatch.*;
import akka.pattern.Patterns;
import com.feiyahan.hanfei.akka.MasterActor;
import com.feiyahan.hanfei.akka.SlaveActor;
import scala.Function1;
import scala.concurrent.Await;
import scala.concurrent.ExecutionContextExecutor;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;
import scala.runtime.BoxedUnit;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;


/**
 * Created by hanfei7 on 2017/7/25.
 */
public class MyActorTest {
    public static void test() throws Exception {
        final ActorSystem system = ActorSystem.create("actorTest");
        final ExecutionContextExecutor dispatcher = system.dispatcher();
        long sTime = System.currentTimeMillis();
        final List<Future<Object>> futureList = new ArrayList<Future<Object>>();
//        final Integer[] result =new Integer[10];

        for(int i=0;i<4;i++){
            Future<Object> future = Futures.future(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    System.out.println(Thread.currentThread().getName()+" sleep");
                    Thread.sleep(5000);
                    return 1;
                }
            }, dispatcher);
            futureList.add(future);
        }
        try {
            Future<Iterable<Object>> sequence = Futures.sequence(futureList, dispatcher);

            Future<Integer> resultFuture = sequence.map(new Mapper<Iterable<Object>,Integer>() {
                @Override
                public Integer apply(Iterable<Object> parameter) {
                    System.out.println(Thread.currentThread().getName()+" sequence.map....");
                    Iterator<Object> iterator = parameter.iterator();
                    Integer r = 0;
                    while (iterator.hasNext()){
                        Object next = iterator.next();
                        r+=(Integer) next;
                    }
                    return r;
                }
            },dispatcher);

            Integer result = Await.result(resultFuture, Duration.create(4000,TimeUnit.MILLISECONDS));
            long eTime = System.currentTimeMillis();
            System.out.println("*******************"+(eTime-sTime));
            /*for (int i = 0;i<10;i++){
            }*/
            System.out.println("result = "+result);
            //        resultFuture.onSuccess(new PrintResult<Integer>(),system.dispatcher());

        } catch (Exception e) {
            e.getStackTrace();
        }finally {
            system.shutdown();
            System.out.println("system.shutdown");
            system.awaitTermination();
            System.out.println("system.awaitTermination");
        }

    }
    public static void main(String[] args) throws Exception {
        MyActorTest.test();
        /*for(int i=0;i<10;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        MyActorTest.test();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }*/

    }
}

/*class PrintResult<T> extends OnSuccess<T> {

    @Override
    public void onSuccess(T result) throws Throwable {
        System.out.println("result = "+result);
    }

    *//*@Override
    public void onComplete(Throwable failure, T success) throws Throwable {
        System.out.println("result = "+success);
    }*//*
}*/
