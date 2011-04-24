package code
package model

import net.liftweb.mapper._
import net.liftweb.util._
import net.liftweb.common._

/**
 * A Recipe is simply an online link to a given recipe with a name, rating, and description
 */
class Recipe extends LongKeyedMapper[Recipe] with IdPK {
  def getSingleton = Recipe
  
  object name extends MappedString(this, 200)
  object url extends MappedString(this, 3000)
  object rating extends MappedInt(this)
 
  object desc extends MappedTextarea(this, 2048) {
    override def textareaRows  = 10
    override def textareaCols = 50
    override def displayName = "Personal Essay"
  }
}

object Recipe extends Recipe with LongKeyedMetaMapper[Recipe]  {
  override def fieldOrder = List(name, url, rating)
}
