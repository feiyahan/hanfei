package com.feiyahan.hanfei.service.akka;

import akka.actor.UntypedActor;
import com.alibaba.fastjson.JSONObject;
import com.feiyahan.hanfei.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by hanfei7 on 2016/9/21.
 */
//@Named("CountingChildActor")
public class CountingChildActor extends UntypedActor{

    int num=0;

    private String str;

    public CountingChildActor(String str) {
        this.str = str;
    }


    @Override
    public void onReceive(Object o) throws Exception {
        if(o instanceof Exception){
            throw (Exception) o;
        }else if(o instanceof Integer){
            System.out.println("---------"+o);
            num+=(Integer) o;
        }else if(o.equals("GET")){
            getSender().tell(num,getSelf());
        }else {
            unhandled(o);
        }
    }

}
