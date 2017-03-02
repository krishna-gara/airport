package service

import javax.inject.Inject

import com.google.inject.Singleton
import dao.{AirportDAO, FlightDAO}
import model.{Airport, Flight}

import scala.concurrent.Future

@Singleton
class AirportImpl @Inject()(airportDAO: AirportDAO, flightDAO: FlightDAO) extends AirportService {
  override def getAirports(): Future[Seq[Airport]] = {
    airportDAO.listAll
  }

  override def getInBoundFlight(id: String,startDate:Long,endDate:Long): Future[Seq[Flight]] = {
    flightDAO.getInBoundFlight(id,startDate,endDate)
  }

  override def getOutboundFlight(id: String,startDate:Long,endDate:Long): Future[Seq[Flight]] = {

    flightDAO.getOutboundFlight(id,startDate,endDate)
  }
}
