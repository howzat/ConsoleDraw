package com.howzat.model

import com.howzat.draw.model.Position

trait Element {
  def topLeft:Position
  def bottomRight:Position
  private def setValue(v:Int) = if(v == 0 ) 1 else v
  lazy val height = setValue(bottomRight.y - topLeft.y)
  lazy val width = setValue(bottomRight.x - topLeft.x)
}

abstract class SinglePointElement(position:Position) extends Element {
  override def topLeft = position
  override def bottomRight = position
}

case class Line(topLeft:Position, bottomRight:Position) extends Element
case class Rectangle(topLeft:Position, bottomRight:Position) extends Element
case class Empty(p:Position) extends SinglePointElement(p)
case class HBorder(p:Position) extends SinglePointElement(p)
case class VBorder(p:Position) extends SinglePointElement(p)
case class FillPoint(p:Position, colour: String) extends SinglePointElement(p)



