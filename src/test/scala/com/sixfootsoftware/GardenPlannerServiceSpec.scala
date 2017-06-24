package com.sixfootsoftware

import spray.testkit.ScalatestRouteTest
import spray.http._
import StatusCodes._
import com.sixfootsoftware.MonthOfYear.{Feb, Mar}
import com.sixfootsoftware.SoilType.Acidic
import org.scalatest.{Matchers, WordSpec}
import spray.json._
import ImplicitVegetableJson._

class GardenPlannerServiceSpec extends WordSpec with Matchers with ScalatestRouteTest with GardenPlannerService {

  def actorRefFactory = system

  "GardenPlannerService" should {
    "Accept POST requests with vegetable facts" in {

      val carrot = Vegetable("Carrot", harvest = List(Feb, Mar), sowing = List(Feb), soilPreference = List(Acidic))
      Post("/vegetable", carrot) ~> vegetable ~> check {
        status === OK
        responseAs[String] shouldBe carrot.toJson.toString
        handled shouldBe true
      }
      Get("/vegetable") ~> vegetable ~> check {
        status === OK
        handled shouldBe true
      }
    }
  }
}
