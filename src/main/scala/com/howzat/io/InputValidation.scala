package com.howzat.io

import com.howzat.draw.commands.Command
import scala.util.{Failure, Success, Try}

class InputValidation {

  def validateParameters[T, P](results: Either[String, T]*)(f: Seq[T] => P) = {
    if(results exists (_.isLeft)) Left(collectErrors(results))
    else Right(f(results map (_.right.get)))
  }

  def collectErrors[T](results: Seq[Either[String, T]]): String = {
    results filter (_.isLeft) map (_.left.get) mkString (", ")
  }

  def greaterThanZero(value:Int, param:String) : Either[String, Int] = {
    if(value >0) Right(value) else Left(s"param '$param' must be greater than zero")
  }

  def notNegative(value:Int, param:String) : Either[String, Int] = {
      if(value >=0) Right(value) else Left(s"param '$param' cannot be a negative number")
    }

  def nonEmptyString(value: String, param: String): Either[String, String] = {
    if(!value.trim.isEmpty) Right(value) else Left(s"the value for '$param' [$value] must not be an empty string")
  }

  def safelyToInt(value: String, param: String): Either[String, Int] = {
    Try(value.toInt) match {
      case Success(v) => Right(v)
      case Failure(e) => Left(s"the value for '$param' [$value] couldn't be parsed to an Int [${e.getMessage}]")
    }
  }
}
