package controllers

import com.google.inject.Inject
import play.api.mvc.{Action, Controller}
import service.{AirportService}
import model.{Airport,Flight}
import play.api.mvc._
import play.api.i18n.{I18nSupport, Messages, MessagesApi}
import play.api.libs.concurrent.Execution.Implicits._

/**
  * The Airport controller.
  *
  * @param messagesApi The Play messages API.
  * @param airportService The user service implementation.
  */
class AirportController @Inject()
(val airportService: AirportService,
 val messagesApi: MessagesApi) extends Controller with I18nSupport {

  /**
    * Home Page.
    *
    * @return The result to display.
    */
  def home = Action {
    Ok(views.html.airport("Airport"))
  }

  def airports = Action.async(request => {
    import play.api.libs.json.Json
    implicit val airportFormat = Json.format[Airport]

    val results =  airportService.getAirports()
    results.map { airport =>
      Ok(Json.toJson(airport))
    }
  })

  def inBoundFlight(id:String,startDate:Long,endDate:Long) =  Action.async(request => {
    import play.api.libs.json.Json
    implicit val flightFormat = Json.format[Flight]
    val results = airportService.getInBoundFlight(id,startDate,endDate);
    results.map { airport =>
      Ok(Json.toJson(airport))
    }
  })

  def outBoundFlight(id:String,startDate:Long,endDate:Long) = Action.async(request => {
    import play.api.libs.json.Json
    implicit val flightFormat = Json.format[Flight]
    airportService.getOutboundFlight(id,startDate,endDate).map { results =>
      Ok(Json.toJson(results))
    }
  })
}
