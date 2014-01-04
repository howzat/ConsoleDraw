package com.howzat.model

import com.howzat.draw.model.Position._
import scala.collection.immutable.IndexedSeq
import scala.annotation.tailrec
import com.howzat.CanvasEither
import com.howzat.draw.model.Position
import scala.collection.parallel.immutable.ParVector

object Canvas {

  val CanvasTopLeft    = (1, 1)
  val BorderValue: Int = 2

  def apply(height: Int, width: Int) = {
    new Canvas(height, width, emptyGrid(height, width))
  }

  def emptyGrid(height: Int, width: Int): List[List[Element]] = {
    val boundry = Borders(height, width)
    (ParVector.tabulate(width + 2) {
      y => ParVector.tabulate(height + 2) {
        x =>
          val p = Position(x, y)
          if(boundry.isVerticalBorder(p)) VBorder(p)
          else if(boundry.isHorizontalBorder(p)) HBorder(p)
          else Empty(p)
      }
    } toList) map(_.toList)
  }
}

case class Canvas(width: Int, height: Int, grid: List[List[Element]]) {

  def update(ps: List[Element]): Canvas = {
    this
  }

  def topLeft = Canvas.CanvasTopLeft
  def bottomRight = Position(width - 1, height - 1)
}