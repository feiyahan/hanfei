package com.feiyahan.hanfei.service.akka;

import akka.actor.UntypedActor;

/**
 * Created by hanfei7 on 2016/9/19.
 */
public class HelloAkka2 extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof String){
            System.out.println(Thread.currentThread().getName()+" Hello "+message);
        }
    }
}
