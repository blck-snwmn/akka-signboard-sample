package sample.signboard

import akka.actor.{Actor, ActorRef, Props}

object ColumnActor {
  def props(name: String) = Props(new ColumnActor(name))

  case object GetCards

  case class AddCard(name: String)

}

class ColumnActor(name: String) extends Actor {

  import ColumnActor._

  var cards: Vector[String] = Vector[String]()

  override def receive: Receive = {
    case AddCard(cardName) => cards = cards :+ cardName
    case GetCards => sender() ! cards
  }
}