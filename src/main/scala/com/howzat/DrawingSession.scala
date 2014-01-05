package com.howzat

import com.howzat.model._
import scala._
import com.howzat.model.Position
import com.howzat.model.Rectangle
import com.howzat.model.FillPoint
import scala.Some

class DrawingSession {

  var canvasState: Option[Canvas] = None

  private[this] def withCanvas(draw: (Canvas) => CanvasEither): CanvasEither = {
    canvasState map ( draw(_) ) getOrElse {
      Left("you must create a canvas before using draw commands e.g. 'C 10 10'")
    }
  }

  private def draw(elements: List[Element], canvas:Canvas): CanvasEither = {
    canvas withinCanvas elements match {
      case er@Left(error) => er
      case Right(_) => {
        val newState = canvas update elements
        canvasState = Some(newState)
        Right(newState)
      }
    }
  }

  def newCanvas(width: Int, height: Int) = {
    canvasState = Some(Canvas(width, height, Nil))
    Right(canvasState get)
  }

  def drawLine(from: Position, to: Position) = withCanvas { 
    canvas =>
      val lines = Line renderLines(from, to)
      draw(lines, canvas)
  }

  def fill(clickPoint: Position, colour: String) = withCanvas {
    canvas => 
      val fillArea = FillPoint renderFillArea(clickPoint, colour, canvas)
      draw(fillArea, canvas)
  }

  def drawRectangle(topLeft: Position, bottomRight: Position) = withCanvas {
    canvas =>
      val rectangles = Rectangle renderRectangles (topLeft, bottomRight)
      draw(rectangles, canvas)
  }
}
