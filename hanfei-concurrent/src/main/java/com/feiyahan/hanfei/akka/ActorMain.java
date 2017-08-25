package com.feiyahan.hanfei.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.Future;

import java.util.UUID;

/**
 * Created by hanfei7 on 2017/8/15.
 */
public class ActorMain {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("as", ConfigFactory.parseString(""));
        ActorRef ar1 = system.actorOf(MasterActor.props("ar1"));
        ActorRef ar2 = system.actorOf(MasterActor.props("ar2"));
        ar1.tell(null,ActorRef.noSender());
        Future<Object> future = Patterns.ask(ar1, "get", 5000);

        UUID.randomUUID().toString();
        system.shutdown();
        system.awaitTermination();
    }
}
