package com.howzat.model

import com.howzat.draw.model.Position

object Line {

  def isVertical(topLeft: Position, bottomRight: Position): Boolean = {
    bottomRight.y > topLeft.y && bottomRight.x == topLeft.x
  }
  def isHorizontal(topLeft: Position, bottomRight: Position): Boolean = {
    bottomRight.x > topLeft.x && bottomRight.y == topLeft.y
  }

  def renderPositions(from: Position, to: Position): List[Element] = {
    if(isVertical(from, to)) from.y to to.y map {
      y => Line(Position(to.x, y), Position(to.x, y))
    }
    else from.x to to.x map {
      x => Line(Position(x, from.y), Position(x, from.y))
    }
  } toList
}

case class Line(from: Position, to: Position) extends Element {
  def topLeft = from
  def bottomRight = to
}