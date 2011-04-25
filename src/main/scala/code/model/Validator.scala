package code
package model

import net.liftweb.mapper._
import net.liftweb.util._
import net.liftweb.common._

object Validators extends Validators

trait Validators {

  def notEmptyValidator(field: String, m: FieldIdentifier):(String => List[FieldError]) =
    v => 
      if (v.length == 0)
        List(FieldError(m, field + " must not be empty"))
      else
        List()

  def rangeValidator(min: Int,  max: Int, name: String, m: FieldIdentifier) =
    (v:Int) =>
      if (v < min)
        List(FieldError(m, name + " must be greater than " + min))
      else if (v > max)
        List(FieldError(m, name + " must be less than " + max))
      else
        List()

}
