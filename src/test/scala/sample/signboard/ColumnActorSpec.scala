package sample.signboard

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestActorRef, TestKit}
import org.scalatest.{MustMatchers, WordSpecLike}
import sample.StopSystemAfterAll

class ColumnActorSpec extends TestKit(ActorSystem("StageActorSpec"))
  with WordSpecLike
  with MustMatchers
  with ImplicitSender
  with StopSystemAfterAll {

  "BoardActorSpec" must {
    "have sent cards when send AddCard Message" in {
      import ColumnActor._
      val props = ColumnActor.props("doing")
      val columnActor = system.actorOf(props)
      columnActor ! AddCard("create frame1")
      columnActor ! AddCard("create frame2")
      columnActor ! AddCard("create frame3")
      columnActor ! GetCards
      expectMsg(Vector("create frame1", "create frame2", "create frame3"))
    }

  }
}
