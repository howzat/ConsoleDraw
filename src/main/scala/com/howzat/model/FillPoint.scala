package com.howzat.model

import com.howzat.draw.model.Position

object FillPoint {
  def renderPositions(clickPoint:Position, colour:String, canvas:Canvas) : List[Element] = {
    Nil
  }
}

case class FillPoint(p: Position, colour: String) extends SinglePointElement(p)