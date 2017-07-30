package com.sixfootsoftware.repo
// AUTO-GENERATED Slick data model
/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema = Array(PlantMonth.schema, SoilType.schema, Vegetable.schema, VegetableHarvestMonth.schema, VegetablePruningMonth.schema, VegetableSowingMonth.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table PlantMonth
   *  @param monthId Database column month_id SqlType(INT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(100,true), Default(None)
   *  @param daysInMonth Database column days_in_month SqlType(INT), Default(Some(31)) */
  case class PlantMonthRow(monthId: Int, name: Option[String] = None, daysInMonth: Option[Int] = Some(31))
  /** GetResult implicit for fetching PlantMonthRow objects using plain SQL queries */
  implicit def GetResultPlantMonthRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[Int]]): GR[PlantMonthRow] = GR{
    prs => import prs._
    PlantMonthRow.tupled((<<[Int], <<?[String], <<?[Int]))
  }
  /** Table description of table plant_month. Objects of this class serve as prototypes for rows in queries. */
  class PlantMonth(_tableTag: Tag) extends Table[PlantMonthRow](_tableTag, "plant_month") {
    def * = (monthId, name, daysInMonth) <> (PlantMonthRow.tupled, PlantMonthRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(monthId), name, daysInMonth).shaped.<>({r=>import r._; _1.map(_=> PlantMonthRow.tupled((_1.get, _2, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column month_id SqlType(INT), AutoInc, PrimaryKey */
    val monthId: Rep[Int] = column[Int]("month_id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(100,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(100,varying=true), O.Default(None))
    /** Database column days_in_month SqlType(INT), Default(Some(31)) */
    val daysInMonth: Rep[Option[Int]] = column[Option[Int]]("days_in_month", O.Default(Some(31)))
  }
  /** Collection-like TableQuery object for table PlantMonth */
  lazy val PlantMonth = new TableQuery(tag => new PlantMonth(tag))

  /** Entity class storing rows of table SoilType
   *  @param soilId Database column soil_id SqlType(INT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(60,true), Default(None) */
  case class SoilTypeRow(soilId: Int, name: Option[String] = None)
  /** GetResult implicit for fetching SoilTypeRow objects using plain SQL queries */
  implicit def GetResultSoilTypeRow(implicit e0: GR[Int], e1: GR[Option[String]]): GR[SoilTypeRow] = GR{
    prs => import prs._
    SoilTypeRow.tupled((<<[Int], <<?[String]))
  }
  /** Table description of table soil_type. Objects of this class serve as prototypes for rows in queries. */
  class SoilType(_tableTag: Tag) extends Table[SoilTypeRow](_tableTag, "soil_type") {
    def * = (soilId, name) <> (SoilTypeRow.tupled, SoilTypeRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(soilId), name).shaped.<>({r=>import r._; _1.map(_=> SoilTypeRow.tupled((_1.get, _2)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column soil_id SqlType(INT), AutoInc, PrimaryKey */
    val soilId: Rep[Int] = column[Int]("soil_id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(60,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(60,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table SoilType */
  lazy val SoilType = new TableQuery(tag => new SoilType(tag))

  /** Entity class storing rows of table Vegetable
   *  @param vegetableId Database column vegetable_id SqlType(INT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(300,true), Default(None)
   *  @param soilId Database column soil_id SqlType(INT), Default(None) */
  case class VegetableRow(vegetableId: Int, name: Option[String] = None, soilId: Option[Int] = None)
  /** GetResult implicit for fetching VegetableRow objects using plain SQL queries */
  implicit def GetResultVegetableRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[Int]]): GR[VegetableRow] = GR{
    prs => import prs._
    VegetableRow.tupled((<<[Int], <<?[String], <<?[Int]))
  }
  /** Table description of table vegetable. Objects of this class serve as prototypes for rows in queries. */
  class Vegetable(_tableTag: Tag) extends Table[VegetableRow](_tableTag, "vegetable") {
    def * = (vegetableId, name, soilId) <> (VegetableRow.tupled, VegetableRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(vegetableId), name, soilId).shaped.<>({r=>import r._; _1.map(_=> VegetableRow.tupled((_1.get, _2, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column vegetable_id SqlType(INT), AutoInc, PrimaryKey */
    val vegetableId: Rep[Int] = column[Int]("vegetable_id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(300,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Length(300,varying=true), O.Default(None))
    /** Database column soil_id SqlType(INT), Default(None) */
    val soilId: Rep[Option[Int]] = column[Option[Int]]("soil_id", O.Default(None))

    /** Foreign key referencing SoilType (database name vegetable_ibfk_1) */
    lazy val soilTypeFk = foreignKey("vegetable_ibfk_1", soilId, SoilType)(r => Rep.Some(r.soilId), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Vegetable */
  lazy val Vegetable = new TableQuery(tag => new Vegetable(tag))

  /** Entity class storing rows of table VegetableHarvestMonth
   *  @param vegetableId Database column vegetable_id SqlType(INT)
   *  @param monthId Database column month_id SqlType(INT) */
  case class VegetableHarvestMonthRow(vegetableId: Int, monthId: Int)
  /** GetResult implicit for fetching VegetableHarvestMonthRow objects using plain SQL queries */
  implicit def GetResultVegetableHarvestMonthRow(implicit e0: GR[Int]): GR[VegetableHarvestMonthRow] = GR{
    prs => import prs._
    VegetableHarvestMonthRow.tupled((<<[Int], <<[Int]))
  }
  /** Table description of table vegetable_harvest_month. Objects of this class serve as prototypes for rows in queries. */
  class VegetableHarvestMonth(_tableTag: Tag) extends Table[VegetableHarvestMonthRow](_tableTag, "vegetable_harvest_month") {
    def * = (vegetableId, monthId) <> (VegetableHarvestMonthRow.tupled, VegetableHarvestMonthRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(vegetableId), Rep.Some(monthId)).shaped.<>({r=>import r._; _1.map(_=> VegetableHarvestMonthRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column vegetable_id SqlType(INT) */
    val vegetableId: Rep[Int] = column[Int]("vegetable_id")
    /** Database column month_id SqlType(INT) */
    val monthId: Rep[Int] = column[Int]("month_id")

    /** Primary key of VegetableHarvestMonth (database name vegetable_harvest_month_PK) */
    val pk = primaryKey("vegetable_harvest_month_PK", (vegetableId, monthId))

    /** Foreign key referencing PlantMonth (database name vegetable_harvest_month_ibfk_2) */
    lazy val plantMonthFk = foreignKey("vegetable_harvest_month_ibfk_2", vegetableId, PlantMonth)(r => r.monthId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Vegetable (database name vegetable_harvest_month_ibfk_1) */
    lazy val vegetableFk = foreignKey("vegetable_harvest_month_ibfk_1", vegetableId, Vegetable)(r => r.vegetableId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table VegetableHarvestMonth */
  lazy val VegetableHarvestMonth = new TableQuery(tag => new VegetableHarvestMonth(tag))

  /** Entity class storing rows of table VegetablePruningMonth
   *  @param vegetableId Database column vegetable_id SqlType(INT)
   *  @param monthId Database column month_id SqlType(INT) */
  case class VegetablePruningMonthRow(vegetableId: Int, monthId: Int)
  /** GetResult implicit for fetching VegetablePruningMonthRow objects using plain SQL queries */
  implicit def GetResultVegetablePruningMonthRow(implicit e0: GR[Int]): GR[VegetablePruningMonthRow] = GR{
    prs => import prs._
    VegetablePruningMonthRow.tupled((<<[Int], <<[Int]))
  }
  /** Table description of table vegetable_pruning_month. Objects of this class serve as prototypes for rows in queries. */
  class VegetablePruningMonth(_tableTag: Tag) extends Table[VegetablePruningMonthRow](_tableTag, "vegetable_pruning_month") {
    def * = (vegetableId, monthId) <> (VegetablePruningMonthRow.tupled, VegetablePruningMonthRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(vegetableId), Rep.Some(monthId)).shaped.<>({r=>import r._; _1.map(_=> VegetablePruningMonthRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column vegetable_id SqlType(INT) */
    val vegetableId: Rep[Int] = column[Int]("vegetable_id")
    /** Database column month_id SqlType(INT) */
    val monthId: Rep[Int] = column[Int]("month_id")

    /** Primary key of VegetablePruningMonth (database name vegetable_pruning_month_PK) */
    val pk = primaryKey("vegetable_pruning_month_PK", (vegetableId, monthId))

    /** Foreign key referencing PlantMonth (database name vegetable_pruning_month_ibfk_2) */
    lazy val plantMonthFk = foreignKey("vegetable_pruning_month_ibfk_2", vegetableId, PlantMonth)(r => r.monthId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Vegetable (database name vegetable_pruning_month_ibfk_1) */
    lazy val vegetableFk = foreignKey("vegetable_pruning_month_ibfk_1", vegetableId, Vegetable)(r => r.vegetableId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table VegetablePruningMonth */
  lazy val VegetablePruningMonth = new TableQuery(tag => new VegetablePruningMonth(tag))

  /** Entity class storing rows of table VegetableSowingMonth
   *  @param vegetableId Database column vegetable_id SqlType(INT)
   *  @param monthId Database column month_id SqlType(INT) */
  case class VegetableSowingMonthRow(vegetableId: Int, monthId: Int)
  /** GetResult implicit for fetching VegetableSowingMonthRow objects using plain SQL queries */
  implicit def GetResultVegetableSowingMonthRow(implicit e0: GR[Int]): GR[VegetableSowingMonthRow] = GR{
    prs => import prs._
    VegetableSowingMonthRow.tupled((<<[Int], <<[Int]))
  }
  /** Table description of table vegetable_sowing_month. Objects of this class serve as prototypes for rows in queries. */
  class VegetableSowingMonth(_tableTag: Tag) extends Table[VegetableSowingMonthRow](_tableTag, "vegetable_sowing_month") {
    def * = (vegetableId, monthId) <> (VegetableSowingMonthRow.tupled, VegetableSowingMonthRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(vegetableId), Rep.Some(monthId)).shaped.<>({r=>import r._; _1.map(_=> VegetableSowingMonthRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column vegetable_id SqlType(INT) */
    val vegetableId: Rep[Int] = column[Int]("vegetable_id")
    /** Database column month_id SqlType(INT) */
    val monthId: Rep[Int] = column[Int]("month_id")

    /** Primary key of VegetableSowingMonth (database name vegetable_sowing_month_PK) */
    val pk = primaryKey("vegetable_sowing_month_PK", (vegetableId, monthId))

    /** Foreign key referencing PlantMonth (database name vegetable_sowing_month_ibfk_2) */
    lazy val plantMonthFk = foreignKey("vegetable_sowing_month_ibfk_2", vegetableId, PlantMonth)(r => r.monthId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Vegetable (database name vegetable_sowing_month_ibfk_1) */
    lazy val vegetableFk = foreignKey("vegetable_sowing_month_ibfk_1", vegetableId, Vegetable)(r => r.vegetableId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table VegetableSowingMonth */
  lazy val VegetableSowingMonth = new TableQuery(tag => new VegetableSowingMonth(tag))
}
