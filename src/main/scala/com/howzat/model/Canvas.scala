package com.howzat.model

import com.howzat.draw.model.Position

case class Canvas(width:Int, height:Int, elements:Vector[Element]=Vector.empty) {

  val CanvasTopLeft: Position = Position(0, 0)


  def +(e:Element) = {
    copy(elements = e +: elements)
  }

  def topLeft = CanvasTopLeft
  def bottomRight = Position(width-1, height-1)
}
