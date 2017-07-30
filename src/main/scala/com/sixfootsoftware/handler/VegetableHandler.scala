package com.sixfootsoftware.handler

import com.sixfootsoftware._
import com.sixfootsoftware.config.DbConfiguration
import com.sixfootsoftware.repo.Tables
import slick.driver.JdbcProfile

import scala.collection.mutable.ListBuffer
import scala.concurrent.Await
import scala.concurrent.duration.Duration

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by david on 28/06/2017.
  */
class VegetableHandler(override val profile:JdbcProfile) extends Tables {
  this: DbConfiguration =>

  import profile.api._

  def post(veg: model.Vegetable): Boolean = {
    false
  }

  def list: List[model.Vegetable] = {
    val query = for (
      vegetable <- this.Vegetable;
      harvest <- this.VegetableHarvestMonth if vegetable.vegetableId === harvest.vegetableId
    ) yield {
      (vegetable.vegetableId, vegetable.name, harvest.monthId)
    }
    val buffer: ListBuffer[model.Vegetable] = new ListBuffer[model.Vegetable]()
    Await.result(
      config.db.run(query.result).map { res =>
        buffer.appendAll(res.toList.map(veg => model.Vegetable(Some(veg._1), veg._2.getOrElse(""), "", List(), List(), List())))
      },
      Duration.Inf
    )
    buffer.toList
    //(res => model.Vegetable(None, "", "", List(), List(), List()))
  }

}
