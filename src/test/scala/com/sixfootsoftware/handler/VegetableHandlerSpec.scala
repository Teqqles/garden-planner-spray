package com.sixfootsoftware.handler

import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, Matchers, WordSpec}
import com.sixfootsoftware._
import com.sixfootsoftware.config.{DbConfiguration, TestDbConfiguration}
import com.sixfootsoftware.model.MonthOfYear._
import com.sixfootsoftware.model.SoilType.{Acidic, Neutral}
import slick.driver.{H2Driver, MySQLDriver}
import slick.driver.H2Driver.api._

/**
  * Created by david on 27/06/2017.
  */
object VegetableTestAssets {
  val carrot =
    model.Vegetable(
      None,
      name = "Carrot",
      latinName = "Carrotus",
      sowing = List(Jan, Feb),
      harvest = List(Jun, Jul),
      soilPreference = List(Neutral)
    )

  val existingCabbage =
    model.Vegetable(
      Some(1),
      name = "Cabbage",
      latinName = "Cabbagie",
      sowing = List(Feb),
      harvest = List(May),
      soilPreference = List(Acidic)
    )
}

class VegetableHandlerSpec extends WordSpec with Matchers with BeforeAndAfter with BeforeAndAfterAll {

  val vegHandler = new VegetableHandler(MySQLDriver) with DbConfiguration

  override def beforeAll() {
    import vegHandler.profile.api._
    vegHandler.config.db.run(vegHandler.schema.drop)
    vegHandler.config.db.run(vegHandler.schema.create)
  }

  override def afterAll(): Unit = {
    vegHandler.config.db.close()
  }

  before {
    //truncate
  }

  "Vegetable Handler" should {
    "save new varieties" in {
      vegHandler.post(VegetableTestAssets.carrot) should be(true)
    }
    "ignore existing vegetables" in {
      vegHandler.post(VegetableTestAssets.existingCabbage) should be(false)
    }
  }
}
