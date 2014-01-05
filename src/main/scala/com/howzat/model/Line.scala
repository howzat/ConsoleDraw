package com.howzat.model


object Line {

  def isVertical(topLeft: Position, bottomRight: Position): Boolean = {
    bottomRight.y > topLeft.y && bottomRight.x == topLeft.x
  }
  def isHorizontal(topLeft: Position, bottomRight: Position): Boolean = {
    bottomRight.x > topLeft.x && bottomRight.y == topLeft.y
  }

  def renderLines(from: Position, to: Position): List[Element] = {
    renderPositions(from, to) map {
      pos => Line(pos, pos)
    }
  }

  def renderPositions(from: Position, to: Position): List[Position] = {
    if(isVertical(from, to)) from.y to to.y map (y => Position(to.x, y))
    else from.x to to.x map (x => Position(x, from.y))
  } toList
}

case class Line(from: Position, to: Position) extends Element {
  def topLeft = from
  def bottomRight = to
}