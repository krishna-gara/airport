package dao

import javax.inject.Inject

import com.google.inject.Singleton
import model.Flight
import org.joda.time.DateTime
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.Future

@Singleton
class FlightDAOImpl @Inject()
(dbConfigProvider: DatabaseConfigProvider) extends FlightDAO {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  class FlightTable(tag:Tag)
    extends Table[Flight](tag, "flights") {
    def id = column[Long]("id", O.PrimaryKey,O.AutoInc)
    def year = column[Int]("year")
    def month = column[Int]("month")
    def day = column[Int]("day")
    def depTime = column[String]("dep_time")
    def depDelay = column[String]("dep_delay")
    def arrTime = column[String]("arr_time")
    def arrDelay = column[String]("arr_delay")
    def carrier = column[String]("carrier")
    def tailnum = column[String]("tailnum")
    def flight = column[Long]("flight")
    def origin = column[String]("origin")
    def dest = column[String]("dest")
    def airTime=column[String]("air_time")
    def distance = column[Long]("distance")
    def hour = column[String]("hour")
    def minute = column[String]("minute")
    override def * =
      (id, year,month,day,depTime,depDelay,arrTime,arrDelay,carrier,tailnum,flight,origin,dest,airTime,distance,hour,minute) <> (Flight.tupled, Flight.unapply)
  }
  implicit val flights = TableQuery[FlightTable]

  override def getInBoundFlight(id:String,startDate:Long,endDate:Long): Future[Seq[Flight]] = {
    var sd = new DateTime(startDate)
    var ed = new DateTime(endDate)
    val query = flights.filter(f=>f.dest === id && f.year >= sd.getYear && f.year <=ed.getYear && f.month >= sd.getMonthOfYear && f.month <=ed.getMonthOfYear && f.day >= sd.getDayOfMonth && f.day <=ed.getDayOfMonth);
    db.run(query.result)
  }
  override def getOutboundFlight(id:String,startDate:Long,endDate:Long): Future[Seq[Flight]] = {
    var sd = new DateTime(startDate)
    var ed = new DateTime(endDate)
    val query = flights.filter(f=>f.origin === id && f.year >= sd.getYear && f.year <=ed.getYear && f.month >= sd.getMonthOfYear && f.month <=ed.getMonthOfYear && f.day >= sd.getDayOfMonth && f.day <=ed.getDayOfMonth);
    db.run(query.result)
  }
}
