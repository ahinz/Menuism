package code 
package snippet 

import net.liftweb.mapper._
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

  val recipeBox:Box[Recipe] = S.param("id").flatMap(id => Recipe.find(By(Recipe.id, id.toLong)))

  def recipe:Recipe = recipeBox match {
    case Full(r) => r
    case Empty => S.redirectTo("FAIL")
  }
  

  def recipe_main = "#recipe_name *" #> recipe.name
  def upcoming = ".liftrow *" #> <td>No Upcoming Meals</td>










/*
////////////////////////////////////////////////////////////////////////
*/

  def list = ".liftrow" #> Recipe.findAll.map(r =>
    ".liftrow [id]" #> r.rowid() & 
    "#name *" #> r.name &
    "#url *" #> <a href={r.url}>{r.url}</a> &
    "#rating *" #> r.rating &
    "#delete *" #> SHtml.ajaxButton( "Delete",  () => { r.delete_! ; SetHtml(r.rowid(), Nil)}))
                       
  var name = ""
  var url = ""
  var rating = 0
 
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
