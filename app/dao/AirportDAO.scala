package dao

import com.google.inject.ImplementedBy
import model.Airport

import scala.concurrent.Future

@ImplementedBy(classOf[AirportDAOImpl])
trait AirportDAO {
  def listAll : Future[Seq[Airport]]

}
