package com.sixfootsoftware.service

import com.sixfootsoftware.model.MonthOfYear.{Feb, Mar}
import com.sixfootsoftware.model.SoilType.Acidic
import com.sixfootsoftware.model.Vegetable
import com.sixfootsoftware.model.ImplicitVegetableJson._
import org.scalatest.{Matchers, WordSpec}
import spray.http.StatusCodes._
import spray.http._
import spray.json._
import spray.testkit.ScalatestRouteTest

class GardenPlannerServiceSpec extends WordSpec with Matchers with ScalatestRouteTest with GardenPlannerService {

  def actorRefFactory = system

  "GardenPlannerService" should {
    "Accept POST requests with vegetable facts" in {

      val carrot =
        Vegetable(id = None, "Carrot", "Carrotus", harvest = List(Feb, Mar), sowing = List(Feb), soilPreference = List(Acidic))
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
