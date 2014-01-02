package com.howzat.draw.commands



import scala.util.{Failure, Success, Try}
import com.howzat.io.InputValidation
import com.howzat.CommandEither

class CommandValidation(inputValidation: InputValidation) {

  import inputValidation._

  def ensure(results: CommandEither*) : CommandEither = {
    if(results exists (_.isLeft)) Left(collectErrors(results))
    else Right(results.head.right.get)
  }

  def isValid(command:Command) : CommandEither = {
    command match {
      case cmd@Quit() => Right(cmd)
      case cmd@NewCanvas(_,_) => validateNewCanvas(cmd)
      case cmd@DrawLine(_,_)  => validateDrawLine(cmd)
      case cmd@ApplyFill(_,_)  => validateApplyFill(cmd)
      case cmd@DrawRectangle(_,_)  => validateRectangle(cmd)
      case _ => Left(s"unrecognised command $command")
    }
  }
  
  private def validateNewCanvas(canvas: NewCanvas): CommandEither = {
    validateParameters (
      greaterThanZero(canvas height, "height"),
      greaterThanZero(canvas width, "width")) {
      _ => canvas
    }
  }


  private def validateDrawLine(line: DrawLine): CommandEither = {
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

  private def validateApplyFill(fill: ApplyFill) : CommandEither = {
    Right(fill)
  }

  private def validateRectangle(rectangle: DrawRectangle) : CommandEither = {
    Right(rectangle)
  }
}
