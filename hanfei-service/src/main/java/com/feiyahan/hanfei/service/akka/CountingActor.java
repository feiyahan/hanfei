package com.feiyahan.hanfei.service.akka;

import akka.actor.*;

import javax.inject.Inject;
import javax.inject.Named;

import akka.japi.Function;
import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Scope;
import scala.Option;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

import static akka.actor.SupervisorStrategy.*;
import static akka.actor.SupervisorStrategy.escalate;

/**
 * An actor that can count using an injected CountingService.
 *
 * @note The scope here is prototype since we want to create a new actor
 * instance for use of this bean.
 */
//@Named("CountingActor")
public class CountingActor extends UntypedActor {

  public static class Get {}
  public static class Count {}
  public static class Get2 {}
  private static SupervisorStrategy strategy=
      new OneForOneStrategy(10, Duration.create("1 minute"),
          new Function<Throwable, Directive>() {
            @Override
            public Directive apply(Throwable throwable) {
              if (throwable instanceof ArithmeticException) {
                return resume();
              } else if (throwable instanceof NullPointerException) {
                return restart();
              } else if (throwable instanceof IllegalArgumentException) {
                return stop();
              }else {
                return escalate();
              }
            }
          }
      );
  public SupervisorStrategy supervisorStrategy(){
    return strategy;
  }

  @Override
  public void onReceive(Object message) throws Exception {
    if (message instanceof Props) {
        getSender().tell(getContext().actorOf((Props) message), getSelf());
    }else {
      unhandled(message);
    }
  }

  @Override
  public void preRestart(Throwable cause, Option<Object> msg) {
    // do not kill all children, which is the default here
  }
}
