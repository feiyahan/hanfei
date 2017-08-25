package com.feiyahan.hanfei.akka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

import java.util.Map;

/**
 * Created by hanfei7 on 2017/8/24.
 * 客户端Actor
 */
public class PingClientActor extends UntypedActor {
    private final ActorRef serviceActorRef;

    public PingClientActor(ActorRef serviceActorRef) {
        this.serviceActorRef = serviceActorRef;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof Map){
            System.out.println("这里是客户端："+message.toString());
            serviceActorRef.tell(message,serviceActorRef);
        }
    }

    public static Props props(ActorRef serviceActorRef){
        return Props.create(PingClientActor.class,serviceActorRef);
    }
}
