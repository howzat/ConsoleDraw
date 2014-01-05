package com.howzat.model

import com.howzat.draw.model.Position._
import scala.collection.immutable.IndexedSeq
import scala.annotation.tailrec
import com.howzat.CanvasEither
import com.howzat.draw.model.Position
import scala.collection.parallel.immutable.ParVector



case class Canvas(width: Int, height: Int, val state:List[Element]=Nil) {

  val CanvasTopLeft = (1, 1)
  val BorderValue   = 2

  def emptyGrid(): List[Element] = {
    (ParVector.tabulate(height + 2) {
      y => ParVector.tabulate(width + 2) {
        x =>
          val p = Position(x, y)
          if(isVerticalBorder(p)) VBorder(p)
          else if(isHorizontalBorder(p)) HBorder(p)
          else Empty(p)
      }
    } flatten) toList
  }

  lazy val elementMap:Map[Position, Element] = {
    getElements map ( e => (e.topLeft -> e) ) toMap
  }

  def getElements:List[Element] = if(state.isEmpty) emptyGrid() else state

  def update(ne: List[Element]): Canvas = {
    val newState = this.getElements map {
      element => ne find (_.samePosition(element)) getOrElse (element)
    }

    Canvas(width, height, state = newState)
  }

  def withinCanvas(p: Position): Boolean = {
    (p.x >= 1 && p.x <= width) && (p.y <= height && p.y >= 1)
  }

  def withinCanvas(e: Element): Boolean = {
    withinCanvas(e.topLeft) && withinCanvas(e.bottomRight)
  }

  def withinCanvas(es: List[Element]): CanvasEither = {
    es find (e => !withinCanvas(e)) map {
      outOfBounds => Left(s"element $outOfBounds is positioned outside of the canvas")
    } getOrElse Right(this)
  }

  def isRightEdge(pos: Position) = (pos.x == borderedWidth - 1)
  def isRightEdge(element: Element) = (element.topLeft.x == borderedWidth - 1) || (element.bottomRight.x == borderedWidth - 1)

  def topLeft = CanvasTopLeft
  def bottomRight = Position(width - 1, height - 1)
  val borderedWidth = width + BorderValue
  val borderedHeight = height + BorderValue
  def isLeftBorder(pos: Position) = (!isVerticalBorder(pos) && pos.x == 0)
  def isRightBorder(pos: Position) = (!isVerticalBorder(pos) && pos.x == borderedWidth - 1)
  def isTopBorder(pos: Position) = (pos.y == 0 && pos.x >= 0 && pos.x <= borderedWidth)
  def isBottomBorder(pos: Position) = (pos.y == borderedHeight - 1 && pos.x >= 0 && pos.x <= borderedWidth)
  def isVerticalBorder(pos: Position) = (isTopBorder(pos) || isBottomBorder(pos))
  def isHorizontalBorder(pos: Position) = (isLeftBorder(pos) || isRightBorder(pos))
}