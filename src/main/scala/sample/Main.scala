package sample

import akka.actor._
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.concurrent.Future
import scala.util.{Failure, Success}

object Main extends App {
  val interface = "localhost"
  val port = 8080

  implicit val system = ActorSystem()
  implicit val ec = system.dispatcher
  implicit val materializer = ActorMaterializer()
  val binding: Future[ServerBinding] = Http().bindAndHandle(new Api().rout, interface, port)
  binding.onComplete {
    case Success(_) => {}
    case Failure(_) => {}
  }
}

class Api {
  def rout =
    pathPrefix("greeting") {
      pathEndOrSingleSlash {
        get {
          complete("hello akka")
        }
      }
    }
}
