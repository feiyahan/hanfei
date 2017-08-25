package com.feiyahan.hanfei.akka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * Created by hanfei7 on 2017/7/25.
 */
public class MasterActor extends UntypedActor {

    public static Props props(String name) {
        return Props.create(MyActorRef.class, name);
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Props) {
            getSender().tell(getContext().actorOf((Props) message), getSelf());
        } else if(message instanceof UntypedActor){

        }
    }

    private class MyActorRef {
        public ActorRef target;

        public MyActorRef(ActorRef actor) {
            this.target = actor;
        }
    }
}

