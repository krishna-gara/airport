package model

import play.api.data.Form
import play.api.data.Forms._
import utils.DoubleFormat._
case class Airport(id : Long,faa : String, name : String , lat : Double,lon: Double, alt : Double, tz :Long, dst:String )

case class AirportFormData(id : Long,faa : String, name : String , lat : Double,lon: Double, alt : Double, tz :Long, dst:String )

object AirportForm {

  val form = Form(
    mapping(
      "id" -> longNumber,
      "faa" -> nonEmptyText,
      "name"->nonEmptyText,
      "lat"-> double,
      "lon"-> double,
      "alt"-> double,
      "tz"-> longNumber,
      "dst"->nonEmptyText
    )(AirportFormData.apply)(AirportFormData.unapply)
  )
}
