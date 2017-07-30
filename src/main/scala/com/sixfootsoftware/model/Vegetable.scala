package com.sixfootsoftware.model

import com.sixfootsoftware.model.MonthOfYear.Month
import spray.httpx.SprayJsonSupport
import spray.json._

/**
  * Created by david on 23/06/2017.
  */

case class Vegetable(override val id: Option[Int],
                     override val name: String,
                     override val latinName: String,
                     override val sowing: List[MonthOfYear.Month],
                     harvest: List[MonthOfYear.Month],
                     override val soilPreference: List[SoilType.Soil])
  extends Plant

object ImplicitVegetableJson extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val vegJson: RootJsonFormat[Vegetable] = jsonFormat6(Vegetable.apply)
}
