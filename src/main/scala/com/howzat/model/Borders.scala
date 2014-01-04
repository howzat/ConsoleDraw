package com.howzat.model

import com.howzat.draw.model.Position
import com.howzat._

object Borders {

  def positionWithinCanvas(p: Position, c: Canvas): Boolean = {
    (p.x >= 1 && p.x <= c.width) && (p.y <= c.height && p.y >= 1)
  }

  def elementWithinCanvas(e: Element, c: Canvas): Boolean = {
    positionWithinCanvas(e.topLeft, c) && positionWithinCanvas(e.bottomRight, c)
  }

  def withinCanvas(es: List[Element], c: Canvas): CanvasEither = {
    es find (e => !elementWithinCanvas(e, c)) map {
      outOfBounds => Left(s"element $outOfBounds is positioned outside of the canvas")
    } getOrElse Right(c)
  }

  def apply(c:Canvas) : Borders = {
    Borders(c.width, c.height)
  }
}

case class Borders(width: Int, height: Int) {

  val borderedWidth  = width + Canvas.BorderValue
  val borderedHeight = height + Canvas.BorderValue

  def isLeftBorder(pos: Position) = (!isVerticalBorder(pos) && pos.x == 0)
  def isRightBorder(pos: Position) = (!isVerticalBorder(pos) && pos.x == borderedWidth - 1)
  def isTopBorder(pos: Position) = (pos.y == 0 && pos.x >= 0 && pos.x <= borderedWidth)
  def isBottomBorder(pos: Position) = (pos.y == borderedHeight - 1 && pos.x >= 0 && pos.x <= borderedWidth)
  def isVerticalBorder(pos: Position) = (isTopBorder(pos) || isBottomBorder(pos))
  def isHorizontalBorder(pos: Position) = (isLeftBorder(pos) || isRightBorder(pos))
}
