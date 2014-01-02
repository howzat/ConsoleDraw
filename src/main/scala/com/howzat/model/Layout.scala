package com.howzat.model

import com.howzat.CanvasResult
import com.howzat.draw.model.Position


object Layout {
  def default = new BasicLayout()
}

trait Layout {

  def placeElement(e:Element, canvas:Canvas) : CanvasResult

  def positionedWithinCanvas(e:Element, c:Canvas) = {
    if(e.topLeft > c.topLeft && e.bottomRight < c.bottomRight ) Right((e, c))
    else Left("element is too big, or positioned outside of the canvas")
  }

  def fitsHorizontally(e:Element, c:Canvas) = {
    if(e.bottomRight.x < c.bottomRight.x) Right((e,c))
    else  Left("element is too wide, or positined too far to the right to fit on canvas")
  }

  def fitsVertically(e:Element, c:Canvas) = {
    if(e.bottomRight.y < c.bottomRight.y) Right((e,c))
    else Left("element is too tall, or positined too far down to fit on canvas")
  }

  def fitsWithinCanvas(e:Element, c:Canvas): Either[String, Unit] = {
    for {
      _ <- positionedWithinCanvas(e,c).right
      _ <- fitsHorizontally(e,c).right
      _ <- fitsVertically(e,c).right
    } yield ()
  }

  def fits(e:Element, c:Canvas) = {


  }
}

class BasicLayout() extends Layout {

  override def placeElement(e: Element, canvas: Canvas): CanvasResult = {
    fitsWithinCanvas(e, canvas) match {
      case Right(_) => Right(canvas + e)
      case Left(errors) => Left(errors)
    }
  }
}