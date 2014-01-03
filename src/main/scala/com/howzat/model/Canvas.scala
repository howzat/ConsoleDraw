package com.howzat.model

import com.howzat.draw.model.Position
import scala.collection.immutable.IndexedSeq
import scala.annotation.tailrec
import com.howzat.CanvasEither

case class Canvas(width: Int, height: Int) {

  val CanvasTopLeft  = Position(0, 0)
  val BorderValue: Int = 2
  val borderedWidth  = width + BorderValue
  val borderedHeight = height + BorderValue
  
  lazy val initialState = emptyGrid

  def place(element: Element) : CanvasEither = {
    element match {
      case e@Rectangle(_, _) => addRectangle(e)
      case e@Line(_, _) => addLine(e)
      case e@FillPoint(_, _) => addFill(e)
    }
  }

  
  def addRectangle(e:Rectangle) : CanvasEither = {
    Left("")
  }
  
  def addLine(e:Line) : CanvasEither = {
    Left("")
  }
  
  def addFill(e:FillPoint) : CanvasEither = {
    Left("")  
  }


  def renderedPosition(line:Line) : Option[List[Element]] = {
    None
  }

  private def emptyGrid: List[List[Element]] = {
    List.tabulate(height+2) {
      y => List.tabulate(width+2) {
        x =>
          val p = Position(x, y)
          if(isVerticalBorder(p)) VBorder(p)
          else if (isHorizontalBorder(p)) HBorder(p)
          else Empty(p)
      }
    }
  }

  def isLeftBorder(pos:Position) = (!isVerticalBorder(pos) && pos.x == 0)
  def isRightBorder(pos:Position) = (!isVerticalBorder(pos) && pos.x == borderedWidth-1)
  def isTopBorder(pos:Position) = (pos.y == 0 && pos.x >=0 && pos.x <= borderedWidth)
  def isBottomBorder(pos:Position)  = (pos.y == borderedHeight-1 && pos.x >=0 && pos.x <= borderedWidth)
  def isVerticalBorder(pos:Position) = (isTopBorder(pos) || isBottomBorder(pos))
  def isHorizontalBorder(pos:Position) = (isLeftBorder(pos) || isRightBorder(pos))
  def toElementGrid: List[List[Element]] = emptyGrid
  def topLeft = CanvasTopLeft
  def bottomRight = Position(width-1, height-1)
}