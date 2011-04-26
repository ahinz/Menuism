package code
package model

import net.liftweb.mapper._
import net.liftweb.util._
import net.liftweb.common._

class Meal extends LongKeyedMapper[Meal] with IdPK {
  def getSingleton = Meal
  
  object dateOf extends MappedDateTime(this)
  object ate extends MappedBoolean(this)

  object recipe extends MappedLongForeignKey(this,Recipe)
}

object Meal extends Meal with LongKeyedMetaMapper[Meal] 
