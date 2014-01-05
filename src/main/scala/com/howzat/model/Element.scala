package com.howzat.model

import com.howzat.draw.model.Position

trait Element {
  def topLeft: Position
  def bottomRight: Position
  def samePosition(e:Element) = {
    e.topLeft == this.topLeft && e.bottomRight == this.bottomRight
  }
}

abstract class SinglePointElement(position: Position) extends Element {
  override def topLeft = position
  override def bottomRight = position
}

case class Empty(p: Position) extends SinglePointElement(p)
case class HBorder(p: Position) extends SinglePointElement(p)
case class VBorder(p: Position) extends SinglePointElement(p)




