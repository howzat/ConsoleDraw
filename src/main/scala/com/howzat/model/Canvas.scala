package com.howzat.model

import com.howzat.draw.model.Position
import scala.collection.immutable.IndexedSeq

case class Canvas(width:Int, height:Int, elements:Vector[Element]=Vector.empty) {

  val CanvasTopLeft: Position = Position(0, 0)


  def +(e:Element) = {
    copy(elements = e +: elements)
  }

  private def emptyGrid(): List[List[Option[Element]]] = {

    val pixelGrid =  List.fill(width, height) (None)

    for {
      x <- 0 to width by 1
      y <- 0 to height by 1
      position = Position(x,y)
    } yield {
      if(position == CanvasTopLeft) Some(VBorder(position))
      else if (position.x == 0 || position.x == bottomRight.x ) Some(VBorder(position))
      else if (position.y == 0 || position.y == bottomRight.y ) Some(HBorder(position))
      else None
    }

    pixelGrid
  }

  def toElementGrid : List[List[Option[Element]]] = {
    emptyGrid()
  }

  def topLeft = CanvasTopLeft
  def bottomRight = Position(width-1, height-1)
}
