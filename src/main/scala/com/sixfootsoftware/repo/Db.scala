package com.sixfootsoftware.repo

import slick.backend.DatabaseConfig
import slick.driver.JdbcProfile

/**
  * Created by david on 24/06/2017.
  */
trait Db {
  val config: DatabaseConfig[JdbcProfile]
  val db: JdbcProfile#Backend#Database = config.db
}