package com.feiyahan.hanfei.service.impl;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.testkit.TestProbe;
import akka.util.Timeout;
import com.alibaba.fastjson.JSONObject;
import com.feiyahan.hanfei.service.IndexService;
import com.feiyahan.hanfei.service.akka.CountingActor;
import com.feiyahan.hanfei.service.AkkaService;
import com.feiyahan.hanfei.service.akka.CountingChild2Actor;
import com.feiyahan.hanfei.service.akka.CountingChildActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

import static akka.pattern.Patterns.ask;
import static com.feiyahan.hanfei.service.akka.SpringExtension.SpringExtProvider;

/**
 * Created by hanfei7 on 2016/9/21.
 */
@Service
public class AkkaServiceImpl implements AkkaService {

    @Autowired
    private ActorSystem actorSystem;

    @Autowired
    private IndexService indexService;

//    private ActorRef masterActor;
    @Override
    public String service2Akka(int num,String host) {
        StringBuffer sb =new StringBuffer();
//        ActorRef actorRef = actorSystem.actorOf(SpringExtProvider.get(actorSystem).props("CountingActor"));


        try {
            ActorRef masterActor = actorSystem.actorOf(Props.create(CountingActor.class));
            System.out.println("masterActor="+masterActor);
//            xxxx();
            FiniteDuration duration = FiniteDuration.create(3*1000, TimeUnit.MILLISECONDS);

            ActorRef child1Actor = (ActorRef) Await.result(ask(masterActor, Props.create(CountingChildActor.class,"child1"),
                    Timeout.longToTimeout(5000)), duration);

            /*ActorRef child2Actor = (ActorRef) Await.result(ask(actorRef, Props.create(CountingChild2Actor.class),
                    Timeout.longToTimeout(5000)), duration);
            System.out.println("child2Actor "+child2Actor);
*/
            child1Actor.tell(num,ActorRef.noSender());
            System.out.println(Await.result(ask(child1Actor, "GET", 5000), duration));
            /*final TestProbe probe=new TestProbe(actorSystem);
            probe.watch(child1Actor);
            child1Actor.tell(new Exception(),ActorRef.noSender());
//            System.out.println(Await.result(ask(child1Actor,"GET",5000),duration));
            probe.expectMsgClass(Terminated.class);*/

//            child1Actor.tell(new NullPointerException(),ActorRef.noSender());
//            child2Actor.tell("child2",ActorRef.noSender());
//            child2Actor.tell("child2",ActorRef.noSender());

            System.out.println("-------------------------");

//            System.out.println(Await.result(ask(child2Actor, new CountingChild2Actor.Get(), 5000), duration));

            System.out.println("=========================");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    /*private void xxxx(){
        System.out.println("xxxx masterActor="+masterActor);
    }*/

}
