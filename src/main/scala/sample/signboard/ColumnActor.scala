package sample.signboard

import akka.actor.{Actor, ActorRef, Props}

object ColumnActor {
  def props(receiver: ActorRef) = Props(new ColumnActor(receiver))

  case object GetCards

  case class AddCard(name: String)

}

class ColumnActor(receiver: ActorRef) extends Actor {

  import ColumnActor._

  var cards: Vector[String] = Vector[String]()

  override def receive: Receive = {
    case AddCard(cardName) => cards = cards :+ cardName
    case GetCards => receiver ! cards
  }
}