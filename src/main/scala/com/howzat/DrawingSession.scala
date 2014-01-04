package com.howzat

import com.howzat.model._
import java.util.concurrent.atomic.{AtomicReference, AtomicBoolean}
import scala._
import com.howzat.draw.model.Position
import com.howzat.model.Rectangle
import com.howzat.model.FillPoint
import scala.Some

class DrawingSession {

  import Borders._

  var canvasState: Option[Canvas] = None

  private[this] def withCanvas(draw: (Canvas) => CanvasEither): CanvasEither = {
    canvasState map ( draw(_) ) getOrElse {
      Left("you must create a canvas before using draw commands e.g. 'C 10 10'")
    }
  }

  private def draw(elements: List[Element], canvas:Canvas): CanvasEither = {
    for {
      _ <- Borders withinCanvas(elements, canvas) right
    } yield canvas update (elements)
  }

  def newCanvas(width: Int, height: Int) = {
    canvasState = Some(Canvas(width, height))
    Right(canvasState get)
  }

  def drawLine(from: Position, to: Position) = withCanvas { 
    canvas =>
      val lines = Line renderPositions(from, to)
      draw(lines, canvas)
  }

  def fill(clickPoint: Position, colour: String) = withCanvas {
    canvas => 
      val fillArea = FillPoint renderPositions(clickPoint, colour, canvas)
      draw(fillArea, canvas)
  }

  def drawRectangle(topLeft: Position, bottomRight: Position) = withCanvas {
    canvas =>
      val rectangles = Rectangle renderPositions(topLeft, bottomRight)
      draw(rectangles, canvas)
  }
}
