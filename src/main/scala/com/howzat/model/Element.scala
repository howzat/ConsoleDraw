package com.howzat.model

import com.howzat.draw.model.Position

trait Element {
  def height:Int
  def width:Int
  def topLeft:Position
  def bottomRight:Position
}

case class Line(topLeft:Position, bottomRight:Position) extends Element {
  def height = 1
  def width = 1
}

case class FillPoint(position:Position, colour:String) extends Element {
  def height = 1
  def width = 1
  def topLeft = position
  def bottomRight = position
}

case class Rectangle(topLeft:Position, bottomRight:Position) extends Element{
  def height = bottomRight.y - topLeft.y
  def width = bottomRight.x - topLeft.x
}

