package dao

import javax.inject.Inject

import com.google.inject.Singleton
import model.Airport
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.Future

@Singleton
class AirportDAOImpl @Inject()
(dbConfigProvider: DatabaseConfigProvider) extends AirportDAO {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  class AirportTable(tag:Tag)
    extends Table[Airport](tag, "airports") {
    def id = column[Long]("id", O.PrimaryKey,O.AutoInc)
    def faa = column[String]("faa")
    def name = column[String]("name")
    def lat = column[Double]("lat")
    def lon = column[Double]("lon")
    def alt = column[Double]("alt")
    def tz = column[Long]("tz")
    def dst = column[String]("dst")

    override def * =
      (id, faa,name,lat,lon,alt,tz,dst) <> (Airport.tupled, Airport.unapply)
  }
  implicit val airports = TableQuery[AirportTable]

  override def listAll: Future[Seq[Airport]] = {
    db.run(airports.result)
  }
}
