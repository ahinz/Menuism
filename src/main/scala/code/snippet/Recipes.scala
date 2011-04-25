package code 
package snippet 

import net.liftweb.http._
import net.liftweb.http.js.JsCmds._
import scala.xml.{NodeSeq, Text}
import net.liftweb.util._
import net.liftweb.common._
import java.util.Date
import code.lib._
import Helpers._

import code.model._

class Recipes {

  def list = ".liftrow" #> Recipe.findAll.map(r =>
    ".liftrow [id]" #> r.rowid() & 
    "#name *" #> r.name &
    "#url *" #> <a href={r.url}>{r.url}</a> &
    "#rating *" #> r.rating &
    "#delete *" #> SHtml.ajaxButton( "Delete",  () => { r.delete_! ; SetHtml(r.rowid(), Nil)}))
                       
  var name = ""
  var url = ""
  var rating = 0
 
  def t = 1

  def processEntryAdd() = {
    val rcp = Recipe.create.name(name).url(url).rating(rating)

    rcp.validate match {
      case Nil => rcp.save
      case errors => S.error(errors)
    }
  }
    
  def add = "#name *" #> SHtml.text(name, name = _, "size" -> "35") &
            "#url *" #> SHtml.text(url, url = _, "size" -> "70") &
            "#rating *" #> SHtml.text(rating.toString, a => rating = a.toInt, "size" -> "4") &
            "#submit" #> SHtml.submit("Add", processEntryAdd)
}
