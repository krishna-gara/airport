package service

import com.google.inject.ImplementedBy
import model.{Airport, Flight}

import scala.concurrent.Future

@ImplementedBy(classOf[AirportImpl])
trait AirportService {

  def getAirports() :Future[Seq[Airport]]
  def getInBoundFlight(id:String,startDate:Long,endDate:Long) : Future[Seq[Flight]]
  def getOutboundFlight(id:String,startDate:Long,endDate:Long) : Future[Seq[Flight]]
}
