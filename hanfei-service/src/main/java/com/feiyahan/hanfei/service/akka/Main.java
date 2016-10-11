package com.feiyahan.hanfei.service.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import static akka.pattern.Patterns.ask;
import akka.util.Timeout;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.feiyahan.hanfei.service.akka.CountingActor.Count;
import com.feiyahan.hanfei.service.akka.CountingActor.Get;

import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;
import java.util.concurrent.TimeUnit;

/**
 * A main class to start up the application.
 */
public class Main {
  public static void main(String[] args) throws Exception {
    // create a spring context and scan the classes
    AnnotationConfigApplicationContext ctx =
      new AnnotationConfigApplicationContext();
    ctx.scan("com.feiyahan.hanfei.akka");
    ctx.refresh();

    // get hold of the actor system
    ActorSystem system = ctx.getBean(ActorSystem.class);
    // use the Spring Extension to create props for a named actor bean
    ActorRef counter = system.actorOf(SpringExtension.SpringExtProvider.get(system).props("CountingActor"), "counter");
    ActorRef counter2 = system.actorOf(SpringExtension.SpringExtProvider.get(system).props("CountingActor"), "counter2");

    // tell it to count three times
    counter.tell(new Count(), null);
    /*system.shutdown();
    system.awaitTermination();*/
    counter.tell(new Count(), null);
    counter.tell(new Count(), null);

    // print the result
    FiniteDuration duration = FiniteDuration.create(3, TimeUnit.SECONDS);
    Future<Object> result = ask(counter, new Get(),
      Timeout.durationToTimeout(duration));
    System.out.println("------------------");
    Future<Object> ask2 = ask(counter2, "", Timeout.durationToTimeout(duration));

    try {
      System.out.println("Got back " + Await.result(result, duration));
    } catch (Exception e) {
      System.err.println("Failed getting result: " + e.getMessage());
      throw e;
    } finally {
      system.shutdown();
      system.awaitTermination();
    }
  }
}
