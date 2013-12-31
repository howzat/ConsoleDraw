package com.howzat.draw.commands



import scala.util.{Failure, Success, Try}
import com.howzat.io.InputValidation

class Validation(inputValidation: InputValidation) {

  import inputValidation._

  def ensure[T](results: Either[String, T]*) : Either[String, T] = {
    if(results exists (_.isLeft)) Left(collectErrors(results))
    else Right(results.head.right.get)
  }


  def isValid(canvas: NewCanvas): Either[String, Command] = {
    validateParameters (
      notZero(canvas height, "height"),
      notZero(canvas width, "width")) {
      _ => canvas
    }
  }

  def isValid(line: DrawLine): Either[String, Command] = {

    def isHorizontal(line: DrawLine) = line.topLeft.x == line.bottomRight.x
    def isVertical(line: DrawLine) = line.topLeft.y == line.bottomRight.y

    def hasLength(line: DrawLine) = if(line.topLeft != line.bottomRight) Right(line) else {
      Left("line lenght cannot be zero")
    }

    def validDirection(line: DrawLine) = if(isHorizontal(line) || isVertical(line)) Right(line) else {
      Left("lines must be drawn either vertically or horizontally")
    }

    ensure(validDirection(line), hasLength(line))
  }
}
