package code
package model

import net.liftweb.mapper._
import net.liftweb.util._
import net.liftweb.common._

/**
 * A Recipe is simply an online link to a given recipe with a name, rating, and description
 */
class Recipe extends LongKeyedMapper[Recipe] with IdPK with Validators {
  def getSingleton = Recipe    

  def rowid(module:String = "table"):String =
    module + "#recipe#" + id

  object name extends MappedString(this, 200) {
    override def validations = valMinLen(1,"Name cannot be empty") _ :: Nil
  }
           
  object url extends MappedString(this, 3000) {
    override def validations = valMinLen(1,"URL cannot be empty") _ :: Nil
  }

  object rating extends MappedInt(this) {
    override def validations = rangeValidator(0,100, "Rating", this) :: Nil
  }
 
  object desc extends MappedTextarea(this, 2048) {
    override def textareaRows  = 10
    override def textareaCols = 50
    override def displayName = "Personal Essay"
  }
}

object Recipe extends Recipe with LongKeyedMetaMapper[Recipe]  {
  override def fieldOrder = List(name, url, rating)
}
