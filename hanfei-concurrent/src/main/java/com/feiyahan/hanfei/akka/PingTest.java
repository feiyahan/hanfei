package com.feiyahan.hanfei.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.dispatch.*;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.Function1;
import scala.concurrent.Await;
import scala.concurrent.ExecutionContextExecutor;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.ClassTag$class;
import scala.reflect.ManifestFactory;
import scala.runtime.BoxedUnit;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by hanfei7 on 2017/8/23.
 */
public class PingTest {
    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create();
        Timeout timeout = Timeout.longToTimeout(10000);
        ActorRef serviceRef = system.actorOf(PongActor.props());
        ActorRef clientRef = system.actorOf(PingClientActor.props(serviceRef));
        Map map = new HashMap<String,Object>();
        map.put("result","client");
        clientRef.tell(map,ActorRef.noSender());
        System.out.println("result ==== "+map);
    }

    public void test() throws Exception {
        long s = System.currentTimeMillis();
        ActorSystem system = ActorSystem.create();
        ExecutionContextExecutor dispatcher = system.dispatcher();
        List<Future<Object>> futures = new ArrayList<Future<Object>>();

        for (int i = 0; i < 10; i++) {
            ActorRef actorRef = system.actorOf(PongActor.props());
            System.out.println(actorRef.path());
            String message = (i==5)?"pong":"ping";
            Future<Object> future = Patterns.ask(actorRef, message, 1500);
            futures.add(future);
        }

        Future<Iterable<Object>> sequence = Futures.sequence(futures, dispatcher);
        Future<Object> map = sequence.map(new Mapper<Iterable<Object>, Object>() {
            @Override
            public Object apply(Iterable<Object> parameter) {
                Iterator<Object> iterator = parameter.iterator();
                String result="";
                while (iterator.hasNext()) {
                    Object next = iterator.next();
                    result+=next+",";
                }
                return result;
            }
        }, dispatcher);

        Object result = Await.result(map, Duration.create(2000, TimeUnit.MILLISECONDS));
        System.out.println("result ="+result);
        system.shutdown();
        System.out.println("shutdown");
        system.awaitTermination();
        System.out.println("awaitTermination "+(System.currentTimeMillis()-s));
    }
}
