package com.feiyahan.hanfei.akka;

import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * Created by hanfei7 on 2017/7/25.
 */
public class MasterActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof Props){
            getSender().tell(getContext().actorOf((Props) message),getSelf());
        }
    }
}
