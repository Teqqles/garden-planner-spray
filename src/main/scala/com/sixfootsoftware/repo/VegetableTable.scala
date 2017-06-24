package com.sixfootsoftware.repo

import com.sixfootsoftware.model.MonthOfYear.Month
import com.sixfootsoftware.model.SoilType.Soil
import com.sixfootsoftware.model.Vegetable

/**
  * Created by david on 24/06/2017.
  */
trait VegetableTable {
  this: Db =>

  import config.driver.api._

  private class Vegetables(tag: Tag) extends Table[Vegetable](tag, "VEGETABLE") {

    implicit def monthEmbedding = MappedColumnType.base[List[Month], String](
      list => list.map(x => x.name + ';' + x.daysInMonth) mkString """,""",
      str => str.split(",").map(x => {
        val month = x split ";"
        Month(month(0), month(1).toInt)
      }).toList
    )

    implicit def soilEmbedding = MappedColumnType.base[List[Soil], String](
      list => list.map(x => x.ph._1 + ';' + x.ph._2) mkString """,""",
      str => str.split(",").map(x => {
        val soil = x split ";"
        Soil(soil(0).toFloat -> soil(1).toFloat)
      }).toList
    )

    // Columns
    def id = column[Int]("VEGETABLE_ID", O.PrimaryKey, O.AutoInc)

    def name = column[String]("NAME", O.Length(512))

    def sowing = column[List[Month]]("SOWING", O.Length(4000))

    def harvest = column[List[Month]]("HARVEST", O.Length(4000))

    def soil = column[List[Soil]]("SOIL", O.Length(4000))

    // Select
    def * = (id.?, name, sowing, harvest, soil) <> (Vegetable.tupled, Vegetable.unapply)
  }

}
