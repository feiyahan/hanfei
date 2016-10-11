package com.feiyahan.hanfei.service.akka;

import akka.actor.UntypedActor;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by hanfei7 on 2016/9/21.
 */
//@Named("CountingChild2Actor")
public class CountingChild2Actor extends UntypedActor {

    /*@Inject
    private CountingService countingService;*/
    public static class Get {}
    private String temp;


    @Override
    public void onReceive(Object o) throws Exception {
        if(o instanceof Exception){
            throw (Exception) o;
        }else if(o instanceof String){
            temp=(String) o;
        }else if(o instanceof Get){
            getSender().tell(temp,getSelf());
        }else {
            unhandled(o);
        }
    }
}
