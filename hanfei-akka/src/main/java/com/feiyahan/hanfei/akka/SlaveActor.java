package com.feiyahan.hanfei.akka;

import akka.actor.UntypedActor;

/**
 * Created by hanfei7 on 2017/7/25.
 */
public class SlaveActor extends UntypedActor {
    private Integer count=0;
    @Override
    public void onReceive(Object message) throws Exception {
        if(message.equals("GET")){
            System.out.println(Thread.currentThread().getName()+" get count sleep "+2500);
            Thread.sleep(2500);
            getSender().tell(count,getSelf());
        } else if(message.equals("ADD")){
            count++;
            System.out.println(Thread.currentThread().getName()+" count="+count);
        } else if(message instanceof Exception) {
            System.out.println("异常啦啦啦啦啦啦啦"+message);
        } else {
            unhandled(message);
        }

    }
}
