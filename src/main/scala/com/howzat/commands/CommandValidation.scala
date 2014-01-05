package com.howzat.draw.commands



import com.howzat.io.InputValidation
import com.howzat._
import com.howzat.draw.commands.ApplyFill
import com.howzat.draw.commands.NewCanvas
import com.howzat.draw.commands.Quit
import com.howzat.draw.commands.DrawLine
import com.howzat.draw.commands.DrawRectangle

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
    
    def isNotNegative(rectangle: DrawRectangle) = {
      import rectangle._
      validateParameters(
        greaterThanZero(topLeft.x, "top left x"),
        greaterThanZero(topLeft.y, "top left y"),
        greaterThanZero(bottomRight.x, "bottom right x"),
        greaterThanZero(bottomRight.y, "bottom right y")) {
        params => rectangle
      }
    }

    def topLeftLowerThanBottomRight(rectangle: DrawRectangle) : CommandEither = {
      if(rectangle.topLeft < rectangle.bottomRight) Right(rectangle)
      else Left("Rectangles top left position was below that of the bottom right. Use R tl.x tl.y br.x br.y")
    }
    
    ensure (
       isNotNegative(rectangle),
       topLeftLowerThanBottomRight(rectangle)
     )
  }
}
