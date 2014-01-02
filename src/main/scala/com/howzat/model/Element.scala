package com.howzat.model

import com.howzat.draw.model.Position

trait Element {
  def height:Int
  def width:Int
  def topLeft:Position
  def bottomRight:Position
}

abstract class SinglePointElement(val position:Position) extends Element {
  def height = 1
  def width = 1
  def topLeft = position
  def bottomRight = position
}

abstract class Border(p:Position) extends SinglePointElement(p)

case class Empty(p:Position) extends SinglePointElement(p)
case class HBorder(p:Position) extends Border(p)
case class VBorder(p:Position) extends Border(p)
case class FillPoint(p:Position, colour:String) extends SinglePointElement(p)

case class Line(topLeft:Position, bottomRight:Position) extends Element {
  def height = 1
  def width = 1
}

case class Rectangle(topLeft:Position, bottomRight:Position) extends Element{
  def height = bottomRight.y - topLeft.y
  def width = bottomRight.x - topLeft.x
}


