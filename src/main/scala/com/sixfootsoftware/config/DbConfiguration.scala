package com.sixfootsoftware.config

import slick.backend.DatabaseConfig
import slick.driver.JdbcProfile

/**
  * Created by david on 24/06/2017.
  */
trait DbConfiguration {
  lazy val config = DatabaseConfig.forConfig[JdbcProfile]("db")
}