package com.feiyahan.hanfei.akka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

import java.util.Map;

/**
 * Created by hanfei7 on 2017/8/23.
 */
public class PongActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if ("ping".equals(message)) {
            Object pong = getPong();
            getSender().tell(pong, ActorRef.noSender());
        } else if (message instanceof Map){
            ((Map) message).put("result","service");
            System.out.println("这里是服务端，接收到了你的消息"+message);
        } else {
//            unhandled(message);
            new RuntimeException("ssssssssss");
        }
    }

    public static Props props() {
        return Props.create(PongActor.class);
    }

    public Object getPong() throws Exception {
        System.out.println("thread name " + Thread.currentThread().getName());
        return "pong";
    }
}
