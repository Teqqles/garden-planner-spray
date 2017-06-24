package com.sixfootsoftware.service

import akka.actor.Actor
import com.sixfootsoftware.model.ImplicitVegetableJson._
import com.sixfootsoftware.model.Vegetable
import spray.json._
import spray.routing._

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class GardenPlannerServiceActor extends Actor with GardenPlannerService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(vegetable)
}


// this trait defines our service behavior independently from the service actor
trait GardenPlannerService extends HttpService {

  val vegetable: Route =
    path("vegetable") {
      get {
        complete {
          ""
        }
      } ~
      post {
        entity(as[Vegetable]) { vegetable =>
          complete {
            vegetable.toJson.toString()
          }
        }
      }
    }
}