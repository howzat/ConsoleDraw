package com.howzat.model

import com.howzat.draw.model.Position

object Rectangle {

  def renderPositions(from:Position, to:Position) : List[Element] = {
    Nil
  }
}

case class Rectangle(topLeft: Position, bottomRight: Position) extends Element