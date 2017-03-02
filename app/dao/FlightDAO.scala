package dao

import com.google.inject.ImplementedBy
import model.Flight

import scala.concurrent.Future

@ImplementedBy(classOf[FlightDAOImpl])
trait FlightDAO {
  def getInBoundFlight(id:String,startDate:Long,endDate:Long) : Future[Seq[Flight]]
  def getOutboundFlight(id:String,startDate:Long,endDate:Long) : Future[Seq[Flight]]

}
