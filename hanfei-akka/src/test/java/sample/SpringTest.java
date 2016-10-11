package sample;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import static akka.pattern.Patterns.ask;

import akka.actor.Props;
import akka.util.Timeout;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;


import scala.concurrent.Await;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

public class SpringTest {
  /*@Test
  public void testSpring() throws Exception {
    // create a spring context and scan the classes
    AnnotationConfigApplicationContext ctx =
      new AnnotationConfigApplicationContext();
    ctx.scan("com.feiyahan.hanfei.akka");
    ctx.refresh();

    // get hold of the actor system
    ActorSystem system = ctx.getBean(ActorSystem.class);
    // use the Spring Extension to create props for a named actor bean
    ActorRef counter = system.actorOf(
      SpringExtProvider.get(system).props("countingActor"), "counter");

    // tell it to count three times
    counter.tell(new Count(), null);
    counter.tell(new Count(), null);
    counter.tell(new Count(), null);

    // check that it has counted correctly
    FiniteDuration duration = FiniteDuration.create(3, TimeUnit.SECONDS);
    Future<Object> result = ask(counter, new Get(),
            Timeout.durationToTimeout(duration));
//    assertEquals(3, Await.result(result, duration));
    System.out.println(Await.result(result, duration));

    // shut down the actor system
    system.shutdown();
    system.awaitTermination();
  }

  @Test
  public void testJavaAkka(){
    ActorSystem system=ActorSystem.create("java-akka-test");
    ActorSystem system2=ActorSystem.create("java-akka-test");
    System.out.println(system.equals(system2));
    ActorRef actorRef = system.actorOf(Props.create(HelloAkka.class));
    ActorRef actorRef2 = system2.actorOf(Props.create(HelloAkka2.class));
    actorRef.tell("akka1",ActorRef.noSender());
    actorRef.tell("akka4",ActorRef.noSender());
    actorRef2.tell("akka2",ActorRef.noSender());
    actorRef2.tell("akka3",ActorRef.noSender());

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {

    }
    system.shutdown();
  }
  @Test
  public void testWhat(){
    System.out.println(this.getClass().getSimpleName());
    System.out.println(this.getClass().getName());
  }*/
}
