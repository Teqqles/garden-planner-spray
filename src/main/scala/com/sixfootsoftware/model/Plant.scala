package com.sixfootsoftware.model

import spray.httpx.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

object MonthOfYear {

  sealed case class Month(name: String, daysInMonth: Int) {
    override def toString: String = {
      name
    }
  }

  object Month extends DefaultJsonProtocol with SprayJsonSupport {
    implicit val monthValue: RootJsonFormat[Month] = jsonFormat2(Month.apply)
  }

  object Jan extends Month("January", 31)

  object Feb extends Month("February", 28)

  object Mar extends Month("March", 31)

  object Apr extends Month("April", 30)

  object May extends Month("May", 31)

  object Jun extends Month("June", 30)

  object Jul extends Month("July", 31)

  object Aug extends Month("August", 30)

  object Sep extends Month("September", 31)

  object Oct extends Month("October", 31)

  object Nov extends Month("November", 30)

  object Dec extends Month("December", 31)

}


object SoilType {

  sealed case class Soil(ph: (Float, Float))

  object Soil extends DefaultJsonProtocol with SprayJsonSupport {
    implicit val soilJson: RootJsonFormat[Soil] = jsonFormat1(Soil.apply)
  }

  object Neutral extends Soil(6.6f -> 7.3f)

  object Acidic extends Soil(0f -> 6.5f)

  object Alkaline extends Soil(7.4f -> 14f)

}

/**
  * Created by david on 27/06/2017.
  */
trait Plant {
  val id: Option[Int]
  val name: String
  val latinName: String
  val sowing: List[MonthOfYear.Month]
  val soilPreference: List[SoilType.Soil]
}