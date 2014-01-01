package com.howzat.draw.commands



import scala.util.{Failure, Success, Try}
import com.howzat.io.InputValidation

class CommandValidation(inputValidation: InputValidation) {

  import inputValidation._

  def ensure[T](results: Either[String, T]*) : Either[String, T] = {
    if(results exists (_.isLeft)) Left(collectErrors(results))
    else Right(results.head.right.get)
  }

  def isValid(command:Command) : Either[String, Command] = {
    command match {
      case cmd@Quit() => Right(cmd)
      case cmd@NewCanvas(_,_) => validateNewCanvas(cmd)
      case cmd@DrawLine(_,_)  => validateDrawLine(cmd)
      case cmd@ApplyFill(_,_)  => validateApplyFill(cmd)
      case cmd@DrawRectangle(_,_)  => validateRectangle(cmd)
      case _ => Left(s"unrecognised command $command")
    }
  }
  
  private def validateNewCanvas(canvas: NewCanvas): Either[String, Command] = {
    validateParameters (
      greaterThanZero(canvas height, "height"),
      greaterThanZero(canvas width, "width")) {
      _ => canvas
    }
  }


  private def validateDrawLine(line: DrawLine): Either[String, Command] = {
    import line._
    def isHorizontal(line: DrawLine) = topLeft.x == line.bottomRight.x
    def isVertical(line: DrawLine) = topLeft.y == bottomRight.y
    def hasLength(line: DrawLine) = if(topLeft != bottomRight) Right(line) else Left("line length cannot be zero")
    def validDirection(line: DrawLine) = if(isHorizontal(line) || isVertical(line)) Right(line) else Left("lines must be drawn either vertically or horizontally")
    def isNotNegative(line: DrawLine) = {
      validateParameters(
        greaterThanZero(topLeft.x, "top left x"),
        greaterThanZero(topLeft.y, "top left y"),
        greaterThanZero(bottomRight.x, "bottom right x"),
        greaterThanZero(bottomRight.y, "bottom right y")) {
        params => line
      }
    }

    ensure(
      validDirection(line),
      hasLength(line),
      isNotNegative(line)
    )
  }

  private def validateApplyFill(fill: ApplyFill) : Either[String, Command] = {
    Right(fill)
  }

  private def validateRectangle(rectangle: DrawRectangle) : Either[String, Command] = {
    Right(rectangle)
  }
}
