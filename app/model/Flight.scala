package model

import play.api.data.Form
import play.api.data.Forms._

case class Flight(id : Long,year : Int, month: Int , day : Int, depTime: String, depDelay : String, arrTime :String, arrDelay:String,
                  carrier:String,tailnum:String, flight:Long, origin:String, dest:String, airTime:String,distance:Long,hour:String,minute:String )

case class FlightFormData(id : Long,year : Int, month: Int , day : Int,depTime: String, depDelay : String, arrTime :String, arrDelay:String, carrier:String,tailnum:String, flight:Long, origin:String, dest:String, airTime:String,distance:Long,hour:String,minute:String)

object FlightForm {

  val form = Form(
    mapping(
      "id" -> longNumber,
      "year" -> number,
      "month"->number,
      "day"->number,
      "depTime"->nonEmptyText,
      "depDelay"->nonEmptyText,
      "arrTime"->nonEmptyText,
      "arrDelay"->nonEmptyText,
      "carrier"->nonEmptyText,
      "tailnum"->nonEmptyText,
      "flight"->longNumber,
      "origin"->nonEmptyText,
      "dest"->nonEmptyText,
      "airTime"->nonEmptyText,
      "distance"->longNumber,
      "hour"->nonEmptyText,
      "minute"->nonEmptyText
    )(FlightFormData.apply)(FlightFormData.unapply)
  )
}
